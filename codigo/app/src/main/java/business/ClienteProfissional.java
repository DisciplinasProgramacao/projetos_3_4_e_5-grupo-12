package business;

public class ClienteProfissional extends Cliente implements IComentarista {

    public ClienteProfissional(String nomeCompleto, String nomeDeUsuario, String senha)
            throws ClienteInvalidoException {
        super(nomeCompleto, nomeDeUsuario, senha);
    }

    @Override
    public void comentar(String comentario, Midia midia, String nomeUsuario)
            throws ClienteInvalidoException, AvaliacaoInvalidaException {
                midia.setComentario(comentario, nomeUsuario);
    }
    
}
