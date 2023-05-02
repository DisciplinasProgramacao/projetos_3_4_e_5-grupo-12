package business;

public class Filme extends Midia implements ISalvavel {

    private int duracao;

    public Filme(String genero, String nome, String idioma, int duracao) {
        super(genero, nome, idioma);
        setDuracao(duracao);

    }

    public Filme(int id, String nome, String dataLancamento, int duracao) {
        super(id, nome);
        super.setDataLancamento(dataLancamento);
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao > 0) {
            this.duracao = duracao;
        }
    }

    @Override
    public String getDadosString() {
        int id = getId();
        String nome = getNome();
        String dataL = getDataLancamento();
        int duracao = getDuracao();

        return ("\n" + id + ";" + nome + ";" + dataL + ";" + duracao);
    }
}