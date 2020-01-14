package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.roisel.cdd.app.models.entity.Banco;

public interface BancoRepo extends CrudRepository<Banco,Long>{

	public List<Banco> findAll();
	public Optional<Banco> findByNombre(String nombre);
	
}
