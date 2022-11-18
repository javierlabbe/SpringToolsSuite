package cl.generation.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity // anotacion oara transformar la clase en entidad
@Table(name="paises")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String continente;
	
	@NotNull
	private String idioma;
	
	@NotNull
	private String capital;

	public Pais() {
		super();
	}

	public Pais(Long id, @NotNull String nombre, @NotNull String continente, @NotNull String idioma,
			@NotNull String capital) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.continente = continente;
		this.idioma = idioma;
		this.capital = capital;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	
	
	
	
}
