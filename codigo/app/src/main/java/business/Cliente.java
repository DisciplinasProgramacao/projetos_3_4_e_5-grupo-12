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
     * @throws ClienteInvalidoException Exceção dos métodos set, caso algum valor
     *                                  for inválido
     */
    public Cliente(String nomeCompleto, String nomeDeUsuario, String senha) throws ClienteInvalidoException {
        setNomeCompleto(nomeCompleto);
        setNomeDeUsuario(nomeDeUsuario);
        setSenha(senha);
    }

    /**
     * Método para transformar um cliente em especialista
     * 
     * @param meuTipo Objeto IComentarista para alterar o valor do meuTipo
     * @throws ClienteInvalidoException propaga exceção se existir valor inválido
     */
    public void setMeuTipo(IComentarista meuTipo) throws ClienteInvalidoException {
        this.meuTipo = meuTipo;
    }

    /**
     * Método que retorna o valor do meuTipo
     * 
     * @return retorna valor do objeto IComentarista
     */
    public IComentarista getMeuTipo() {
        return this.meuTipo;
    }

    /**
     * Método para verificar se uma mídia está na lista ja vista
     * 
     * @param midia Mídia que se deseja buscar
     * @return true se achar a mídia, false se não achar
     */
    public boolean jaViu(Midia midia) {

        return listaJaVistas.contains(midia);
    }

    /**
     * Método para verificar se uma mídia está na lista para ver
     * 
     * @param midia Mídia que se deseja buscar
     * @return true se achar a mídia, false se não achar
     */
    public boolean querVer(Midia midia) {

        return listaParaVer.contains(midia);
    }

    /**
     * Método que chama um método de uma mídia específica para criar uma avaliação
     * 
     * @param nota  Nota da avaliação que será criada
     * @param midia Mídia que armazenará a avaliação
     * @return true se conseguir criar a avaliação, false se não conseguir
     * @throws AvaliacaoInvalidaException propaga a exceção se existir valores
     *                                    inválidos na avaliação
     * @throws MidiaInvalidaException     cria e propaga a exceção se o usuário
     *                                    ainda não assistiu a mídia
     */
    public boolean criarAvaliacao(float nota, Midia midia) throws AvaliacaoInvalidaException, MidiaInvalidaException {
        boolean permitido = false;

        if (querVer(midia)) {
            retirarDaLista(midia);
        }

        if ((this.listaJaVistas.contains(midia))) { // correto
            permitido = true;
            Avaliacao avaliacao = new Avaliacao(nota);
            midia.colocarAvaliacao(getNomeDeUsuario(), avaliacao);
            adicionarDataAssistida(LocalDate.now().toString());
        } else {
            throw new MidiaInvalidaException("Você ainda não assistiu essa mídia");
        }
        return permitido;
    }

    /**
     * Método para chamar o método de comentar da interface IComentarista
     * 
     * @param comentario Comentário que será inserido em uma avaliação
     * @param midia      Mídia em que o comentário será inserido
     * @throws AvaliacaoInvalidaException Propaga exceção se houver valores
     *                                    inválidos
     * @throws ClienteInvalidoException   Propaga exceção se houver valores
     *                                    inválidos
     */
    public void fazerComentario(String comentario, Midia midia)
            throws AvaliacaoInvalidaException, ClienteInvalidoException {
        if (meuTipo != null) {
            meuTipo.comentar(comentario, midia, getNomeDeUsuario());
        } else {
            System.out.println("Desculpe, apenas clientes especialistas podem comentar.");
        }
    }

    /**
     * Método de set do nome completo do usuário
     * 
     * @param nomeCompleto Novo nome completo do usuário
     * @throws ClienteInvalidoException Cria e propaga exceção se o nomeCompleto for
     *                                  vazio
     */
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
     * @throws ClienteInvalidoException Cria e propaga exceção se o nomeDeUsuario for
     *                                  vazio
     */
    public void setNomeDeUsuario(String nomeDeUsuario) throws ClienteInvalidoException {
        if (nomeDeUsuario.length() > 0) {
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
     * @throws ClienteInvalidoException Cria e propaga exceção se a senha for menor
     *                                  que 6 caracteres e n possuir letra
     *                                  maiúscula, minúscula e digito
     */
    public void setSenha(String senha) throws ClienteInvalidoException {
        if (checkString(senha) && senha.length() > 6)
            this.senha = senha;
        else
            throw new ClienteInvalidoException(
                    "A senha deve possuir pelo menos 6 caracteres, entre letras maiusculas, minusculas e numeros.");
    }

    /**
     * Retorna uma string com a lista de séries que o cliente deseja assistir.
     *
     * @return uma string com as séries que o cliente deseja assistir.
     */
    public String getListaParaVer() {
        return this.listaParaVer.toString();
    }

    /**
     * Retorna o tamanho da lista de mídias assistidas do cliente.
     *
     * @return o tamanho da lista de mídias assistidas do cliente.
     */
    public int getTamanhoListaJaVista() {
        return this.listaJaVistas.size();
    }

    /**
     * Retorna o tamanho da lista de mídias para ver do cliente.
     *
     * @return o tamanho da lista de mídias para ver do cliente.
     */
    public int getTamanhoListaParaVer() {
        return this.listaParaVer.size();
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
     * @return true se conseguir adicionar a midia ou false se não conseguir
     * @throws MidiaInvalidaException propaga exceção se houver valores inválidos
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
     * Retorna uma string com a lista de séries que o cliente já assistiu.
     *
     * @return uma string com a lista de séries que o cliente já assistiu.
     */
    public String getListaJaVista() {
        return this.listaJaVistas.toString();
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
     * Implementa o método da interface ISalvavel e retorna uma String formatada contendo os dados do objeto.
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

    /**
     * Método que valida uma string com as regras da senha 
     * @param str string que será testada 
     * @return true se a string for validada, false se falhar a verificação
     */
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

    /**
     * Adiciona uma data na lista de datas
     * @param data data que será adicionada na lista
     */
    public void adicionarDataAssistida(String data) {
        this.dataAssitida.add(data);
    }

    /**
     * Retorna a lista de datas
     * @return lista de datas
     */
    public List<String> getListaDataAssistida() {
        return this.dataAssitida;
    }

}