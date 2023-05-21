package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlataformaStreaming {
    private static final String arqFilmes = "POO_Filmes.csv";
    private static final String arqSeries = "POO_Series.csv";
    private static final String arqClientes = "POO_Espectadores.csv";
    private static final String arqAudiencia = "POO_Audiencia.csv";
    private String nome;
    private HashMap<Integer, Serie> series = new HashMap<>();
    private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
    private HashMap<Integer, Filme> filmes = new HashMap<>();
    private static HashMap<Key<String, Integer>, Avaliacao> Avaliacoes = new HashMap<>(); // (id cliente / id Serie)
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
    public HashMap<Integer, Serie> getSeries() {
        return series;
    }

    /**
     * Método para cadastrar as series da plataforma
     * 
     * @param series mapa de todas as series que a plataforma ira conter
     */
    public void setSeries(HashMap<Integer, Serie> series) {
        this.series = series;
    }

    /**
     * Método para cadastrar os filmes da plataforma
     * 
     * @param filmes mapa de todos os filmes que a plataforma irá conter
     */
    public void setFilmes(HashMap<Integer, Filme> filmes) {
        this.filmes = filmes;
    }

    /**
     * Método para pegar os filmes cadastrados
     * 
     * @return retorna os filmes cadastrados na plataforma
     */
    public HashMap<Integer, Filme> getFilmes() {
        return filmes;
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
     * Método para cadastrar os clientes da plataforma
     * 
     * @param clientes mapa de todos os clientes que a plataforma irá conter
     */
    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
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
    public void adicionarSerie(Serie serie) {
        series.put(serie.getId(), serie);

    }

    /**
     * Método para adicionar um filme na lista de filmes
     * 
     * @param filme Esse é o filme que será recebido
     */
    public void adicionarFilme(Filme filme) {
        filmes.put(filme.getId(), filme);

    }

    /**
     * Método para adicionar um cliente na lista de clientes
     * 
     * @param c Esse é o cliente que será recebido
     */
    public void adicionarCliente(Cliente c) {

        this.clientes.put(c.getNomeDeUsuario(), c);
    }

    /**
     * Metodo para filtrar uma serie pelo seu genero
     * 
     * @param genero Esse é o genero que foi escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Serie> filtarPorGenero(String genero) {
        return clienteAtual.filtrarPorGenero(genero);
    }

    /**
     * Metodo para filtrar uma serie pela quantidade de episodios
     * 
     * @param quantEpisodios Essa é a quantidade de episodioes escolhida para ser
     *                       filtrada
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    /**
     * Metodo para filtrar uma serie pelo idioma
     * 
     * @param idioma Esse é o idioma escolhido para ser filtrado
     * @return retorna a lista filtrada do cliente atual
     */
    public List<Serie> filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarPorIdioma(idioma);
    }

    /**
     * Esse método registra o cliente atual como audiencia da serie especificada
     * 
     * @param serie Essa é a serie especificada
     */
    public void registrarAudiencia(Serie serie) {
        clienteAtual.registrarAudiencia(serie);
    }

    /**
     * Esse método faz o cliente logar se a senha e o nome de usuario ja estiverem
     * cadastrados
     * 
     * @param nomeUsuario esse é o nome de login do usuario
     * @param senha       essa é a senha do usuario
     * @return retorna o cliente logado como cliente atual
     */
    public Cliente login(String nomeUsuario, String senha) {
        clienteAtual = clientes.get(nomeUsuario);
        if (!senha.equals(clienteAtual.getSenha())) {
            this.clienteAtual = null;
        }
        return clienteAtual;
    }

    /**
     * Esse método carrega os clientes do arquivo de clientes
     * 
     * @return Retorna um mapa com as series do arquivo
     * @throws Exception
     */
    public HashMap<String, Cliente> carregarClientes() throws Exception {
        HashMap<String, Cliente> mapClientes = new HashMap<String, Cliente>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Espectadores.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");

            Cliente cliente = new Cliente(str.nextToken(), str.nextToken(), str.nextToken());
            mapClientes.put(cliente.getNomeDeUsuario(), cliente);
        }
        reader.close();
        return mapClientes;
    }

    /**
     * Esse método carrega as series do arquivo de serie
     * 
     * @return Retorna um mapa com as series do arquivo
     * @throws Exception
     */
    public HashMap<Integer, Serie> carregarSeries() throws Exception {
        HashMap<Integer, Serie> mapSeries = new HashMap<Integer, Serie>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Series.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha.trim(), ";");
            int id = Integer.parseInt(str.nextToken());
            Serie serie = new Serie(id, str.nextToken(), str.nextToken());
            mapSeries.put(serie.getId(), serie);
        }
        reader.close();
        return mapSeries;
    }

    /**
     * 
     * @return esse método carrega os filems do arquivo de filmes
     * @throws Exception
     */
    public HashMap<Integer, Filme> carregarFilmes() throws Exception {
        HashMap<Integer, Filme> mapFilmes = new HashMap<Integer, Filme>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Filmes.csv"));
        String linha;

        reader.readLine();

        while ((linha = reader.readLine()) != null) {

            StringTokenizer str = new StringTokenizer(linha, ";");
            int id = Integer.parseInt(str.nextToken());
            Filme filme = new Filme(id, str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken()));
            mapFilmes.put(filme.getId(), filme);
        }

        reader.close();
        return mapFilmes;
    }

    /**
     * Esse metodo carrega as audiencias do arquivo de audiencia
     * 
     * @throws Exception
     */
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
                Serie temp = series.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarNaLista(temp);
            } else {
                int id = Integer.parseInt(str.nextToken());
                Serie temp = series.get(id);
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

                clienteAtual.adicionarSerieVista(temp);
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

    /**
     * Esse metodo escreve um filme no arquivo de filmes
     * 
     * @param filmeCad Esse é o filme a ser escrito
     */
    public void escreveArqFilme(Filme filmeCad) {
        escreveArquivo(filmeCad, arqFilmes);

    }

    /**
     * Esse metodo escreve uma serie no arquivo de serie
     * 
     * @param serieCad Esse é a serie a ser escrita
     */
    public void escreveArqSerie(Serie serieCad) {
        escreveArquivo(serieCad, arqSeries);
    }

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
            System.out.println("Cliente cadastrado com sucesso");
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
        HashMap<String, Cliente> mapClientes = carregarClientes();
        if (mapClientes.containsKey(cliente.getNomeDeUsuario())) {
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
     * Esse metodo acha a serie com o nome enviado
     * 
     * @param nome Esse é o nome enviado
     * @return Retorna a serie com o mesmo nome que o enviado
     */
    public Serie filtrarSeriePorNome(String nome) {
        List<Serie> listaNova = new LinkedList<>();
        for (Serie s : this.series.values()) {
            if (s.getNome().equals(nome)) {
                listaNova.add(s);
            }
        }

        return listaNova.get(0);
    }

    /**
     * Esse metodo acha o filme com o nome enviado
     * 
     * @param nome Esse é o nome enviado
     * @return Retorna o filme com o mesmo nome que o enviado
     */
    public Filme filtrarFilmePorNome(String nome) {

        List<Filme> listaNova = new LinkedList<>();
        for (Filme s : this.filmes.values()) {
            if (s.getNome().equals(nome)) {
                listaNova.add(s);
            }
        }

        return listaNova.get(0);
    }

    public Avaliacao setNota(String nomeUsuario, int id_Midia, float nota) {

        Avaliacao avaliacao = new Avaliacao(nota);
        Key<String, Integer> key = new Key<String, Integer>(nomeUsuario, id_Midia);
        Avaliacoes.put(key, avaliacao);

        return avaliacao;
    }

    public Avaliacao setNota(String nomeUsuario, int id_Midia, float nota, String comentario) {

        Avaliacao avaliacao = new Avaliacao(nota, comentario);
        Key<String, Integer> key = new Key<String, Integer>(nomeUsuario, id_Midia);
        Avaliacoes.put(key, avaliacao);
        return avaliacao;
    }

    public boolean checkAvaliacaoFilme(String nomeM) {

        boolean permitido = false;

        Filme f = filtrarFilmePorNome(nomeM);

        if (this.clienteAtual.getFilmesJaVistos().contains(f)) {
            int idMidia = f.getId();
            String nomeUsuario = this.clienteAtual.getNomeDeUsuario();
            Key<String, Integer> key = new Key<String, Integer>(nomeUsuario, idMidia);

            if (!Avaliacoes.containsKey(key)) {
                permitido = true;
            }
        }

        return permitido;
    }

    public boolean checkAvaliacaoSerie(String nomeM) {

        boolean permitido = false;

        Serie s = filtrarSeriePorNome(nomeM);
        System.out.println("checkAvaliacao:" + this.clienteAtual.getListaJaVista());
        if (this.clienteAtual.getListaJaVista().contains(s)) {
            int idMidia = s.getId();
            String nomeUsuario = this.clienteAtual.getNomeDeUsuario();
            Key<String, Integer> key = new Key<String, Integer>(nomeUsuario, idMidia);

            System.out.println("Passou aqui");
            if (!Avaliacoes.containsKey(key)) {
                System.out.println("Passou aqui 2");
                permitido = true;
            }
        }

        return permitido;
    }

    public boolean eEspecialista() {

        return (this.clienteAtual instanceof ClienteEspecialista);
    }

    // Na mudança de Regular para Especialista, todos as series começam a ja ter
    // sido avaliadas

    public Cliente getQtdAvaliacoes() {

        int contador = 0;

        for (Key<String, Integer> chave : Avaliacoes.keySet()) {
            if (chave.getKey1().equals(this.clienteAtual.getNomeDeUsuario())) {
                contador++;

            }
        }

        if (contador >= 0) {
            String usuario = clienteAtual.getNomeDeUsuario();
            clientes.remove(usuario);
            ClienteEspecialista novo = new ClienteEspecialista(clienteAtual.getNomeCompleto(),
                    clienteAtual.getNomeDeUsuario(), clienteAtual.getSenha());
            this.clienteAtual = novo;
            adicionarCliente(novo);
            System.out.println(this.clienteAtual.getListaJaVista());
        }

        return this.clienteAtual;
    }

    public String getMidia(int id) {

        String nome = "";
        for (Serie s : this.series.values()) {
            if (s.getId() == id) {
                nome = s.getNome();

            }
        }
        return nome;
    }

}
