package cl.generation.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //sin requestMapping
public class IndexController {
	//localhost:8080
	@GetMapping("/") //al dejarlo vacio la primera peticion es la ruta principal
	public String home() {
		return "index.jsp";
	}
}
