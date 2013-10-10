package fi.helsinki.jkmv;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateTest {
    private Validate val;
    private Reference ref;
    
    public ValidateTest() {
    }
        
    @Before
    public void setUp() {
        val = new Validate();
        ref = new Reference();
    }

    /*
     * Fill correct values for testing
     */
    private void setupValidConfig(){
        ref.setEntryType("book");
        ref.setAuthor("Tapani");       
        ref.setTitle("testiTitle");
        ref.setPublisher("Otava");
        ref.setYear("1923");
        ref.setKey("T23");        
    }
    
    /*
     * Test when required fields are incomplete
     */
    @Test
    public void testValidateIncompleteRequired() {
        ref.setEntryType("book");
        ref.setTitle("testiTitle");
        ref.setAuthor("Tapani");       
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }
    
    /*
     * Test when required fields are complete
     */
    @Test
    public void testValidateCompleteOnlyReq() {
        setupValidConfig();
        boolean isValid = val.validateEntry(ref);
        assertEquals(true, isValid);
    }
    
    /*
     * Test when required fields are valid with alternative field
     */
    @Test
    public void testValidateRequiredWithAlt() {
        setupValidConfig();
        ref.setAuthor(null);
        ref.setEditor("Kansi");
        boolean isValid = val.validateEntry(ref);
        assertEquals(true, isValid);
    }
    
    /*
     * Test when required fields are complete but also alternatives filled
     */
    @Test
    public void testValidateTooManyFields() {
        setupValidConfig();
        ref.setAuthor("Kansi");       
        ref.setEditor("Kansi");       
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }
    
    /*
     * Test using optional fields with alternatives filled
     */
    @Test
    public void testValidateTooManyOptional() {
        setupValidConfig();
        ref.setVolume(("123"));
        ref.setNumber("12");
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }
    
    /*
     * Test when year is not numbers
     */
    @Test
    public void testValidateYearChars() {
        setupValidConfig();
        ref.setYear("kfds");
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }
    
    /*
     * Test when year is not long enough
     */
    @Test
    public void testValidateYearTooShort() {
        setupValidConfig();
        ref.setYear("34");
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }   
    
    /*
     * Test when year is not long enough when optional
     */
    @Test
    public void testValidateOptYearTooShort() {
        ref.setEntryType("booklet");
        ref.setTitle("Tester");
        ref.setKey("TEST23");
        ref.setYear("34");
        boolean isValid = val.validateEntry(ref);
        assertEquals(false, isValid);
    }   
    
}
