package fi.helsinki.jkmv;

public class Reference {
	private String type;
	private String key;
	
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
	private String note;
	private String year;
	
	public Reference(){
            this.type = "Inproceedings";
	}
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setYear(String year){
		this.year = year;
	}
	
	public String getYear() {
		return this.year;
	}
}