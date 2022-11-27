package cl.generation.web.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity //anotacion @entity transforma nuestra clase en una entidad (tabla)
@Table(name="roles") // entidad nombre en singular, tabla nombre en plural
@NoArgsConstructor // constructor vacio
@AllArgsConstructor // constructor con argumentos
@Getter // gracias a libreria lombok se ahorran las lineas de getter
@Setter // setters 
@ToString
public class Rol {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto incrementable
	private Long id;
	private String nombre;
	private String descripcion;
	
	//ManyToMany
	//@JsonIgnore
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) //mappedby establece el nombre para que genere una relacion //
	private List<Usuario> usuarios;

	
	
}
