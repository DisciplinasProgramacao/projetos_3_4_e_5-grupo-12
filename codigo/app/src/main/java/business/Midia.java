package business;
import java.util.Random;
public abstract class Midia {
    private static int cont = 1;
    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private String dataLancamento;
    private int audiencia = 0;
    private static final String[] generos = new String[] { "comedia", "terror", "romance" };
    private static final String[] idiomas = new String[] { "portugues", "ingles", "espanhol" };

    public Midia(String genero, String nome, String idioma) {

        this.id = Midia.cont++;
        boolean generoValido = false;
        for (int i = 0; i < generos.length; i++) {
            if (genero.equals(generos[i])) {
                generoValido = true;
            }
        }

        if (generoValido) {
            this.genero = genero;
        } else {
            this.genero = "Sem genero";
        }

        this.nome = nome;
        this.idioma = idioma;

    }
    
    // Construtor para a criacao de midias durante leitura de dados
    public Midia(int id, String nome) {
        Random random = new Random();
        this.id = id;
        this.nome = nome;
        this.genero = generos[random.nextInt(2)];
        this.idioma = idiomas[random.nextInt(2)];
    }

    public int getId(){
        return this.id;
    }

    public int getdata() {
        return 0;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
    }

    public String getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                " genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                ", audiencia='" + getAudiencia() + "'" +
                "}";
    }

    public void registrarAudiencia() {
        this.audiencia++;
    }
}