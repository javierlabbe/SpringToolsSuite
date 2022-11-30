package cl.generation.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.generation.web.models.Usuario;
import cl.generation.web.services.UsuarioServiceImpl;



@Controller
@RequestMapping("/registro")
//http://localhost:8080/registro/
public class RegistroController {
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	//1. capturar la url 
	@RequestMapping("/usuario")
	public String mostrarformulario() {
		//y desplegar el jsp (controlador)
		return "registro.jsp";
	}
	//2. usuario llena el formulario (vista)
	//3. se envia el formulario (vista) al controlador (boton enviar)
	//4. se captura la url,
	
	//http://localhost:8080/registro/formulario
	@RequestMapping("/formulario")
	//5. capturar los parametros @RequestParam
	public String guardarFormulario(@RequestParam("nombre") String nombre,
			@RequestParam("nick") String nick,
			@RequestParam("correo") String correo,
			@RequestParam("pass") String pass,
			@RequestParam("pass2") String pass2) {
		System.out.println(nombre+" "+correo+" "+pass+" "+pass2);
		//instanciar objeto
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setNick(nick);
		usuario.setCorreo(correo);
		usuario.setPassword(pass);
		usuario.setPassword2(pass2);
		//6. enviar a BD
		usuarioServiceImpl.guardarUsuario(usuario);
		
		return "index.jsp";
	}
	
	
	
	
	
}
