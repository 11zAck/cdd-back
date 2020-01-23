package cl.roisel.cdd.app.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.roisel.cdd.app.models.entity.Deseo;
import cl.roisel.cdd.app.models.repo.DeseoRepo;

@Service
public class DeseoService {	
	private static final Logger log = LoggerFactory.getLogger(DeseoService.class);

	@Autowired private DeseoRepo repo;
	
	@Transactional(readOnly = true)
	public List<Deseo> findAll() {
		log.info("");
		return (List<Deseo>) repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Deseo findById(Long id) {
		log.info("");
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Deseo findByNombre(String nombre) {
		log.info("");
		return repo.findByNombre(nombre).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Deseo save(Deseo deseo) {
		log.info("");
		return repo.save(deseo);
	}
	
	@Transactional(readOnly = false)
	public Deseo update(Deseo deseo) {
		Deseo anterior = repo.findById(deseo.getId()).orElse(new Deseo());
		anterior.setNombre( deseo.getNombre() );
		anterior.setDescripcion( deseo.getDescripcion() );
		anterior.setValor( deseo.getValor() );
		anterior.setActivo( true );
		return repo.save(anterior);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
