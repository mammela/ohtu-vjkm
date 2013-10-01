package fi.helsinki.jkmv;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferencesTest {
    
    public ReferencesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addReference & getlist methods, of class References.
     */
    @Test
    public void testAddReference() {
        Reference ref = new Reference();
        ref.setTitle("test");
        References refs = new References();
        refs.addReference(ref);

        List result = refs.getList();
        assertEquals(result.size(),1);
    }

}
