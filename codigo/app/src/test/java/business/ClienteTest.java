package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTeste {
Cliente c1;
Cliente c2;
Serie NanaABanana;
Serie Polyforme;
Serie PapaLeguas;

    @BeforeEach
    void setUp() throws Exception {
        c1 = new Cliente("George","123pogchamp456");
        c2 = new Cliente("paulo","MalFeitoFeito");
        NanaABanana = new Serie("comedia","Nana a Banana", "portugues",10);
        Polyforme = new Serie("terror","Polyforme", "portugues",8);
        PapaLeguas = new Serie("comedia","Papaleguas", "alemao",12);
    }

    @Test
    void adicionarNaListaTest() {
        c1.adiconarNaLista(NanaABanana);
        assertTrue(c1.find(NanaABanana));
    }
    
    @Test
    void retirarDaListaTest() {
        c1.adiconarNaLista(NanaABanana);
        c1.retirarDaLista(NanaABanana);
        assertFalse(c1.find(NanaABanana));
    }

    
    @Test
    void filtrarPorGeneroTest() {
        
        c1.adiconarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        assertEquals(2,c1.filtrarPorGenero("comedia").size());
    }
    
    @Test
    void filtrarPorIdiomaTest() {
        c1.adiconarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        assertEquals(2,c1.filtrarPorIdioma("portugues").size());
    }
    
    @Test
    void filtrarPorQntEpisodiosTest() {
        c1.adiconarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        assertEquals(1,c1.filtrarPorQntEpisodios(8).size());
    }
    
    @Test
    void registrarAudienciaTest() {
        c1.registrarAudiencia(NanaABanana);
        c1.registrarAudiencia(NanaABanana);
        c2.registrarAudiencia(NanaABanana);
        assertEquals(2,NanaABanana.getAudiencia());
    }
    
}