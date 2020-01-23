package cl.roisel.cdd.app.models.repo;




import org.springframework.data.repository.CrudRepository;



import cl.roisel.cdd.app.models.entity.Permiso;

public interface PermisoRepo extends CrudRepository<Permiso, Long>{
  
	
}
