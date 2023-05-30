package business;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public abstract class Midia implements ISalvavel {

    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private LocalDate dataLancamento;
    private int audiencia = 0;
    private static final String[] generos = new String[] { "comedia", "terror", "romance", "acao", "anime", "aventura", "documentario", "drama", "policial", "suspense"};
    private static final String[] idiomas = new String[] { "portugues", "ingles", "espanhol" };
    private HashMap<String, Avaliacao> avaliacoes = new HashMap<>();
    private static Random random = new Random();

    /**
     * Construtor da classe Midia.
     * 
     * @param genero o gênero da mídia.
     * @param nome   o nome da mídia.
     * @param idioma o idioma da mídia.
     * @throws SerieInvalidaException
     * @throws MidiaInvalidaException
     */
    public Midia(String genero, String nome, String idioma) throws MidiaInvalidaException {

        setId(random.nextInt(Integer.MAX_VALUE));
        setGenero(genero);
        setNome(nome);
        setIdioma(idioma);
        setDataLancamento(LocalDate.now());
    }

    /**
     * Construtor da classe Midia que recebe um id e um nome para criar um objeto
     * Midia.
     * O genero e idioma são selecionados aleatoriamente entre as opções disponíveis
     * na classe.
     * 
     * @param id   Identificador numérico único da Midia.
     * @param nome Nome da Midia.
     * @throws MidiaInvalidaException
     */
    public Midia(int id, String nome, LocalDate dataLancamento) throws MidiaInvalidaException {
        setId(id);
        setNome(nome);
        setGenero(generos[random.nextInt(generos.length-1)]);
        setIdioma(idiomas[random.nextInt(idiomas.length-1)]);
        setDataLancamento(dataLancamento);
    }

    public boolean getNotaAvaliacao(String nomeUsuario) {
        System.out.println(avaliacoes.get(nomeUsuario));
        if(avaliacoes.containsKey(nomeUsuario)) {
            return false;
        } else {
            return true;
        }
    }

    public void setId(int id) throws MidiaInvalidaException {
        if(id>0){
            this.id = id;
        } else {
            throw new MidiaInvalidaException("ID da midia inválida");
        }
    }

    /**
     * Retorna o id da mídia.
     * 
     * @return o id da mídia.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna o gênero da mídia.
     * 
     * @return o gênero da mídia.
     */
    public String getGenero() {
        return this.genero;
    }

    /**
     * Define um novo gênero para este objeto.
     * 
     * @param genero o novo valor do gênero a ser definido.
     * @throws MidiaInvalidaException
     */
    //perguntar como faz a excecao aqui
    public void setGenero(String genero) throws MidiaInvalidaException {
        boolean generoValido = false;

        for(String g : generos) {
            if(g.equals(genero)){
                this.genero = genero;
                generoValido = true;
                break;
            } 
        }

        if (!generoValido) {
            throw new MidiaInvalidaException("Gênero inválido: " + genero);
        }
    }

    /**
     * Retorna o idioma associado a este objeto.
     * 
     * @return o idioma associado a este objeto.
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * Define um novo idioma para este objeto, se a string de entrada tiver um
     * comprimento maior que zero.
     * 
     * @param idioma o novo valor do idioma a ser definido.
     * @throws SerieInvalidaException
     */
    public void setIdioma(String idioma) throws MidiaInvalidaException {
        boolean idiomaValido = false;
        for(String i : idiomas) {
            if(i.equals(idioma)){
                this.idioma = idioma;
                idiomaValido = true;
                break;
            } 
        }
        if (!idiomaValido) {
            throw new MidiaInvalidaException("Idioma inválido: " + idioma);
        }
    }

    /**
     * Retorna a audiência associada a este objeto.
     * 
     * @return a audiência associada a este objeto.
     */
    public int getAudiencia() {
        return this.audiencia;
    }

    /**
     * Retorna a data de lançamento associada a este objeto.
     * 
     * @return a data de lançamento associada a este objeto.
     */
    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    /**
     * Define uma nova data de lançamento para este objeto.
     * 
     * @param dataLancamento a nova data de lançamento a ser definida.
     */
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Retorna o nome associado a este objeto.
     * 
     * @return o nome associado a este objeto.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define um novo nome para este objeto.
     * 
     * @param nome o novo nome a ser definido.
     * @throws MidiaInvalidaException
     */
    public void setNome(String nome) throws MidiaInvalidaException {
        if (nome.length() > 0) {
            this.nome = nome;
        } else {
            throw new MidiaInvalidaException("O nome da midia não pode ser vazio!");
        }
    }

    /**
     * Retorna uma representação em String do objeto Midia.
     * 
     * @return uma String que contém o nome, id, genero, idioma e audiencia.
     */
    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", id='" + getId() + "'" +
                ", genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                ", audiencia='" + getAudiencia() + "'" +
                "}";
    }

    /**
     * Registra um aumento na audiência associada a este objeto.
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    public void setComentario(String comentario, String nomeUsuario) throws AvaliacaoInvalidaException {

       avaliacoes.get(nomeUsuario).setComentario(comentario);
    }

    
    public String getDados(String nomeUsuario, String tipo) {
        int id = getId();
        return ("\n" + nomeUsuario + ";" + tipo + ";" + id);
    }

    public void colocarAvaliacao(String nomeUsuario, Avaliacao avaliacao) throws MidiaInvalidaException {
        avaliacoes.put(nomeUsuario, avaliacao);
    }

    public double calcularNotaMedia() {
        return this.avaliacoes.values().stream()
            .mapToDouble(Avaliacao::getNota)
            .average()
            .orElse(0.0);
    }

    public String getAvaliacoes () {
        return avaliacoes.toString();
    }

}