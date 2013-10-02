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
            //tulostukset eri viite-tiedoille
            if (item.getType().equals("Inproceedings")){
                refList.add(item.getKey() + ", " +item.getType() + ", " +
                        item.getAuthor() + ", " +item.getTitle() + ", " +
                        item.getBooktitle() + ", " + item.getYear());
            } else if (item.getType().equals("Book")){
                refList.add(item.getKey() + ", " +item.getType() + ", " +
                        item.getAuthor() + ", " +item.getTitle() + ", " +
                        item.getPublisher() + ", " + item.getYear());
            } else if (item.getType().equals("Article")){
                refList.add(item.getKey() + ", " +item.getType() + ", " +
                        item.getAuthor() + ", " +item.getTitle() + ", " +
                        item.getJournal() + ", " + item.getYear());
            } else {
                refList.add(item.getKey() + ", " +item.getTitle());
            }            
        }
        return refList;
    }
    
    public String renderBibtex() {
    	   BibtexIO bio = new BibtexIO(references.getList());
    	   return bio.renderToBibtex();
    }
}