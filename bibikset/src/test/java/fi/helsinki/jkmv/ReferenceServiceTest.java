package fi.helsinki.jkmv;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceServiceTest {
	
	ReferenceService refServ;
	
	public ReferenceServiceTest() {
	}
	
	@Before
	public void setUp() {
		refServ = new ReferenceService(true, true);
		
		Reference ref = new Reference();
		ref.setEntryType("book");
		ref.setKey("sauri98");
		ref.setAuthor("Pekka Sauri");
		refServ.addRef(ref);
		
		Reference ref2 = new Reference();
		ref2.setEntryType("article");
		ref2.setEntryType("artikkeli1");
		ref2.setKey("kirja02");
		ref2.setAuthor("Timo Soini");
		refServ.addRef(ref2);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testAdd() {
		assertEquals(refServ.findAllRefs().size(), 2);
		
		Reference ref = new Reference();
		ref.setEntryType("book");
		ref.setKey("sauri01");
		refServ.addRef(ref);
		
		assertEquals(refServ.findAllRefs().size(), 3);
	}
	
	@Test
	public void testFindByKey() {
		Reference ref = refServ.findByKey("sauri98");
		
		assertNotNull(ref);
		assertEquals(ref.getEntryType(), "book");
		assertEquals(ref.getAuthor(), "Pekka Sauri");
	}
	
	@Test
	public void testTrashRef() {
		// initial state
		assertEquals(refServ.findAllRefs(false).size(), 2);
		assertEquals(refServ.findAllRefs(true).size(), 0);
		
		// trash one reference
		Reference ref = refServ.findByKey("sauri98");
		refServ.trashRef(ref.getId());
		ref = refServ.findByKey("sauri98");
		
		// state after one trash
		assertTrue(ref.isInTrash());
		assertEquals(refServ.findAllRefs(false).size(), 1);
		assertEquals(refServ.findAllRefs(true).size(), 1);
		
		// real delete
		refServ.emptyTrash();
		ref = refServ.findByKey("sauri98");
		
		// state after deleting trash
		assertNull(ref);
		assertEquals(refServ.findAllRefs(false).size(), 1);
		assertEquals(refServ.findAllRefs(true).size(), 0);
	}
	
}
