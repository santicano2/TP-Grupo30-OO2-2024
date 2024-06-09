package com.oo2.tpgrupo30.services;

import java.util.List;

import com.oo2.tpgrupo30.entities.Lote;

public interface ILoteService {

	public Lote findByIdLote(int id);

	public List<Lote> findByProductoIdProducto(int productoId);

	public List<Lote> getAll();

	public Lote insertOrUpdate(Lote lote);

	public boolean remove(int id);

}