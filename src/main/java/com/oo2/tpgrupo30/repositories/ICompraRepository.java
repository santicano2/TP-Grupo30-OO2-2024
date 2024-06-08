package com.oo2.tpgrupo30.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.tpgrupo30.entities.Compra;

@Repository("compraRepository")
public interface ICompraRepository extends JpaRepository<Compra, Serializable> {
    public abstract Compra findByIdCompra(int id);

    public abstract List<Compra> findByProductoIdProducto(int idProducto);
} 	
