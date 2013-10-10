package fi.helsinki.jkmv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BibtexController {
        
        @Autowired
        public ReferenceService referenceService;

        
        /** Add reference view (GET) */
        @RequestMapping(value = "add", method = RequestMethod.GET)
        public String showAdd(Model model) { 
                model.addAttribute("entrytypes", referenceService.getTypeNames());
                model.addAttribute("usedkeys", referenceService.getKeys());                
        	return "addNew";
        }
        
	/** Add reference handling (POST) */
        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String add(@ModelAttribute("reference") Reference reference, Model model) {
            
                /** if reference is valid, add data to reference service */
                if (referenceService.isValidReference(reference)){
                        referenceService.addRef(reference);
                        model.addAttribute("addok",  true);
                        model.addAttribute("savedkey",  reference.getKey());
                } else {
                        System.out.println(reference.toString());                    
                        model.addAttribute("addok", false);                    
                }        	
            
                model.addAttribute("entrytypes", referenceService.getTypeNames());
                model.addAttribute("usedkeys", referenceService.getKeys());                
        	return "addNew";
        }

	/** Ref to trash -> list */
        @RequestMapping(value = "trash", method = RequestMethod.GET)
        public String trash(@RequestParam(value = "id") int id) {
        	referenceService.trashRef(id);
        	return "redirect:/app/list";
        }
        
	/** Ref from trash -> list */
        @RequestMapping(value = "untrash", method = RequestMethod.GET)
        public String unTrash(@RequestParam(value = "id") int id) {
        	referenceService.unTrashRef(id);
        	return "redirect:/app/list";
        }
        
        /** Empty trash -> list */
        @RequestMapping(value = "emptytrash", method = RequestMethod.POST)
        public String emptyTrash() {
        	referenceService.emptyTrash();
        	return "redirect:/app/list";
        }
        
        /** List view */
        @RequestMapping(value="list", method=RequestMethod.GET)
        public String showReferences(Model model) {
        	List<Reference> refList = referenceService.findAllRefs(false);
		List<Reference> trashList = referenceService.findAllRefs(true);
		model.addAttribute("reflist", refList);
		model.addAttribute("trashlist", trashList);
		return "list";
        }
        
        /** Bibtex view */
        @RequestMapping(value="bibtex", method=RequestMethod.GET)
        public String showBibtex(Model model) {
                model.addAttribute("bibtex", referenceService.renderBibtex());
                return "bibtex";
        } 
}