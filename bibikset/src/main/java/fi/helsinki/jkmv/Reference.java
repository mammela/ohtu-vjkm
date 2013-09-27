package fi.helsinki.jkmv;

public class Reference {
    private String type;
    private String key;
    private String author;
    private String title;
    private String booktitle;
    private int year;

    public Reference(){
        // asetetaan toistaiseksi kaikki inproceedings-muotoon
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
    
    public void setYear(int year){
        this.year = year;
    }
    
    public int getYear() {
        return this.year;
    }
}