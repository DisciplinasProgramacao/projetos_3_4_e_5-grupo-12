package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.Cleaner.Cleanable;
import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer, Serie> series = new HashMap<>();
    private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
    private HashMap<Integer, Filme> filmes = new HashMap<>();
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

    public HashMap<Integer, Serie> getSeries() {
        return series;
    }

    public void setSeries(HashMap<Integer, Serie> series) {
        this.series = series;
    }

    public void setFilmes(HashMap<Integer, Filme> filmes) {
        this.filmes = filmes;
    }

    public HashMap<Integer, Filme> getFilmes() {
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
        series.put(serie.getId(), serie);

    }

    public void adicionarFilme(Filme filme) {
        filmes.put(filme.getId(), filme);

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
            
            Cliente cliente = new Cliente(str.nextToken(),str.nextToken(), str.nextToken());
            mapClientes.put(cliente.getNomeDeUsuario(), cliente);
        }
        reader.close();
        return mapClientes;
    }

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

    public void carregarAudiencia() throws Exception { 
        
        BufferedReader reader = new BufferedReader(new FileReader("POO_Audiencia.csv"));
        String linha;
        reader.readLine();
        
        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");
            String login = str.nextToken();
            clienteAtual = clientes.get(login);
            if(str.nextToken().equals("F")){
                Serie temp = series.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarNaLista(temp);
            } else {
                Serie temp = series.get(Integer.parseInt(str.nextToken()));
                clienteAtual.adicionarSerieVista(temp);
            }
        }
        reader.close();
    }

    public void escreveArqSeries(Serie serieCad) {
        
        try {
            FileWriter arquivo = new FileWriter("POO_Series.csv", true);
            int id = serieCad.getId();
            String nome = serieCad.getNome();
            String dataL = serieCad.getDataLancamento();
            arquivo.write("\n" + id + ";" + nome + ";" + dataL);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    public void escreveArqClientes(Cliente clienteCad) {
        
        try {
            FileWriter arquivo = new FileWriter("POO_Espectadores.csv", true);
      
            String login = clienteCad.getNomeDeUsuario();
            String senha = clienteCad.getSenha();
            String nome = clienteCad.getNomeCompleto();
            arquivo.write("\n" + nome + ";" + login + ";" + senha);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    public void escreveArqFilme(Filme filmeCad) {
        
        try {
            FileWriter arquivo = new FileWriter("POO_Filmes.csv", true);
      
            int id = filmeCad.getId();
            String nome = filmeCad.getNome();
            String dataL = filmeCad.getDataLancamento();
            int duracao = filmeCad.getDuracao();
            arquivo.write("\n" + id + ";" + nome + ";" + dataL + ";" + duracao);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }
  

}
