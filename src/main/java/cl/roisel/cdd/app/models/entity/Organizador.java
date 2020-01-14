package cl.roisel.cdd.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prov_tp_organizadores")
public class Organizador extends Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int cantidadEventos;
	private boolean registroCompleto;

	public Organizador() {
		super();
	};
	
	public Organizador(Long id, String username, String password, String dni, String firstname, String lastname,
			Date birthday, boolean enabled, Long id2, int cantidadEventos, boolean registroCompleto) {
		super(id, username, password, dni, firstname, lastname, birthday, enabled);
		id = id2;
		this.cantidadEventos = cantidadEventos;
		this.registroCompleto = registroCompleto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidadEventos() {
		return cantidadEventos;
	}

	public void setCantidadEventos(int cantidadEventos) {
		this.cantidadEventos = cantidadEventos;
	}

	public boolean isRegistroCompleto() {
		return registroCompleto;
	}

	public void setRegistroCompleto(boolean registroCompleto) {
		this.registroCompleto = registroCompleto;
	}

	@Override
	public String toString() {
		return "Organizador:{ id:" + id + ", cantidadEventos:" + cantidadEventos + ", registroCompleto:"
				+ registroCompleto + "}";
	}

	private static final long serialVersionUID = 2270993129939687009L;
}
