package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.roisel.cdd.app.models.entity.Organizador;

public interface OrganizadorRepo extends CrudRepository<Organizador, Long>{

	public List<Organizador> findAll();
	public Optional<Organizador> findById(Long id);
	public Optional<Organizador> findByUsername(String username);
	
}
