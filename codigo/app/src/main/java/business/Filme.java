package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme extends Midia {

    private int duracao;

    /**
     * Esse é o construtor da classe filme para usar quando cadastrado manualmente
     * 
     * @param genero  Esse é o genero do filme, como terror ou comedia
     * @param nome    Esse é o nome do filme
     * @param idioma  Esse é o idioma do filme como portugues ou ingles
     * @param duracao Essa é a duração em minutos do filme
     * @throws SerieInvalidaException
     * @throws FilmeInvalidoException
     * @throws MidiaInvalidaException
     */
    public Filme(String genero, String nome, String idioma, int duracao) throws FilmeInvalidoException, MidiaInvalidaException {
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
     * @throws MidiaInvalidaException
     * @throws FilmeInvalidoException
     */
    public Filme(int id, String nome, LocalDate dataLancamento, int duracao) throws MidiaInvalidaException, FilmeInvalidoException {
        super(id, nome, dataLancamento);
        setDuracao(duracao);
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
     * @throws FilmeInvalidoException
     */
    public void setDuracao(int duracao) throws FilmeInvalidoException {
        if (duracao > 0) {
            this.duracao = duracao;
        } else {
            throw new FilmeInvalidoException("O filme deve possuir pelo menos 1 minuto");
        }
    }

    /**
     * Esse método é para receber os dados do filme formatados para colocar no
     * arquivo
     */
    @Override
    public String getDados() {
        int id = getId();
        String nome = getNome();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = getDataLancamento().format(formatter);
        int duracao = getDuracao();

        return ("\n" + id + ";" + nome + ";" + data + ";" + duracao);
    }

    @Override
    public String toString() {
        return "'filme'{" +
                " nome='" + getNome() + "'" +
                ", id='" + getId() + "'" +
                ", genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                ", duracao='" + getDuracao() + "'" +
                ", audiencia='" + getAudiencia() + "'" +
                "}";
    }
}