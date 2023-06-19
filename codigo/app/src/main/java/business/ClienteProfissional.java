package business;

public class ClienteProfissional extends Cliente implements IComentarista {

    /**
    * Constrói um objeto ClienteProfissional com as informações fornecidas.
    *
    * @param nomeCompleto    o nome completo do cliente profissional
    * @param nomeDeUsuario   o nome de usuário do cliente profissional
    * @param senha           a senha do cliente profissional
    * @throws ClienteInvalidoException se ocorrer um erro ao criar o cliente profissional
    */
    public ClienteProfissional(String nomeCompleto, String nomeDeUsuario, String senha)
            throws ClienteInvalidoException {
        super(nomeCompleto, nomeDeUsuario, senha);
    }

    /**
    * Comenta em uma mídia especificada.
    *
    * @param comentario   o comentário a ser feito
    * @param midia        a mídia em que será feito o comentário
    * @param nomeUsuario  o nome de usuário do cliente que está fazendo o comentário
    * @throws ClienteInvalidoException     se ocorrer um erro com o cliente
    * @throws AvaliacaoInvalidaException   se ocorrer um erro ao definir o comentário na mídia
    */
    @Override
    public void comentar(String comentario, IAssistivel midia, String nomeUsuario)
            throws ClienteInvalidoException, AvaliacaoInvalidaException {
        midia.setComentario(comentario, nomeUsuario);
    }

}
