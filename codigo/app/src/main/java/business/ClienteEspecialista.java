package business;

public class ClienteEspecialista extends Cliente implements IComentarista {

    /**
     * Cria um objeto do tipo ClienteEspecialista
     * @param nomeCompleto nome do cliente
     * @param nomeDeUsuario nome de usuário do cliente
     * @param senha senha do cliente
     * @throws ClienteInvalidoException propaga exceção se houver valores inválidos
     */
    public ClienteEspecialista(String nomeCompleto, String nomeDeUsuario, String senha) throws ClienteInvalidoException {
        super(nomeCompleto, nomeDeUsuario, senha);
    }


    /**
     * Implementa o método da interface IComentarista e chama o método da mídia para setar o comentário
     * @param comentario comentário da mídia
     * @param midia mídia que armazenará o comentário
     * @param nomeusuario nome de usuário que realizou o comentário
     * @throws AvaliacaoInvalidaException propaga a exceção se houver valores inválidos
     */
    @Override
    public void comentar(String comentario, Midia midia, String nomeUsuario) throws AvaliacaoInvalidaException{

        midia.setComentario(comentario, nomeUsuario);
    }
    
}
