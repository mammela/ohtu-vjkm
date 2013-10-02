package fi.helsinki.jkmv;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTest {
    private Reference ref;
    
    public ReferenceTest() {
    }

    @Before
    public void init(){
        ref = new Reference();
    }
    
    @Test
    public void testHasValidType() {
        ref.setType("Book");
        assertEquals(true, ref.hasValidType());
        ref.setType("Hatsa");
        assertEquals(false, ref.hasValidType());
    }
    
    @Test
    public void testType() {
        ref.setType("Book");
        assertEquals("Book", ref.getType());
    }

    @Test
    public void testKey() {
        ref.setKey("test23");
        assertEquals("test23", ref.getKey());
    }

    @Test
    public void testAuthor() {
        ref.setAuthor("test23");
        assertEquals("test23", ref.getAuthor());
    }
    
    @Test
    public void testEditor() {
        ref.setEditor("test23");
        assertEquals("test23", ref.getEditor());
    }

    @Test
    public void testTitle() {
        ref.setTitle("test23");
        assertEquals("test23", ref.getTitle());
    }

    @Test
    public void testBooktitle() {
        ref.setBooktitle("test23");
        assertEquals("test23", ref.getBooktitle());
    }

    @Test
    public void testYear() {
        ref.setYear("1839");
        assertEquals("1839", ref.getYear());
    }

    @Test
    public void testPublisher() {
        ref.setPublisher("Otava");
        assertEquals("Otava", ref.getPublisher());
    }
    
    @Test
    public void testJournal() {
        ref.setJournal("test23");
        assertEquals("test23", ref.getJournal());
    }
    
    @Test
    public void testVolume() {
        ref.setVolume("3");
        assertEquals("3", ref.getVolume());
    }
    
    @Test
    public void testNumber() {
        ref.setNumber("123");
        assertEquals("123", ref.getNumber());
    }
    
    @Test
    public void testSeries() {
        ref.setSeries("test23");
        assertEquals("test23", ref.getSeries());
    }

    @Test
    public void testEdition() {
        ref.setEdition("3rd");
        assertEquals("3rd", ref.getEdition());
    }
    
    @Test
    public void testPages() {
        ref.setPages("12-35");
        assertEquals("12-35", ref.getPages());
    }

    @Test
    public void testMonth() {
        ref.setMonth("May");
        assertEquals("May", ref.getMonth());
    }

    @Test
    public void testNote() {
        ref.setNote("test23");
        assertEquals("test23", ref.getNote());
    }

}
