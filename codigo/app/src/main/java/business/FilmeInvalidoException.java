package business;

public class FilmeInvalidoException extends Exception {

    public FilmeInvalidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar filme";
    }

}
