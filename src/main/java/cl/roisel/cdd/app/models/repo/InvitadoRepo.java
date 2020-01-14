package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.roisel.cdd.app.models.entity.Invitado;

public interface InvitadoRepo extends CrudRepository<Invitado, Long>{

	public List<Invitado> findAll();
	public Optional<Invitado> findByEmail(String email);
	
}
