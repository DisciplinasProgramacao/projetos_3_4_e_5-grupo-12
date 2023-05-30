package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlataformaStreaming {
    private static final String arqFilmes = "POO_Filmes.csv";
    private static final String arqSeries = "POO_Series.csv";
    private static final String arqClientes = "POO_Espectadores.csv";
    private static final String arqAudiencia = "POO_Audiencia.csv";
    private String nome;
    private HashMap<Integer, Midia> midias = new HashMap<>();
    private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
    private Cliente clienteAtual;

    /**
     * Constutor da plataforma de Streamming
     * 
     * @param nome esse é o nome da plataforma de Streamming
     * @throws Exception
     */
    public PlataformaStreaming(String nome) throws Exception {

        setNome(nome);
        carregarMidia(arqSeries);
        carregarMidia(arqFilmes);
        carregarClientes();
        carregarAudiencia();
    }

    public void setNome(String nome) throws Exception {
        if (nome.length() >= 0) {
            this.nome = nome;
        } else {
            throw new Exception("O nome não pode ser vazio!");
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
     * Método para adicionar uma serie na lista de series
     * 
     * @param serie Esse é a serie que será recebido
     * @throws Exception
     */

    public void adicionarMidia(Midia midia) throws MidiaInvalidaException {
        if (!midias.containsKey(midia.getId())) {
            midias.put(midia.getId(), midia);
            escreveArqMidia(midia);
        } else {
            throw new MidiaInvalidaException("Essa midia já existe!");
        }
    }

    public void adicionarSerie(String nome, String idioma, String genero, int qtdEpisodios) throws MidiaInvalidaException {

        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        adicionarMidia(serieCad);
    }

    public void adicionarFilme(String nome, String idioma, String genero, int duracao) throws MidiaInvalidaException{
        Filme filmeCad = new Filme(genero, nome, idioma, duracao);
        adicionarMidia(filmeCad);
    }

    public void adicionarMidiaParaAssistir(String nomeMidia) throws MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        if (midia != null) {
            this.clienteAtual.adicionarListaParaVer(midia);
            escreveArqAudiencia("F", midia, -1);
        } else {
            throw new MidiaInvalidaException("Nenhuma midia encontrada com esse nome!");
        }
    }

    public void adicionarMidiaVista(String nomeMidia) throws MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        if(clienteAtual.querVer(midia)){
            clienteAtual.retirarDaLista(midia);
        }
        if(clienteAtual.adicionarMidiaVista(midia)){
            escreveArqAudiencia("A", midia, -1F);
        }  
    }
    

    public void adicionarAvaliacao(String nomeMidia, float nota) throws MidiaInvalidaException, AvaliacaoInvalidaException, ClienteInvalidoException {

        Midia midia = filtrarMidiaPorNome(nomeMidia);
        setClienteEspecialista();

        if(!this.clienteAtual.jaViu(midia)) {
            if(this.clienteAtual.criarAvaliacao(nota, midia ) == true) {        
                escreveArqAudiencia("A", midia, nota);
            } else {
                throw new AvaliacaoInvalidaException("Você já avaliou essa midia!");
            }
        } else {
            throw new MidiaInvalidaException("Você ainda não assistiu a essa midia!");
        }
   
    }

     public String getListaJaVista() {
         return this.clienteAtual.getListaJaVista();
     }

    public String getListaParaAssistir() {
        return this.clienteAtual.getListaParaVer().toString();
    }

    /**
     * Método para adicionar um cliente na lista de clientes
     * 
     * @param c Esse é o cliente que será recebido
     * @throws MidiaInvalidaException
     */

    // REVISAR ESSE TROWS
    public void adicionarCliente(String nomeCompleto, String nomeDeUsuario, String senha)
            throws ClienteInvalidoException, Exception {
        Cliente c = new Cliente(nomeCompleto, nomeDeUsuario, senha);

        if (!clientes.containsKey(c.getNomeDeUsuario())) {
            escreveArqCliente(c);
            this.clientes.put(c.getNomeDeUsuario(), c);
        } else {
            throw new ClienteInvalidoException("Esse cliente já existe!");
        }
    }

    /**
     * Metodo para filtrar uma serie pelo seu genero
     * 
     * @param genero Esse é o genero que foi escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorGenero(String genero) {
        return clienteAtual.filtrarMidiaPorGenero(genero).toString();
    }

    /**
     * Metodo para filtrar uma serie pela quantidade de episodios
     * 
     * @param quantEpisodios Essa é a quantidade de episodioes escolhida para ser
     *                       filtrada
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios).toString();
    }

    /**
     * Metodo para filtrar uma serie pelo idioma
     * 
     * @param idioma Esse é o idioma escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public String filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarMidiaPorIdioma(idioma).toString();
    }

    /**
     * Esse método registra o cliente atual como audiencia da serie especificada
     * 
     * @param serie Essa é a serie especificada
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
     * 
     * @return Retorna um mapa com as series do arquivo
     * @throws MidiaInvalidaException
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
            e.printStackTrace();
        } 
    }

    /**
     * Esse metodo carrega as audiencias do arquivo de audiencia
     * 
     * @throws MidiaInvalidaException
     */

    // revisar typecasting
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
            e.printStackTrace();
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
     *                 assistira
     * @param serieCad Essa é a serie que tera a audiencia
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
     * Esse metodo acha a serie com o nome enviado
     * 
     * @param nome Esse é o nome enviado
     * @return Retorna a serie com o mesmo nome que o enviado
     */
    public Midia filtrarMidiaPorNome(String nome) throws MidiaInvalidaException {
        return this.midias.values().stream()
                .filter(s -> s.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new MidiaInvalidaException("Nenhuma midia encontrada com esse nome!"));
    }

    public boolean eEspecialista() {
        return (clienteAtual.getMeuTipo() != null);
    }

    public void comentar(String comentario, String nomeMidia) throws ClienteInvalidoException, AvaliacaoInvalidaException, MidiaInvalidaException {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        clienteAtual.fazerComentario(comentario, midia);
    }

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


    public String getNotaMedia(String nomeMidia) throws MidiaInvalidaException{
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        System.out.println(midia.getAvaliacoes());
    
        return (Double.toString(midia.calcularNotaMedia()));
    }

}
