package cl.roisel.cdd.app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import cl.roisel.cdd.app.models.entity.Organizador;
import cl.roisel.cdd.app.models.entity.Usuario;
import cl.roisel.cdd.app.models.services.OrganizadorService;
import cl.roisel.cdd.app.models.services.UsuarioService;


@RestController
@RequestMapping("/v1/organizadores")
public class OrganizadorRestController {

	@Autowired private OrganizadorService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(OrganizadorRestController.class);
	

	@GetMapping
	public List<Organizador> list() {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Organizador showId(@PathVariable Long id) {
		return this.service.findById(id);
	}
	
	@GetMapping("/username/{username}")
	public Organizador showUsername(@PathVariable String username) {
		return this.service.findByUsername(username);
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Organizador create(@RequestBody Organizador nuevo) {
		this.service.saveNew(nuevo);
		return nuevo;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Organizador update(@RequestBody Organizador nuevo, @PathVariable Long id) {
		Organizador antiguo = this.service.findById(id);
		
		if(antiguo==null) {
			
			return null;
		}
			
		
		this.service.update(nuevo);
		return nuevo;
	}

	@DeleteMapping("/{id}")
	public HttpStatus delete(@PathVariable Long id) {
		Organizador antiguo = this.service.findById(id);
		
		if( antiguo != null ) {
			this.service.delete(id);
			return HttpStatus.NO_CONTENT;
		}
		else{
			return HttpStatus.NOT_FOUND;
		}
		
		
	}
}

	
	

