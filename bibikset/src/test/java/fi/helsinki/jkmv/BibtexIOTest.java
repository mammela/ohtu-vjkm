package fi.helsinki.jkmv;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BibtexIOTest {
	
	ArrayList<Reference> list;
	
	
	public BibtexIOTest() {
		list = new ArrayList<Reference>();
	}
	
	@Before
	public void setUp() {
		Reference ref1 = new Reference();
		ref1.setEntryType("inproceedings");
		ref1.setKey("sauri2098");
		ref1.setAuthor("Pekka Sauri");
		ref1.setTitle("Perusetti");
		ref1.setBooktitle(null);
		
		Reference ref2 = new Reference();
		ref2.setEntryType("inproceedings");
		ref2.setKey("kondor1967");
		ref2.setAuthor("El Kondor Jyyströmi");
		ref2.setTitle("Panhuilut kolmen sepän patsaalla");
		ref2.setBooktitle("Tarinoita tampereelta");
		
		Reference ref3 = new Reference();
		ref3.setEntryType("inproceedings");
		ref3.setKey("aakkoset2000");
		ref3.setAuthor("Ä ä Ö ö");
		
		list.add(ref1);
		list.add(ref2);
		list.add(ref3);
	}
	
	@After
	public void tearDown() {
		list.clear();
	}
	
	@Test
	public void testFilterSpecialLetters() {
		BibtexIO bio = new BibtexIO(null);
		String filtered = bio.filterSpecialLetters("Ä ä Ö ö");
		
		assertEquals("{\\\"A} {\\\"a} {\\\"O} {\\\"o}", filtered);
	}
	
	@Test
	public void testRenderSingleTag() {
		StringBuilder sb = new StringBuilder();
		BibtexIO bio = new BibtexIO(null);
		bio.renderSingleTag(sb, "author", "Pekka Sauri Jyyströmi");
		
		assertEquals("  author = {Pekka Sauri Jyystr{\\\"o}mi},\n", sb.toString());
	}
	
	@Test
	public void testRenderSingleReference() {
		StringBuilder sb = new StringBuilder();
		BibtexIO bio = new BibtexIO(null);
		bio.renderSingleReference(sb, list.get(0));
		
		StringBuilder expected = new StringBuilder();
		expected.append("@inproceedings{sauri2098,\n");
		expected.append("  author = {Pekka Sauri},\n");
		expected.append("  title = {Perusetti},\n");
		expected.append("}\n\n");
		
		assertEquals(expected.toString(), sb.toString());
	}
	
	@Test
	public void testRenderToBibtex() {
		BibtexIO bio = new BibtexIO(list);
		String result = bio.renderToBibtex();
		
		String expected = 
		"@inproceedings{sauri2098,\n" +
		"  author = {Pekka Sauri},\n" +
		"  title = {Perusetti},\n" +
		"}\n" +
		"\n" +
		"@inproceedings{kondor1967,\n" +
		"  author = {El Kondor Jyystr{\\\"o}mi},\n" +
		"  title = {Panhuilut kolmen sep{\\\"a}n patsaalla},\n" +
		"  booktitle = {Tarinoita tampereelta},\n" +
		"}\n" +
		"\n" +
		"@inproceedings{aakkoset2000,\n" +
		"  author = {{\\\"A} {\\\"a} {\\\"O} {\\\"o}},\n" +
		"}\n" +
		"\n";
		
		assertEquals(expected, result);
	}
}
