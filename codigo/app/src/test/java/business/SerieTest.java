package business;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SerieTest {

    static Serie serie;
   
    @BeforeEach
    void setUp() {
        serie = new Serie("comedia", "Brookling 99", "Ingles", 12);
        
    }

    @Test
    public void testRegistarAudiencia() {
        serie.registrarAudiencia();
        assertEquals(1, serie.getAudiencia());;
    }
}
