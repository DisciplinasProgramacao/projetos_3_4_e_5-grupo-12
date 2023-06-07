package business;

import java.time.format.DateTimeFormatter;

public class Trailer extends Midia {

    private String nomeMidiaCompleta;

    Trailer(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);
        setNomeMidiaCompleta(nome);
    }

    public String getNomeMidiaCompleta() {
        return nomeMidiaCompleta;
    }

    public void setNomeMidiaCompleta(String nomeMidiaCompleta) {
        if (nomeMidiaCompleta.equals("")) {

            this.nomeMidiaCompleta = nomeMidiaCompleta;
        }

    }

    @Override
    public String getDados() {
        int id = getId();
        String nome = getNome();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = getDataLancamento().format(formatter);
        

        return ("\n" + id + ";" + nome + ";" + data);
    }

}