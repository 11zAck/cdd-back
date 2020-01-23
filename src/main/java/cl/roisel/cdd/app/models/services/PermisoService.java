package cl.roisel.cdd.app.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cl.roisel.cdd.app.models.entity.Permiso;

import cl.roisel.cdd.app.models.repo.PermisoRepo;

@Service
public class PermisoService {

	private static final Logger log = LoggerFactory.getLogger(PermisoService.class);
	
	@Autowired private PermisoRepo repo;
	
	@Transactional(readOnly = true)
	public List<Permiso> findAll(){
		log.info("");
		return (List<Permiso>)repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Permiso findById(Long id) {
		log.info("");
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Permiso update(Permiso nuevo) {
		log.info("");
		Permiso anterior = repo.findById( nuevo.getId() ).orElse(null);
		if( anterior == null ) {
			return null;
		}
		anterior.setName(nuevo.getName());
		
		return repo.save(anterior);
	}
	
	@Transactional(readOnly = false)
	public Permiso saveNew(Permiso nuevo) {
		log.info("");
		return repo.save(nuevo);
	}
	

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
