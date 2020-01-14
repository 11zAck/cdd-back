package cl.roisel.cdd.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.roisel.cdd.app.models.entity.Banco;
import cl.roisel.cdd.app.models.services.BancoService;

@RestController
@RequestMapping("/v1/bancos")
public class BancoRestController {

	@Autowired private BancoService service;
	
	@GetMapping
	public List<Banco> getBancos() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Banco getBanco( @PathVariable Long id ) {
		return service.findById(id);
	}
	
}
