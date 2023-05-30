package business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MidiaTest {

    Serie s1;
    Serie s2;
    Serie s3;
    Serie s4;
    Filme f1;
    Filme f2;

    @BeforeEach
    void setUp() throws MidiaInvalidaException {
        s1 = new Serie("comedia", "Nana a Banana", "portugues", 12);
        s2 = new Serie("terror", "Polyforme", "ingles", 8);
        s3 = new Serie("documentario", "Papaleguas", "ingles", 12);
        s4 = new Serie("comedia", "Papaleguas", "espanhol", 12);
        f1 = new Filme("aventura", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }

    @Test
    void test() throws MidiaInvalidaException, AvaliacaoInvalidaException {
        s1.calcularNotaMedia(); // possivelmente na avaliaçao
        s1.colocarAvaliacao(null, null); // possivelmente na avaliaçao
        s1.registrarAudiencia();
        s1.setComentario(null, null); // possivelmente na avaliaçao
        s1.setDataLancamento(null);
        s1.setGenero(null);
        s1.setId(0);
        s1.setIdioma(null);
        s1.setNome(null);
        s1.setQuantidadeEpisodios(0);
        f1.setDuracao(0);
    }

    @Test
    void testSetDataLancamento(){
        s1.setDataLancamento(LocalDate.now());
        assertEquals(LocalDate.now(), s1.getDataLancamento());
    }

    @Test
    void testSetGenero() throws MidiaInvalidaException{
        s1.setGenero("documentario");
        assertEquals("documentario", s1.getGenero());
    }

    @Test
    void testSetId() throws MidiaInvalidaException {
        s1.setId(6996);
        assertEquals(6996, s1.getId());
    }

    @Test
    void testSetIdioma() throws MidiaInvalidaException{
        f1.setIdioma("espanhol");
        assertEquals("espanhol", f1.getIdioma());
    }

    @Test
    void testSetNome() throws MidiaInvalidaException{
        f1.setNome("XAcumiscar");
        assertEquals("XAcumiscar", f1.getNome());
    }

    @Test
    void testSetQuantidadeEpisodios() throws MidiaInvalidaException{
        s1.setQuantidadeEpisodios(66);
        assertEquals(66, s1.getQuantidadeEpisodios());
    }

    @Test
    void testSetDuracao() throws MidiaInvalidaException{
        f1.setDuracao(196);
        assertEquals(196, f1.getDuracao());
    }
}