package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.roisel.cdd.app.models.entity.Deseo;

public interface DeseoRepo extends CrudRepository<Deseo, Long>{

	public List<Deseo> findAll();
	public Optional<Deseo> findById(Long id);
	public Optional<Deseo> findByNombre(String nombre);
	
}
