package business;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void test() throws MidiaInvalidaException, AvaliacaoInvalidaException, ClienteInvalidoException, Exception{
        p.adicionarAvaliacao(null, 0); //Avaliaçao?
        p.adicionarCliente(null, null, null);
        p.adicionarMidia(f1);
        p.adicionarMidiaParaAssistir(null);
        p.adicionarMidiaVista(null);
        p.comentar(null, null); // Avaliaçao?
        p.eEspecialista();
        p.filtrarMidiaPorNome(null);
        p.filtrarPorGenero(null);
        p.filtrarPorIdioma(null);
        p.filtrarPorQtdEpisodios(0);
        p.login(null, null);
        p.registrarAudiencia(f1);
        p.setClienteEspecialista();
        p.setNome(null);
        p.registrarAudiencia(f1);
    }

    @Test
    void testLogin() throws Exception{
        p.adicionarCliente("Geoge o Curioso","George","123poGchamp456");
        p.login("George", "123poGchamp456");
        assertEquals(c1,p.getClienteAtual());
    }
   
}
