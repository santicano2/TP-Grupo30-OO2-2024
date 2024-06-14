package com.oo2.tpgrupo30.controllers;

import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.helpers.ViewRouteHelper;
import com.oo2.tpgrupo30.services.implementation.InventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/")
    public String informeInventario(Model model) {
        Producto productoMasVendido = inventarioService.obtenerProductoMasVendido();
        int cantidadVentasProductoMasVendido = inventarioService.calcularCantidadComprada(productoMasVendido);

        model.addAttribute("productoMasVendido", productoMasVendido);
        model.addAttribute("productoMasVendidoVentas", cantidadVentasProductoMasVendido);
        model.addAttribute("productoMasAntiguo", inventarioService.obtenerProductoMasAntiguo());
        model.addAttribute("fechaAntiguedadProductoMasAntiguo", inventarioService.obtenerFechaAntiguedadProductoMasAntiguo());
        model.addAttribute("productos", inventarioService.obtenerTodosLosProductos());
        model.addAttribute("valorTotalInventario", inventarioService.calcularValorTotalInventario());
        return ViewRouteHelper.INVENTARIO_INDEX;
    }
}