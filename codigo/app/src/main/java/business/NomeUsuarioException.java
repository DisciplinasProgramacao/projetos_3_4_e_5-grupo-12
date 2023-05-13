package business;

public class NomeUsuarioException extends Exception {

    public NomeUsuarioException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Este nome de usuario esta indisponivel, tente novamente";
    }
}
