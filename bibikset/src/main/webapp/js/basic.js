                //         0           1           2       3           4           5           6           7       8           9           10              11          12        13     14       15       16           17           18        19          20        21        22      23     24       25       26      
    var entryFields = ["abstract", "address", "annote", "author", "booktitle", "chapter", "crossref", "edition", "editor", "eprint", "howpublished", "institution", "journal", "key", "month", "note", "number", "organization", "pages", "publisher", "school", "series", "title", "type", "url", "volume", "year"];
    var entryTypes = ["article", "book", "booklet", "conference", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport","unpublished"];

    /*
     * Required, alternative and optional fields:
     */
    var reqFields = [[3, 22, 12, 26, 13],           //article
                    [3, 22, 19, 26, 13],            //book
                    [22, 13],                       //booklet
                    [3, 22, 4, 26, 13],             //conference
                    [3, 22, 5, 19, 26, 13],         //inbook
                    [3, 22, 4, 19, 26, 13],         //incollection
                    [3, 22, 4, 26, 13],             //inproceedings
                    [22, 13],                       //manual
                    [3, 22, 20, 26, 13],            //mastersthesis
                    [13],                           //misc
                    [3, 22, 20, 26, 13],            //phdthesis
                    [22, 26, 13],                   //proceedings
                    [3, 22, 11, 13],                //techreport
                    [3, 22, 15, 13]];               //unpublished
                
    var altFields = [[],                            //article
                    [3,8, 25,16],                   //book                    
                    [],                             //booklet
                    [25,16],                        //conference
                    [3,8, 5,18, 25,16],             //inbook
                    [25,16],                        //incollection
                    [25,16],                        //inproceedings
                    [],                             //manual
                    [],                             //mastersthesis
                    [],                             //misc
                    [],                             //phdthesis
                    [25,16],                        //proceedings
                    [],                             //techreport
                    []];                            //unpublished
                    
    var optFields = [[25, 16, 18, 14, 15],              //article
                    [25, 21, 1, 7, 14, 15],             //book
                    [3, 10, 1, 14, 26, 15],             //booklet
                    [8, 25, 21, 18, 1, 14, 17, 19, 15], //conference
                    [25, 21, 23, 1, 7, 14, 15],         //inbook
                    [8, 25, 21, 23, 5, 18, 1, 7, 14, 15],   //incollection
                    [8, 25, 21, 18, 1, 14, 17, 19, 15], //inproceedings
                    [3, 17, 1, 7, 14, 26, 15],          //manual
                    [23, 1, 14, 15],                    //mastersthesis
                    [3, 1, 14, 15],                     //misc
                    [23, 1, 14, 15],                    //phdthesis
                    [8, 25, 21, 1, 14, 19, 17, 15],     //proceedings
                    [23, 16, 1, 14, 15],                //techreport
                    [14, 26]];                          //unpublished
        
        
        /*
         * Always reload the page with current reference type selection 
         * and also load usedkeys script from addNew document
         */
        function init(){
            var scripts = document.getElementsByTagName("script");
            eval(scripts[0].innerHTML);

            var e = document.getElementsByName("entryType");
            var selection = e[0].options[e[0].selectedIndex].text;
            if (selection == null){
                selection = "book";
            }                
            changeValue(selection);  
        }       
        
        
        /*
         * Change form items for the website
         */ 
        function changeValue(receivedValue) {           
            var formType = "";            
            var entryTypeLoc = entryTypes.indexOf(receivedValue);
            if (entryTypeLoc > -1){
                formType = fillForm(reqFields[entryTypeLoc], 
                            altFields[entryTypeLoc], optFields[entryTypeLoc]);
            }
            else {
                alert("INVALID VALUE");
                formType = "INVALID VALUE";
            }                        
            document.getElementById('addForm').innerHTML = formType;             
        }

        /*
         * Swap input fields when select has changed
         * & hide non-selected
         */ 
        function changeInput(origField, newField) {
            var inputOldField = document.getElementById(origField+"Input");
            var inputNewField = document.getElementById(newField+"Input");
            if (inputOldField.disabled){
                inputNewField.style.display="none";
                inputOldField.disabled=false;
                inputOldField.value = inputNewField.value;
                inputNewField.value = "";
                inputNewField.disabled=true;
                inputOldField.style.display="inline";
            } else {
                inputOldField.style.display="none";
                inputNewField.disabled=false;
                inputNewField.value = inputOldField.value;
                inputOldField.value = "";
                inputOldField.disabled=true;
                inputNewField.style.display="inline";
            }
        }
        

        /*
         * Fill the form with required, alternative & optional fields
         */
        function fillForm(reqFields, altFields, optFields) {
            var retVal = "<table id='reqTable'><tr><th>Required</th><th></th></tr>"
            retVal += generateFields(reqFields, altFields, true);
            retVal += "<table id='optTable'><tr><th>Optional</th><th></th></tr>"
            retVal += generateFields(optFields, altFields, false);
            return retVal;
        }
        
        
        /*
         * Subfunction to generate fields
         */
        function generateFields(fields, altFields, required) {
            var retVal = "";
            for (var i=0;i<fields.length;i++) {
                var fieldName = entryFields[fields[i]];
                /*if there is an alternative field, display it also */
                var altFieldIndex = altFields.indexOf(fields[i]);
                if (altFieldIndex > -1){
                    /* get fields pair */
                    var altFieldName = entryFields[altFields[altFieldIndex+1]];
                    
                    /* display two fields on a select*/
                    retVal += generateSelectField(fieldName, altFieldName, required);
                    
                } else {    /* no alternative field, display only first */
                    retVal += generateField(fieldName, required);                    
                }
            }
            return retVal;
        }
        
        
        /*
         * Generate one label and input
         */
        function generateField(fieldName, required) {
            var retVal = "<tr><td><label>" + getFriendlyName(fieldName) + "</label></td><td>" +
                    "<input " + generateInputParams(fieldName, required) + " />" +
                    getKeyErrorMsg(fieldName) + "</td></tr>";
            return retVal;
        }

        
        /* 
         * Generate the select and two inputs
         */
        function generateSelectField(fieldName, altFieldName, required) {
            var retVal = "<tr><td><select onchange=\"changeInput('"+fieldName+"','"+ altFieldName+"');\">"+
                    "<option value='" + fieldName + "'>" + getFriendlyName(fieldName) + "</option>" +
                    "<option value='" + altFieldName + "'>" + getFriendlyName(altFieldName) + "</option></select></td><td>" +
                    "<input " + generateInputParams(fieldName, required) + " />" +
                    "<input " + generateInputParams(altFieldName, required) + 
                    " style='display: none;' disabled='true' /></td></tr>";
            return retVal;
        }
       

        /*
         * Get name for the label/select on form
         */
        function getFriendlyName(fieldName) {
            /* if key, return friendier name*/
            if (fieldName=="key"){
                return "Bibtex key";
            }
            /* Capitalize the first letter of text */
            return fieldName.charAt(0).toUpperCase() + fieldName.slice(1);
        }


        
        /* 
         * Fill additional option for input 
         */
        function generateInputParams(fieldName,required) {
            /* our abstract entry type is internally abstractEntry */
            if (fieldName=="abstract") {
                fieldName = "abstractEntry";
            }
            /* fill type, name and id for input*/
            var retVal = "type='text' name='" + fieldName + "' id='"+fieldName +"Input'";
            /* if field is required, add required param*/
            if (required==true){
                retVal += " required";
            }                        
            /* when author, editor or year is changed by user, bibtex key is generated*/
            if (fieldName=="author" || fieldName=="editor" || fieldName=="year") {
                retVal += " onkeyup='generateUniqueKey(this)' onchange='generateUniqueKey(this)'";
            }            
            /* do a pattern check for year */
            if (fieldName=="year") { 
                retVal += " pattern='[12][0-9]{3}' title='enter year with 4 digits'";
            }                                    
            /* check key if user changes it */
            else if (fieldName=="key") {
                retVal += " onkeyup='keyChangedByUser(this)' onchange='keyChangedByUser(this)'";
            }
            return retVal;
        }
        


        /*
         * create unique bibtex key by using author and key-fields
         */
        function generateUniqueKey(){
            var nameVal = document.getElementById("authorInput").value.toUpperCase();
            if (nameVal == ""){
                nameVal = document.getElementById("editorInput").value.toUpperCase();
            }
            nameVal = nameVal.replace("AND","").replace("JA","");
            var yearVal = document.getElementById("yearInput").value;            
            var oneCharForEachWord = nameVal.match(/\b\w/g).join(''); // take only first letters from the names
            if (oneCharForEachWord==null){
                oneCharForEachWord="";
            }
            var yearShort = "";
            if (yearVal.length==4){ //take only 2 last numbers
                yearShort = yearVal.slice(-2);
            }

            var result = oneCharForEachWord + yearShort;
            if(usedKeys.indexOf(result)==-1){
                document.form.key.value=result;
                return;
            }
            
            var addResult = 97; // ASCII 'a'
            
            /* try to find unique key by adding characters a-z to suggested key */
            while (true){                
                var newValue = result+itoa(addResult);
                if(usedKeys.indexOf(newValue)==-1){
                    document.form.key.value=newValue;
                    return;
                } else if (addResult<122){ /* ASCII 'z' */
                    addResult++;
                } else {
                    result = result+"a"; /* if not found, add letter and try again */
                }                
            }
        }


        /* 
         * validate bibtex key every time user changes the key's value
         */
        function keyChangedByUser() {
            if (isKeyValid()){
                document.getElementById('keyErrorMsg').innerHTML = "";
                document.getElementById('keyInput').style.backgroundColor='white';
            } else {
                document.getElementById('keyErrorMsg').innerHTML = "Entered key is not unique!";
                document.getElementById('keyInput').style.backgroundColor='red';
            }
        }    



        /*
         * Check if the bibtex key is unique
         */
        function isKeyValid(){
            var keyValue = document.getElementById("keyInput").value;
            if(usedKeys.indexOf(keyValue)==-1){
                return true;
            }
            return false;            
        }


        /*
         * Add div for non-unique key error message
         */
        function getKeyErrorMsg(field){
            if (field == "key"){
                return "<div id='keyErrorMsg'></div>";                
            }
            return "";
        }


        /*
         *  Generate a running field identifier for css
         */
        function generateFieldDiv(index, required){
            if (required){
                return " id='r" + index + "'";
            } else {
                return " id='o" + index + "'";
            }
        }


        /*
         * Convert int to char
         */
        function itoa(i){
            return String.fromCharCode(i);
        }