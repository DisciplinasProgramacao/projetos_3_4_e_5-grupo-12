package business;
import java.time.LocalDate;

public class Avaliacao {

    private static float contNota = 0;
    private static int contNumeroNotas = 0;
    private float nota;
    private String comentario;
    LocalDate data;

    Avaliacao(float nota) {
        data = LocalDate.now();
        setNota(nota);

    }

    Avaliacao(float nota, String comentario) {
        data = LocalDate.now();
        setNota(nota);
        this.comentario = comentario;

    }

    Avaliacao(float nota, LocalDate data) {
        this.data = data;
        setNota(nota);

    }

    Avaliacao(float nota, String comentario, LocalDate data) {
        this.data = data;
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

    public float getNota() {
        return this.nota;
    }


    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData(){
        return this.data;
    }
}
