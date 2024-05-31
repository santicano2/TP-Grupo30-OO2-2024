package com.oo2.tpgrupo30.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "compraProducto")
public class CompraProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompraProducto;

	@ManyToOne
	@JoinColumn(name = "id_compra", nullable = false)
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@Column(name = "precio", nullable = false)
	private double precio;
}
