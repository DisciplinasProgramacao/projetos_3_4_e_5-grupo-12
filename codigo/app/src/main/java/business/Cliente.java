package business;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer = new LinkedList<>();
    private List<Serie> listaJaVistas = new LinkedList<>();

    public Cliente(String nomeDeUsuario, String senha) {
        setNomeDeUsuario(nomeDeUsuario);
        setSenha(senha);
        this.nomeDeUsuario = getNomeDeUsuario();
        this.senha = getSenha();
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

    /**
     * Adiciona uma série na listaParaVer
     * 
     * @param serie Serie que se deseja adicionar na lista
     */
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
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
}