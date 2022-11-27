package cl.generation.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //anotacion @entity transforma nuestra clase en una entidad (tabla)
@Table(name="usuarios") // entidad nombre en singular, tabla nombre en plural
public class Usuario { // este objeto luego se transformara en entidad
	// con estos datos se pueden hacer registro y login
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto incrementable
	private Long id;
	
	@NotNull // la anotacion es solo para el atributo que sigue a la anotacion
	@Size(min=3,max=15, message= "Error en el ingreso del nombre") // define el tamaño. Cantidad de caracteres alfanumericos
	private String nombre;
	
	@NotNull
	private String correo; 
	
	@NotNull
	private String password;
	
	//Relacion OnetoOne
	@JsonIgnore //permite eliminar error de recursividad. para que usuario muestre auto
	@OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.LAZY) // el mapeo asigna la relacion 
	private Auto auto;																 //cascade es opcional
																					 //FetchType 
	//Relacion oneToMany
	@JsonIgnore 
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.LAZY)  //mappedby > en la relacion onetomany con direcciones lo encontraran con el nombre usuario.
	private List<Direccion> direcciones;
	
	//Relacion ManyToMany
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="roles_usuarios", //nombre de la tabla relacional. el sistema creara la tabla relacional. 
			joinColumns = @JoinColumn(name="usuario_id"), //estoy posicionado en rol, por eso es el primer id del join (buena practica)
			inverseJoinColumns =  @JoinColumn(name="rol_id")
			)
	private List<Rol> roles;
	
	@Transient // va asignificar que la columna no sea considerada en la creacion de la tabla
	private String password2; // para confirmar contraseña
	
	private String nick;
	
	@Range(min=40, max=300, message="Peso fuera de rango") //define un rango numerico para el valor
	private Float peso;
	
	@Column(updatable = false) // la columna createAt una vez insertado el registro no se puede cambiar
	@DateTimeFormat(pattern="yyyy-MM-dd") //pattern es el formato
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	// constructores
	public Usuario() {
		super();
	}
	// getters and setters

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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) { 
		this.createdAt = createdAt;
	}
	
	// atributos de control
	// agregar a la columna la fecha antes de insertar
	@PrePersist
	protected void onCreate(){
		this.setCreatedAt(new Date());
	}
	
	// inserta la fecha del momento en que se esta actualizando
	@PreUpdate
	protected void onUpdate(){
		this.setUpdatedAt(new Date());
	}
	
}
