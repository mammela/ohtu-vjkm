package fi.helsinki.jkmv;

import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.beanutils.BeanUtils;

public class Validate {
    /* reference entry field names*/
    private static final String[] entryFields = {"abstract", "address", "annote", "author", "booktitle", 
                    "chapter", "crossref", "edition", "editor", "eprint", "howpublished",
                    "institution", "journal", "key", "month", "note", "number", 
                    "organization", "pages", "publisher", "school", "series", "title",
                    "type", "url", "volume", "year"}; 

    /* reference entry type names*/
    private static final String[] entryTypes = {"article", "book", "booklet", 
                    "conference", "inbook", "incollection", "inproceedings", "manual",
                    "mastersthesis", "misc", "phdthesis", "proceedings", "techreport",
                    "unpublished"};

    /* Required, alternative and optional fields */
    private static final Integer[][] reqFields = {{3, 22, 12, 26, 13},  //article
                                {3, 22, 19, 26, 13},    //book
                                {22, 13},               //booklet
                                {3, 22, 4, 26, 13},     //conference
                                {3, 22, 5, 19, 26, 13}, //inbook
                                {3, 22, 4, 19, 26, 13}, //incollection
                                {3, 22, 4, 26, 13},     //inproceedings
                                {22, 13},               //manual
                                {3, 22, 20, 26, 13},    //mastersthesis
                                {13},                   //misc
                                {3, 22, 20, 26, 13},    //phdthesis
                                {22, 26, 13},           //proceedings
                                {3, 22, 11, 13},        //techreport
                                {3, 22, 15, 13}};       //unpublished
    
    private static final Integer[][] altFields = {{},       //article
                                {3,8, 25,16},           //book                    
                                {},                     //booklet
                                {25,16},                //conference
                                {3,8, 5,18, 25,16},     //inbook
                                {25,16},                //incollection
                                {25,16},                //inproceedings
                                {},                     //manual
                                {},                     //mastersthesis
                                {},                     //misc
                                {},                     //phdthesis
                                {25,16},                //proceedings
                                {},                     //techreport
                                {}};                    //unpublished

    private static final Integer[][] optFields = {{25, 16, 18, 14, 15}, //article
                                {25, 21, 1, 7, 14, 15},             //book
                                {3, 10, 1, 14, 26, 15},             //booklet
                                {8, 25, 21, 18, 1, 14, 17, 19, 15}, //conference
                                {25, 21, 23, 1, 7, 14, 15},         //inbook
                                {8, 25, 21, 23, 5, 18, 1, 7, 14, 15},//incollection
                                {8, 25, 21, 18, 1, 14, 17, 19, 15}, //inproceedings
                                {3, 17, 1, 7, 14, 26, 15},          //manual
                                {23, 1, 14, 15},                    //mastersthesis
                                {3, 1, 14, 15},                     //misc
                                {23, 1, 14, 15},                    //phdthesis
                                {8, 25, 21, 1, 14, 19, 17, 15},     //proceedings
                                {23, 16, 1, 14, 15},                //techreport
                                {14, 26}};                          //unpublished

    private Map<String, Object> refObjects;

    
    /*
     * Constructor
     */
    public Validate(){
        getReferenceObjects();
    }

    
    /*
     * Return all entry type names. 
    */
    public String[] getTypeNames() {
        return this.entryTypes;
    } 
	
    /*
     * Get all reference data from the reference object using beans
     */
    private void getReferenceObjects(){
        try{
            Introspector.getBeanInfo(Reference.class, Object.class);
        } catch (Exception e){
            //do nothing
        }
    }

    
    /*
     * check that year value is between (1000-2999) 
     */
    private boolean isYearValid(String year){
        Pattern yearPat = Pattern.compile("[12][0-9]{3}");
        if (yearPat.matcher(year).matches()){
            return true;
        }
        return false;
    }
    
    
    /*
     * Return value for field
     */
    private String getFieldValue(String fieldName){
        if (refObjects.get(fieldName) != null){
            return refObjects.get(fieldName).toString();
        } else {
            return "";
        }
    }

    
    /*
     * Check if field has been assigned a value
     */
    private boolean isFieldSet(String fieldName){
        if(getFieldValue(fieldName) == ""){
            return false;
        }
        return true;
    }
    
    
    /*
     * Validation to check that all data is correct
     */
    public boolean validateEntry(Reference ref){
        /* try to get values from reference-class*/
        try{
            refObjects = BeanUtils.describe(ref);
        }catch(Exception e){ /* don't validate if this fails */  
            return false;
        }
        String entryType = ref.getEntryType();
        
        /* if not in our list, then return false (shouldn't happen)*/
        int typeIndex = ArrayUtils.indexOf(entryTypes, entryType);
        if (typeIndex == -1){
            return false;
        }
        
        List<Integer> reqFieldList = Arrays.asList(reqFields[typeIndex]);
        List<Integer> altFieldList = Arrays.asList(altFields[typeIndex]);
        List<Integer> optFieldList = Arrays.asList(optFields[typeIndex]);
            
        /* go through all required fields for valid entries */
        for (Integer i : reqFieldList){
            String reqField = entryFields[i];
            
            /* check if year is required and set correctly */
            if (reqField.equals("year") && !isYearValid(getFieldValue(reqField))){
                return false;
            }
            
            /*get index for alternative field*/
            Integer altIndex = altFieldList.indexOf(i);
            /* if alternative field available, check that only one of the is set */
            if (altIndex > -1){
                String altField = entryFields[altFieldList.get(altIndex+1)];
                if ((!isFieldSet(reqField) && !isFieldSet(altField)) ||
                    (isFieldSet(reqField) && isFieldSet(altField))){
                    return false;
                }
            } else if (!isFieldSet(reqField)){
                    return false;
            }
        }
            
        /* go through all optional fields for valid entries */
        for (Integer i : optFieldList){
            String optField = entryFields[i];
            
            /* check if optional year value is set and correct */
            if (optField.equals("year") && this.isFieldSet(optField) &&
                    !isYearValid(getFieldValue(optField))){
                return false;
            }

            /*get index for alternative field*/
            Integer altIndex = altFieldList.indexOf(i);
            /* if alternative field available, check that both are not set at the same time */
            if (altIndex > -1){
                String altField = entryFields[altFieldList.get(altIndex+1)];
                if (isFieldSet(optField) && isFieldSet(altField)){
                    return false;
                }
            }
        }           
        /* no false entries on required, alternative or optional fields so return true */
        return true;
    }
}
