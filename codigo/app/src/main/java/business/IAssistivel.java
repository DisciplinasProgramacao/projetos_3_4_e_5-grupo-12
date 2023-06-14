package business;

import java.util.List;

public interface IAssistivel{
    

    public void setComentario(String comentario, String nomeUsuario) throws AvaliacaoInvalidaException;

    public void colocarAvaliacao(String nomeUsuario, Avaliacao avaliacao) throws MidiaInvalidaException;

    public double calcularNotaMedia();

    public String getAvaliacoes();

    public List<String> getAvaliadores();

    public void registrarAudiencia();

    public int getAudiencia();

}
