package cl.generation.web.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Usuario;
import cl.generation.web.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
//Aqui realizamos toda la logica de negocio del sistema web (existe o no el correo, existe o no el usuario, etc.)
	@Autowired // para inyectar nuestras dependencias
	private UsuarioRepository usuarioRepository;

	@Override
	public Boolean guardarUsuario(Usuario usuario) {

		// validarUsuario (email)
		Usuario retornoUsuario = usuarioRepository.findByCorreo(usuario.getCorreo());
		//System.out.println(retornoUsuario.getCorreo());
		if (retornoUsuario == null) {
			//1234 -> 1231245321425fas4352
			String passHashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
			//reemplazando el valor por el hash
			usuario.setPassword(passHashed);
			// usuarioRepository.save(usuario); lo pasa al repository y con el metodo save
			// lo guarda en el repository
			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String eliminarUsuario(Long id) {
		Boolean existe = usuarioRepository.existsById(id);

		if (existe) {
			// elimino el usuario pasando el id (pk)
			usuarioRepository.deleteById(id); // este metodo no retorna nada porque tiene el void
		} else {
			return "Usuario no existe en la tabla";
		}

		existe = usuarioRepository.existsById(id);

		// validar si fue eliminado o no
		/*
		 * Optional<Usuario> usuario = usuarioRepository.findById(id); //findByid
		 * retorna un optional de usuario, .get retorna un usuario if(usuario!=null) {
		 * return "Usuario no eliminado"; } return "El usuario fue eliminado";
		 */

		if (existe) {
			return "Usuario no eliminado";
		}
		return "El usuario fue eliminado";
	}

	@Override
	public String actualizarUsuario(Usuario usuario) {

		Boolean existe = usuarioRepository.existsById(usuario.getId()); // logica de negocio, para ver si existe el
																		// usuario en BD
		if (existe) {
			usuarioRepository.save(usuario);
			return "Usuario actualizado";
		}
		return "Usuario no actualizado";
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		// Optional<Usuario> user = usuarioRepository.findById(id);
		Boolean existe = usuarioRepository.existsById(id);

		if (existe) {
			Usuario user = usuarioRepository.findById(id).get();
			return user;
		}
		return null;
	}

	@Override
	public List<Usuario> obtenerListaUsuarios() {
		// obtener todos los usuarios
		return usuarioRepository.findAll();
	}

}
