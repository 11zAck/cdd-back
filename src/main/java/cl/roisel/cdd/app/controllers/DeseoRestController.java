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

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.entity.Deseo;

import cl.roisel.cdd.app.models.services.DeseoService;

@RestController
@RequestMapping("/v1/deseos")
public class DeseoRestController {

	@Autowired private DeseoService service;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(DeseoRestController.class);
	

	@GetMapping
	public List<Deseo> list() {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Deseo showId(@PathVariable Long id) {
		return this.service.findById(id);
	}
	
	@GetMapping("/nombre/{nombre}")
	public Deseo showNombre(@PathVariable String nombre) {
		return this.service.findByNombre(nombre);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Deseo create(@RequestBody Deseo deseo) {
		// Como buena practica es recomendable crear una fecha de creaciòn para los registros 
		//Deseo.setCreateAt(new Date()); 
		this.service.save(deseo);
		return deseo;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Deseo update(@RequestBody Deseo banco, @PathVariable Long id) {
		Deseo newDeseo = this.service.findById(id);
		newDeseo.setNombre(banco.getNombre());
		
		this.service.update(newDeseo);
		return newDeseo;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Deseo banco = this.service.findById(id);
		if( banco != null ) {
			this.service.delete(id);
		}
		
	}
}

	
	

