package com.oo2.tpgrupo30.services;

import java.util.List;

import com.oo2.tpgrupo30.entities.Lote;

public interface ICompraService {

    public Lote findById(Long id);

    public List<Lote> getAll();

    public Lote insertOrUpdate(Lote lote);

    public boolean remove(Long id);

}
