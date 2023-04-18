package business;

public class Serie {

    private static int cont = 1;
    private int id;
    private String dataLancamento;
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia = 0;
    static final String[] generos = new String[] { "comedia", "terror", "romance" };

    public Serie(String genero, String nome, String idioma, int quantidadeEpisodios) {

        this.id = Serie.cont++;
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

        if (quantidadeEpisodios > 0) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        } else {
            this.quantidadeEpisodios = 1;
        }

    }

    public Serie(int id, String nome, String dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;

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

    public String getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public int getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(int audiencia) {
        this.audiencia = audiencia;
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
                " genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                ", quantidadeEpisodios='" + getQuantidadeEpisodios() + "'" +
                ", audiencia='" + getAudiencia() + "'" +
                "}";
    }

    public void registrarAudiencia() {
        this.audiencia++;
    }

}
