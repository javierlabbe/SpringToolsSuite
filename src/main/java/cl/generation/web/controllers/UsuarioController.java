package cl.generation.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //simepre va en los controladores //mas pensado para trabajo en el mismo sistema internamente
@RequestMapping("/usuario") // a las rutas de abajo se les antepone usuario (ruta generica)
public class UsuarioController { // controlador de usuario. No todos los objetos llevan controlador, solo aquellos en los que necesitemos hacer al menos 
									// una de las 4 operaciones basicas CRUD
	//https://localhost:8080/usuario/
	@RequestMapping("/") //para agregar rutas
	public String getUsuario() {
		System.out.println("metodo de obetner usuario");
		return "index.jsp";
	}
	
	//https://localhost:8080/usuario/home
	@RequestMapping("/home")
	public String home() {
		System.out.println("En el metodo home");
		return "home.jsp";
	}
	
	//https://localhost:8080/usuario/javier/labbe
	@RequestMapping("/javier/labbe")
	public String jlabbe() {
		System.out.println("En el metodo de javier");
		return "";
	}
}
