package cl.roisel.cdd.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prov_tp_invitados")
public class Invitado extends Usuario implements Serializable {

	private String email;

	public Invitado(Long id, String username, String password, String dni, String firstname, String lastname,
			Date birthday, boolean enabled, String email) {
		super(id, username, password, dni, firstname, lastname, birthday, enabled);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Invitado:{ email:" + email + "}";
	}

	private static final long serialVersionUID = -3297181447705443929L;
}
