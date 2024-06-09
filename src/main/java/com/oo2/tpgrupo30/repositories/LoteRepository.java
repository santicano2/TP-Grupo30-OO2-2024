package com.oo2.tpgrupo30.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.tpgrupo30.entities.Lote;

@Repository("loteRepository")
public interface LoteRepository extends JpaRepository<Lote, Serializable> {
	public abstract Lote findByIdLote(int id);

	public abstract Lote findByPrecioCompra(double precioCompra);

	public abstract Lote findByProveedor(String proveedor);

	public abstract List<Lote> findByProductoIdProducto(int productoId);

	public abstract List<Lote> findByProveedorOrderByProveedorDesc(String proveedor);
}