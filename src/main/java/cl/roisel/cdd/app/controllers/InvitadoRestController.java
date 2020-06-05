package cl.roisel.cdd.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.roisel.cdd.app.models.entity.Invitado;
import cl.roisel.cdd.app.models.services.InvitadoService;

@RestController
@RequestMapping("/v1/invitados")
public class InvitadoRestController {

	@Autowired private InvitadoService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(InvitadoRestController.class);
	

	@GetMapping
	public List<Invitado> list() {
		
		return service.findAll();
	}

	@GetMapping("/{email}")
	public Invitado showId(@PathVariable String email) {
		return this.service.findByEmail(email);
	}
	
	@Secured({"ROLE_REGISTRADO"})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Invitado create(@RequestBody Invitado invitado) {
		this.service.saveNew(invitado);
		return invitado;
	}

	@Secured({"ROLE_REGISTRADO"})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Invitado update(@RequestBody Invitado invitado, @PathVariable Long id) {
		
		Invitado newInvitado = this.service.findById(id);
		
		this.service.update(newInvitado);
		return newInvitado;
	}

	@Secured({"ROLE_REGISTRADO"})
	@DeleteMapping("/{id}")
	public HttpStatus delete(@PathVariable Long id) {
		
		Invitado evento = this.service.findById(id);
		
		if( evento != null ) {
			this.service.delete(id);
			return HttpStatus.NO_CONTENT;
		}
		else{
			return HttpStatus.NOT_FOUND;
		}
		
		
	}
}

	
	

