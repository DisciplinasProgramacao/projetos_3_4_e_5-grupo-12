import java.util.HashSet;

public class PlataformaStreaming {
    private String nome;
    private Hash<Serie> series;
    private Hash<Cliente> clientes;
    private Cliente clienteAtual; 


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Hash<Serie> getSeries() {
        return this.series;
    }

    public void setSeries(Hash<Serie> series) {
        this.series = series;
    }

    public Hash<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(Hash<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteAtual() {
        return this.clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }


    public Cliente login(String nomeUsuario, String senha){

    }

    public void adicionarSerie(Serie serie){

    }

    public void adicionarCliente(Cliente cliente){

    }

    public Lista<Serie> filtrarPorGenero(String genero){

    }

    public Lista<Serie> filtrarPorIdioma(String idioma){

    }

    public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios){

    }
    
    public void registrarAudiencia(Serie serie){
        
    }
}