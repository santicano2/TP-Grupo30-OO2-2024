package com.oo2.tpgrupo30.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int id;
	
	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;
	
	@Column(name = "clave", nullable = false)
	private String clave;
	
	@Column(name = "createdAt", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
}
