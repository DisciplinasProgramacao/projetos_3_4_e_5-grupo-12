package business;

import java.util.List;

 /**
 * Uma interface que representa um item assistível, como uma mídia.
 */
public interface IAssistivel{
    
     /**
     * Define o comentário realizado por um usuário sobre este item.
     *
     * @param comentario   o comentário a ser definido.
     * @param nomeUsuario  o nome de usuário do usuário que está fazendo o comentário.
     * @throws AvaliacaoInvalidaException se o comentário for inválido.
     */
    public void setComentario(String comentario, String nomeUsuario) throws AvaliacaoInvalidaException;

     /**
     * Adiciona uma avaliação realizada por um usuário a este item.
     *
     * @param nomeUsuario  o nome de usuário do usuário que está fazendo a avaliação.
     * @param avaliacao    a avaliação a ser adicionada.
     * @throws MidiaInvalidaException se a avaliação não for aplicável a este item.
     */
    public void colocarAvaliacao(String nomeUsuario, Avaliacao avaliacao) throws MidiaInvalidaException;

     /**
     * Calcula a nota média deste item com base nas avaliações recebidas.
     *
     * @return a nota média deste item.
     */
    public double calcularNotaMedia();

     /**
     * Obtém uma representação em string das avaliações deste item.
     *
     * @return as avaliações deste item.
     */
    public String getAvaliacoes();

     /**
     * Obtém uma lista de nomes dos avaliadores deste item.
     *
     * @return uma lista de nomes dos avaliadores deste item.
     */
    public List<String> getAvaliadores();

     /**
     * Registra a audiência deste item.
     */
    public void registrarAudiencia();

     /**
     * Obtém a audiência deste item.
     *
     * @return a audiência deste item.
     */
    public int getAudiencia();

}
