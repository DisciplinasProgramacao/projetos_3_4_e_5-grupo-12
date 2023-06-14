package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Trailer extends Midia {

    private static final String arqTrailer = "codigo/POO_Trailers.csv";

    public Trailer(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);

    }

    public Trailer(int id, String nome, LocalDate dataLancamento) throws MidiaInvalidaException {
        super(id, nome, dataLancamento);
    }

    @Override
    public String getArquivo() {

        return arqTrailer;
    }

    @Override
    public void registrarAudienciaSeNecessario() {

    }

    public double calcularNotaMedia() {
        return 0.0F;
    }

    public int getAudiencia() {
        return 0;
    }

    /**
     * Retorna uma String formatada contendo os dados do objeto.
     * 
     * @return uma String contendo o id, nome e data de lançamento do objeto
     */
    @Override
    public String getDados() {
        int id = getId();
        String nome = getNome();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = getDataLancamento().format(formatter);

        return ("\n" + id + ";" + nome + ";" + data + ";" + "T");
    }

    /**
     * Retorna uma representação em String do objeto Série.
     * 
     * @return uma String que contém o nome, o id, o gênero, o idioma, a quantidade
     *         de episódios
     *         e a audiência da Série
     */
    @Override
    public String toString() {
        return "'trailer'{" +
                " nome='" + getNome() + "'" +
                ", id='" + getId() + "'" +
                ", genero='" + getGenero() + "'" +
                ", idioma='" + getIdioma() + "'" +
                "}";
    }

    public boolean eTrailer() {
        return true;
    }

    @Override
    public String getTipoMidia() {
        return "T";
    }

    @Override
    public double getNotaCliente(String nomeUsuario) {
        if(avaliacoes.get(nomeUsuario).getNota() != -1) {
            return avaliacoes.get(nomeUsuario).getNota();
        } else {
            return -1;
        }
    }
}
