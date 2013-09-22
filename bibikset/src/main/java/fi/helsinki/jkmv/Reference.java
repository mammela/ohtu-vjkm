package fi.helsinki.jkmv;

public class Reference {
    private String type;
    private String key;
    private String author;
    private String title;
    private String booktitle;
    private int year;

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
}