package business;

public class FilmeInvalido extends Exception {
    private Filme f;

    public FilmeInvalido(Filme f) {
        super();
        this.f = f;
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar filme";
    }
    
}
