package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Serie extends Midia implements Lancavel, IAssistivel {

    private int quantidadeEpisodios;
    private Random random = new Random();
    private static final String arqSeries = "codigo/POO_Series.csv";
    private HashMap<String, Avaliacao> avaliacoes = new HashMap<>();
        private int audiencia = 0;

    /**
     * Esse é o construtor da classe serie para usar quando cadastrado manualmente
     * 
     * @param genero              Esse é o genero da serie, como terror ou comedia
     * @param nome                Esse é o nome da serie
     * @param idioma              Esse é o idioma da serie como portugues ou ingles
     * @param quantidadeEpisodios Essa é a quantidade de episodios da serie
     * @throws MidiaInvalidaException Essa é a Excecao quando o set da midia esta
     *                                incorreto
     */
    public Serie(String genero, String nome, String idioma, int quantidadeEpisodios) throws MidiaInvalidaException {
        super(genero, nome, idioma);
        setQuantidadeEpisodios(quantidadeEpisodios);
    }

    /**
     * Esse é o construtor da classe serie para usar quando cadastrado manualmente
     * 
     * @param genero Esse é o genero da serie, como terror ou comedia
     * @param nome   Esse é o nome da serie
     * @param idioma Esse é o idioma da serie como portugues ou ingles
     * @throws MidiaInvalidaException Essa é a Excecao quando o set da midia esta
     *                                incorreto
     */
    public Serie(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);
    }

    /**
     * Esse é o construtor da classe serie para usar quando pego do arquivo
     * 
     * @param id             Esse é o id da serie
     * @param nome           Esse é o nome da serie
     * @param dataLancamento Esse é a data de lançamento da serie
     * @throws SerieInvalidaException Essa é a Excecao quando o set da serie esta
     *                                incorreto
     * @throws MidiaInvalidaException Essa é a Excecao quando o set da midia esta
     *                                incorreto
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
     * @throws SerieInvalidaException Essa é a Excecao quando o set da serie esta
     *                                incorreto
     */
    public void setQuantidadeEpisodios(int quantidadeEpisodios) throws MidiaInvalidaException {
        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        } else {
            throw new MidiaInvalidaException("A serie deve possuir pelo menos 1 episodio!");
        }
    }

    /**
     * Registra um aumento na audiência associada a este objeto.
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    /**
     * Metodo para colocar um comentario em uma avaliaçao
     * 
     * @param comentario  esse é o comentario que sera enviado
     * @param nomeUsuario nome de quem esta enviando
     * @throws AvaliacaoInvalidaException excecao para caso a avaliaçao esteje
     *                                    errada
     */
    @Override
    public void setComentario(String comentario, String nomeUsuario) throws AvaliacaoInvalidaException {

        avaliacoes.get(nomeUsuario).setComentario(comentario);
    }

    /**
     * Coloca avaliaçao na midia em questão
     * 
     * @param nomeUsuario nome do usuario enviando a avaliaçao
     * @param avaliacao   avaliaçao enviada
     * @throws MidiaInvalidaException excecao caso a midia seje invalida
     */
    @Override
    public void colocarAvaliacao(String nomeUsuario, Avaliacao avaliacao) throws MidiaInvalidaException {
        avaliacoes.put(nomeUsuario, avaliacao);
    }

    /**
     * Método que calcula a nota media da midia
     * 
     * @return retorna a media da avaliação
     */
    @Override
    public double calcularNotaMedia() {
        return this.avaliacoes.values().stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }

    /**
     * Metodo para pegar as avaliaçoes feitas para determindada midia
     * 
     * @return retorna uma string com avaliaçoes e quem fez elas
     */
    @Override
    public String getAvaliacoes() {
        return avaliacoes.toString();
    }

    @Override
    public List<String> getAvaliadores() {
        List<String> nomes = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{(.*?)=\\{");
        Matcher matcher = pattern.matcher(getAvaliacoes());
        while (matcher.find()) {
            String nome = matcher.group(1);
            nomes.add(nome);
        }

        return nomes;
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

        return ("\n" + id + ";" + nome + ";" + data + ";S");
    }

    @Override
    public String getArquivo() {
        return arqSeries;
    }

        /**
     * Retorna a audiência associada a este objeto.
     * 
     * @return a audiência associada a este objeto.
     */
    public int getAudiencia() {
        return this.audiencia;
    }

    @Override
public void registrarAudienciaSeNecessario() {
    registrarAudiencia();
}

}