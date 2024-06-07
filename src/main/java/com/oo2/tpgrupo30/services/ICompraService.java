package com.oo2.tpgrupo30.services;

import java.util.List;

import com.oo2.tpgrupo30.entities.Compra;

public interface ICompraService {

    public Compra findByIdCompra(int id);

    public List<Compra> findByProductoIdProducto(int idProducto);

    public List<Compra> getAll();

    public Compra insertOrUpdate(Compra compra);

    public boolean remove(int id);
}