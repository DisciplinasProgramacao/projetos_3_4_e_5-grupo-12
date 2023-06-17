package business;

import java.time.LocalDate;
import java.util.HashMap;
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
    private HashMap<Integer, String> dataAssitida = new HashMap<Integer, String>();
    private IComentarista meuTipo = null;
    private ClienteProfissional profissional = null;
    private static final String arqClientes = "codigo/POO_Espectadores.csv";

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
    public void criarAvaliacao(float nota, IAssistivel midia)
            throws AvaliacaoInvalidaException, MidiaInvalidaException {

        if ((this.listaJaVistas.contains((Midia) midia))) {
            Avaliacao avaliacao = new Avaliacao(nota);
            midia.colocarAvaliacao(getNomeDeUsuario(), avaliacao);
        } else {
            throw new MidiaInvalidaException("Você ainda não assistiu essa mídia");
        }
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
    public void fazerComentario(String comentario, IAssistivel midia)
            throws AvaliacaoInvalidaException, ClienteInvalidoException {
        if (meuTipo != null) {
            meuTipo.comentar(comentario, midia, getNomeDeUsuario());
        } else {
            throw new ClienteInvalidoException("Somente clientes especialistas podem comentar!");
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
     * @throws ClienteInvalidoException Cria e propaga exceção se o nomeDeUsuario
     *                                  for
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
    public void adicionarListaParaVer(IAssistivel midia) throws MidiaInvalidaException {
        if (!(listaParaVer.contains((Midia) midia)) && !(listaJaVistas.contains((Midia) midia))) {
            listaParaVer.add((Midia) midia);
        } else {
            throw new MidiaInvalidaException("Você ja assistiu ou já adicionou essa midia!");
        }
    }

    /**
     * Remove uma série da listaParaVer
     * 
     * @param serie Serie que se deseja remover da lista
     */
    public void retirarDaLista(IAssistivel midia) {
        listaParaVer.remove((Midia) midia);
    }

    /**
     * Adiciona uma série à lista de séries já vistas pelo cliente e registra a
     * audiência da série.
     *
     * @param midia a série que foi vista pelo cliente.
     * @return true se conseguir adicionar a midia ou false se não conseguir
     * @throws MidiaInvalidaException propaga exceção se houver valores inválidos
     */
    public void adicionarMidiaVista(Midia midia) throws MidiaInvalidaException {


        if(listaParaVer.contains(midia)){
            retirarDaLista((IAssistivel)midia);
        }
        if (!listaJaVistas.contains(midia)) {
            adicionarDataAssistida(midia.getId(), LocalDate.now().toString());
            listaJaVistas.add(midia);
            midia.registrarAudienciaSeNecessario();
        } else {
            throw new MidiaInvalidaException("Voce já assistiu essa midia");
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
        List<Midia> listaFiltrada = Stream.concat((((List<Midia>) listaParaVer).stream()), listaJaVistas.stream())
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
            midia.registrarAudienciaSeNecessario();
            listaJaVistas.add(midia);
            for (int j = 0; j < listaParaVer.size(); j++) {

                if ((listaParaVer.get(0).equals(midia))) {
                    listaParaVer.remove(listaParaVer.get(0));
                }
            }
        } else {
            for (int i = 0; i < listaJaVistas.size(); i++) {

                if (!(listaJaVistas.get(i).equals(midia))) {
                    midia.registrarAudienciaSeNecessario();
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
     * Implementa o método da interface ISalvavel e retorna uma String formatada
     * contendo os dados do objeto.
     * 
     * @return uma String contendo o login, senha e nome
     */
    @Override
    public String getDados() {
        String result = "";
        String login = getNomeDeUsuario();
        String senha = getSenha();
        String nome = getNomeCompleto();

        result =  ("\n" + nome + ";" + login + ";" + senha);
        if(getMeuTipoProfissional() != null) {
            result += ";P";
        }
        return result;
    }

    /**
     * Método que valida uma string com as regras da senha
     * 
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
     * 
     * @param data data que será adicionada na lista
     */
    public void adicionarDataAssistida(int idMidia, String data) {
        this.dataAssitida.put(idMidia, data);
    }

    /**
     * Retorna a lista de datas
     * 
     * @return lista de datas
     */
    public HashMap<Integer, String> getListaDataAssistida() {
        return this.dataAssitida;
    }

    public ClienteProfissional getMeuTipoProfissional() {
        return this.profissional;
    }

    public void setMeuTipoProfissional(ClienteProfissional cliente) {
        this.profissional = cliente;
    }

    @Override
    public String getArquivo() {
        return arqClientes;
    }

    public String getDadosAudiencia() {
        String dados = "";
        for (Midia m : listaJaVistas) {
            String tipo = "A";
            dados += getDadosMidia(m, tipo);
        }
        for (Midia m : listaParaVer) {
            String tipo = "F";
            dados += getDadosMidia(m, tipo);
        }
        return dados;
    }

    private String getDadosMidia(Midia m, String tipo) {
        String data = this.dataAssitida.getOrDefault(m.getId(), "semData");
        double nota = m.getNotaCliente(this.nomeDeUsuario);
        String result = this.nomeDeUsuario + ";" + tipo + ";" + m.getId();
        if (nota != -1 || !data.equals("semData")) {
            result += ";" + data;
            if (nota != -1) {
                result += ";" + nota;
            }
        }
        return result + "\n";
    }

}