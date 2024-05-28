package com.oo2.tpgrupo30.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int id_producto;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "costos", nullable = false)
	private double costos;

	@Column(name = "precioVenta", nullable = false)
	private double precio_venta;

}
