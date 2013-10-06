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
                
    var orFields =  [[],                            //article
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
            console.log(receivedValue + " received value");

            var formType = "";
            
            var entryTypeLoc = entryTypes.indexOf(receivedValue);
            if (entryTypeLoc > -1){
                formType = fillForm(reqFields[entryTypeLoc], 
                            orFields[entryTypeLoc], optFields[entryTypeLoc]);
            }
            else {
                alert("INVALID VALUE");
                formType = "INVALID VALUE";
                console.log("INVALID VALUE in changeValue()");
            }
                         
            //formType += "<input name='Add' class='submit' type='submit' value='Add'/>";    
            document.getElementById('addForm').innerHTML = formType;             
        }
        

        /*
         * Fill the form with required, alternative & optional fields
         */
        function fillForm(reqFields, orFields, optFields) {
            var retVal = "<div class='formFields' id='req'><p>Required:</p>"
            retVal += generateFields(reqFields, orFields, true);
            retVal += "</div><div class='formFields' id='opt'><p/><p>Optional:</p>"
            retVal += generateFields(optFields, orFields, false);
            retVal += "</div>"
            return retVal;
        }
        
        
        /*
         * subfunction to generate fields
         */
        function generateFields(fields, orFields, required) {
            var retVal = "";
            for (var i=0;i<fields.length;i++) {
                var field = entryFields[fields[i]];

                //if there is an alternative field, display it also
                var orFieldIndex = orFields.indexOf(fields[i]);
                if (orFieldIndex > -1){
                    var field2 = entryFields[orFields[orFieldIndex+1]]; //get fields pair
                    retVal += generateField(field, false, false); //check required fields elsewhere
                    retVal += "<div id='or'>";
                    retVal += generateField(field2, false , true); //check required fields elsewhere
                    retVal += "</div>"
                } else {    //no alternative field, display only first
                    retVal += generateField(field, required, false);                    
                }
            }
            return retVal;
        }
        
        /*
         * subfunction to generate one label and input
         */
        function generateField(field, required, addOr) {
                        var retVal;
            var orText = "";
            var pattern = "";
            var title = "";
            var fieldWeb = "";
            var checkKey = "";
            var reqVar = "";
            var keyErrorMsg = "";
            
            if (required==true){    // append if needed
                reqVar = " required";
            }            
            if (addOr){
                orText = "or ";    // append if needed
            }

            fieldWeb = forWebSite(field);
            if (field=="year") {    // do a pattern check for year
                pattern=" pattern='[12][0-9]{3}'";
                title=" title='enter year with 4 digits'";
            }
            if (field=="author" || field=="year") { //when author or year is changed by user, bibtex key is generated
                checkKey = " onkeyup='generateUniqueKey(this)' onchange='generateUniqueKey(this)'";
            }
            else if (field=="abstract") {
                field = "abstractEntry";    //abstract entry type is internally abstractEntry
            }
            else if (field=="key") {
                fieldWeb = "Bibtex key";
                checkKey = " onkeyup='keyChangedByUser(this)' onchange='keyChangedByUser(this)'"; // check key is user changes it
                keyErrorMsg = "<div id='keyErrorMsg'></div>";
            }
            
            // generate the label and input for the form
            retVal = "<label>" + orText + fieldWeb + "<input" + checkKey +
                    " type='text' name='" + field + "' id='"+field +"' " + pattern + title + 
                    reqVar + "></label>"+keyErrorMsg;            
            
            return retVal;
        }
       
        /*
         * Capitalize the first letter of text
         */
        function forWebSite(text) {
            text = text.charAt(0).toUpperCase() + text.slice(1);
            console.log(text + " forWebSite");
            return text;
        }

        /*
         * This will prevent form submit if bibtex key is not unique
         */
        function isKeyValid(){
            var keyValue = document.getElementById("key").value;
            if(usedKeys.indexOf(keyValue)==-1){
                return true;
            }
            return false;
            
        }
        
        /* 
         * validate bibtex key every time user changes the key's value
         */
        function keyChangedByUser() {
            var keyValue = document.getElementById("key").value;
            if(usedKeys.indexOf(keyValue)>-1){
                document.getElementById('keyErrorMsg').innerHTML = "Entered key is not unique!";
                document.getElementById('key').style.backgroundColor='red';
            } else {
                document.getElementById('keyErrorMsg').innerHTML = "";
                document.getElementById('key').style.backgroundColor='white';
            }
        }    
        
        /*
         * create unique bibtex key by using author and key-fields
         */
        function generateUniqueKey(){
            var authorVal = document.getElementById("author").value.toUpperCase();
            authorVal = authorVal.replace("AND","").replace("JA","");
            var yearVal = document.getElementById("year").value;            
            var oneCharForEachWord = authorVal.match(/\b\w/g).join(''); // take only first letters from the names
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
            
            // try to find unique key by adding characters a-z to suggested key
            while (true){                
                var newValue = result+itoa(addResult);
                if(usedKeys.indexOf(newValue)==-1){
                    document.form.key.value=newValue;
                    return;
                } else if (addResult<122){ // ASCII 'z'
                    addResult++;
                } else {
                    result = result+"a"; // if not found, add letter and try again
                }                
            }
        }
                
        /*
         * Convert int to char
         */
        function itoa(i){
            return String.fromCharCode(i);
        }