package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.management.InvalidAttributeValueException;

public class PlataformaStreaming {
    private static final String arqFilmes = "codigo/POO_Filmes.csv";
    private static final String arqSeries = "codigo/POO_Series.csv";
    private static final String arqClientes = "codigo/POO_Espectadores.csv";
    private static final String arqAudiencia = "codigo/POO_Audiencia.csv";
    private String nome;
    private HashMap<Integer, Midia> midias = new HashMap<>();
    private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
    private Cliente clienteAtual;

    /**
     * Constutor da plataforma de Streamming
     * 
     * @param nome esse é o nome da plataforma de Streamming
     * @throws InvalidAtributeValue propaga esssa excecao
     */
    public PlataformaStreaming(String nome) throws InvalidAttributeValueException {

        setNome(nome);
        carregarMidia(arqSeries);
        carregarMidia(arqFilmes);
        carregarClientes();
        carregarAudiencia();
    }

    /**
     * Método para setar o nome da plataforma
     * 
     * @param nome atributo nome
     * @throws InvalidAttributeValueException propaga excecao de atributo inválido
     */
    public void setNome(String nome) throws InvalidAttributeValueException {
        if (nome.length() > 0) {
            this.nome = nome;
        } else {
            throw new InvalidAttributeValueException("O nome não pode ser vazio!");
        }
    }

    /**
     * Método para pegar os clientes cadastrados
     * 
     * @return retorna os clientes cadastrados na plataforma
     */
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    /**
     * Método para pegar o cliente logado atualmente
     * 
     * @return retorna o cliente logado atualmente
     */
    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    /**
     * Método para adicionar uma midia na lista de midia
     * 
     * @param midia Esse é a midia que será recebida
     * @throws MidiaInvalidaException caso midia ja existe
     */
    public void adicionarMidia(Midia midia) throws MidiaInvalidaException {
        if (!midias.containsKey(midia.getId())) {
            midias.put(midia.getId(), midia);
            escreveArqMidia(midia);
        } else {
            throw new MidiaInvalidaException("Essa midia já existe!");
        }
    }

    /**
     * Esse metodo recebe parametros do cliente, cria uma serie e envia para um metodo polimorfico
     * que adiciona na lista de midias
     *
     * @param nome nome da midia
     * @param idioma idioma da midia
     * @param genero genero da midia
     * @param qtdEpisodios quantidade de episiodios da serie
     * @throws MidiaInvalidaException caso atributos invalidos
     */
    public void adicionarSerie(String nome, String idioma, String genero, int qtdEpisodios) throws MidiaInvalidaException {

        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        adicionarMidia(serieCad);
    }

    /**
     * Esse metodo recebe parametros do cliente, cria um filme e envia para um metodo polimorfico
     * que adiciona na lista de midias
     *
     * @param nome nome da midia
     * @param idioma idioma da midia
     * @param genero genero da midia
     * @param duracao duracao do filme
     * @throws MidiaInvalidaException caso atributos invalidos
     */
    public void adicionarFilme(String nome, String idioma, String genero, int duracao) throws MidiaInvalidaException{
        Filme filmeCad = new Filme(genero, nome, idioma, duracao);
        adicionarMidia(filmeCad);
    }

    /**
     * Esse metodo recebe o nome da midia, filtra e adiciona na lista para ver do cliente.
     * 
     * @param nomeMidia nome da midia
     * @throws MidiaInvalidaException caso nenhuma midia seja encontrada com o nome recebido
     */
    public void adicionarMidiaParaAssistir(String nomeMidia) throws MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        if (midia != null) {
            this.clienteAtual.adicionarListaParaVer(midia);
            escreveArqAudiencia("F", midia, -1);
        } else {
            throw new MidiaInvalidaException("Nenhuma midia encontrada com esse nome!");
        }
    }

    /**
     * Adiciona a midia como vista ao receber como parametro o nome da midia que quer assistir.
     * 
     * @param nomeMidia nome da midia
     * @throws MidiaInvalidaException caso ja tenha assitido essa midia
     */
    public void adicionarMidiaVista(String nomeMidia) throws MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        if(clienteAtual.querVer(midia)){
            clienteAtual.retirarDaLista(midia);
        }

        if(clienteAtual.adicionarMidiaVista(midia)){
            escreveArqAudiencia("A", midia, -1F);
        } else {
            throw new MidiaInvalidaException("Voce já assistiu essa midia");
        }
    }
    
    /**
     * Adiciona avaliacao do cliente recebendo como parametro o nome da midia que quer avaliar e a nota
     * 
     * @param nomeMidia nome da midia
     * @param nota nota da midia
     * @throws MidiaInvalidaException caso midia seja invalida
     * @throws AvaliacaoInvalidaException caso avaliacao seja invalida
     * @throws ClienteInvalidoException caso cliente seja invalido
     */
    public void adicionarAvaliacao(String nomeMidia, float nota)
            throws MidiaInvalidaException, AvaliacaoInvalidaException, ClienteInvalidoException {

        Midia midia = filtrarMidiaPorNome(nomeMidia);
        setClienteEspecialista();

        if(!midia.getAvaliacoes().contains(clienteAtual.getNomeDeUsuario())){
            if (this.clienteAtual.criarAvaliacao(nota, midia)) {
                escreveArqAudiencia("A", midia, nota);
            }
        } else {
            throw new AvaliacaoInvalidaException("Você já avaliou essa midia!");
        } 
    }


    /**
     * Retorna a lista ja vista do cliente logado
     * @return lista ja vista do cliente logado
     */
    public String getListaJaVista() {
        return this.clienteAtual.getListaJaVista();
    }

    /**
     * Retorna a lista para assistir do cliente atual em formato de string
     * @return
     */
    public String getListaParaAssistir() {
        return this.clienteAtual.getListaParaVer().toString();
    }

    /**
     * Cria o cliente, adiciona ele no hash map de clientes e escreve no arquivo
     * 
     * @param nomeCompleto nome completo do usuario
     * @param nomeDeUsuario nome do usuario
     * @param senha senha do usuario
     * @throws ClienteInvalidoException caso atributos do cliente seja invalido
     * @throws MidiaInvalidaException caso midia seja invalida
     */
    public void adicionarCliente(String nomeCompleto, String nomeDeUsuario, String senha)
            throws ClienteInvalidoException, MidiaInvalidaException {
        Cliente c = new Cliente(nomeCompleto, nomeDeUsuario, senha);

        if (!clientes.containsKey(c.getNomeDeUsuario())) {
            escreveArqCliente(c);
            this.clientes.put(c.getNomeDeUsuario(), c);
        } else {
            throw new ClienteInvalidoException("Esse cliente já existe!");
        }
    }

    /**
     * Metodo para filtrar uma midia pelo seu genero
     * 
     * @param genero Esse é o genero que foi escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorGenero(String genero) {
        return clienteAtual.filtrarMidiaPorGenero(genero).toString();
    }

    /**
     * Metodo para filtrar uma midia pela quantidade de episodios
     * 
     * @param quantEpisodios Essa é a quantidade de episodioes escolhida para ser
     *                       filtrada
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios).toString();
    }

    /**
     * Metodo para filtrar uma midia pelo idioma
     * 
     * @param idioma Esse é o idioma escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarMidiaPorIdioma(idioma).toString();
    }

    /**
     * Esse método registra o cliente atual como audiencia da midia especificada
     * 
     * @param midia Essa é a midia especificada
     */
    public void registrarAudiencia(Midia midia) {
        clienteAtual.registrarAudiencia(midia);
    }

    /**
     * Esse método faz o cliente logar se a senha e o nome de usuario ja estiverem
     * cadastrados
     * 
     * @param nomeUsuario esse é o nome de login do usuario
     * @param senha       essa é a senha do usuario
     * @return retorna o cliente logado como cliente atual
     * @throws ClienteInvalidoException
     */
    public boolean login(String nomeUsuario, String senha) throws ClienteInvalidoException {

        boolean logado = true;

        clienteAtual = clientes.get(nomeUsuario);
        if (!senha.equals(clienteAtual.getSenha())) {
            logado = false;
            this.clienteAtual = null;
            throw new ClienteInvalidoException("Senha incorreta!");
        }

        return logado;
    }

    /**
     * Esse método carrega os clientes do arquivo de clientes
     * @throws ClienteInvalidoException caso cliente invalido
     */
    private void carregarClientes() throws ClienteInvalidoException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arqClientes));
            String linha;
            reader.readLine();
    
            while ((linha = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(linha, ";");
                Cliente cliente = new Cliente(str.nextToken(), str.nextToken(), str.nextToken());
                clientes.put(cliente.getNomeDeUsuario(), cliente);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * carrega arquivo de midias do sistema
     * @param tipoArquivo tipo do arquivo
     * @throws MidiaInvalidaException caso midia invalida
     */
    private void carregarMidia(String tipoArquivo) throws MidiaInvalidaException {

        try {
            Midia midia;

            BufferedReader reader = new BufferedReader(new FileReader(tipoArquivo));
            String linha;
    
            reader.readLine();
    
            while ((linha = reader.readLine()) != null) {
    
                StringTokenizer str = new StringTokenizer(linha, ";");
                int id = Integer.parseInt(str.nextToken());
                String nome = str.nextToken();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataLancamento = LocalDate.parse(str.nextToken(), formatter);
    
                if (tipoArquivo.equals(arqFilmes)) {
                    int n = Integer.parseInt(str.nextToken());
                    midia = new Filme(id, nome, dataLancamento, n);
                } else {
                    midia = new Serie(id, nome, dataLancamento);
                }
    
                midias.put(midia.getId(), midia);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * carrega o arquivo que possui todas as audiencias dos clientes do sistema
     * @throws AvaliacaoInvalidaException caso avaliacao invalida
     * @throws MidiaInvalidaException caso midia invalida
     */
    private void carregarAudiencia() throws AvaliacaoInvalidaException, MidiaInvalidaException {

        try {
            float nota;
            BufferedReader reader = new BufferedReader(new FileReader(arqAudiencia));
            String linha;
            reader.readLine();
    
            while ((linha = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(linha, ";");
                String login = str.nextToken();
                String tipo = str.nextToken();
                int idMidia = Integer.parseInt(str.nextToken());         
                Midia midia = midias.get(idMidia);
                this.clienteAtual = clientes.get(login);

                if (tipo.equals("F")) {
                    clienteAtual.adicionarListaParaVer(midia);
                } else {
                    if (str.hasMoreTokens()) {
                        String valor = str.nextToken();
                        if (valor.contains("-")) {
                            clienteAtual.adicionarDataAssistida(valor);
                        } else {
                            nota = Float.parseFloat(valor);
                            clienteAtual.criarAvaliacao(nota, midia);
                        }
                    }
                    clienteAtual.adicionarMidiaVista(midia);
                    if(clienteAtual.querVer(midia)){
                        clienteAtual.retirarDaLista(midia);
                    }
                }
            }
    
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
        
    }

    /**
     * esse metodo escreve o objeto no arquivo escolhido
     * 
     * @param objeto      Esse é o objeto a ser escrito
     * @param nomeArquivo Esse é o arquivo em que sera escrito o objeto
     */
    private void escreveArquivo(ISalvavel objeto, String nomeArquivo) {

        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, true);
            arquivo.write(objeto.getDados());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * esse metodo recebe uma midia que implementa isalvavel e chama um metodo de escrever no arquivo
     * @param midia
     */
    private void escreveArqMidia(ISalvavel midia) {
        if (midia instanceof Filme) {
            escreveArquivo(midia, arqFilmes);
        } else if (midia instanceof Serie) {
            escreveArquivo(midia, arqSeries);
        }
    }

    /**
     * Esse metodo escreve um cliente no arquivo de cliente, se o nome de usuario
     * dele ja nao estiver cadastrado
     * 
     * @param clienteCad Esse é o cliente a ser posto
     * @throws MidiaInvalidaException excecao a ser lancada caso o nome de usuario esteja
     *                   indisponivel ou haja algum erro
     */
    private void escreveArqCliente(Cliente clienteCad) throws MidiaInvalidaException {
        escreveArquivo(clienteCad, arqClientes);
    }

    /**
     * Esse metodo escreve a audiencia no arquivo de audiencia
     * 
     * @param tipo     Isso indica se o cliente ja viu a serie ou futuramente
     *                 assistida
     * @param midiaCad Essa é a midiaque tera a audiencia
     */
    private void escreveArqAudiencia(String tipo, Midia midiaCad, float nota) {

        try {
            FileWriter arquivo = new FileWriter(arqAudiencia, true);
            int id = midiaCad.getId();
            String login = clienteAtual.getNomeDeUsuario();

            LocalDate data = LocalDate.now();

            if (nota != -1) {
                arquivo.write("\n" + login + ";" + tipo + ";" + id + ";" + nota + ";" + data);
            } else {
                arquivo.write("\n" + login + ";" + tipo + ";" + id + ";" + data);
            }

            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo!");
            e.printStackTrace();
        }
    }

    /**
     * Esse metodo acha a midia com o nome enviado
     * 
     * @param nome Esse é o nome enviado
     * @return Retorna a midia com o mesmo nome que o enviado
     */
    public Midia filtrarMidiaPorNome(String nome) throws MidiaInvalidaException {
        return this.midias.values().stream()
                .filter(s -> s.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new MidiaInvalidaException("Nenhuma midia encontrada com esse nome!"));
    }

    /**
     * testa se o cliente é especialista
     * @return true caso ele seja especialista
     */
    public boolean eEspecialista() {
        return (clienteAtual.getMeuTipo() != null);
    }

    /**
     * Metodo que permite o cliente comentar caso ele seja especialista
     * 
     * @param comentario comentario do cliente
     * @param nomeMidia midia a receber o comentario
     * @throws ClienteInvalidoException caso cliente nao seja especialista
     * @throws AvaliacaoInvalidaException caso avaliacao invalida
     * @throws MidiaInvalidaException caso midia invalida
     */
    public void comentar(String comentario, String nomeMidia) throws ClienteInvalidoException, AvaliacaoInvalidaException, MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        clienteAtual.fazerComentario(comentario, midia);
    }

    /**
     * Seta o cliente como especialista caso ele tenha assistido 5 midias no ultimo mes.
     * 
     * @throws ClienteInvalidoException caso cliente invalido
     */
    public void setClienteEspecialista() throws ClienteInvalidoException {

        if (this.clienteAtual.getTamanhoListaJaVista() >= 5) {
            List<String> datasAssistidas = clienteAtual.getListaDataAssistida();
            LocalDate mesPassado = LocalDate.now().minusMonths(1);
            long contador = datasAssistidas.stream()
                    .map(LocalDate::parse)
                    .filter(dataAssistida -> (dataAssistida.getMonthValue() == mesPassado.getMonthValue() &&
                            dataAssistida.getYear() == mesPassado.getYear()) ||
                            (dataAssistida.getMonthValue() == LocalDate.now().getMonthValue() &&
                                    dataAssistida.getYear() == LocalDate.now().getYear()))
                    .count();
            if (contador >= 5) {
                clienteAtual.setMeuTipo(new ClienteEspecialista(clienteAtual.getNomeCompleto(),
                        clienteAtual.getNomeDeUsuario(), clienteAtual.getSenha()));
            }
        }
        System.out.println(this.clienteAtual.getListaDataAssistida());
    }


    /**
     * Retorna a nota media de uma midia especificada
     * 
     * @param nomeMidia midia que quer calcular a nota media
     * @return a nota media da midia
     * @throws MidiaInvalidaException caso midia invalida
     */
    public double getNotaMedia(String nomeMidia) throws MidiaInvalidaException{
        Midia midia = filtrarMidiaPorNome(nomeMidia);

        return midia.calcularNotaMedia();
    }

}
