package medicineNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import medicineNote.model.mapper.TokenMapper;

@Controller
@RequestMapping("/medicineNote/setToken")
public class SetTokenController {
	
	@Autowired
	TokenMapper tm;
	
	@GetMapping("/")
	public String setToken() {
		return "setToken";
	}
	
	@PostMapping("/confirm")
	public String confirm(@RequestParam String token, Model m) {
		tm.updateToken(token);
		return "redirect:/medicineNote/";
	}
}
