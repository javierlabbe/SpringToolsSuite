package cl.generation.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home") //aqui va RequestMapping
public class HomeController { //encargado de desplegar home.jsp
	
	@GetMapping("") //peticion de ruta para despliegue
	public String home() {
		return "home.jsp";
	}
}
