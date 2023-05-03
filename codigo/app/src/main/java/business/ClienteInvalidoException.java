package business;

public class ClienteInvalidoException extends Exception {
   

    public ClienteInvalidoException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar cliente";
    }

}
