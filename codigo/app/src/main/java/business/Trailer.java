package business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Trailer extends Midia {


    /**
     * Cria um novo objeto Trailer com o gênero, nome e idioma especificados.
    *
    * @param genero  o gênero do trailer
    * @param nome    o nome do trailer
    * @param idioma  o idioma do trailer
    * @throws MidiaInvalidaException se ocorrer um erro ao criar o trailer
    */
    public Trailer(String genero, String nome, String idioma) throws MidiaInvalidaException {
        super(genero, nome, idioma);

    }

    /**
    * Cria um novo objeto Trailer com o id, nome e data de lançamento especificados.
    *
    * @param id             o id do trailer
    * @param nome           o nome do trailer
    * @param dataLancamento a data de lançamento do trailer
    * @throws MidiaInvalidaException se ocorrer um erro ao criar o trailer
    */
    public Trailer(int id, String nome, LocalDate dataLancamento) throws MidiaInvalidaException {
        super(id, nome, dataLancamento);
    }

    /**
    * Registra a audiência, se necessário.
    */
    @Override
    public void registrarAudienciaSeNecessario() {

    }

    /**
    * Calcula a média das notas atribuídas a esta mídia.
    *
    * @return A média das notas atribuídas à mídia. Se não houver notas disponíveis, retorna 0.0.
    */
    public double calcularNotaMedia() {
        return 0.0F;
    }

    /**
    * Obtém a audiência desta mídia.
     *
     * @return O número de audiência desta mídia. Se não houver informações de audiência disponíveis, retorna 0.
    */
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

    /**
    * Verifica se esta mídia é um trailer.
    *
    * @return true se esta mídia for um trailer, caso contrário, retorna false.
    */
    public boolean eTrailer() {
        return true;
    }

    /**
    * Obtém a nota atribuída pelo cliente especificado a esta mídia.
    *
    * @param nomeUsuario o nome de usuário do cliente.
    * @return a nota atribuída pelo cliente, ou -1 se o cliente não tiver avaliado esta mídia.
    */
    @Override
    public double getNotaCliente(String nomeUsuario) {
        return -1;
    }
}
