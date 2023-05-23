package business;

public class ClienteComentarista implements IComentarista {

    @Override
    public void comentar(String comentario) {
        System.out.println("Comentário do cliente especialista: " + comentario);
    }
    
}
