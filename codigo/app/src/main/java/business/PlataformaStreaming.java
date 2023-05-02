package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.Cleaner.Cleanable;
import java.util.*;

public class PlataformaStreaming {
    private static final String arqFilmes = "POO_Filmes.csv";
    private static final String arqSeries = "POO_Series.csv";
    private static final String arqClientes = "POO_Espectadores.csv";
    private static final String arqAudiencia = "POO_Audiencia.csv";
    private String nome;
    private HashMap<String, Serie> series = new HashMap<>();
    private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
    private HashMap<String, Filme> filmes = new HashMap<>();
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String, Serie> getSeries() {
        return series;
    }

    public void setSeries(HashMap<String, Serie> series) {
        this.series = series;
    }

    public void setFilmes(HashMap<String, Filme> filmes) {
        this.filmes = filmes;
    }

    public HashMap<String, Filme> getFilmes() {
        return filmes;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public void adicionarSerie(Serie serie) {
        series.put(serie.getNome(), serie);

    }

    public void adicionarFilme(Filme filme) {
        filmes.put(filme.getNome(), filme);

    }

    public void adicionarCliente(Cliente c) {

        this.clientes.put(c.getNomeDeUsuario(), c);
    }

    public List<Serie> filtarPorGenero(String genero) {
        return clienteAtual.filtrarPorGenero(genero);
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarPorIdioma(idioma);
    }

    public void registrarAudiencia(Serie serie) {
        clienteAtual.registrarAudiencia(serie);
    }

    public Cliente login(String nomeUsuario, String senha) {
        clienteAtual = clientes.get(nomeUsuario);
        if (!senha.equals(clienteAtual.getSenha())) {
            this.clienteAtual = null;
        }
        return clienteAtual;
    }

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

    public HashMap<String, Serie> carregarSeries() throws Exception {
        HashMap<String, Serie> mapSeries = new HashMap<String, Serie>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Series.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha.trim(), ";");
            int id = Integer.parseInt(str.nextToken());
            Serie serie = new Serie(id, str.nextToken(), str.nextToken());
            mapSeries.put(serie.getNome(), serie);
        }
        reader.close();
        return mapSeries;
    }

    public HashMap<String, Filme> carregarFilmes() throws Exception {
        HashMap<String, Filme> mapFilmes = new HashMap<String, Filme>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Filmes.csv"));
        String linha;

        reader.readLine();

        while ((linha = reader.readLine()) != null) {

            StringTokenizer str = new StringTokenizer(linha, ";");
            int id = Integer.parseInt(str.nextToken());
            Filme filme = new Filme(id, str.nextToken(), str.nextToken(), Integer.parseInt(str.nextToken()));
            mapFilmes.put(filme.getNome(), filme);
        }

        reader.close();
        return mapFilmes;
    }

    public void carregarAudiencia() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("POO_Audiencia.csv"));
        String linha;
        reader.readLine();

        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");
            String login = str.nextToken();
            clienteAtual = clientes.get(login);
            if (str.nextToken().equals("F")) {
                Serie temp = series.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarNaLista(temp);
            } else {
                Serie temp = series.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarSerieVista(temp);
            }
        }
        reader.close();
    }

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

    public void escreveArqFilme(Filme filmeCad) {
        escreveArquivo(filmeCad, arqFilmes);

    }

    public void escreveArqSerie(Serie serieCad) {
        escreveArquivo(serieCad, arqSeries);
    }

    public void escreveArqCliente(Cliente clienteCad) {
        escreveArquivo(clienteCad, arqClientes);

    }

    public void escreveArqAudiencia(Cliente clienteCad) {
        escreveArquivo(clienteCad, arqAudiencia);

    }

    // fazer verificacao
    public void escreveArqAudiencia(String tipo, Serie serieCad) {

        try {
            FileWriter arquivo = new FileWriter("POO_Audiencia.csv", true);

            String login = clienteAtual.getNomeDeUsuario();
            int id = serieCad.getId();

            arquivo.write("\n" + login + ";" + tipo + ";" + id);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    public Serie findSerie(String nome) {
        Serie serie;

        serie = series.get(nome);
        return serie;
    }

    public Filme findFilme(String nome) {
        Filme filme;

        filme = filmes.get(nome);
        return filme;
    }

}
