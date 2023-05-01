package business;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

    private String nomeDeUsuario;
    private String senha;
    private String nomeCompleto;
    private List<Serie> listaParaVer = new LinkedList<>();
    private List<Serie> listaJaVistas = new LinkedList<>();
    private List<Filme> filmesParaVer = new LinkedList<>();
    private List<Filme> filmesJaVistos = new LinkedList<>();

    public Cliente(String nomeCompleto, String nomeDeUsuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        setNomeDeUsuario(nomeDeUsuario);
        setSenha(senha);
        this.nomeDeUsuario = getNomeDeUsuario();
        this.senha = getSenha();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        if (nomeDeUsuario != "") {
            this.nomeDeUsuario = nomeDeUsuario;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != "") {
            this.senha = senha;
        }
    }

    public List<Serie> getListaParaVer() {
        return this.listaParaVer;
    }

    public List<Serie> getListaJaVista() {
        return this.listaJaVistas;
    }

    public List<Filme> getFilmesParaVer() {
        return this.filmesParaVer;
    }

    public List<Filme> getFilmesJaVistos() {
        return this.filmesJaVistos;
    }

    /**
     * Adiciona uma série na listaParaVer
     * 
     * @param serie Serie que se deseja adicionar na lista
     */
    public void adicionarNaLista(Serie serie) {
        if(!listaParaVer.contains(serie)){
            listaParaVer.add(serie);
        }
    }

     /**
     * Adiciona um filme em filmesParaVer
     * 
     * @param filme Filme que se deseja adicionar na lista
     */
    public void adicionarFilmeParaVer(Filme filme) {
        if (!filmesParaVer.contains(filme)) {
            filmesParaVer.add(filme);
        }
    }

     /**
     * Remove um filme de filmesParaVer
     * 
     * @param filme Filme que se deseja remover da lista
     */
    public void removerFilmeParaVer(Filme filme) {
        filmesParaVer.remove(filme);
    }

    /**
     * Remove uma série da listaParaVer
     * 
     * @param serie Serie que se deseja remover da lista
     */
    public void retirarDaLista(Serie serie) {
        listaParaVer.remove(serie);
    }

    public void adicionarSerieVista(Serie serie) {
        listaJaVistas.add(serie);
        serie.registrarAudiencia();
    }

    public void retirarSerieVista(Serie serie) {
        listaJaVistas.remove(serie);
    }

    public void adicionarFilmeVisto(Filme filme) {
        filmesJaVistos.add(filme);
        filme.registrarAudiencia();
    }

    public void retirarFilmeVisto(Filme filme) {
        filmesJaVistos.remove(filme);
    }

    /**
     * Filtra filmesParaVer e a filmesJaVistos por gênero
     * 
     * @param genero Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Filme> filtrarFilmePorGenero(String genero) {

        List<Filme> listaMesmoGen = new LinkedList<Filme>();
        for (int i = 0; i < filmesParaVer.size(); i++) {
            if (this.filmesParaVer.get(i).getGenero().equals(genero)) {
                listaMesmoGen.add(filmesParaVer.get(i));
            }
        }
        for (int i = 0; i < filmesJaVistos.size(); i++) {
            if (this.filmesJaVistos.get(i).getGenero().equals(genero)) {
                listaMesmoGen.add(filmesJaVistos.get(i));
            }
        }

        return listaMesmoGen;
    }

    /**
     * Filtra filmesParaVer e filmesJaVistos por idioma
     * 
     * @param idioma Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Filme> filtrarFilmePorIdioma(String idioma) {

        List<Filme> listaFiltrada = new LinkedList<>();

        for (int i = 0; i < filmesParaVer.size(); i++) {
            if (filmesParaVer.get(i).getIdioma().equals(idioma)) {
                listaFiltrada.add(filmesParaVer.get(i));
            }
        }

        for (int i = 0; i < filmesJaVistos.size(); i++) {
            if (filmesJaVistos.get(i).getIdioma().equals(idioma)) {
                listaFiltrada.add(filmesJaVistos.get(i));
            }
        }

        return listaFiltrada;
    }

    /**
    * Filtra a listaParaVer e a listaJaVista por gênero
    * 
    * @param genero Filtro que será utilizado no método
    * @return Uma lista com o resultado do filtro realizado
    */
    public List<Serie> filtrarPorGenero(String genero) {

        List<Serie> listaMesmoGen = new LinkedList<Serie>();
        for (int i = 0; i < listaParaVer.size(); i++) {
            if (this.listaParaVer.get(i).getGenero().equals(genero)) {
                listaMesmoGen.add(listaParaVer.get(i));
            }
        }
        for (int i = 0; i < listaJaVistas.size(); i++) {
            if (this.listaJaVistas.get(i).getGenero().equals(genero)) {
                listaMesmoGen.add(listaJaVistas.get(i));
            }
        }

        return listaMesmoGen;
    }


    /**
     * Filtra a listaParaVer e a listaJaVista por idioma
     * 
     * @param idioma Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Serie> filtrarPorIdioma(String idioma) {

        List<Serie> listaFiltrada = new LinkedList<>();

        for (int i = 0; i < listaParaVer.size(); i++) {
            if (listaParaVer.get(i).getIdioma().equals(idioma)) {
                listaFiltrada.add(listaParaVer.get(i));
            }
        }

        for (int i = 0; i < listaJaVistas.size(); i++) {
            if (listaJaVistas.get(i).getIdioma().equals(idioma)) {
                listaFiltrada.add(listaJaVistas.get(i));
            }
        }

        return listaFiltrada;
    }


    /**
     * Filtra a listaParaVer e a listaJaVista por quantidade de episódios
     * 
     * @param quantEpisodios Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {

        List<Serie> listaFiltrada = new LinkedList<>();

        for (int i = 0; i < listaParaVer.size(); i++) {
            if (listaParaVer.get(i).getQuantidadeEpisodios() == quantEpisodios) {
                listaFiltrada.add(listaParaVer.get(i));
            }
        }

        for (int i = 0; i < listaJaVistas.size(); i++) {
            if (listaJaVistas.get(i).getQuantidadeEpisodios() == quantEpisodios) {
                listaFiltrada.add(listaJaVistas.get(i));
            }
        }

        return listaFiltrada;
    }

    /**
     * Registra que o cliente assistiu uma determinada série se ele não tenha visto
     * ainda
     * 
     * @param serie Série em que o cliente assitiu
     */
    public void registrarAudiencia(Serie serie) {
        if (listaJaVistas.size() == 0) {
            serie.registrarAudiencia();
            listaJaVistas.add(serie);
            for (int j = 0; j < listaParaVer.size(); j++) {

                if ((listaParaVer.get(0).equals(serie))) {
                    listaParaVer.remove(listaParaVer.get(0));
                }
            }
        } else {
            for (int i = 0; i < listaJaVistas.size(); i++) {

                if (!(listaJaVistas.get(i).equals(serie))) {
                    serie.registrarAudiencia();
                    listaJaVistas.add(serie);

                    for (int j = 0; j < listaParaVer.size(); j++) {

                        if ((listaParaVer.get(i).equals(serie))) {
                            listaParaVer.remove(listaParaVer.get(i));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "{" +
                " nomeDeUsuario='" + getNomeDeUsuario() + "'" +
                ", senha='" + getSenha() + "'" +
                "}";
    }

    

}