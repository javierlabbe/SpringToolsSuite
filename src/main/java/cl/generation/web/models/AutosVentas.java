package cl.generation.web.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "autos_ventas")
public class AutosVentas {
	// tabla relacional para agregar columnas adicionales

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;
	private Float valorUnitario;
	private Float total; // cantidad*valorUnitario

	// RELACIONES ManyToMany = 2 ManyToOne

	// 1 ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	// 2 ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venta_id")
	private Venta venta;

	@Column(updatable = false) // la columna createAt una vez insertado el registro no se puede cambiar
	@DateTimeFormat(pattern = "yyyy-MM-dd") // pattern es el formato
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public AutosVentas() {
		super();
	}

	public AutosVentas(Long id, Integer cantidad, Float valorUnitario, Float total, Auto auto, Venta venta,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.total = total;
		this.auto = auto;
		this.venta = venta;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// atributos de control
	// agregar a la columna la fecha antes de insertar
	@PrePersist
	protected void onCreate() {
		this.setCreatedAt(new Date());
	}

	// inserta la fecha del momento en que se esta actualizando
	@PreUpdate
	protected void onUpdate() {
		this.setUpdatedAt(new Date());
	}
}
