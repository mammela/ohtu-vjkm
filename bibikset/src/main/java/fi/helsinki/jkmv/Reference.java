package fi.helsinki.jkmv;

import javax.persistence.Entity;
import javax.persistence.Id;

/** 
* Object representing single reference. ORM mapped to the DB.
*/
@Entity
public class Reference {
	
	// basic fields
	@Id
	private int id;

        private boolean inTrash;
	private String entryType;
	private String key;
	
	// other data entries
	private String abstractEntry, address, annote, author, booktitle, chapter, crossref,
                edition, editor, eprint, howpublished, institution, journal,
                month, note, number, organization, pages, publisher, school,
                series, title, type, url, volume, year;	

        
	/*____________________________________________________________________
	* Constructor
	*/
	
	public Reference(){
            this.entryType = "misc";
            this.inTrash = false;
	}
		
	
	/*____________________________________________________________________
	* Get/Set
	*/
	
	public int getId(){
		return this.id;
	}    
	
	public void setId(int id){
		this.id = id;    
	}
	
	public boolean isInTrash(){
		return this.inTrash;
	}    
	
	public void setInTrash(boolean inTrash){
		this.inTrash = inTrash;    
	}
	
	public String getEntryType(){
		return this.entryType;
	}    
	
	public void setEntryType(String entryType){
		this.entryType = entryType;    
	}
	
	public String getKey(){
		return this.key;
	}    
        
	public void setKey(String key){
		this.key = key;    
	}
                
	public String getAbstractEntry(){
		return this.abstractEntry;
	}
	
	public void setAbstractEntry(String abstractEntry){
		this.abstractEntry = abstractEntry;
	}	
        
        public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
        
	public String getAnnote(){
		return this.annote;
	}
	
	public void setAnnote(String annote){
		this.annote = annote;
	}

        public String getAuthor(){
		return this.author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}

	public String getBooktitle(){
		return this.booktitle;
	}
	
	public void setBooktitle(String booktitle){
		this.booktitle = booktitle;
	}
	
	public String getChapter(){
		return this.chapter;
	}
	
	public void setChapter(String chapter){
		this.chapter = chapter;
	}
	
	public String getCrossref(){
		return this.crossref;
	}
	
	public void setCrossref(String crossref){
		this.crossref = crossref;
	}
	
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
        
	public String getEprint() {
		return eprint;
	}

	public void setEprint(String eprint) {
		this.eprint = eprint;
	}

	public String getHowpublished() {
		return howpublished;
	}

	public void setHowpublished(String howpublished) {
		this.howpublished = howpublished;
	}
               
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
        
	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
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

        public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrganization() {
		return journal;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
        
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSchool(){
		return this.school;
	}
	
	public void setSchool(String school){
		this.school = school;
	}
        
	public String getSeries(){
		return this.series;
	}
	
	public void setSeries(String series){
		this.series = series;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
        
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}

        public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setYear(String year){
		this.year = year;
	}
	
	public String getYear() {
		return this.year;
	}
	
}