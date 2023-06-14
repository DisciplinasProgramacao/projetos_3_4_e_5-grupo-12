package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filme extends Midia implements Lancavel, IAssistivel {

    private int duracao;
    private static final String arqFilmes = "codigo/POO_Filmes.csv";
    private HashMap<String, Avaliacao> avaliacoes = new HashMap<>();
    private int audiencia = 0;

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
     * Esse é o construtor da classe filme para usar quando cadastrado manualmente
     * 
     * @param genero  Esse é o genero do filme, como terror ou comedia
     * @param nome    Esse é o nome do filme
     * @param idioma  Esse é o idioma do filme como portugues ou ingles
     * @param duracao Essa é a duração em minutos do filme
     * @throws MidiaInvalidaException propaga exceção se houver valores inválidos
     */
    public Filme(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);
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
     * Registra um aumento na audiência associada a este objeto.
     */
    public void registrarAudiencia() {
        this.audiencia++;
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

        return ("\n" + id + ";" + nome + ";" + data + ";" + duracao + ";F");
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

    @Override
    public String getArquivo() {
        return arqFilmes;
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

        @Override
    public boolean eTrailer() {
        return false;
    }

}