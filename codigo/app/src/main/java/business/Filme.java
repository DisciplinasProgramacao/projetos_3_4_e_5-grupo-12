package business;

public class Filme extends Midia implements ISalvavel {

    private int duracao;

    /**
     * Esse é o construtor da classe filme para usar quando cadastrado manualmente
     * 
     * @param genero  Esse é o genero do filme, como terror ou comedia
     * @param nome    Esse é o nome do filme
     * @param idioma  Esse é o idioma do filme como portugues ou ingles
     * @param duracao Essa é a duração em minutos do filme
     */
    public Filme(String genero, String nome, String idioma, int duracao) {
        super(genero, nome, idioma);
        setDuracao(duracao);

    }

    /**
     * Esse é o construtor do filme para usar quando pego do arquivo
     * 
     * @param id             Esse é o id do filme
     * @param nome           Esse é o nome do filme
     * @param dataLancamento Esse é a data de lançamento do filme
     * @param duracao        Essa é a duração em minutos do filme
     */
    public Filme(int id, String nome, String dataLancamento, int duracao) {
        super(id, nome);
        super.setDataLancamento(dataLancamento);
    }

    /**
     * metodo para pegar a duração do filme
     * 
     * @return retorna a duraçao do filme
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Metodo para por a duração do filme
     * 
     * @param duracao essa é a duração do filme em minutos, sendo que ela deve ter
     *                mais de 1 minuto
     */
    public void setDuracao(int duracao) {
        if (duracao > 0) {
            this.duracao = duracao;
        }
    }

    /**
     * Esse método é para receber os dados do filme formatados para colocar no
     * arquivo
     */
    @Override
    public String getDadosString() {
        int id = getId();
        String nome = getNome();
        String dataL = getDataLancamento();
        int duracao = getDuracao();

        return ("\n" + id + ";" + nome + ";" + dataL + ";" + duracao);
    }
}