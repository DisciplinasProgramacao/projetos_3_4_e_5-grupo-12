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
    Filme DemandaDosConselheiros;
    Filme SinestesiaDoOdio;

    @BeforeEach
    void setUp() throws Exception {
        c1 = new Cliente("Geoge o Curioso","George","123pogchamp456");
        c2 = new Cliente("Paulo BEM Grande", "paulo","MalFeitoFeito");
        c3 = new Cliente("Pede feijao", "João","orei123");
        NanaABanana = new Serie("comedia","Nana a Banana", "portugues",10);
        Polyforme = new Serie("terror","Polyforme", "portugues",8);
        PapaLeguas = new Serie("comedia","Papaleguas", "alemao",12);
        p = new PlataformaStreaming("POPlux");
        DemandaDosConselheiros = new Filme("ficção científica", "Demanda Dos Conselheiros", "portugues", 160);
        SinestesiaDoOdio = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }


    @Test
    void AdicionarSerieTest() {
        p.adicionarSerie(NanaABanana);
        p.adicionarSerie(Polyforme);
        p.adicionarSerie(PapaLeguas);
        p.adicionarSerie(PapaLeguas);
        assertEquals(3,p.getSeries().size());
    }

    @Test
    void AdicionarClienteTest() {
        p.adicionarCliente(c1);
        p.adicionarCliente(c2);
        p.adicionarCliente(c3);
        p.adicionarCliente(c1);
        assertEquals(3,p.getClientes().size());
    }
     
    @Test
    void FiltrarSeriePorGeneroTest() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(Polyforme);
        p.login("George", "123pogchamp456");
        assertEquals(2,p.filtarPorGenero("comedia").size());
    }


    @Test
    void FiltrarSeriePorQntEpisodiosTest() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        p.login("George", "123pogchamp456");
        assertEquals(1,p.filtrarPorQtdEpisodios(8).size());
    }

    @Test
    void FiltrarSeriePorIdiomaTest() {
        p.adicionarCliente(c1);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(PapaLeguas);
        p.login("George", "123pogchamp456");
        assertEquals(2,p.filtrarPorIdioma("portugues").size());
    }

    @Test
    void RegistrarAudienciaTest() {
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

    @Test
    void testLogin() {
        p.adicionarCliente(c1);
        assertEquals(c1, p.login("George", "123pogchamp456"));
    }

    @Test
    void FiltrarSeriePorNomeTest() {
        p.adicionarCliente(c1);
        p.adicionarSerie(PapaLeguas);
        p.adicionarSerie(NanaABanana);
        p.adicionarSerie(Polyforme);
        c1.adicionarNaLista(NanaABanana);
        c1.adicionarNaLista(Polyforme);
        c1.adicionarNaLista(PapaLeguas);
        c1.adicionarNaLista(PapaLeguas);
        p.login("George", "123pogchamp456");
        assertEquals("{ nome='Papaleguas', id='3', genero='comedia', idioma='alemao', quantidadeEpisodios='12', audiencia='0'}",p.filtrarSeriePorNome("Papaleguas"));
    }

    @Test
    void FiltrarFilmePorNomeTest() {
        p.adicionarCliente(c1);
        p.adicionarFilme(DemandaDosConselheiros);
        p.adicionarFilme(SinestesiaDoOdio);
        c1.adicionarFilmeParaVer(DemandaDosConselheiros);
        c1.adicionarFilmeParaVer(DemandaDosConselheiros);
        c1.adicionarFilmeParaVer(SinestesiaDoOdio);
        c1.adicionarFilmeParaVer(DemandaDosConselheiros);
        p.login("George", "123pogchamp456");
        assertEquals("{ nome='Demanda Dos Conselheiros', id='4', genero='Sem genero', idioma='portugues', audiencia='0'}",p.filtrarFilmePorNome("Demanda Dos Conselheiros"));
    }
}
