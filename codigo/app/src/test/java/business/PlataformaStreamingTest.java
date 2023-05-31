package business;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.management.InvalidAttributeValueException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PlataformaStreamingTest {
    Cliente c1;
    Cliente c2;
    Cliente c3;
    Serie s1;
    Serie s2;
    Serie s3;
    PlataformaStreaming p;
    Filme f1;
    Filme f2;

    @BeforeEach
    void setUp() throws ClienteInvalidoException, MidiaInvalidaException, Exception  {
        c1 = new Cliente("Geoge o Curioso","George","123poGchamp456");
        c2 = new Cliente("Paulo BEM Grande", "paulo","MalFeit0Feito");
        c3 = new Cliente("Pede feijao", "João","Orei123");
        p = new PlataformaStreaming("POPlux");
        s1 = new Serie("comedia", "Nana a Banana", "portugues", 12);
        s2 = new Serie("terror", "Polyforme", "ingles", 8);
        s3 = new Serie("documentario", "Papaleguas", "ingles", 12);
        f1 = new Filme("aventura", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }


    @Test
    void testAdicionarMidiaParaAssistir() throws MidiaInvalidaException, ClienteInvalidoException{ // conferir
        p.adicionarCliente("Geoge o Curioso","George","123poGchamp456");
        p.login("George", "123poGchamp456");
        p.adicionarSerie("Nana a Banana","portugues","comedia",16);
        p.adicionarSerie("Papaleguas","portugues","terror",16);
        p.adicionarMidiaParaAssistir("Nana a Banana");
        p.adicionarMidiaParaAssistir("Nana a Banana");
        p.adicionarMidiaParaAssistir("Papaleguas");
        assertTrue(p.getListaParaAssistir().contains("Papaleguas"));
        assertTrue(p.getListaParaAssistir().contains("Nana a Banana"));
    }

    @Test
    void testAdicionarMidiaVista() throws MidiaInvalidaException, ClienteInvalidoException{ // conferir
        p.adicionarCliente("Geoge o Curioso","George","123poGchamp456");
        p.login("George", "123poGchamp456");
        p.adicionarSerie("Nana a Banana","portugues","comedia",16);
        p.adicionarSerie("Papaleguas","portugues","terror",16);
        p.adicionarMidiaVista("Nana a Banana");
        p.adicionarMidiaVista("Nana a Banana");
        p.adicionarMidiaVista("Papaleguas");
        assertTrue(p.getListaJaVista().contains("Papaleguas"));
        assertTrue(p.getListaJaVista().contains("Nana a Banana"));
    }

    
    @Test
    void testLogin() throws ClienteInvalidoException, MidiaInvalidaException   {
        p.adicionarCliente("Geoge o Curioso","George","123poGchamp456");
        p.login("George", "123poGchamp456");
        assertEquals(c1.toString(),p.getClienteAtual().toString());
    }

    @Test
    void testFiltrarMidiaPorNome() throws MidiaInvalidaException{
        p.adicionarFilme("Demanda Dos Conselheiros","portugues","aventura",160);
        p.adicionarSerie("Nana a Banana","portugues","comedia",16);
        assertEquals("Demanda Dos Conselheiros",p.filtrarMidiaPorNome("Demanda Dos Conselheiros").getNome());
    }

    @Test
    void testNomeInvalido(){
        assertThrows(InvalidAttributeValueException.class, () -> {
            p.setNome("");;
        });
    }

}
