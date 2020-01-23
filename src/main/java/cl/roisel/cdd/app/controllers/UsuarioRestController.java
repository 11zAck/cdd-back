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
import cl.roisel.cdd.app.models.entity.Usuario;
import cl.roisel.cdd.app.models.entity.Evento;
import cl.roisel.cdd.app.models.services.EventoService;
import cl.roisel.cdd.app.models.services.PermisoService;
import cl.roisel.cdd.app.models.services.UsuarioService;
import cl.roisel.cdd.app.models.services.PermisoService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioRestController {

	@Autowired private UsuarioService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(UsuarioRestController.class);
	

	@GetMapping
	public List<Usuario> list() {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Usuario showId(@PathVariable Long id) {
		return this.service.findById(id);
	}
	
	@GetMapping("/{username}")
	public Usuario showUsername(@PathVariable String username) {
		return this.service.findByUsuario(username);
	}
	
	@GetMapping("/{dni}")
	public Usuario showDni(@PathVariable String dni) {
		return this.service.findByDni(dni);
	}
	
	


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario nuevo) {
		this.service.saveNew(nuevo);
		return nuevo;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario nuevo, @PathVariable Long id) {
		Usuario antiguo = this.service.findById(id);
		
		if(antiguo==null) {
			
			return null;
		}
			
		
		this.service.update(nuevo);
		return nuevo;
	}

	@DeleteMapping("/{id}")
	public HttpStatus delete(@PathVariable Long id) {
		Usuario antiguo = this.service.findById(id);
		
		if( antiguo != null ) {
			this.service.delete(id);
			return HttpStatus.NO_CONTENT;
		}
		else{
			return HttpStatus.NOT_FOUND;
		}
		
		
	}
}

	
	

