package business;

public class SerieInvalidaException extends Exception {

    public SerieInvalidaException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar serie";
    }
}
