package cl.roisel.cdd.app.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.repo.BancoRepo;

@Service
public class BancoService {
		
	private static final Logger log = LoggerFactory.getLogger(BancoService.class);

	@Autowired private BancoRepo repo;
	
	@Transactional(readOnly = true)
	public List<Banco> findAll() {
		log.info("");
		return repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Banco findById( Long id ) {
		log.info("");
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Banco findByNombre( String nombre ) {
		log.info("");
		return repo.findByNombre(nombre).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Banco saveNew( Banco banco ) {
		log.info("");
		return repo.save(banco);
	}
	
	@Transactional(readOnly = false)
	public Banco update(Banco nuevo) {
		log.info("");
		Banco anterior = repo.findById( nuevo.getId() ).orElse(null);
		if( anterior == null ) {
			return null;
		}
		anterior.setNombre( nuevo.getNombre() );
		return repo.save( anterior );
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		log.info("");
		repo.deleteById(id);
	}
	
}
