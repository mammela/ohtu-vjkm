package fi.helsinki.jkmv;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestSpring {
	
	@RequestMapping(value = "springtest", method = RequestMethod.GET)
	public String test(Model model) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("yks");
		list.add("kaks");
		list.add("kol");
		
		model.addAttribute("message", "Ke Pasa?");
		model.addAttribute("testlist", list);
		return "TestSpringView";
	}

}
