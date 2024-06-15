package com.oo2.tpgrupo30.services.implementation;

import com.oo2.tpgrupo30.entities.Compra;
import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.repositories.ICompraRepository;
import com.oo2.tpgrupo30.repositories.ILoteRepository;
import com.oo2.tpgrupo30.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ICompraRepository compraRepository;

	@Autowired
	private ILoteRepository loteRepository;

	public List<Producto> obtenerTodosLosProductos() {
		return productRepository.findAll();
	}

	public Producto obtenerProductoMasVendido() {
		List<Producto> productos = obtenerTodosLosProductos();
		Producto masVendido = null;
		int maxVentas = 0;

		for (Producto producto : productos) {
			int ventas = calcularCantidadComprada(producto);
			if (ventas > maxVentas) {
				maxVentas = ventas;
				masVendido = producto;
			}
		}

		return masVendido;
	}

	public int calcularCantidadComprada(Producto producto) {
		List<Compra> compras = compraRepository.findByProductoIdProducto(producto.getIdProducto());
		return compras.stream().mapToInt(Compra::getCantidad).sum();
	}

	public Producto obtenerProductoMasAntiguo() {
		List<Lote> lotes = loteRepository.findAll();
		Lote loteMasAntiguo = null;

		for (Lote lote : lotes) {
			if (loteMasAntiguo == null || lote.getFechaIngreso().isBefore(loteMasAntiguo.getFechaIngreso())) {
				loteMasAntiguo = lote;
			}
		}

		return loteMasAntiguo != null ? loteMasAntiguo.getProducto() : null;
	}

	public LocalDate obtenerFechaAntiguedadProductoMasAntiguo() {
		List<Lote> lotes = loteRepository.findAll();

		Optional<LocalDate> fechaMasAntigua = lotes.stream().map(Lote::getFechaIngreso).min(Comparator.naturalOrder());

		return fechaMasAntigua.orElse(null);
	}

	public long obtenerTiempoEnInventario(Producto producto) {
		List<Lote> lotes = loteRepository.findByProductoIdProducto(producto.getIdProducto());
		LocalDate fechaMasAntigua = lotes.stream().map(Lote::getFechaIngreso).min(LocalDate::compareTo).orElse(null);
		return fechaMasAntigua != null ? ChronoUnit.DAYS.between(fechaMasAntigua, LocalDate.now()) : 0;
	}

	public String calcularRotacionInventario(Producto producto) {
		int ventas = calcularCantidadComprada(producto);
		long diasEnInventario = obtenerTiempoEnInventario(producto);
		double rotacion = diasEnInventario > 0 ? (double) ventas / diasEnInventario : 0;
		return String.format("%.1f", rotacion);
	}

	public double calcularValorTotalInventario() {
		List<Producto> productos = productRepository.findAll();
		return productos.stream().mapToDouble(producto -> producto.getCantidad_en_stock() * producto.getPrecioVenta())
				.sum();
	}

	public double calcularValorTotalCompras() {
		List<Compra> compras = compraRepository.findAll();
		return compras.stream().mapToDouble(compra -> compra.getCantidad() * compra.getProducto().getPrecioVenta())
				.sum();
	}
}