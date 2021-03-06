package cl.roisel.cdd.app.models.services;

import java.util.Date;
import java.util.List;

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
	public List<Organizador> findAll(){
		log.info("");
		return (List<Organizador>)repo.findAll();
	}
	
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
		
		
		//Funciones hereradas de usuario, No deberia poseer un Objeto Usuario?
        anterior.setEditedAt(new Date()); 
		
		anterior.setAccountNonExpired(nuevo.getAccountNonExpired());
		anterior.setAccountNonLocked(nuevo.getAccountNonLocked());
		anterior.setBirthday(nuevo.getBirthday());
		//anterior.setCreateAt(createAt);
		anterior.setCredentialsNonExpired(nuevo.getCredentialsNonExpired());
		anterior.setDni(nuevo.getDni());
		
		anterior.setEnabled(nuevo.isEnabled());
		anterior.setFirstname(nuevo.getFirstname());
		anterior.setLastname(nuevo.getLastname());
		anterior.setPassword(nuevo.getPassword());
		anterior.setUsername(nuevo.getUsername());
		
		
		
		return repo.save( anterior );
	}
	
	@Transactional(readOnly = false)
	public Organizador saveNew(Organizador nuevo) {
		log.info("");
		nuevo.setCreateAt(new Date()); 
		nuevo.setEditedAt(new Date());
		
		return repo.save(nuevo);
	}
	

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
}
