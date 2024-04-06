package com.review.tfg.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(length = 16000000)
	@Size(max = 16000000, message = "La imagen de perfil es demasiado grande")
	private byte[] imagenPerfil;
	
	@NotNull(message = "El nick no puede ser nulo")
    @Size(max = 100, message = "El nick debe tener un maximo de 100 caracteres")
	@Column(unique=true)
	private String nick;
	
    @Email
	@NotNull(message = "El email no puede ser nulo")
    @Column(unique=true)
	private String email;
    
	private String comunidad;

	@NotNull(message = "La contraseña no puede ser nula")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	private String password;
	
    @Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="usuario_rol",
    	      		joinColumns = @JoinColumn(name = "id_usuario"),
			    	foreignKey = @ForeignKey(
			            name = "user_fk",
			            foreignKeyDefinition = "FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE"
			            )
    				)
    @Column(name ="RolesUsuario")
    private Set<Role> roles = new HashSet<>();
	
	// RELACIONES //

	@OneToMany(mappedBy = "escritor")
	private List<Review> reviewsEscritas;

	@Override
	@Transactional
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Para cargar la coleccion
		roles.size();

		return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
	}
	
	@PrePersist
	void camposPorDefecto() {
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		this.roles.add(Role.ROLE_USER);
		this.fechaCreacion = new Date();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return nick;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}
}
