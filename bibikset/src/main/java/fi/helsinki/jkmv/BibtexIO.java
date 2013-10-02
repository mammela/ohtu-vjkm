package fi.helsinki.jkmv;

import java.util.List;
import java.util.regex.Matcher;

public class BibtexIO {
	private List<Reference> refList;
	
	public BibtexIO(List<Reference> list) {
		this.refList = list;
	}
	
	public String renderToBibtex() {
		StringBuilder sb = new StringBuilder();
		for(Reference ref : refList) {
			renderSingleReference(sb, ref);
		}
		
		return sb.toString();
	}
	
	protected void renderSingleReference(StringBuilder sb, Reference ref) {
		// head
		sb.append("@");
		sb.append(ref.getType());
		sb.append("{");
		sb.append(ref.getKey());
		sb.append(",\n");
		
		// entrys
		renderSingleTag(sb, "author", ref.getAuthor());
		renderSingleTag(sb, "editor", ref.getEditor());
		renderSingleTag(sb, "publisher", ref.getPublisher());
		renderSingleTag(sb, "title", ref.getTitle());
		renderSingleTag(sb, "booktitle", ref.getBooktitle());
		renderSingleTag(sb, "journal", ref.getJournal());
		renderSingleTag(sb, "volume", ref.getVolume());
		renderSingleTag(sb, "number", ref.getNumber());
		renderSingleTag(sb, "series", ref.getSeries());
		renderSingleTag(sb, "edition", ref.getEdition());
		renderSingleTag(sb, "pages", ref.getPages());
		renderSingleTag(sb, "month", ref.getMonth());
		renderSingleTag(sb, "year", ref.getYear());
		renderSingleTag(sb, "note", ref.getNote());
		
		// end
		sb.append("}\n\n");
	}
	
	protected void renderSingleTag(StringBuilder sb, String entry, String value) {
		// do not render empty entrys
		if(value == null || value.trim().equals(""))
			return;
		
		value = filterSpecialLetters(value);
		
		sb.append("  ");
		sb.append(entry);
		sb.append(" = {");
		sb.append(value);
		sb.append("},\n");
	}
	
	protected String filterSpecialLetters(String value) {
		value = value.replaceAll( "\u00C4", Matcher.quoteReplacement("{\\\"A}") ); // Ä
		value = value.replaceAll( "\u00E4", Matcher.quoteReplacement("{\\\"a}") ); // ä
		value = value.replaceAll( "\u00D6", Matcher.quoteReplacement("{\\\"O}") ); // Ö
		value = value.replaceAll( "\u00F6", Matcher.quoteReplacement("{\\\"o}") ); // ö
		
		return value;
	}
	
}