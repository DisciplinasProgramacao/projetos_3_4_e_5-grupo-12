package business;

public class ClienteEspecialista extends Cliente implements IComentarista {

    public ClienteEspecialista(String nomeCompleto, String nomeDeUsuario, String senha) throws ClienteInvalidoException {
        super(nomeCompleto, nomeDeUsuario, senha);
    }

    @Override
    public void comentar(String comentario, Midia midia, String nomeUsuario) throws AvaliacaoInvalidaException{

        midia.setComentario(comentario, nomeUsuario);
    }
    
}
