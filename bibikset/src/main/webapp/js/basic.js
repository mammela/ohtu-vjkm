

    var entryFields = ["abstract", "address", "annote", "author", "booktitle", "editor", "journal", "key", "keywords", "month", "number", "pages","publisher", "series", "title", "type", "url", "volume", "year"];
    
/*
     * Required fields
     */
    var bookFields = [3, 14, 12, 18];
    var articleFields = [3, 14, 6, 18];
    var inproceedingFields = [3, 14, 4, 18];
    var manualFields = [14];
    
    var initValue;
    var text="";
    
        /*
         * function to initialize form onload of the body
         */
        function init() {
            initValue = "inproceeding";
            text = "inproceeding";
            changeValue(initValue);
        }
    
        /*
         * Function to change form on the website
         * 
         * All required fields
         */ 
        function changeValue(receivedValue) {
            
            console.log(receivedValue + " received value");

            var formType= "";
            var numberValidate="type= 'number'";
            
            if (receivedValue==="book") {
                console.log(receivedValue+" changeValue() if founded");
                for (var i=0;i<bookFields.length;i++) {
                    var field = entryFields[bookFields[i]];
                    
                    if (field==="year") {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' pattern= '[0-9]*' title= 'numbers only' required></label>";
                    }
                    else {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' required></label>";
                    }
                }
            }
            else if (receivedValue==="inproceeding") {
                console.log(receivedValue+" changeValue() if founded");
                for (var i=0;i<inproceedingFields.length;i++) {
                    var field = entryFields[inproceedingFields[i]];
                    
                    if (field==="year") {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' pattern= '[0-9]*' title= 'numbers only' required></label>";
                    }
                    else {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' required></label>";
                    }
                }
            }
            else if (receivedValue==="article") {
                console.log(receivedValue+" changeValue() if founded");
                for (var i=0;i<articleFields.length;i++) {
                    var field = entryFields[articleFields[i]];
                    
                    if (field==="year") {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' pattern= '[0-9]*' title= 'numbers only' required></label>";
                    }
                    else {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' required></label>";
                    }
                }
            }
            else if (receivedValue==="manual") {
                console.log(receivedValue+" changeValue() if founded");
                for (var i=0;i<manualFields.length;i++) {
                    var field = entryFields[manualFields[i]];
                    
                    if (field==="year") {
                        formType += "<label>"+forWebSite(field) + "<input type='number' name='" +field+ "' pattern= '[0-9]*' title= 'numbers only' required></label>";
                    }
                    else {
                        formType += "<label>"+forWebSite(field) + "<input type='text' name='" +field+ "' required></label>";
                    }
                }
            }
            else {
                alert("INVALID VALUE");
                formType += "INVALID VALUE";
                console.log("INVALID VALUE in changeValue()");
            }
            
        formType += "<input class='submit' type='submit' value='Add'/>";    
        document.getElementById('addForm').innerHTML = formType;
             
        }
        
        /*
         * Funtion to capitalize the first letter of text
         */
        function forWebSite(text) {
            text = text.charAt(0).toUpperCase() + text.slice(1);
            console.log(text + " forWebSite");
            return text;
        }
                
