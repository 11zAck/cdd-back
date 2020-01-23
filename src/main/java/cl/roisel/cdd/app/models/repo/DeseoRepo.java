package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.entity.Deseo;

public interface DeseoRepo extends CrudRepository<Deseo, Long>{
	
	@Query("from Deseo where nombre=:nombre")
	public Optional<Deseo> findByNombre(@Param("nombre") String nombre);  
	
}
