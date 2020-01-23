package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.roisel.cdd.app.models.entity.Deseo;
import cl.roisel.cdd.app.models.entity.Invitado;

public interface InvitadoRepo extends CrudRepository<Invitado, Long>{

	
	@Query("from Invitado where email=:email")
	public Optional<Invitado> findByEmail(@Param("email") String email);  
	
}
