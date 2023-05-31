package business;

public interface IComentarista {

    /**
     * Método para um usuário comentar uma mídia 
     * @param comentario comentário realizado pelo cliente
     * @param midia mídia que irá armazenar o comentário
     * @param nomeUsuario nome de usuário que realizou o comentário
     * @throws ClienteInvalidoException propaga exceção se houver valores inválidos
     * @throws AvaliacaoInvalidaException propaga exceção se houver valores inválidos
     */
    public void comentar(String comentario, Midia midia, String nomeUsuario) throws ClienteInvalidoException, AvaliacaoInvalidaException;

}
