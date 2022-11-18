package cl.generation.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Usuario;
import cl.generation.web.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
//Aqui realizamos toda la logica de negocio del sistema web (existe o no el correo, existe o no el usuario, etc.)
	@Autowired //para inyectar nuestras dependencias
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario guardarUsuario(Usuario usuario) { 
		 //usuarioRepository.save(usuario); lo pasa al repository y con el metodo save lo guarda en el repository
		return usuarioRepository.save(usuario);
	}

	@Override
	public String eliminarUsuario(Long id) {
		Boolean existe = usuarioRepository.existsById(id);
		
		if(existe) {
			//elimino el usuario pasando el id (pk)
			usuarioRepository.deleteById(id); //este metodo no retorna nada porque tiene el void			
		} else {
			return "Usuario no existe en la tabla";
		}
		
		existe = usuarioRepository.existsById(id);
		
		//validar si fue eliminado o no
		/*
		 * Optional<Usuario> usuario = usuarioRepository.findById(id); //findByid
		 * retorna un optional de usuario, .get retorna un usuario if(usuario!=null) {
		 * return "Usuario no eliminado"; } return "El usuario fue eliminado";
		 */
		
		if(existe) {
			return "Usuario no eliminado";
		}
		return "El usuario fue eliminado";
	}
	
	@Override
	public String actualizarUsuario(Usuario usuario) {
		
		Boolean existe = usuarioRepository.existsById(usuario.getId()); //logica de negocio, para ver si existe el usuario en BD
		if(existe) { 
			usuarioRepository.save(usuario);		
			return "Usuario actualizado";
		}
		return "Usuario no actualizado";
	}
	
	@Override
	public Usuario obtenerUsuario(Long id) {
		//Optional<Usuario> user = usuarioRepository.findById(id);
		Boolean existe = usuarioRepository.existsById(id);
		
		if(existe) {
		Usuario user= usuarioRepository.findById(id).get();
			return user;
		}
		return null;
	}

	@Override
	public List<Usuario> obtenerListaUsuarios() {
		//obtener todos los usuarios
		return usuarioRepository.findAll();
	}
	
	
}
