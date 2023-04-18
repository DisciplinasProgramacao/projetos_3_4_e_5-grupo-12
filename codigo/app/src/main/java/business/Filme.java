package business;

public class Filme extends Midia {

    private int duracao;

    public Filme(String genero, String nome, String idioma, int duracao) {
        super(genero, nome, idioma);
        setDuracao(duracao);

    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao > 0) {
            this.duracao = duracao;
        }
    }

}