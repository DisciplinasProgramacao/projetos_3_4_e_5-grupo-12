package business;

public class Avaliacao {

    private float nota;
    private String comentario;

    Avaliacao(float nota) throws Exception {
        setNota(nota);
    }

    Avaliacao(float nota, String comentario) throws Exception {
        setNota(nota);
        setComentario(comentario);
    }

    /**
     * Define uma nova nota para este objeto e atualiza a média das notas.
     *
     * @param nota o novo valor da nota a ser definida.
     * @throws Exception
     */
    public void setNota(float nota) throws Exception {
        if((nota>=1) && (nota<=5)){
            this.nota = nota;
        } else {
            throw new Exception("Digite somente valores entre de 1 a 5!");
        }  
    }

/**
 * Retorna a nota atribuída à avaliação.
 *
 * @return a nota atribuída à avaliação
 */
    public float getNota() {
        return this.nota;
    }

/**
 * Define o comentário da avaliação.
 *
 * @param comentario o novo comentário da avaliação
 * @throws Exception
 */
    public void setComentario(String comentario) throws Exception {
        if(comentario.length()>0) {
            this.comentario = comentario;
        } else {
            throw new Exception("Comentario não pode ser vazio!");
        }
       
    }

    public String getComentario(){
        return this.comentario;
    }
}
