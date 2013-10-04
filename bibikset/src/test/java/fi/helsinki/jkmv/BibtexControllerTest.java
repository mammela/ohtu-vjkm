package fi.helsinki.jkmv;

import org.junit.Test;
import static org.junit.Assert.*;

public class BibtexControllerTest {
    
    @Test
    public void testInstance() {
        BibtexController instance = new BibtexController();
        assertTrue(instance.toString().contains("BibtexController"));        
    }

}
