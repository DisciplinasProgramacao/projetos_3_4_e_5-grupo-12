package business;
import java.util.Random;
public class Serie extends Midia {

    
    private int quantidadeEpisodios;

    static final String[] generos = new String[] { "comedia", "terror", "romance" };

    public Serie(String genero, String nome, String idioma, int quantidadeEpisodios) {

        super(genero, nome, idioma);
        setQuantidadeEpisodios(quantidadeEpisodios);
    }

    // Construtor para a criacao de series durante leitura de dados
    public Serie(int id, String nome, String dataLancamento) {
        super(id, nome);
        Random random = new Random();
        super.setDataLancamento(dataLancamento);
        setQuantidadeEpisodios(random.nextInt(99) + 1); ;
    }

    /*  public Serie(int id, String nome, String dataLancamento) {
        Random random = new Random();
        int numAleatorio;

        numAleatorio = random.nextInt(2);
        this.genero = generos[numAleatorio];

        numAleatorio = random.nextInt(2);
        this.idioma = idiomas[numAleatorio];

        this.quantidadeEpisodios = random.nextInt(99) + 1;

        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;


    } */

    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        }
    }


    @Override
    public String toString() {
        return "{" +
        " nome='" + getNome() + "'" +
        " genero='" + getGenero() + "'" +
        ", idioma='" + getIdioma() + "'" +
        " quantidadeEpisodios='" + getQuantidadeEpisodios() + "'" +
        ", audiencia='" + getAudiencia() + "'" +
        "}";
    }

}