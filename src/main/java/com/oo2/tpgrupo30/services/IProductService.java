package com.oo2.tpgrupo30.services;

import java.util.List;

import com.oo2.tpgrupo30.entities.Producto;

public interface IProductService {
	
	public List<Producto> getAll();
	
	public Producto insertOrUpdate( Producto productoModel);
	
	public boolean remove(int id);
 
}
