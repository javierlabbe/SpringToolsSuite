package cl.generation.web.services;

import java.util.List;

import cl.generation.web.models.Usuario;

public interface UsuarioService {
	//en interfaz solo se definen metodos, no se implementan
	//definir metodos para crud usuario (no se implementan, por eso van sin {})
	public Boolean guardarUsuario(Usuario usuario);
	public String eliminarUsuario(Long id);
	public String actualizarUsuario(Usuario usuario);
	public Usuario obtenerUsuario(Long id);
	public List<Usuario> obtenerListaUsuarios();
	//login
	public Boolean ingresoUsuario(String email, String password);
	public Usuario obtenerUsuarioEmail(String email);
	
}
