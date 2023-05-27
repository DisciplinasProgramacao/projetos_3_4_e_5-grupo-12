package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
    }

    /**
     * Método para pegar o nome da plataforma
     * 
     * @return retorna o nome da plataforma
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Método para pegar as series cadastradas
     * 
     * @return retorna as series cadastradas na plataforma
     */
    public HashMap<Integer, Midia> getMidias() {
        return midias;
    }

    /**
     * Método para cadastrar as series da plataforma
     * 
     * @param series mapa de todas as series que a plataforma ira conter
     */
    public void setMidia(HashMap<Integer, Midia> midias) {
        this.midias = midias;
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
     */

    //REVISAR -- contains key
    public void adicionarMidia(Midia midia) {
        if(!midias.containsValue(midia)) {
            System.out.println(midia.getId());
            midias.put(midia.getId(), midia);
            escreveArqMidia(midia);
        }
    }

    public void adicionarSerie(String nome, String idioma, String genero, int qtdEpisodios) throws SerieInvalidaException, MidiaInvalidaException {
        
        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        adicionarMidia(serieCad);
    }

    public void adicionarFilme(String nome, String idioma, String genero, int duracao) throws  MidiaInvalidaException, FilmeInvalidoException {
        Filme filmeCad = new Filme(genero, nome, idioma, duracao);
        adicionarMidia(filmeCad);
    }


    //adicionar exeption
    public void adicionarMidiaParaAssistir(String nomeMidia) {

        Midia midia = filtrarMidiaPorNome(nomeMidia);
        if(midia != null) {
            this.clienteAtual.adicionarListaParaVer(midia);
        }
    }

    public void adicionarMidiaAssistida(String nomeMidia) {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        clienteAtual.adicionarMidiaVista(midia);
    }


    public String getListaJaVista() {

        return this.clienteAtual.getListaJaVista().toString();
    }

    public String getListaParaAssistir() {

        return this.clienteAtual.getListaParaVer().toString();
    }


    /**
     * Método para adicionar um cliente na lista de clientes
     * 
     * @param c Esse é o cliente que será recebido
     * @throws Exception
     */

     //REVISAR ESSE TROWS
    public void adicionarCliente(String nomeCompleto, String nomeDeUsuario, String senha) throws Exception  {

        Cliente c = new Cliente(nomeCompleto, nomeDeUsuario, senha);
        
        if(!clientes.containsValue(c)){
            escreveArqCliente(c);
            this.clientes.put(c.getNomeDeUsuario(), c);     
            
        }
    }

    /**
     * Metodo para filtrar uma serie pelo seu genero
     * 
     * @param genero Esse é o genero que foi escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Midia> filtarPorGenero(String genero) {
        return clienteAtual.filtrarMidiaPorGenero(genero);
    }

    /**
     * Metodo para filtrar uma serie pela quantidade de episodios
     * 
     * @param quantEpisodios Essa é a quantidade de episodioes escolhida para ser
     *                       filtrada
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    /**
     * Metodo para filtrar uma serie pelo idioma
     * 
     * @param idioma Esse é o idioma escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarMidiaPorIdioma(idioma);
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
     * @throws Exception
     */
    public void carregarClientes() throws Exception {
        
        BufferedReader reader = new BufferedReader(new FileReader("POO_Espectadores.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");

            Cliente cliente = new Cliente(str.nextToken(), str.nextToken(), str.nextToken());
            clientes.put(cliente.getNomeDeUsuario(), cliente);
        }
        reader.close();
    }

    /**
     * Esse método carrega as series do arquivo de serie
     * 
     * @return Retorna um mapa com as series do arquivo
     * @throws Exception
     */
    public void carregarSeries() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("POO_Series.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha.trim(), ";");
            int id = Integer.parseInt(str.nextToken());
            Serie serie = new Serie(id, str.nextToken(), str.nextToken());
            midias.put(serie.getId(), serie);
        }
        reader.close();
    }

    /**
     * 
     * @return esse método carrega os filems do arquivo de filmes
     * @throws Exception
     */
    public void carregarFilmes() throws Exception {
        
        BufferedReader reader = new BufferedReader(new FileReader("POO_Filmes.csv"));
        String linha;

        reader.readLine();

        while ((linha = reader.readLine()) != null) {

            StringTokenizer str = new StringTokenizer(linha, ";");
            int id = Integer.parseInt(str.nextToken());
            Filme filme = new Filme(id, str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken()));
            midias.put(filme.getId(), filme);
        }

        reader.close();
    }

    /**
     * Esse metodo carrega as audiencias do arquivo de audiencia
     * 
     * @throws Exception
     */

     //revisar typecasting
    public void carregarAudiencia() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        float nota;
        BufferedReader reader = new BufferedReader(new FileReader(arqAudiencia));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");
            String login = str.nextToken();

            this.clienteAtual = clientes.get(login);

            if (str.nextToken().equals("F")) {
                Midia temp = midias.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarListaParaVer(temp);
            } else {
                int id = Integer.parseInt(str.nextToken());
                Midia temp = midias.get(id);
                if (str.hasMoreTokens()) {
                    nota = Float.parseFloat(str.nextToken());
                    Key<String, Integer> key = new Key<String, Integer>(login, id);
                    if (str.hasMoreTokens()) {
                        String dataS = str.nextToken();
                        LocalDate data = LocalDate.parse(dataS, formatter);
                        Avaliacao av = new Avaliacao(nota, data);
                        Avaliacoes.put(key, av);
                    } else {
                        Avaliacao av = new Avaliacao(nota);
                        Avaliacoes.put(key, av);
                    }
                }

                clienteAtual.adicionarMidiaVista(temp);
            }
        }
        reader.close();
    }

    /**
     * esse metodo escreve o objeto no arquivo escolhido
     * 
     * @param objeto      Esse é o objeto a ser escrito
     * @param nomeArquivo Esse é o arquivo em que sera escrito o objeto
     */
    public void escreveArquivo(ISalvavel objeto, String nomeArquivo) {

        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, true);
            arquivo.write(objeto.getDadosString());
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    public void escreveArqMidia(ISalvavel midia) {
        if(midia instanceof Filme) {
            escreveArquivo(midia, arqFilmes);
        } else if (midia instanceof Serie) {
            escreveArquivo(midia, arqSeries);
        }
    }

    // /**
    //  * Esse metodo escreve um filme no arquivo de filmes
    //  * 
    //  * @param filmeCad Esse é o filme a ser escrito
    //  */
    // public void escreveArqFilme(Filme filmeCad) {
    //     escreveArquivo(filmeCad, arqFilmes);

    // }

    // /**
    //  * Esse metodo escreve uma serie no arquivo de serie
    //  * 
    //  * @param serieCad Esse é a serie a ser escrita
    //  */
    // public void escreveArqSerie(Serie serieCad) {
    //     escreveArquivo(serieCad, arqSeries);
    // }

    /**
     * Esse metodo escreve um cliente no arquivo de cliente, se o nome de usuario
     * dele ja nao estiver cadastrado
     * 
     * @param clienteCad Esse é o cliente a ser posto
     * @throws Exception excecao a ser lancada caso o nome de usuario esteja
     *                   indisponivel ou haja algum erro
     */
    public void escreveArqCliente(Cliente clienteCad) throws Exception {
        boolean clienteExistente = clienteExistente(clienteCad);
        if (!clienteExistente) {
            escreveArquivo(clienteCad, arqClientes);
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            throw new NomeUsuarioException();
        }
    }

    /**
     * Esse metodo verifica se o nome de usuario escolhido ja esta cadastrado
     * 
     * @param cliente é o cliente cujo nome de usuario sera verificado
     * @throws Exception exceção a ser lançada caso haja algum erro
     */
    public boolean clienteExistente(Cliente cliente) throws Exception {
        boolean flag = false;
        
        if (clientes.containsKey(cliente.getNomeDeUsuario())) { //vou comparar com o antigo pera ai
            System.out.println("passou aqui");
            flag = true;
        }
        return flag;
    }

    // /**
    // * Esse metodo escreve a audiencia no arquivo de audiencia
    // * @param clienteCad esse é o cliente que esta sendo posto como audiencia
    // */
    // public void escreveArqAudiencia(Cliente clienteCad) {
    // escreveArquivo(clienteCad, arqAudiencia);

    // }

    /**
     * Esse metodo escreve a audiencia no arquivo de audiencia
     * 
     * @param tipo     Isso indica se o cliente ja viu a serie ou futuramente
     *                 assistira
     * @param serieCad Essa é a serie que tera a audiencia
     */
    public void escreveArqAudiencia(String tipo, Serie serieCad, float nota) {

        try {
            FileWriter arquivo = new FileWriter(arqAudiencia, true);
            int id = serieCad.getId();
            String login = clienteAtual.getNomeDeUsuario();

            Key<String, Integer> chave = new Key<>(login, id);
            LocalDate data = Avaliacoes.get(chave).getData();
            arquivo.write("\n" + login + ";" + tipo + ";" + id + ";" + nota + ";" + data);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

/**
 * Escreve os dados de audiência no arquivo, contendo o tipo (visualização ou recomendação) e a série cadastrada.
 *
 * @param tipo o tipo de audiência (visualização ou recomendação)
 * @param serieCad a série cadastrada
 */
    public void escreveArqAudiencia(String tipo, Serie serieCad) {

        try {
            FileWriter arquivo = new FileWriter(arqAudiencia, true);
            int id = serieCad.getId();
            String login = clienteAtual.getNomeDeUsuario();

            //Key<String, Integer> chave = new Key<>(login, id);
            //LocalDate data = Avaliacoes.get(chave).getData();
            arquivo.write(login + ";" + tipo + ";" + id + "\n");
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    /**
     * Esse metodo acha a serie com o nome enviado
     * 
     * @param nome Esse é o nome enviado
     * @return Retorna a serie com o mesmo nome que o enviado
     */
    public Midia filtrarMidiaPorNome(String nome) {
        List<Midia> listaNova = new LinkedList<>();
        for (Midia s : this.midias.values()) {
            if (s.getNome().equals(nome)) {
                listaNova.add(s);
            }
        }

        if (!listaNova.isEmpty()) {
            return listaNova.get(0);
        } else {
            return null;
        }
    }


    public void adicionarAvaliacao(float nota, String nomeMidia) {

        Midia midia = filtrarMidiaPorNome(nomeMidia);
        clienteAtual.adicionarAvaliacao(nota, midia);
    }


/**
 * Verifica se um cliente pode avaliar um filme.
 *
 * @param nomeM o nome do filme a ser verificado
 * @return true se o cliente pode avaliar o filme, false caso contrário
 */
    public boolean checkAvaliacaoMidia(String nomeM) {

        boolean permitido = false;

        Midia m = filtrarMidiaPorNome(nomeM);

        if (this.clienteAtual.getListaJaVista().contains(m)) {
            int idMidia = m.getId();
            String nomeUsuario = this.clienteAtual.getNomeDeUsuario();
            Key<String, Integer> key = new Key<String, Integer>(nomeUsuario, idMidia);

            if (!Avaliacoes.containsKey(key)) {
                permitido = true;
            }
        }

        return permitido;
    }

    // public int getQtdAvaliacoes() {

    //     int contador = 0;

    //     for (Key<String, Integer> chave : Avaliacoes.keySet()) {
    //         if (chave.getKey1().equals(this.clienteAtual.getNomeDeUsuario())) {
    //             contador++;
    //         }
    //     }
        
    //     return contador;
    // }

    public boolean eEspecialista() {
        return (clienteAtual.getMeuTipo() != null);
    }


    public void comentar(String comentario, String nomeMidia) {
        Midia midia = filtrarMidiaPorNome(nomeMidia);
        clienteAtual.fazerComentario(comentario, midia);
    }


    //REVISAR
    public Cliente setClienteEspecialista() throws Exception {

        //int contador = getQtdAvaliacoes();

        List<LocalDate> datasAvaliacoes = new ArrayList<LocalDate>();


        for(Key<String, Integer> chave : Avaliacoes.keySet()) {
            if (chave.getKey1().equals(this.clienteAtual.getNomeDeUsuario())) {
                datasAvaliacoes.add(Avaliacoes.get(chave).getData());
                System.out.println("Data " + Avaliacoes.get(chave).getData());
            }   
        }

        System.out.println(datasAvaliacoes);

        if (datasAvaliacoes.size() >= 5) {

            LocalDate quintaDataMaisRecente = datasAvaliacoes.get(datasAvaliacoes.size() -5);
            LocalDate dataMaisRecente = datasAvaliacoes.get(datasAvaliacoes.size() - 1);

            long diasEntre = ChronoUnit.DAYS.between(quintaDataMaisRecente, dataMaisRecente);

            if (diasEntre <= 30) {
                
                String usuario = clienteAtual.getNomeDeUsuario();
                clientes.remove(usuario);
                ClienteEspecialista novo = new ClienteEspecialista(clienteAtual.getNomeCompleto(), clienteAtual.getNomeDeUsuario(), clienteAtual.getSenha());
                this.clienteAtual = novo;
           
                adicionarCliente(novo);
            } else {

                String usuario = clienteAtual.getNomeDeUsuario();
                clientes.remove(usuario);
                ClienteRegular novo = new ClienteRegular(clienteAtual.getNomeCompleto(), clienteAtual.getNomeDeUsuario(), clienteAtual.getSenha());
                this.clienteAtual = novo;
                adicionarCliente(novo);
            }

            
        }

        return this.clienteAtual;
    }

  

}



