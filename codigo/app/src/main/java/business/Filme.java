package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme extends Midia implements Lancavel {

    private int duracao;

    /**
     * Esse é o construtor da classe filme para usar quando cadastrado manualmente
     * 
     * @param genero  Esse é o genero do filme, como terror ou comedia
     * @param nome    Esse é o nome do filme
     * @param idioma  Esse é o idioma do filme como portugues ou ingles
     * @param duracao Essa é a duração em minutos do filme
     * @throws MidiaInvalidaException propaga exceção se houver valores inválidos
     */
    public Filme(String genero, String nome, String idioma, int duracao) throws MidiaInvalidaException {
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
     * @throws MidiaInvalidaException propaga exceção se houver valores inválidos
     */
    public Filme(int id, String nome, LocalDate dataLancamento, int duracao) throws MidiaInvalidaException {
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
     * @throws MidiaInvalidaException propaga exceção se a duração do filme for
     *                                menor que 1 minuto
     */
    public void setDuracao(int duracao) throws MidiaInvalidaException {
        if (duracao > 0) {
            this.duracao = duracao;
        } else {
            throw new MidiaInvalidaException("O filme deve possuir pelo menos 1 minuto");
        }
    }

    /**
     * Esse método implementa o método da interface ISalvavel é para receber os
     * dados do filme formatados para colocar no
     * arquivo
     * 
     * @return uma string com os dados do filme
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

    /**
     * Método que retorna todos os dados do filme formatados para mostrar na tela
     * 
     * @return uma string com os dados do filme
     */
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