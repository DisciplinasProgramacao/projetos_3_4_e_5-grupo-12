package business;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MidiaTest {

    Serie s1;
    Serie s2;
    Serie s3;
    Serie s4;
    Filme f1;
    Filme f2;
   
    @BeforeEach
    void setUp() throws MidiaInvalidaException {
        s1 = new Serie("comedia", "Brookling 99", "Ingles", 12);
        s2 = new Serie("comedia","Nana a Banana", "portugues",10);
        s3 = new Serie("terror","Polyforme", "portugues",8);
        s4 = new Serie("comedia","Papaleguas", "alemao",12);
        f1 = new Filme("ficção científica", "Demanda Dos Conselheiros", "portugues", 160);
        f2 = new Filme("terror", "Sinestesia Do Ódio", "portugues", 180);
    }

  
}
