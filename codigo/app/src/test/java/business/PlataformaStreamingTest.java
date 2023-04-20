package business;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PlataformaStreamingTest {
    Cliente c1;
    Cliente c2;
    Serie NanaABanana;
    Serie Polyforme;
    Serie PapaLeguas;
    PlataformaStreaming p;

    @BeforeEach
    void setUp() throws Exception {
        c1 = new Cliente("George","123pogchamp456");
        c2 = new Cliente("paulo","MalFeitoFeito");
        NanaABanana = new Serie("comedia","Nana a Banana", "portugues",10);
        Polyforme = new Serie("terror","Polyforme", "portugues",8);
        PapaLeguas = new Serie("comedia","Papaleguas", "alemao",12);
        p = new PlataformaStreaming("POPlux");
    }


    @Test
    void testAdicionarSerie() {
        p.adicionarSerie(NanaABanana);
        p.adicionarSerie(Polyforme);
        p.adicionarSerie(PapaLeguas);
        assertEquals(3,p.getSeries().size());
    }


    @Test
    void testFiltrarPorGenero() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(Polyforme);
        p.login("George", "123pogchamp456");
        assertEquals(2,p.filtarPorGenero("comedia").size());
    }


    @Test
    void testFiltrarPorQntEpisodios() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        p.login("George", "123pogchamp456");
        assertEquals(1,p.filtrarPorQtdEpisodios(8).size());
    }

    @Test
    void filtrarPorIdiomaTest() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(PapaLeguas);
        p.login("George", "123pogchamp456");
        assertEquals(2,p.filtrarPorIdioma("portugues").size());
    }
    
}
