package business;

import java.time.format.DateTimeFormatter;

public class Trailer extends Midia {

    public Trailer(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getArquivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArquivo'");
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

        return ("\n" + id + ";" + nome + ";" + data);
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

}