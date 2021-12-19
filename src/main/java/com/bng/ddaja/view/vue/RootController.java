package com.bng.ddaja.view.vue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping({"/vue/**"})
	public String root(){
		
		return "vue/index";
	}
	
	@GetMapping({"/"})
    public String vue(){
		
        return "forward:vue/";
    }
}
