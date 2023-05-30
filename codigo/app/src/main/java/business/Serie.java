package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Serie extends Midia {

    private int quantidadeEpisodios;
    private Random random = new Random();

    /**
     * Esse é o construtor da classe serie para usar quando cadastrado manualmente
     * 
     * @param genero              Esse é o genero da serie, como terror ou comedia
     * @param nome                Esse é o nome da serie
     * @param idioma              Esse é o idioma da serie como portugues ou ingles
     * @param quantidadeEpisodios Essa é a quantidade de episodios da serie
     * @throws MidiaInvalidaException
     */
    public Serie(String genero, String nome, String idioma, int quantidadeEpisodios) throws MidiaInvalidaException{
        super(genero, nome, idioma);
        setQuantidadeEpisodios(quantidadeEpisodios);
    }

    /**
     * Esse é o construtor da classe serie para usar quando pego do arquivo
     * 
     * @param id             Esse é o id da serie
     * @param nome           Esse é o nome da serie
     * @param dataLancamento Esse é a data de lançamento da serie
     * @throws SerieInvalidaException
     * @throws MidiaInvalidaException
     */
    public Serie(int id, String nome, LocalDate dataLancamento) throws MidiaInvalidaException {
        super(id, nome, dataLancamento);
        setQuantidadeEpisodios(random.nextInt(99) + 1);
    }

    /**
     * metodo para pegar a quantida de episodios da serie
     * 
     * @return retorna a quantida de episodios da serie
     */
    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    /**
     * Metodo para por a quantida de episodios da serie
     * 
     * @param quantidadeEpisodios a quantidade de episódios a ser definida
     * @throws SerieInvalidaException
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) throws MidiaInvalidaException {
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        } else {
            throw new MidiaInvalidaException("A serie deve possuir pelo menos 1 episodio!");
        }
    }

    /**
     * Retorna uma representação em String do objeto Série.
     * 
     * @return uma String que contém o nome, o id, o gênero, o idioma, a quantidade
     *         de episódios
     *         e a audiência da Série
     */
    @Override
    public String toString() {
        return "'serie'{" +
                " nome='" + getNome() + "'" +
                ", id='" + getId() + "'" +
                ", genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                ", quantidadeEpisodios='" + getQuantidadeEpisodios() + "'" +
                ", audiencia='" + getAudiencia() + "'" +
                "}";
    }

    /**
     * Retorna uma String formatada contendo os dados do objeto.
     * 
     * @return uma String contendo o id, nome e data de lançamento do objeto
     */
    @Override
    public String getDados() {
        int id = getId();
        String nome = getNome();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = getDataLancamento().format(formatter);

        return ("\n" + id + ";" + nome + ";" + data);
    }
}