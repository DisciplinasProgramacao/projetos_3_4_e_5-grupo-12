package business;

public class ClienteInvalidoException extends Exception {

    public ClienteInvalidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar cliente";
    }


    //Falta mensagenm de erro no super, alem de ter que ser adicionado thrws em sets e nao no main
}
