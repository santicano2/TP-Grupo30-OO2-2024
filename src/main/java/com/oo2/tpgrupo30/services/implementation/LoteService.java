package com.oo2.tpgrupo30.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.repositories.LoteRepository;
import com.oo2.tpgrupo30.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {

	private LoteRepository loteRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public LoteService(LoteRepository loteRepository) {
		this.loteRepository = loteRepository;
	}

	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public Lote insertOrUpdate(Lote lote) {
		Lote loteN = loteRepository.save(modelMapper.map(lote, Lote.class));
		return modelMapper.map(loteN, Lote.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			loteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Lote findByIdLote(int id) {
		return loteRepository.findByIdLote(id);
	}

	@Override
	public List<Lote> findByProductoIdProducto(int productoId) {
		return loteRepository.findByProductoIdProducto(productoId);
	}
}