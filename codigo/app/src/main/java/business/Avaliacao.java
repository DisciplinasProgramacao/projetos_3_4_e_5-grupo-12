package business;

public class Avaliacao {

    private static float contNota = 0;
    private static int contNumeroNotas = 0;
    private float nota;
    private String comentario;

    Avaliacao(float nota) {
        setNota(nota);

    }

    Avaliacao(float nota, String comentario) {
        setNota(nota);
        this.comentario = comentario;

    }

    /**
     * Define uma nova nota para este objeto e atualiza a média das notas.
     *
     * @param nota o novo valor da nota a ser definida.
     */
    public void setNota(float nota) {
        contNumeroNotas++;
        contNota += nota;
        this.nota = contNota / contNumeroNotas;
    }
}
