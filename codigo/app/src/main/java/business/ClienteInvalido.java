package business;

public class ClienteInvalido extends Exception {
    private Cliente c;

    public ClienteInvalido(Cliente c) {
        super();
        this.c = c;
    }

    @Override
    public String getMessage() {
        return "Erro ao cadastrar cliente";
    }
    
}
