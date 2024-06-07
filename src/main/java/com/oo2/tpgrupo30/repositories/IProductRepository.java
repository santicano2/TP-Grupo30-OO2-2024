package com.oo2.tpgrupo30.repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.tpgrupo30.entities.Producto;

@Repository("productRepository")
public interface IProductRepository extends JpaRepository<Producto, Serializable> {
	
	
	public abstract Producto findByidProducto(int id);
	public abstract Producto findByCodigo(String codigo);
	public abstract Producto findByNombre(String nombre);
	public abstract List<Producto> findByNombreOrderByNombreDesc(String nombre);

} 	
