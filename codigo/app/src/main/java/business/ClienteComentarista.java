package business;

public class ClienteComentarista extends Cliente implements IComentarista {

    public ClienteComentarista(String nomeCompleto, String nomeDeUsuario, String senha) throws ClienteInvalidoException {
        super(nomeCompleto, nomeDeUsuario, senha);
    }

    @Override
    public void comentar(String comentario, Midia midia, String nomeUsuario) {

        midia.setComentario(comentario, nomeUsuario);
    }
    
}
