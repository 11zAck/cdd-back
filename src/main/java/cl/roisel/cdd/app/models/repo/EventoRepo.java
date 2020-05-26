package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.entity.Deseo;
import cl.roisel.cdd.app.models.entity.Evento;

public interface EventoRepo extends CrudRepository<Evento, Long>{
	
	@Query("from Evento where titulo=:titulo")
	public Optional<Evento> findByTitulo(@Param("titulo") String titulo);  
	
}
