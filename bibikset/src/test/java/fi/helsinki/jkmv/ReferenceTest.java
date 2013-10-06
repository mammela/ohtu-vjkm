package fi.helsinki.jkmv;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTest {
    private Reference ref;
    
    @Before
    public void init(){
        ref = new Reference();
    }
    
    //testit validTypelle
    @Test
    public void testHasValidType() {
        ref.setEntryType("book");
        assertEquals(true, ref.hasValidEntryType());
        ref.setEntryType("article");
        assertEquals(true, ref.hasValidEntryType());
        ref.setEntryType("inproceedings");
        assertEquals(true, ref.hasValidEntryType());
        ref.setEntryType("misc");
        assertEquals(true, ref.hasValidEntryType());

        ref.setEntryType("hatsa");
        assertEquals(false, ref.hasValidEntryType());
    }
    
    //testit muille get-seteille
    @Test
    public void testGetSets() {
        ref.setKey("test23");
        assertEquals("test23", ref.getKey());
        ref.setAuthor("test23");
        assertEquals("test23", ref.getAuthor());
        ref.setEditor("test23");
        assertEquals("test23", ref.getEditor());
        ref.setTitle("test23");
        assertEquals("test23", ref.getTitle());
        ref.setBooktitle("test23");
        assertEquals("test23", ref.getBooktitle());
        ref.setYear("1839");
        assertEquals("1839", ref.getYear());
        ref.setPublisher("Otava");
        assertEquals("Otava", ref.getPublisher());
        ref.setJournal("test23");
        assertEquals("test23", ref.getJournal());
        ref.setVolume("3");
        assertEquals("3", ref.getVolume());
        ref.setNumber("123");
        assertEquals("123", ref.getNumber());
        ref.setSeries("test23");
        assertEquals("test23", ref.getSeries());
        ref.setEdition("3rd");
        assertEquals("3rd", ref.getEdition());
        ref.setPages("12-35");
        assertEquals("12-35", ref.getPages());
        ref.setMonth("May");
        assertEquals("May", ref.getMonth());
        ref.setNote("test23");
        assertEquals("test23", ref.getNote());
    }

}
