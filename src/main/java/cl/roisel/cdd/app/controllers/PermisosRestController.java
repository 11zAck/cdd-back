package cl.roisel.cdd.app.controllers;

import java.util.Date;
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

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.entity.Evento;
import cl.roisel.cdd.app.models.entity.Permiso;
import cl.roisel.cdd.app.models.entity.Evento;
import cl.roisel.cdd.app.models.services.EventoService;
import cl.roisel.cdd.app.models.services.PermisoService;
import cl.roisel.cdd.app.models.services.PermisoService;

@RestController
@RequestMapping("/v1/permisos")
public class PermisosRestController {

	@Autowired private PermisoService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PermisosRestController.class);
	

	@GetMapping
	public List<Permiso> list() {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Permiso showId(@PathVariable Long id) {
		return this.service.findById(id);
	}
	


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permiso create(@RequestBody Permiso nuevo) {
		this.service.saveNew(nuevo);
		return nuevo;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Permiso update(@RequestBody Permiso nuevo, @PathVariable Long id) {
		Permiso newPermiso = this.service.findById(id);
		
		this.service.update(nuevo);
		return newPermiso;
	}

	@DeleteMapping("/{id}")
	
	public HttpStatus delete(@PathVariable Long id) {
		Permiso evento = this.service.findById(id);
		
		if( evento != null ) {
			this.service.delete(id);
			return HttpStatus.NO_CONTENT;
		}
		else{
			return HttpStatus.NOT_FOUND;
		}
		  
		
	}
}

	
	

