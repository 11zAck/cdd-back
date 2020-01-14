package cl.roisel.cdd.app.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.roisel.cdd.app.models.entity.Organizador;
import cl.roisel.cdd.app.models.repo.OrganizadorRepo;

@Service
public class OrganizadorService {

	private static final Logger log = LoggerFactory.getLogger(OrganizadorService.class);
	
	@Autowired private OrganizadorRepo repo;
	
	@Transactional(readOnly = true)
	public Organizador findByUsername( String username ) {
		log.info("");
		return repo.findByUsername(username).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Organizador findById(Long id) {
		log.info("");
		return repo.findById(id).orElse(null);
	}

	@Transactional(readOnly = false)
	public Organizador update(Organizador nuevo) {
		log.info("");
		Organizador anterior = repo.findById( nuevo.getId() ).orElse( null );
		if( anterior == null ) {
			log.error("");
			return null;
		}
		anterior.setRegistroCompleto( nuevo.isRegistroCompleto() );
		anterior.setCantidadEventos( nuevo.getCantidadEventos() + 1 );
		return repo.save( anterior );
	}
	
	
}
