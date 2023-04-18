package business;

public class Serie extends Midia {

    
    private int quantidadeEpisodios;
    static final String[] generos = new String[] { "comedia", "terror", "romance" };

    public Serie(String genero, String nome, String idioma, int quantidadeEpisodios) {

        super(genero, nome, idioma);
        setQuantidadeEpisodios(quantidadeEpisodios);
    }

    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        }
    }
}