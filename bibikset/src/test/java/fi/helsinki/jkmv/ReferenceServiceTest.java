package fi.helsinki.jkmv;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceServiceTest {
    
    public ReferenceServiceTest() {
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

    // Test of add -method
    @Test
    public void testAdd() {
        ReferenceService refs = new ReferenceService();
        // test list without refs
        assertEquals(refs.list().size(),0);
        Reference ref = new Reference();
        ref.setType("Inproceedings");
        refs.add(ref);
        // test list with 1 ref
        assertEquals(refs.list().size(),1);
    }

    // Tests for listing
    @Test
    public void testListing() {
        ReferenceService refs = new ReferenceService();
        Reference ref1 = new Reference();
        Reference ref2 = new Reference();
        Reference ref3 = new Reference();
        Reference ref4 = new Reference();
        ref1.setType("Inproceedings");
        ref2.setType("Book");
        ref3.setType("Article");
        ref4.setType("Misc");
        refs.add(ref1);
        assertEquals(refs.list().size(),1);
        refs.add(ref2);
        assertEquals(refs.list().size(),2);
        refs.add(ref3);
        assertEquals(refs.list().size(),3);
        refs.add(ref4);
        assertEquals(refs.list().size(),4);
    }
}
