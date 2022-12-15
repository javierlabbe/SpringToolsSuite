package cl.generation.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.generation.web.models.Usuario;
import cl.generation.web.services.UsuarioServiceImpl;

@Controller
@RequestMapping("/registro")
//http://localhost:8080/registro/
public class RegistroController {
	/*
	 * http://localhost:8080/registro/usuario GET -> desplegar el jsp
	 * http://localhost:8080/registro/usuario POST -> capturar los datos en el
	 * controlador
	 * 
	 * http://localhost:8080/registro/login GET -> desplegar el login.jsp
	 * http://localhost:8080/registro/login POST -> capturar los datos(email y
	 * pasword) en el controlador
	 */	
	
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	//1. capturar la url 
	@GetMapping("/usuario")
	public String mostrarformulario() {
		//y desplegar el jsp (controlador)
		return "registro.jsp";
	}
	//2. usuario llena el formulario (vista)
	//3. se envia el formulario (vista) al controlador (boton enviar)
	//4. se captura la url,
	
	//http://localhost:8080/registro/formulario
	//requestMapping permite peticiones get o post
	@PostMapping("/usuario") //antes request con /formulario
	//5. capturar los parametros @RequestParam
	public String guardarFormulario(@RequestParam("nombre") String nombre,
			@RequestParam("nick") String nick,
			@RequestParam("correo") String correo,
			@RequestParam("pass") String pass,
			@RequestParam("pass2") String pass2,
			Model model) { //permite enviar respuesta del backend al frontend (usado con libreria C)
		System.out.println(nombre+" "+correo+" "+pass+" "+pass2);
		//instanciar objeto
		
		//equals es para comparar String; ==
		if(pass.equals(pass2)) {
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setNick(nick);
			usuario.setCorreo(correo);
			usuario.setPassword(pass);
			usuario.setPassword2(pass2);
			//6. enviar a BD
			Boolean resultado = usuarioServiceImpl.guardarUsuario(usuario);
			
			if (resultado) { //si es verdadero
				return "login.jsp"; //enviar a una vista		
			} else {
				model.addAttribute("msgError", "Email ya registrado");
				return "registro.jsp";
			}
			
		} else {
			model.addAttribute("msgError", "Passwords distintos");
			return "registro.jsp";
		}
		
	}
	
	//desplegar jsp
	//con getmapping podemos pasar parametros por url. 
	//Peticion de ruta sin parametros (o con parametros por url)
	@GetMapping("/login") 
	public String login() {
		return "login.jsp";
	}
	
	//capturar el email y password
	//con get podemos pasar parametros ocultos
	@PostMapping("/login")
	public String ingresoUsuario(@RequestParam("email") String email,
			@RequestParam("pass") String pass,
			Model model,
			HttpSession session) { //variable para almacenar datos en browser
		
		//lamando al método
		Boolean resultadoLogin = usuarioServiceImpl.ingresoUsuario(email, pass);
		
		if (resultadoLogin) { //si resultadoLogin == true, login correcto
			
			Usuario usuario = usuarioServiceImpl.obtenerUsuarioEmail(email);
			//guardar informacion en session
			session.setAttribute("usuarioId", usuario.getId());
			session.setAttribute("usuarioEmail", email); //se le puede agregar el nombre que uno quiera
			session.setAttribute("usuarioRol", usuario.getRoles());
			session.setAttribute("usuarioNombre", usuario.getNombre());
			
			return "redirect:/home"; //ir a una ruta interna http:localhost:8080/home
		} else {
			model.addAttribute("msgError", "Usuario o Contraseña incorrecto"); //no se puede entregar un mensaje muy descriptivo como "contraseña incorrecta" porque no es seguro.
			return "login.jsp";			
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("usuarioId")!=null) {
			session.invalidate(); //el invalidate mata toda la session
		}
		return "redirect:/registro/login"; //redirect llama al controlador, no al jsp
	}
}
