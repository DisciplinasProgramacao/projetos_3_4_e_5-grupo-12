package business;

import java.util.LinkedList;
import java.util.List;

public class Cliente implements ISalvavel {

    private String nomeDeUsuario;
    private String senha;
    private String nomeCompleto;
    private List<Serie> listaParaVer = new LinkedList<>();
    private List<Serie> listaJaVistas = new LinkedList<>();
    private List<Filme> filmesParaVer = new LinkedList<>();
    private List<Filme> filmesJaVistos = new LinkedList<>();

    /**
     * Cria um novo objeto Cliente com nome completo, nome de usuário e senha.
     * 
     * @param nomeCompleto  o nome completo do Cliente
     * @param nomeDeUsuario o nome de usuário do Cliente
     * @param senha         a senha do Cliente
     */
    public Cliente(String nomeCompleto, String nomeDeUsuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        setNomeDeUsuario(nomeDeUsuario);
        setSenha(senha);
        this.nomeDeUsuario = getNomeDeUsuario();
        this.senha = getSenha();
    }

    /**
     * Retorna o nome completo do cliente.
     *
     * @return o nome completo do cliente.
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Retorna o nome de usuário do cliente.
     *
     * @return o nome de usuário do cliente.
     */
    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    /**
     * Define o nome de usuário do cliente.
     *
     * @param nomeDeUsuario o novo nome de usuário do cliente.
     */
    public void setNomeDeUsuario(String nomeDeUsuario) {
        if (nomeDeUsuario != "") {
            this.nomeDeUsuario = nomeDeUsuario;
        }
    }

    /**
     * Retorna a senha do cliente.
     *
     * @return a senha do cliente.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do cliente.
     *
     * @param senha a nova senha do cliente.
     */
    public void setSenha(String senha) {
        if (senha != "") {
            this.senha = senha;
        }
    }

    /**
     * Retorna a lista de séries que o cliente deseja assistir.
     *
     * @return a lista de séries que o cliente deseja assistir.
     */
    public List<Serie> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Retorna a lista de séries que o cliente já assistiu.
     *
     * @return a lista de séries que o cliente já assistiu.
     */
    public List<Serie> getListaJaVista() {
        return this.listaJaVistas;
    }

    /**
     * Retorna a lista de filmes que o cliente deseja assistir.
     *
     * @return a lista de filmes que o cliente deseja assistir.
     */
    public List<Filme> getFilmesParaVer() {
        return this.filmesParaVer;
    }

    /**
     * Retorna a lista de filmes que o cliente já assistiu.
     *
     * @return a lista de filmes que o cliente já assistiu.
     */
    public List<Filme> getFilmesJaVistos() {
        return this.filmesJaVistos;
    }

    /**
     * Adiciona uma série na listaParaVer
     * 
     * @param serie Serie que se deseja adicionar na lista
     */
    public void adicionarNaLista(Serie serie) {
        if (!listaParaVer.contains(serie)) {
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

    /**
     * Adiciona uma série à lista de séries já vistas pelo cliente e registra a
     * audiência da série.
     *
     * @param serie a série que foi vista pelo cliente.
     */
    public void adicionarSerieVista(Serie serie) {
        listaJaVistas.add(serie);
        serie.registrarAudiencia();
    }

    /**
     * Remove uma série da lista de séries já vistas pelo cliente.
     *
     * @param serie a série que deve ser removida da lista.
     */
    public void retirarSerieVista(Serie serie) {
        listaJaVistas.remove(serie);
    }

    /**
     * Adiciona um filme à lista de filmes já vistos pelo cliente e registra sua
     * audiência.
     *
     * @param filme o filme que deve ser adicionado à lista.
     */
    public void adicionarFilmeVisto(Filme filme) {
        filmesJaVistos.add(filme);
        filme.registrarAudiencia();
    }

    /**
     * Método que remove um filme da lista de filmes já vistos pelo cliente.
     *
     * @param filme O objeto do tipo Filme que representa o filme a ser removido da
     *              lista de filmes já vistos.
     */
    public void retirarFilmeVisto(Filme filme) {
        filmesJaVistos.remove(filme);
    }

    /**
     * Método que adiciona uma série à lista de séries já vistas pelo cliente e
     * atribui uma nota para essa série.
     *
     * @param serie O objeto do tipo Serie que representa a série a ser adicionada à
     *              lista de séries já vistas.
     * @param nota  Um inteiro que representa a nota que será atribuída à série.
     */
    public void adicionarSerieVista(Serie serie, Integer nota) {
        listaJaVistas.add(serie);
        serie.registrarAudiencia();
    }

    /**
     * Adiciona um filme na lista de filmes já vistos pelo cliente, registrando a
     * nota dada pelo cliente e a audiência do filme.
     *
     * @param filme o objeto Filme a ser adicionado na lista de filmes já vistos
     * @param nota  a nota dada pelo cliente ao filme
     */
    public void adicionarFilmeVisto(Filme filme, Integer nota) {
        filmesJaVistos.add(filme);
        filme.registrarAudiencia();
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

    /**
     * Retorna uma representação em String do objeto Cliente.
     * 
     * @return uma String que contém o nome de usuario e senha
     */
    @Override
    public String toString() {
        return "{" +
                " nomeDeUsuario='" + getNomeDeUsuario() + "'" +
                ", senha='" + getSenha() + "'" +
                "}";
    }

    /**
     * Retorna uma String formatada contendo os dados do objeto.
     * 
     * @return uma String contendo o login, senha e nome
     */
    @Override
    public String getDadosString() {
        String login = getNomeDeUsuario();
        String senha = getSenha();
        String nome = getNomeCompleto();
        return ("\n" + nome + ";" + login + ";" + senha);
    }

}