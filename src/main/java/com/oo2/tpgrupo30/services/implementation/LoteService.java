package com.oo2.tpgrupo30.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.repositories.ILoteRepository;
import com.oo2.tpgrupo30.repositories.IProductRepository;
import com.oo2.tpgrupo30.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {

	private ILoteRepository loteRepository;
	private IProductRepository productRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public LoteService(ILoteRepository loteRepository, IProductRepository productRepository) {
		this.loteRepository = loteRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public Lote insertOrUpdate(Lote lote) {
		// Obtener el producto del lote
		Producto producto = lote.getProducto();

		// Sumar la cantidad del nuevo lote a la cantidad en stock actual del producto
		int nuevaCantidadEnStock = producto.getCantidad_en_stock() + lote.getCantidad();
		producto.setCantidad_en_stock(nuevaCantidadEnStock);

		// Guardar el producto actualizado en la base de datos
		productRepository.save(producto);

		// Guardar el lote en la base de datos
		Lote loteN = loteRepository.save(lote);

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