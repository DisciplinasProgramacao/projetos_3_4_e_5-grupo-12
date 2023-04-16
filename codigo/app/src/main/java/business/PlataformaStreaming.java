package business;

import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Serie> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome, HashSet<Serie> series, Cliente clienteAtual) {
        this.nome = nome;
        this.series = series;
        this.clienteAtual = clienteAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashSet<Serie> getSeries() {
        return series;
    }

    public void setSeries(HashSet<Serie> series) {
        this.series = series;
    }

    public HashSet<Serie> getClientes() {
        return clientes;
    }

    public void setClientes(HashSet<Serie> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public void adicionarSerie(Serie serie) {
        series.add(serie);
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

}
