package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Phim;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/phim")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		Phim tp = new Phim("abc", "test");
		System.out.println("Ma phim: " + tp.maPhim);
		model.addAttribute("tp", tp.maPhim);
		
		return "phimtest";
	}

	

	

}
