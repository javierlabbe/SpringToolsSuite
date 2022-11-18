package cl.generation.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.generation.web.models.Usuario;
import cl.generation.web.services.UsuarioServiceImpl;

@RestController //creando una API restcotroller que recibe un objeto (desde postman) y lo guarda dentro de una variable usuario
public class UsuarioApiRestController {
	@Autowired //para inyectar informacion en usuarioServiceImpl
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@RequestMapping("/guardar/usuario")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) { //requestbody va a capturar los datos de un usuario desde el cuerpo de una peticion
		//http://localhost:8080/guardar/usuario
		/*// atributos de usuario
		 * {
		 * nombre:"Mijail",
		 * correo: "a@example.cl",
		 * password: "secret"
		 * }
		 * */
		
		return usuarioServiceImpl.guardarUsuario(usuario);//"Usuario guardado";
	}
	
	@RequestMapping("/eliminar/usuario") //definiendo ruta
	public String eliminarUsuario(@RequestParam(value="id",required = false) Long id) { //creando metodo base
		//llamando al metodo eliminar en el service
		return usuarioServiceImpl.eliminarUsuario(id);
	}
	
	@RequestMapping("/actualizar/usuario") //actualizar debe pedir id
	public String actualizarUsuario(@RequestBody Usuario usuario) { 
		
		if(usuario.getId()!=null) { //validacion logica para ver si viene o no el id, pues para actualizar debe venir el id
			String mensaje = usuarioServiceImpl.actualizarUsuario(usuario); //o se puede crear el metodo en services para poder hacer actualizaciones			
			return mensaje;
		}
		
		return null;
	}
	
	@RequestMapping("/obtener/usuario") //definiendo ruta
	public Usuario obtenerUsuario(@RequestParam(value="id",required = true) Long id) { //creando metodo base
		//llamando al metodo eliminar en el service
		return usuarioServiceImpl.obtenerUsuario(id);
	}
	
	//listar todos los usuarios
	
	@GetMapping("/listar/usuarios") //Getmapping solo para solicitudes get
	public List<Usuario> obtenerListaUsuarios() {
		return usuarioServiceImpl.obtenerListaUsuarios();
	}
	
}
