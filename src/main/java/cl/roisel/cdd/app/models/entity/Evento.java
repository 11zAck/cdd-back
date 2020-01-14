package cl.roisel.cdd.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "prov_tp_eventos")
public class Evento implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    private String titulo;
    private String descripcion;
    private String direccion;
    private boolean activo;
    private boolean reportado;
    
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_banco")
    private Banco banco;
    
    private String tipoCuenta;
    private String numeroCuenta;
    private String rutCuenta;
    private String emailCuenta;
    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_evento")
    private Date fechaEvento;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="creado_at",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creacion;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="editado_at",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date edicion;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="organizador_id")
    private Organizador propietario;
    
    @OneToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="prov_eventos_invitados",
			joinColumns=@JoinColumn(name="evento_id"),
			inverseJoinColumns=@JoinColumn(name="invitado_id"),
			uniqueConstraints= {@UniqueConstraint(columnNames= {"evento_id","invitado_id"})})
    private Set<Invitado> invitados;
    
    @OneToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="prov_eventos_deseos",
			joinColumns=@JoinColumn(name="evento_id"),
			inverseJoinColumns=@JoinColumn(name="deseo_id"),
			uniqueConstraints= {@UniqueConstraint(columnNames= {"evento_id","deseo_id"})})
    private Set<Deseo> deseos;

    @PrePersist
	public void init() {
		this.creacion = new Date();
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isReportado() {
		return reportado;
	}

	public void setReportado(boolean reportado) {
		this.reportado = reportado;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getRutCuenta() {
		return rutCuenta;
	}

	public void setRutCuenta(String rutCuenta) {
		this.rutCuenta = rutCuenta;
	}

	public String getEmailCuenta() {
		return emailCuenta;
	}

	public void setEmailCuenta(String emailCuenta) {
		this.emailCuenta = emailCuenta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public Date getEdicion() {
		return edicion;
	}

	public void setEdicion(Date edicion) {
		this.edicion = edicion;
	}

	public Organizador getPropietario() {
		return propietario;
	}

	public void setPropietario(Organizador propietario) {
		this.propietario = propietario;
	}

	public Set<Invitado> getInvitados() {
		return invitados;
	}

	public void setInvitados(Set<Invitado> invitados) {
		this.invitados = invitados;
	}

	public Set<Deseo> getDeseos() {
		return deseos;
	}

	public void setDeseos(Set<Deseo> deseos) {
		this.deseos = deseos;
	}

	@Override
	public String toString() {
		return "Evento:{ id:" + id + ", titulo:" + titulo + ", descripcion:" + descripcion + ", direccion:" + direccion
				+ ", activo:" + activo + ", reportado:" + reportado + ", banco:" + banco + ", tipoCuenta:" + tipoCuenta
				+ ", numeroCuenta:" + numeroCuenta + ", rutCuenta:" + rutCuenta + ", emailCuenta:" + emailCuenta
				+ ", telefono:" + telefono + ", fechaEvento:" + fechaEvento + ", creacion:" + creacion + ", edicion:"
				+ edicion + ", propietario:" + propietario + ", invitados:" + invitados + ", deseos:" + deseos + "}";
	}

	private static final long serialVersionUID = 7231036750913960705L;
}
