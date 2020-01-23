package cl.roisel.cdd.app.models.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cl.roisel.cdd.app.models.entity.Evento;
import cl.roisel.cdd.app.models.repo.EventoRepo;

@Service
public class EventoService {	
	private static final Logger log = LoggerFactory.getLogger(EventoService.class);

	@Autowired private EventoRepo repo;
	
	@Transactional(readOnly = true)
	public List<Evento> findAll() {
		log.info("");
		return (List<Evento>) repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Evento findById(Long id) {
		log.info("");
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Evento findByNombre(String nombre) {
		log.info("");
		return repo.findByTitulo(nombre).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Evento save(Evento evento) {
		log.info("");
		
		//Se ingresan fechas de creación y edición
		evento.setCreacion(new Date()); 
		evento.setEdicion(new Date()); 
		
		return repo.save(evento);
	}
	
	@Transactional(readOnly = false)
	public Evento update(Evento evento) {
		log.info("");
		Evento anterior = repo.findById(evento.getId()).orElse(new Evento());
		log.info("");
		//Se mantiene la fecha de creación
		anterior.setCreacion(evento.getCreacion());
		
		// se actualiza la fecha de edición
		anterior.setEdicion(new Date());
		
		
		anterior.setActivo(evento.isActivo());
		anterior.setBanco(evento.getBanco());
		anterior.setDescripcion(evento.getDescripcion());
		anterior.setDeseos(evento.getDeseos());
		anterior.setDireccion(evento.getDireccion());
		anterior.setEmailCuenta(evento.getEmailCuenta());
		anterior.setFechaEvento(evento.getFechaEvento());
		anterior.setInvitados(evento.getInvitados());
		anterior.setNumeroCuenta(evento.getNumeroCuenta());
		anterior.setPropietario(evento.getPropietario());
		anterior.setReportado(evento.isReportado());
		anterior.setRutCuenta(evento.getRutCuenta());
		anterior.setTelefono(evento.getTelefono());
		anterior.setTipoCuenta(evento.getTipoCuenta());
		anterior.setTitulo(evento.getTitulo());
		
		return repo.save(anterior);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
