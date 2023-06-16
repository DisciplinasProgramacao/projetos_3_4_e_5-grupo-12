package business;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public abstract class Midia implements ISalvavel {

    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private LocalDate dataLancamento;
    private static final String[] generos = new String[] { "comedia", "terror", "romance", "acao", "anime", "aventura",
            "documentario", "drama", "policial", "suspense" };
    private static final String[] idiomas = new String[] { "portugues", "ingles", "espanhol" };

    private static Random random = new Random();
    private Lancavel lancamento = null;

    /**
     * Construtor da classe Midia.
     * 
     * @param genero o gênero da mídia.
     * @param nome   o nome da mídia.
     * @param idioma o idioma da mídia.
     * @throws MidiaInvalidaException Excecao para caso a midia seje invalida
     */
    public Midia(String genero, String nome, String idioma) throws MidiaInvalidaException {

        setId(random.nextInt(Integer.MAX_VALUE));
        setGenero(genero);
        setNome(nome);
        setIdioma(idioma);
        setDataLancamento(LocalDate.now());
    }

    /**
     * Construtor da classe Midia que recebe um id, um nome e uma data para criar um
     * objeto
     * Midia.
     * O genero e idioma são selecionados aleatoriamente entre as opções disponíveis
     * na classe.
     * 
     * @param id           Identificador numérico único da Midia.
     * @param nome         Nome da Midia.
     * @param dataLacameto data de lancamento da midia
     * @throws MidiaInvalidaException Excecao para caso a midia seje invalida
     */
    public Midia(int id, String nome, LocalDate dataLancamento) throws MidiaInvalidaException {
        setId(id);
        setNome(nome);
        setGenero(generos[random.nextInt(generos.length - 1)]);
        setIdioma(idiomas[random.nextInt(idiomas.length - 1)]);
        setDataLancamento(dataLancamento);
    }

    /**
     * esse método da set no Id da midia
     * 
     * @param id esse é o id que sera enviado para trocar
     * @throws MidiaInvalidaException Excecao para caso o id da midia enviado esteje
     *                                vazio
     */
    public void setId(int id) throws MidiaInvalidaException {
        if (id > 0) {
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

    public List<String> getAvaliadores() {
        return null;
    }

    /**
     * Define um novo gênero para este objeto.
     * 
     * @param genero o novo valor do gênero a ser definido.
     * @throws MidiaInvalidaException Excecao para caso o genero da midia esteje
     *                                incorreto
     */
    public void setGenero(String genero) throws MidiaInvalidaException {
        boolean generoValido = false;

        for (String g : generos) {
            if (g.equals(genero)) {
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
     * @throws SerieInvalidaException excecao caso o idioma esteje incorreto
     */
    public void setIdioma(String idioma) throws MidiaInvalidaException {
        boolean idiomaValido = false;
        for (String i : idiomas) {
            if (i.equals(idioma)) {
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

                "}";
    }

    public abstract void registrarAudienciaSeNecessario();

    /**
     * pega os dados da midia em questao
     * 
     * @param nomeUsuario nome do usuario
     * @param tipo        tipo da midia
     * @return retorna os dados em formato string
     */
    public String getDados(String nomeUsuario, String tipo) {
        int id = getId();
        return ("\n" + nomeUsuario + ";" + tipo + ";" + id);
    }

    public void setLancamento(Lancavel lancamento) {
        this.lancamento = lancamento;
    }

    public Lancavel getLancamento() {
        return this.lancamento;
    }

    public abstract double calcularNotaMedia();

    public abstract int getAudiencia();

    public abstract boolean eTrailer();

    public abstract String getTipoMidia();

    public abstract double getNotaCliente(String nomeUsuario);

}