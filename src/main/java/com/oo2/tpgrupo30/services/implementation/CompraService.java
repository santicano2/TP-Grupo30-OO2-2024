package com.oo2.tpgrupo30.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.tpgrupo30.entities.Compra;
import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.repositories.ICompraRepository;
import com.oo2.tpgrupo30.repositories.IProductRepository;
import com.oo2.tpgrupo30.services.ICompraService;

@Service("compraService")
public class CompraService implements ICompraService {
    private final ICompraRepository compraRepository;
    private final IProductRepository productRepository;

    public CompraService(ICompraRepository compraRepository, IProductRepository productRepository) {
        this.compraRepository = compraRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    @Override
    public Compra insertOrUpdate(Compra compra) {
        Producto producto = productRepository.findById(compra.getProducto().getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        if (producto.getCantidad_en_stock() < compra.getCantidad()) {
            throw new IllegalArgumentException("Cantidad solicitada supera la cantidad en stock");
        }
        producto.setCantidad_en_stock(producto.getCantidad_en_stock() - compra.getCantidad());
        productRepository.save(producto);
        return compraRepository.save(compra);
    }

    @Override
    public boolean remove(int id) {
        try {
            compraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Compra findByIdCompra(int id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public List<Compra> findByProductoIdProducto(int idProducto) {
        return compraRepository.findByProductoIdProducto(idProducto);
    }
}