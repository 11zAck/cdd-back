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
import cl.roisel.cdd.app.models.services.BancoService;

@RestController
@RequestMapping("/v1/bancos")
public class BancoRestController {

	@Autowired private BancoService bancoService;
	
	private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(BancoRestController.class);
	
	@GetMapping
	public List<Banco> list() {
		
		return bancoService.findAll();
	}

	@GetMapping("/{id}")
	public Banco showId(@PathVariable Long id) {
		return this.bancoService.findById(id);
	}
	
	@GetMapping("/nombre/{nombre}")
	public Banco showNombre(@PathVariable String nombre) {
		return this.bancoService.findByNombre(nombre);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Banco create(@RequestBody Banco banco) {
		// Como buena practica es recomendable crear una fecha de creaciòn para los registros 
		//Banco.setCreateAt(new Date()); 
		this.bancoService.save(banco);
		return banco;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Banco update(@RequestBody Banco banco, @PathVariable Long id) {
		Banco newBanco = this.bancoService.findById(id);
		newBanco.setNombre(banco.getNombre());
		
		this.bancoService.update(newBanco);
		return newBanco;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Banco banco = this.bancoService.findById(id);
		if( banco != null ) {
			this.bancoService.delete(id);
		}
		
	}
}

	
	

