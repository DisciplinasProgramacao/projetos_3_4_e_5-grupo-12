package business;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
    
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer = new LinkedList<>();
    private List<Serie> listaJaVistas = new LinkedList<>();


    public Cliente(String nomeDeUsuario, String senha, List<Serie> listaParaVer, List<Serie> listajaVistas) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = listaParaVer;
        this.listaJaVistas = listajaVistas;
    }

    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarDaLista(Serie serie) {
        listaParaVer.remove(serie);
    }

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

    //diferenciar por tostring exemplo

    public List<Serie> filtrarPorIdioma (String idioma){

        List<Serie> listaFiltrada = new LinkedList<>();

        for(int i=0; i< listaParaVer.size(); i++){
            if(listaParaVer.get(i).getIdioma().equals(idioma)){
                listaFiltrada.add(listaParaVer.get(i));
            }
        }

        for(int i=0; i< listaJaVistas.size(); i++){
            if(listaJaVistas.get(i).getIdioma().equals(idioma)){
                listaFiltrada.add(listaJaVistas.get(i));
            }
        }

        return listaFiltrada;
    }

    public List<Serie> filtrarPorQtdEpisodios (int quantEpisodios){

        List<Serie> listaFiltrada = new LinkedList<>();

        for(int i=0; i< listaParaVer.size(); i++){
            if(listaParaVer.get(i).getQuantidadeEpisodios() == quantEpisodios){
                listaFiltrada.add(listaParaVer.get(i));
            }
        }

        for(int i=0; i< listaJaVistas.size(); i++){
            if(listaJaVistas.get(i).getQuantidadeEpisodios() == quantEpisodios){
                listaFiltrada.add(listaJaVistas.get(i));
            }
        }

        return listaFiltrada;
    }

    public void registrarAudiencia(Serie serie){

        
        for(int i =0; i < listaJaVistas.size(); i++){

            if(!(listaJaVistas.get(i).equals(serie))){
                serie.registrarAudiencia();
                listaJaVistas.add(serie);

                for(int j=0; j< listaParaVer.size(); j++){
            
                    if((listaParaVer.get(i).equals(serie))){
                        listaParaVer.remove(listaParaVer.get(i));
                    }
                }
            }
        }

    }
}
