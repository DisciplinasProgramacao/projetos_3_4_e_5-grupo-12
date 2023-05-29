package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

public abstract class Midia implements ISalvavel {

    private float notaMedia;
    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private String dataLancamento;
    private LocalDate dataAssistida;
    private int audiencia = 0;
    private static final String[] generos = new String[] { "comedia", "terror", "romance" };
    private static final String[] idiomas = new String[] { "portugues", "ingles", "espanhol" };
    private static HashMap<String, Avaliacao> avaliacoes = new HashMap<>(); // (id cliente / id Serie)
    private static Random random = new Random();

    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataAtual = LocalDate.now();

    public LocalDate getDataAssistida() {
        return this.dataAssistida;
    }

    public void setDataAssistida(LocalDate dataAssistida) {
        this.dataAssistida = dataAssistida;
    }

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

        this.id = random.nextInt(Integer.MAX_VALUE);
        setGenero(genero);
        setNome(nome);
        setIdioma(idioma);
        setDataLancamento(dataAtual.format(formatoData).toString());
    }


    public Avaliacao getAvaliacoes(String nomeUsuario) {
        return avaliacoes.get(nomeUsuario);
    }

    public Float getNotaAvaliacao(String nomeUsuario) {
        Avaliacao avaliacao = avaliacoes.get(nomeUsuario);
        if (avaliacao != null) {
            return avaliacao.getNota();
        } else {
            return -1F;
        }
    }

    public LocalDate getDataAvaliacao(String nomeUsuario) {



        Avaliacao avaliacao = avaliacoes.get(nomeUsuario);
        if(avaliacao != null) {
            return avaliacao.getData();
        } else {
            return LocalDate.now();
        }
    }


    /**
     * Construtor da classe Midia que recebe um id e um nome para criar um objeto
     * Midia.
     * O genero e idioma são selecionados aleatoriamente entre as opções disponíveis
     * na classe.
     * 
     * @param id   Identificador numérico único da Midia.
     * @param nome Nome da Midia.
     */
    public Midia(int id, String nome) {
        Random random = new Random();
        this.id = id;
        setNome(nome);
        this.genero = generos[random.nextInt(2)];
        this.idioma = idiomas[random.nextInt(2)];
    }

    /**
     * Retorna a nota associada a este objeto.
     *
     * @return a nota associada a este objeto.
     */
    public float getNotaMedia() {
        return this.notaMedia;
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
     * Retorna o valor inteiro 0.
     * 
     * @return o valor inteiro 0.
     */
    public int getdata() {
        return 0;
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

    //  if (!idioma.equals("ingles") && !idioma.equals("portugues") && !idioma.equals("espanhol")) {
    //     System.out.println("O idioma deve ser 'portugues', 'ingles' ou 'espanhol'");
    //     throw new SerieInvalidaException();
    // }
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
     * Define um novo valor de audiência para este objeto.
     * 
     * @param audiencia o novo valor da audiência a ser definido.
     */
    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    /**
     * Retorna a data de lançamento associada a este objeto.
     * 
     * @return a data de lançamento associada a este objeto.
     */
    public String getDataLancamento() {
        return this.dataLancamento;
    }

    /**
     * Define uma nova data de lançamento para este objeto.
     * 
     * @param dataLancamento a nova data de lançamento a ser definida.
     */
    public void setDataLancamento(String dataLancamento) {
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
     */
    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
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

    public void setComentario(String comentario, String nomeUsuario) {

       avaliacoes.get(nomeUsuario).setComentario(comentario);
    }


    //mudar pra cliente
    public void adicionarAvaliacao(Float nota, String nomeUsuario) {

        Avaliacao avaliacao = new Avaliacao(nota); // tirar isso e colocar em clietne
        avaliacoes.put(nomeUsuario, avaliacao);
    }

    
    public String getDadosString(String nomeUsuario, String tipo) {
        int id = getId();
        LocalDate data = getAvaliacoes(nomeUsuario).getData();

        return ("\n" + nomeUsuario + ";" + tipo + ";" + id + ";" + data);
    }
}