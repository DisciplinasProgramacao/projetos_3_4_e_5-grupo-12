package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {
    Cliente c1;
    Cliente c2;
    Serie s1;
    Serie s2;
    Serie s3;
    Filme f1;
    Filme f2;

    @BeforeEach
    void setUp() throws ClienteInvalidoException, MidiaInvalidaException {
        c1 = new Cliente("Geoge o Curioso", "George", "123poGchamp456");
        c2 = new Cliente("Paulo BEM Grande", "paulo", "MalFeit0Feito");
        s1 = new Serie("comedia", "Nana a Banana", "portugues", 12);
        s2 = new Serie("terror", "Polyforme", "ingles", 8);
        s3 = new Serie("documentario", "Papaleguas", "ingles", 12);
        f1 = new Filme("aventura", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }

    @Test
    void testNomeUsuarioVazio() {
        assertThrows(ClienteInvalidoException.class, () -> {
            c1.setNomeDeUsuario("");
        });
    }

    @Test
    void testNomeCompletoVazio() {
        assertThrows(ClienteInvalidoException.class, () -> {
            c1.setNomeCompleto("");
        });
    }

    @Test
    void testSenhaInvalida() {
        assertThrows(ClienteInvalidoException.class, () -> {
            c1.setSenha("123456");
        });
    }

    @Test
    void testAdicionarListaParaVerDuplicada() {
        assertThrows(MidiaInvalidaException.class, () -> {
            c1.adicionarListaParaVer(s1);
            c1.adicionarListaParaVer(s1);
        });
    }

    @Test
    void testAdicionarMidiaVistaDuplicada() {
        assertThrows(MidiaInvalidaException.class, () -> {
            c1.adicionarMidiaVista(f2);
            c1.adicionarMidiaVista(f2);
        });
    }

    @Test
    void testAdicionarListaParaVer() throws MidiaInvalidaException {
        c1.adicionarListaParaVer(s1);
        c1.adicionarListaParaVer(s2);
        c1.adicionarListaParaVer(s3);
        c1.adicionarListaParaVer(f1);
        c1.adicionarListaParaVer(f2);
        assertEquals(5, c1.getTamanhoListaParaVer());
    }

    @Test
    void testAdicionarMidiaVista() throws MidiaInvalidaException {
        c1.adicionarMidiaVista(f1);
        c1.adicionarMidiaVista(f2);
        c1.adicionarMidiaVista(s1);
        c1.adicionarMidiaVista(s2);
        c1.adicionarMidiaVista(s3);
        assertEquals(5, c1.getTamanhoListaJaVista());
    }

    @Test
    void testRetirarDaLista() throws MidiaInvalidaException {
        c1.adicionarListaParaVer(s1);
        c1.adicionarListaParaVer(s2);
        c1.adicionarListaParaVer(s3);
        c1.adicionarListaParaVer(f1);
        c1.adicionarListaParaVer(f2);
        c1.retirarDaLista(f1);
        c1.retirarDaLista(f2);
        assertEquals(3, c1.getTamanhoListaParaVer());
    }

    @Test
    void testFiltrarMidiaPorGenero() throws MidiaInvalidaException {
        c1.adicionarMidiaVista(f1);
        c1.adicionarMidiaVista(f2);
        c1.adicionarMidiaVista(s1);
        c1.adicionarMidiaVista(s2);
        c1.adicionarMidiaVista(s3);
        assertEquals(2, c1.filtrarMidiaPorGenero("terror").size());
    }

    @Test
    void testFiltrarMidiaPorIdioma() throws MidiaInvalidaException {
        c1.adicionarMidiaVista(f1);
        c1.adicionarMidiaVista(f2);
        c1.adicionarMidiaVista(s1);
        c1.adicionarMidiaVista(s2);
        c1.adicionarMidiaVista(s3);
        assertEquals(3, c1.filtrarMidiaPorIdioma("portugues").size());
    }

    @Test
    void testFiltrarPorQtdEpisodios() throws MidiaInvalidaException {
        c1.adicionarMidiaVista(f1);
        c1.adicionarMidiaVista(f2);
        c1.adicionarMidiaVista(s1);
        c1.adicionarMidiaVista(s2);
        c1.adicionarMidiaVista(s3);
        assertEquals(2, c1.filtrarPorQtdEpisodios(12).size());
    }

    @Test
    void testSetNomeCompleto() throws ClienteInvalidoException {
        c1.setNomeCompleto("Pedro");
        assertEquals("Pedro", c1.getNomeCompleto());
    }

    @Test
    void testSetNomeDeUsuario() throws ClienteInvalidoException {
        c1.setNomeDeUsuario("Pedro");
        assertEquals("Pedro", c1.getNomeDeUsuario());
    }

    @Test
    void testSetSenha() throws ClienteInvalidoException {
        c1.setSenha("AMil13111");
        assertEquals("AMil13111", c1.getSenha());
    }

    @Test
    void testRegistrarAudiencia() {
        c1.registrarAudiencia(f1);
        c1.registrarAudiencia(f1);
        c2.registrarAudiencia(f1);
        assertEquals(2, f1.getAudiencia());
    }

    @Test
    void testSetmeuTipo() throws ClienteInvalidoException {
        c1.setMeuTipo(new ClienteEspecialista(c1.getNomeCompleto(), c1.getNomeDeUsuario(), c1.getSenha()));
        assertEquals(ClienteEspecialista.class, c1.getMeuTipo().getClass());
    }

}