package business;

public class Avaliacao {

    private float nota;
    private String comentario;

    /**
     * Cria uma avaliação com somente a nota
     * 
     * @param nota nota da avaliação
     * @throws AvaliacaoInvalidaException Exceção do setNota, se a nota for inválida
     */
    Avaliacao(float nota) throws AvaliacaoInvalidaException {
        setNota(nota);
    }

    /**
     * Cria uma avaliação com nota e comentário
     * 
     * @param nota       nota da avaliação
     * @param comentario comentário da avaliação
     * @throws AvaliacaoInvalidaException Exceção do setNota e setComentario, se
     *                                    algum valor for
     *                                    inválido
     */
    Avaliacao(float nota, String comentario) throws AvaliacaoInvalidaException {
        setNota(nota);
        setComentario(comentario);
    }

    /**
     * Define uma nova nota para este objeto e realiza a verificação necessária.
     *
     * @param nota o novo valor da nota a ser definida.
     * @throws AvaliacaoInvalidaException Cria e lança uma exceção se o valor for
     *                                    inválido
     */
    public void setNota(float nota) throws AvaliacaoInvalidaException {
        if ((nota >= 1) && (nota <= 5)) {
            this.nota = nota;
        } else {
            throw new AvaliacaoInvalidaException("Digite somente valores entre de 1 a 5!");
        }
    }

    /**
     * Retorna a nota da avaliação.
     *
     * @return a nota atribuída à avaliação
     */
    public float getNota() {
        return this.nota;
    }

    /**
     * Define o comentário da avaliação e realiza a verificação necessária.
     *
     * @param comentario o novo comentário da avaliação
     * @throws AvaliacaoInvalidaException Cria e lança uma exceção se o valor for
     *                                    inválido
     */
    public void setComentario(String comentario) throws AvaliacaoInvalidaException {
        if (comentario.length() > 0) {
            this.comentario = comentario;
        } else {
            throw new AvaliacaoInvalidaException("Comentario não pode ser vazio!");
        }

    }

    /**
     * Retorna o comentário dessa avaliação
     * 
     * @return o comentário dessa avaliação
     */
    public String getComentario() {
        return this.comentario;
    }

    @Override
    public String toString() {
        return "{" +
                " nota='" + getNota() + "'" +
                ", comentario='" + getComentario() + "'" +
                "}";
    }

}
