package cl.roisel.cdd.app.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.roisel.cdd.app.models.entity.Invitado;
import cl.roisel.cdd.app.models.repo.InvitadoRepo;

@Service
public class InvitadoService {

	private static final Logger log = LoggerFactory.getLogger(InvitadoService.class);
	
	@Autowired private InvitadoRepo repo;
	
	@Transactional(readOnly = true)
	public List<Invitado> findAll(){
		log.info("");
		return repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Invitado findByEmail(String email) {
		log.info("");
		return repo.findByEmail(email).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Invitado update(Invitado nuevo) {
		log.info("");
		Invitado anterior = repo.findById( nuevo.getId() ).orElse(null);
		if( anterior == null ) {
			return null;
		}
		anterior.setEmail( nuevo.getEmail() );
		return repo.save(anterior);
	}
	
	@Transactional(readOnly = false)
	public Invitado saveNew(Invitado invitado) {
		log.info("");
		return repo.save(invitado);
	}

}
