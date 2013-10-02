package fi.helsinki.jkmv;

public class Reference {
	
	// static final
	public static final String TYPE_ARTICLE = "Article";
	public static final String TYPE_BOOK = "Book";
	public static final String TYPE_INPROCEEDINGS = "Inproceedings";
	
	// basic fields
	private String type;
	private String key;
	
	// other
	private String author;
	private String editor;
	private String publisher;
	private String title;
	private String booktitle;
	private String journal;
	private String volume;
	private String number;
	private String series;
	private String edition;
	private String pages;
	private String month;
	private String year;
	private String note;
	
	/*____________________________________________________________________
	* Constructor
	*/
	
	public Reference(){
            this.type = TYPE_ARTICLE;
	}
	
	/*____________________________________________________________________
	* Services
	*/
	
	public boolean hasValidType() {
		if(type.equalsIgnoreCase(TYPE_ARTICLE))
			return true;
		else if(type.equalsIgnoreCase(TYPE_BOOK))
			return true;
		if(type.equalsIgnoreCase(TYPE_INPROCEEDINGS))
			return true;
		
		return false;
	} 
	
	
	/*____________________________________________________________________
	* Get/Set
	*/
	
	public String getType(){
		return this.type;
	}    
	
	public void setType(String type){
		this.type = type;    
	}
	
	public String getKey(){
		return this.key;
	}    
	
	public void setKey(String key){
		this.key = key;    
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getBooktitle(){
		return this.booktitle;
	}
	
	public void setBooktitle(String booktitle){
		this.booktitle = booktitle;
	}
	
	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year){
		this.year = year;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
}