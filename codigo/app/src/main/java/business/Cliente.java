package business;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cliente implements ISalvavel {

    private String nomeDeUsuario;
    private String senha;
    private String nomeCompleto;
    private List<Midia> listaParaVer = new LinkedList<>();
    private List<Midia> listaJaVistas = new LinkedList<>();
    private List<String> dataAssitida = new LinkedList<>();
    private IComentarista meuTipo = null;
    /**
     * Cria um novo objeto Cliente com nome completo, nome de usuário e senha.
     * 
     * @param nomeCompleto  o nome completo do Cliente
     * @param nomeDeUsuario o nome de usuário do Cliente
     * @param senha         a senha do Cliente
     * @throws ClienteInvalidoException Exceção dos métodos set, caso algum valor for inválido
     */
    public Cliente(String nomeCompleto, String nomeDeUsuario, String senha) throws ClienteInvalidoException {
        setNomeCompleto(nomeCompleto);
        setNomeDeUsuario(nomeDeUsuario);
        setSenha(senha);
    }

    /**
     * Método para transformar um cliente em especialista
     * @param meuTipo Objeto IComentarista para alterar o valor do meuTipo
     * @throws ClienteInvalidoException
     */
    public void setMeuTipo(IComentarista meuTipo) throws ClienteInvalidoException {
        this.meuTipo = meuTipo;
    }

    /**
     * Método que retorna o valor do meuTipo
     * @return retorna valor do objeto IComentarista
     */
    public IComentarista getMeuTipo() {
        return this.meuTipo;
    }

    /**
     * Método que chama um método de uma mídia específica para criar uma avaliação
     * @param nota Nota da avaliação que será criada
     * @param midia Mídia que armazenará a avaliação
     * @throws Exception Lança uma exceção qu
     */
    public boolean criarAvaliacao(float nota, Midia midia) throws AvaliacaoInvalidaException, MidiaInvalidaException {
        boolean permitido = false;
        
        if(!(getListaJaVista().contains(midia))) {
            permitido = true;
            Avaliacao avaliacao = new Avaliacao(nota);
            midia.colocarAvaliacao(getNomeDeUsuario(), avaliacao);
            adicionarDataAssistida(LocalDate.now().toString());
            adicionarMidiaVista(midia, nota);
        } 
        return permitido;
    }
    //REVISAR
    public void fazerComentario(String comentario, Midia midia) throws AvaliacaoInvalidaException, ClienteInvalidoException {
        if (meuTipo != null) {
            meuTipo.comentar(comentario, midia, getNomeDeUsuario());
        } else {
            System.out.println("Desculpe, apenas clientes especialistas podem comentar.");
        }
    }

    public void setNomeCompleto(String nomeCompleto) throws ClienteInvalidoException {
        if (!nomeCompleto.equals("")) {
            this.nomeCompleto = nomeCompleto;
        } else {
            throw new ClienteInvalidoException("O nome não pode ser vazio!");
        }
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
     * @throws ClienteInvalidoException
     */
    public void setNomeDeUsuario(String nomeDeUsuario) throws ClienteInvalidoException {
        if (nomeDeUsuario.length()>0) {
            this.nomeDeUsuario = nomeDeUsuario;
        } else {
            throw new ClienteInvalidoException("O nome de usuario não pode ser vazio!");
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
     * @throws ClienteInvalidoException
     */
    public void setSenha(String senha) throws ClienteInvalidoException {
        if (checkString(senha) || senha.length() > 6) 
            this.senha = senha;
        else
        throw new ClienteInvalidoException("A senha deve possuir pelo menos 6 caracteres, entre letras maiusculas, minusculas e numeros.");

    }

    /**
     * Retorna a lista de séries que o cliente deseja assistir.
     *
     * @return a lista de séries que o cliente deseja assistir.
     */
    public List<Midia> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Retorna a lista de séries que o cliente já assistiu.
     *
     * @return a lista de séries que o cliente já assistiu.
     */
    public List<Midia> getListaJaVista() {
        return this.listaJaVistas;
    }

    /**
     * Adiciona uma série na listaParaVer
     * 
     * @param serie Serie que se deseja adicionar na lista
     */
    public void adicionarListaParaVer(Midia midia) {
        if (!listaParaVer.contains(midia)) {
            listaParaVer.add(midia);
        }
    }

    /**
     * Remove uma série da listaParaVer
     * 
     * @param serie Serie que se deseja remover da lista
     */
    public void retirarDaLista(Midia midia) {
        listaParaVer.remove(midia);
    }

    /**
     * Adiciona uma série à lista de séries já vistas pelo cliente e registra a
     * audiência da série.
     *
     * @param midia a série que foi vista pelo cliente.
     * @throws Exception
     */
    public boolean adicionarMidiaVista(Midia midia) throws MidiaInvalidaException {

        if (!listaJaVistas.contains(midia)) {
            listaJaVistas.add(midia);
            midia.registrarAudiencia();
            return true;
        } else {
            return false;
        }

    }

    

    /**
     * Remove uma série da lista de séries já vistas pelo cliente.
     *
     * @param serie a série que deve ser removida da lista.
     */
    public void retirarMidiaVista(Midia midia) {
        listaJaVistas.remove(midia);
    }

    /**
     * Método que adiciona uma série à lista de séries já vistas pelo cliente e
     * atribui uma nota para essa série.
     *
     * @param serie O objeto do tipo Serie que representa a série a ser adicionada à
     *              lista de séries já vistas.
     * @param nota  Um inteiro que representa a nota que será atribuída à série.
     */
    public void adicionarMidiaVista(Midia midia, float nota) {
        listaJaVistas.add(midia);
        midia.registrarAudiencia();
    }

    /**
     * Filtra filmesParaVer e a filmesJaVistos por gênero
     * 
     * @param genero Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Midia> filtrarMidiaPorGenero(String genero) {
        List<Midia> listaMesmoGen = Stream.concat(listaParaVer.stream(), listaJaVistas.stream())
                .filter(m -> m.getGenero().equals(genero))
                .collect(Collectors.toList());
    
        return listaMesmoGen;
    }

    /**
     * Filtra filmesParaVer e filmesJaVistos por idioma
     * 
     * @param idioma Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Midia> filtrarMidiaPorIdioma(String idioma) {
        List<Midia> listaFiltrada = Stream.concat(listaParaVer.stream(), listaJaVistas.stream())
                .filter(m -> m.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    
        return listaFiltrada;
    }

    /**
     * Filtra a listaParaVer e a listaJaVista por quantidade de episódios
     * 
     * @param quantEpisodios Filtro que será utilizado no método
     * @return Uma lista com o resultado do filtro realizado
     */
    public List<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Midia> listaFiltrada = new LinkedList<>();
    
        List<Midia> listaFiltradaPorEpisodios = Stream.concat(listaParaVer.stream(), listaJaVistas.stream())
                .filter(m -> m instanceof Serie && ((Serie) m).getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    
        listaFiltrada.addAll(listaFiltradaPorEpisodios);
    
        return listaFiltrada;
    }

    /**
     * Registra que o cliente assistiu uma determinada série se ele não tenha visto
     * ainda
     * 
     * @param serie Série em que o cliente assitiu
     */
    public void registrarAudiencia(Midia midia) {
        if (listaJaVistas.size() == 0) {
            midia.registrarAudiencia();
            listaJaVistas.add(midia);
            for (int j = 0; j < listaParaVer.size(); j++) {

                if ((listaParaVer.get(0).equals(midia))) {
                    listaParaVer.remove(listaParaVer.get(0));
                }
            }
        } else {
            for (int i = 0; i < listaJaVistas.size(); i++) {

                if (!(listaJaVistas.get(i).equals(midia))) {
                    midia.registrarAudiencia();
                    listaJaVistas.add(midia);

                    for (int j = 0; j < listaParaVer.size(); j++) {

                        if ((listaParaVer.get(i).equals(midia))) {
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
    public String getDados() {
        String login = getNomeDeUsuario();
        String senha = getSenha();
        String nome = getNomeCompleto();
        return ("\n" + nome + ";" + login + ";" + senha);
    }

    private boolean checkString(String str) {
        char ch;
        boolean letraMaiuscula = false;
        boolean letraMinuscula = false;
        boolean num = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                num = true;
            } else if (Character.isUpperCase(ch)) {
                letraMaiuscula = true;
            } else if (Character.isLowerCase(ch)) {
                letraMinuscula = true;
            }
            if (num && letraMaiuscula && letraMinuscula)
                return true;
        }
        return false;
    }

    public void adicionarDataAssistida(String data) {
        this.dataAssitida.add(data);
    }

    public List<String> getListaDataAssistida() {
        return this.dataAssitida;
    }


    
}