package cl.roisel.cdd.app.models.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.roisel.cdd.app.models.entity.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Long>{

	List<Usuario> findByDni(String dni);
	Optional<Usuario> findById(Long id);
	Optional<Usuario> findByUsername(String username);
	
}
