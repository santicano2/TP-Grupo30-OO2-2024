package com.oo2.tpgrupo30.services;
import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.repositories.LoteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoteService implements ILoteService{

	@Override
	public Lote findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lote> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lote insertOrUpdate(Lote lote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
/*
    @Autowired
    private LoteRepository loteRepository;

    public List<Lote> getAllLotes() {
        return loteRepository.findAll();
    }

    public Optional<Lote> getLoteById(Integer id) {
        return loteRepository.findById(id);
    }

    public Lote saveOrUpdateLote(Lote lote) {
        return loteRepository.save(lote);
    }

    public void deleteLoteById(Integer id) {
        loteRepository.deleteById(id);
    }*/
}