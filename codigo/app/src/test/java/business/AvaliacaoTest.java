package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AvaliacaoTest {
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
    void setUp() throws ClienteInvalidoException, MidiaInvalidaException, Exception {
        c1 = new Cliente("Geoge o Curioso", "George", "123poGchamp456");
        c2 = new Cliente("Paulo BEM Grande", "paulo", "MalFeit0Feito");
        c3 = new Cliente("Pede feijao", "João", "Orei123");
        p = new PlataformaStreaming("POPlux");
        s1 = new Serie("comedia", "Nana a Banana", "portugues", 12);
        s2 = new Serie("terror", "Polyforme", "ingles", 8);
        s3 = new Serie("documentario", "Papaleguas", "ingles", 12);
        f1 = new Filme("aventura", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }


    @Test
    void testCalcularNotaMedia() throws AvaliacaoInvalidaException, MidiaInvalidaException{
        c1.adicionarMidiaVista(f1);
        c2.adicionarMidiaVista(f1);
        c2.adicionarMidiaVista(f1);
        c3.adicionarMidiaVista(f1);
        c1.criarAvaliacao(4, f1);
        c2.criarAvaliacao(3, f1);
        c2.criarAvaliacao(3, f1);
        c3.criarAvaliacao(5, f1);
        assertEquals(4,f1.calcularNotaMedia()); 
    }

    @Test
    void testSetClienteEspecialista() throws ClienteInvalidoException, MidiaInvalidaException, AvaliacaoInvalidaException {
        p.adicionarCliente("Geoge o Curioso","George","123poGchamp456");
        p.login("George", "123poGchamp456");
        p.adicionarSerie("Nana a Banana","portugues","comedia",16);
        p.adicionarSerie("Papaleguas","portugues","terror",16);
        p.adicionarSerie("jardim","portugues","comedia",16);
        p.adicionarFilme("Lago","portugues","terror",160);
        p.adicionarFilme("Destiny","portugues","terror",160);
        p.adicionarMidiaVista("Nana a Banana");
        p.adicionarMidiaVista("Papaleguas");
        p.adicionarMidiaVista("jardim");
        p.adicionarMidiaVista("Destiny");
        p.adicionarMidiaVista("Lago");
        p.adicionarAvaliacao("Nana a Banana", 3);
        p.adicionarAvaliacao("Papaleguas", 5);
        p.adicionarAvaliacao("jardim", 2);
        p.adicionarAvaliacao("Lago", 1);
        p.adicionarAvaliacao("Destiny", 4);
        p.setClienteEspecialista();
        assertTrue(p.eEspecialista());
    }

    @Test
    void testComentar() throws ClienteInvalidoException, MidiaInvalidaException, AvaliacaoInvalidaException { // revisar
        p.adicionarCliente("Geoge o Curioso","George","123poGchAmp456");
        p.login("George", "123poGchAmp456");
        p.adicionarSerie("Nana a Banana","portugues","comedia",16);
        p.adicionarSerie("Papaleguas","portugues","terror",16);
        p.adicionarSerie("jardim","portugues","comedia",16);
        p.adicionarFilme("Lago","portugues","terror",160);
        p.adicionarFilme("Destiny","portugues","terror",160);
        p.adicionarMidiaVista("Nana a Banana");
        p.adicionarMidiaVista("Papaleguas");
        p.adicionarMidiaVista("jardim");
        p.adicionarMidiaVista("Destiny");
        p.adicionarMidiaVista("Lago");
        p.adicionarAvaliacao("Nana a Banana", 3);
        p.adicionarAvaliacao("Papaleguas", 5);
        p.adicionarAvaliacao("jardim", 2);
        p.adicionarAvaliacao("Lago", 1);
        p.adicionarAvaliacao("Destiny", 4);
        p.setClienteEspecialista();
        p.comentar("Ola", "Papaleguas");
        p.adicionarCliente("Geoge o Curioso","Pablo","123poGchAmp456");
        p.login("Pablo", "123poGchAmp456");
        p.adicionarMidiaVista("Papaleguas");
        p.adicionarAvaliacao("Papaleguas", 5);
        assertEquals(1,p.filtrarMidiaPorNome("Papaleguas").getAvaliacoes());
       
        assertTrue(p.filtrarMidiaPorNome("Papaleguas").getAvaliacoes().contains(p.getClienteAtual().getNomeDeUsuario()));
    }
}
