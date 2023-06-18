package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MidiaTest {

    Cliente c1;
    Serie s1;
    Serie s2;
    Serie s3;
    Serie s4;
    Filme f1;
    Filme f2;
    Trailer t1;

    @BeforeEach
    void setUp() throws MidiaInvalidaException, ClienteInvalidoException {
        c1 = new Cliente("Geoge o Curioso", "George", "123poGchamp456");
        s1 = new Serie("comedia", "Nana a Banana", "portugues", 12);
        s2 = new Serie("terror", "Polyforme", "ingles", 8);
        s3 = new Serie("documentario", "Papaleguas", "ingles", 12);
        s4 = new Serie("comedia", "Papaleguas", "espanhol", 12);
        f1 = new Filme("aventura", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
        t1 = new Trailer("terror", "Laringite", "portugues");
    }


    @Test
    void testQuantidadeDeEpisodiosInvalido() {
        assertThrows(MidiaInvalidaException.class, () -> {
            s1.setQuantidadeEpisodios(-1);
        });
    }

    @Test
    void testDuracaoInvalida() {
        assertThrows(MidiaInvalidaException.class, () -> {
            f1.setDuracao(-1);
        });
    }

    @Test
    void testNomeInvalido() {
        assertThrows(MidiaInvalidaException.class, () -> {
            f1.setNome("");
        });
    }

    @Test
    void testIdInvalido() {
        assertThrows(MidiaInvalidaException.class, () -> {
            f1.setId(-5);
        });
    }

    @Test
    void testIdiomaInvalido() {
        assertThrows(MidiaInvalidaException.class, () -> {
            f1.setIdioma("Laranja");
        });
    }

    @Test
    void testGeneroInvalido() {
        assertThrows(MidiaInvalidaException.class, () -> {
            f1.setGenero("Laranja");
        });
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

    @Test
    void testRegistrarAudiencia(){
        s1.registrarAudiencia();
        s2.registrarAudiencia();
        assertSame(s2.getAudiencia(),s1.getAudiencia());
    }


    @Test
    void testTrailerNaoPorPraAssistir() throws MidiaInvalidaException{
         assertThrows(java.lang.ClassCastException.class, () -> {
            c1.adicionarListaParaVer((IAssistivel) t1);
         });
    }
}