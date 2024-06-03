package com.oo2.tpgrupo30.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lote")
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lote")
	private int idLote;

	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate fechaIngreso;

	@Column(name = "proveedor", nullable = false)
	private String proveedor;

	@Column(name = "precio_compra", nullable = false)
	private double precioCompra;
}
