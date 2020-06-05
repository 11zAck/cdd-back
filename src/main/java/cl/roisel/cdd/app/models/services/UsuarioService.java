package cl.roisel.cdd.app.models.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cl.roisel.cdd.app.models.entity.Usuario;
import cl.roisel.cdd.app.models.repo.UsuarioRepo;

@Service
public class UsuarioService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired private UsuarioRepo repo;
	
	@Transactional(readOnly = true)
	public List<Usuario> findAll(){
		log.info("");
		return (List<Usuario>)repo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		log.info("");
		return repo.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Usuario findByUsuario(String username) {
		log.info("");
		return repo.findByUsername(username).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Usuario findByDni(String dni) {
		log.info("");
		return repo.findByDni(dni).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public Usuario update(Usuario nuevo) {
		log.info("");
		Usuario anterior = repo.findById( nuevo.getId() ).orElse(null);
		if( anterior == null ) {
			return null;
		}
		
		//se ingresa fecha de actualización
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
		
		
		
		return repo.save(anterior);
	}
	
	@Transactional(readOnly = false)
	public Usuario saveNew(Usuario nuevo) {
		log.info("");
		nuevo.setCreateAt(new Date()); 
		nuevo.setEditedAt(new Date()); 
		
		return repo.save(nuevo);
	}
	

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = findByUsuario(username);
		
		if( usuario == null ) {
			log.error("No existe usuario {}", username);
			throw new UsernameNotFoundException("No existe usuario " + username);
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map( role -> new SimpleGrantedAuthority(role.getName()) )
				.collect( Collectors.toList() );
		return new User(usuario.getUsername(), usuario.getPassword(), authorities);
	}

}
