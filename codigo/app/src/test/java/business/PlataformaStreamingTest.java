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
        c1 = new Cliente("Geoge o Curioso","George","123pogchamp456");
        c2 = new Cliente("Paulo BEM Grande", "paulo","MalFeitoFeito");
        c3 = new Cliente("Pede feijao", "João","orei123");
        s1 = new Serie("comedia","Nana a Banana", "portugues",10);
        s2 = new Serie("terror","Polyforme", "portugues",8);
        s3 = new Serie("comedia","Papaleguas", "alemao",12);
        p = new PlataformaStreaming("POPlux");
        f1 = new Filme("ficção científica", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }


   
}
