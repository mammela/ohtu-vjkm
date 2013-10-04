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
        
        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String add(@ModelAttribute("reference") Reference reference) {
        	if(reference.hasValidType() == true)
        		referenceService.addRef(reference);
        	else 
        		System.out.println("huono tyyppi:" + reference.getType()); //pitäis logata johonkin kunnolla
        	
        	return "redirect:/app/list";
        }
        
        @RequestMapping(value = "add", method = RequestMethod.GET)
        public String showAdd(Model model) { 
        	return "addNew";
        }
        
        @RequestMapping(value = "trash", method = RequestMethod.GET)
        public String trash(@RequestParam(value = "id") int id) {
        	referenceService.trashRef(id);
        	return "redirect:/app/list";
        }
        
        @RequestMapping(value = "trash", method = RequestMethod.POST)
        public String emptyTrash() {
        	referenceService.emptyTrash();
        	return "redirect:/app/list";
        }
        
        @RequestMapping(value="list", method=RequestMethod.GET)
        public String showReferences(Model model) {
        	List<Reference> refList = referenceService.findAllRefs(false);
		List<Reference> trashList = referenceService.findAllRefs(true);
		
        	if(refList.size() == 0) {
        		model.addAttribute("msg_reflist", "Empty reference list.");
        	}
        	if(trashList.size() == 0) {
        		model.addAttribute("msg_trashlist", "No trash.");
        	}
        	
		model.addAttribute("reflist", refList);
		model.addAttribute("trashlist", trashList);
		
		return "list";
        }
        
        @RequestMapping(value="bibtex", method=RequestMethod.GET)
        public String showBibtex(Model model) {
        	if(referenceService.findAllRefs().size() == 0) {
        		model.addAttribute("message", "Empty reference list.");
        	}
        	
		model.addAttribute("bibtex", referenceService.renderBibtex());   
		return "bibtex";
        }  
}