package business;

public class SerieInvalida extends Exception {
    private Serie s;

    public SerieInvalida(Serie s) {
        super();
        this.s = s;
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar serie";
    }
}
