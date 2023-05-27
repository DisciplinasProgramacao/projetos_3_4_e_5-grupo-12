package business;

public class SerieInvalidaException extends Exception {

    public SerieInvalidaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar serie";
    }
}
