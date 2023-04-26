package business;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PlataformaStreamingTest {
    Cliente c1;
    Cliente c2;
    Cliente c3;
    Serie NanaABanana;
    Serie Polyforme;
    Serie PapaLeguas;
    PlataformaStreaming p;

    @BeforeEach
    void setUp() throws Exception {
        c1 = new Cliente("George","123pogchamp456");
        c2 = new Cliente("paulo","MalFeitoFeito");
        c3 = new Cliente("João","orei123");
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
        p.adicionarSerie(PapaLeguas);
        assertEquals(3,p.getSeries().size());
    }

    @Test
    void testAdicionarCliente() {
        p.adicionarCliente(c1);
        p.adicionarCliente(c2);
        p.adicionarCliente(c3);
        p.adicionarCliente(c1);
        assertEquals(3,p.getClientes().size());
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

    @Test
    void testRegistrarAudiencia() {
        p.adicionarCliente(c1);
        p.adicionarSerie(NanaABanana);
        p.login("George", "123pogchamp456");
        p.registrarAudiencia(NanaABanana);
        p.adicionarCliente(c2);
        p.login("paulo", "MalFeitoFeito");
        p.registrarAudiencia(NanaABanana);
        p.registrarAudiencia(NanaABanana);
        assertEquals(2,NanaABanana.getAudiencia());
    }

}
