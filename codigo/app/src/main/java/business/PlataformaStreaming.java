package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer,Serie> series = new HashMap<>();
    HashMap<Integer,Cliente> clientes = new HashMap<Integer, Cliente>();
    private Cliente clienteAtual;
    public static int contCliente = 0;
    public static int contSerie = 0;


    public PlataformaStreaming(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<Integer,Serie> getSeries() {
        return series;
    }

    public void setSeries(HashMap<Integer,Serie> series) {
        this.series = series;
    }

    public HashMap<Integer,Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<Integer,Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public void adicionarSerie(Serie serie) {
        series.put(contSerie, serie);
        contSerie++;
    }

    public void adicionarCliente(Cliente c) {
        this.clientes.put(contCliente, c);
        contCliente++;
    }


    public List<Serie> filtarPorGenero(String genero){
        return clienteAtual.filtrarPorGenero(genero);
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return clienteAtual.filtrarPorIdioma(idioma);
    }

    public void registrarAudiencia(Serie serie){
        clienteAtual.registrarAudiencia(serie);
    }

    public Cliente login(String nomeUsuario, String senha) {
        return null;
    }

    public HashMap<String, Cliente> carregarClientes() throws Exception { 
        HashMap<String, Cliente> mapClientes = new HashMap<String, Cliente>();
        BufferedReader reader = new BufferedReader(new FileReader("espectadoresteste.csv"));
        String linha;
        reader.read();
        
        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");
            str.nextToken();
            Cliente cliente = new Cliente(str.nextToken(), str.nextToken());
            mapClientes.put(cliente.getNomeDeUsuario(), cliente) ;
        }
        reader.close();
        return mapClientes;
    }
    


}
