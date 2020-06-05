package cl.roisel.cdd.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cacc_tp_usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotEmpty
	@Column(nullable = false, length = 60)
	private String password;
	
	@Column(length = 12)
	private String dni;
	
	@NotEmpty
	@Size(min = 4, max = 80)
	@Column(name="first_name")
	private String firstname;
	
	@NotEmpty
	@Size(min = 4, max = 80)
	@Column(name="last_name")
	private String lastname;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	private boolean enabled;
	
	/**
	 * Indicates whether the user's account has expired. An expired account cannot be authenticated.
	 * <br>VALUES: true if the user's account is valid (ie non-expired), false if no longer valid (ie expired).
	 */
	@Column(name="account_non_expired")
	private Boolean accountNonExpired;
	
	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
	 * <br>VALUES: true if the user is not locked, false otherwise.
	 */
	@Column(name="account_non_locked")
	private Boolean accountNonLocked;
	
	/**
	 * Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
	 * <br>VALUES: true if the user's credentials are valid (ie non-expired), false if no longer valid (ie expired).
	 */
	@Column(name="credential_non_expired")
	private Boolean credentialsNonExpired;
	
	@Column(name="create_at",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;	
	
	@Column(name="edited_at",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editedAt;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cacc_usuarios_roles", 
		joinColumns = @JoinColumn(name = "usuario_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"),
		uniqueConstraints = {@UniqueConstraint( columnNames = {"usuario_id", "role_id"})})
	private List<Permiso> roles;
	
	
	@PrePersist
	public void init() {
		this.createAt = new Date();
	}
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String username, String password, String dni, String firstname, String lastname,
			Date birthday, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.enabled = enabled;
		this.roles = new ArrayList<>();
	}

	public Usuario(Long id, @NotEmpty @Email String username, @NotEmpty String password, String dni,
			@NotEmpty @Size(min = 4, max = 80) String firstname, @NotEmpty @Size(min = 4, max = 80) String lastname,
			Date birthday, boolean enabled, Boolean accountNonExpired, Boolean accountNonLocked,
			Boolean credentialsNonExpired, Date createAt, Date editedAt, List<Permiso> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.createAt = createAt;
		this.editedAt = editedAt;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}
	
	public List<Permiso> getRoles() {
		return roles;
	}

	public void setRoles(List<Permiso> roles) {
		this.roles = roles;
	}


	private static final long serialVersionUID = 1463680748166446691L;
}
