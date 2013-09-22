package fi.helsinki.jkmv;

import java.util.ArrayList;
import java.util.List;


public class References {
    List<Reference> referenceList;
    
    public References(){
        this.referenceList = new ArrayList<Reference>();
    }
    
    /*
     * Viitteiden lisäysmetodi
     */
    public void addReference(Reference reference){
        this.referenceList.add(reference);
    }
    
    /*
     *  Kaikkien viitelistan olioiden palautus
     */
    public List<Reference> getList(){
        return this.referenceList;
    }
}