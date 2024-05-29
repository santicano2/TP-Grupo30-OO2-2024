package com.oo2.tpgrupo30.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int idProducto;

	@Column(name = "codigo", unique = true, nullable = false)
	private String codigo;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "costo", nullable = false)
	private double costo;

	@Column(name = "precio_venta", nullable = false)
	private double precioVenta;

	@Column(name = "cantidad_en_stock", nullable = false)
	private int cantidad_en_stock;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lote> lotes;
}
