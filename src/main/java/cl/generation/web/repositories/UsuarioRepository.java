package cl.generation.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.generation.web.models.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ //implementara a travez de una herencia
	//Aqui hacemos logica de manipulacion de datos  (CRUD+)				 // T objeto, ID tipo de PK
	// Operaciones que nos permiten trabajar con la DATA
	
	//cuando se pone un findBy y luego el nombre del atributo, spring entiende que se pasara ese dato
	Usuario findByCorreo(String correo);
	Usuario findByNick(String nick);
}
