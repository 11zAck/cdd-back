package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.roisel.cdd.app.models.entity.Banco;

public interface BancoRepo extends CrudRepository<Banco,Long>{
	
	@Query("from Banco where nombre=:nombre")
	public Optional<Banco> findByNombre(@Param("nombre") String nombre);  
	
	
}


