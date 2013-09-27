package fi.helsinki.jkmv;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ReferenceService {
    private References references;
    
    public ReferenceService(){
        this.references = new References();
    }
    
    /*
     * Viitteiden lisäysmetodi
     */ 
    public void add(Reference reference) {
        references.addReference(reference);
    }

    /*
     *  Kaikkien viitteiden palautus Stringeinä
     */
    public List<String> list() {
        List<String> refList = new ArrayList<String>();
        for (Reference item : references.getList()){
            
            // tulostus inproceedings-viitteelle
            if (item.getType().equals("Inproceedings")){
                refList.add(item.getKey() + ", " +item.getType() + ", " +
                        item.getAuthor() + ", " +item.getTitle() + ", " +
                        item.getBooktitle() + ", " + item.getYear());
            }            
        }
        return refList;
    }
}