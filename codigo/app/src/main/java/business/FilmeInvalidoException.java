package business;

public class FilmeInvalidoException extends Exception {

    public FilmeInvalidoException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar filme";
    }

}
