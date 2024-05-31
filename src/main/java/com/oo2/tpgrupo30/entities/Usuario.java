package com.oo2.tpgrupo30.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	@Column(name = "clave", nullable = false)
	private String clave;

	@Column(name = "createdAt", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<UsuarioRoles> usuarioRoles = new HashSet<UsuarioRoles>();
}
