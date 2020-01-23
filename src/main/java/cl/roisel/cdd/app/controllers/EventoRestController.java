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
import cl.roisel.cdd.app.models.entity.Evento;
import cl.roisel.cdd.app.models.services.EventoService;

@RestController
@RequestMapping("/v1/eventos")
public class EventoRestController {

	@Autowired private EventoService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(EventoRestController.class);
	

	@GetMapping
	public List<Evento> list() {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Evento showId(@PathVariable Long id) {
		return this.service.findById(id);
	}
	
	@GetMapping("/nombre/{nombre}")
	public Evento showNombre(@PathVariable String nombre) {
		return this.service.findByNombre(nombre);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento create(@RequestBody Evento evento) {
		this.service.save(evento);
		return evento;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento update(@RequestBody Evento evento, @PathVariable Long id) {
		
		Evento newEvento = this.service.findById(id);
		
		this.service.update(newEvento);
		return newEvento;
	}

	@DeleteMapping("/{id}")
	
	public HttpStatus delete(@PathVariable Long id) {
		
		Evento evento = this.service.findById(id);
		
		if( evento != null ) {
			this.service.delete(id);
			return HttpStatus.NO_CONTENT;
		}
		else{
			return HttpStatus.NOT_FOUND;
		}
		
		
	}
}

	
	

