package com.oo2.tpgrupo30.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.repositories.ICompraRepository;
import com.oo2.tpgrupo30.repositories.IProductRepository;
import com.oo2.tpgrupo30.services.IProductService;

import jakarta.transaction.Transactional;

@Service("productService")
public class ProductService implements IProductService {

	private IProductRepository productRepository;
	
	private ICompraRepository compraRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public ProductService(IProductRepository productRepository, ICompraRepository compraRepository) {

		this.productRepository = productRepository;
		this.compraRepository = compraRepository;
	}

	@Override
	public List<Producto> getAll() {

		return productRepository.findAll();
	}

	@Override
	public Producto findByidProducto(int id) {
		return productRepository.findByidProducto(id);
	}

	@Override
	public Producto insertOrUpdate(Producto product) {
		Producto producto = productRepository.save(modelMapper.map(product, Producto.class));
		return modelMapper.map(producto, Producto.class);
	}

	@Override
	public boolean remove(int id) {

		try {
			productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
    public void eliminarProducto(int idProducto) {
        Producto producto = productRepository.findById(idProducto).orElse(null);
        if (producto != null) {
            // Eliminar todas las compras asociadas al producto
            compraRepository.deleteByProducto(producto);

            // Luego eliminar el producto
            productRepository.delete(producto);
        }
    }

}
