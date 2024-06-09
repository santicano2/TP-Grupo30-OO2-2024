package com.oo2.tpgrupo30.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.helpers.ViewRouteHelper;
import com.oo2.tpgrupo30.services.IProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductController {

	private IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public ModelAndView index() {

		ModelAndView model = new ModelAndView(ViewRouteHelper.PRODUCT_INDEX);
		model.addObject("productos", productService.getAll());
		model.addObject("producto", new Producto());
		return model;
	}
	


	@PostMapping("/")
	public RedirectView create(@ModelAttribute("producto") Producto producto) {
		productService.insertOrUpdate(producto);
		return new RedirectView(ViewRouteHelper.PRODUCT_ROOT);
	}

	@GetMapping("/form")
	public String producto(Model model) {
		model.addAttribute("producto", new Producto());
		return ViewRouteHelper.PRODUCT_FORM;
	}

	@PostMapping("/new")
	public ModelAndView newproducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult bindingResult) {
		ModelAndView m = new ModelAndView();
		if (bindingResult.hasErrors()) {
			m.setViewName(ViewRouteHelper.PRODUCT_FORM);
		} else {
			m.setViewName(ViewRouteHelper.PRODUCT_NEW);
			m.addObject("producto", producto);
		}
		return m;
	}

	@GetMapping("/edit/{idProducto}")
	public String editProducto(@PathVariable("idProducto") int idProducto, Model model) {
		Producto producto = productService.findByidProducto(idProducto);
		if (producto != null) {
			model.addAttribute("producto", producto);
			return ViewRouteHelper.PRODUCT_UPDATE;
		} else {
			return "redirect:/productos/";
		}
	}

	@PostMapping("/update/{idProducto}")
	public String updateProducto(@PathVariable("idProducto") int idProducto,
			@Valid @ModelAttribute("producto") Producto producto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("producto", producto);
			return ViewRouteHelper.PRODUCT_UPDATE;
		}

		Producto existingProducto = productService.findByidProducto(idProducto);
		if (existingProducto != null) {
			producto.setLotes(existingProducto.getLotes()); // Retain the existing lotes
			producto.setIdProducto(idProducto);
			productService.insertOrUpdate(producto);
		}

		return "redirect:/productos/";
	}

	@PostMapping("/delete/{idProducto}")
	public String deleteProducto(@PathVariable("idProducto") int idProducto) {
		productService.remove(idProducto);
		return "redirect:/productos/";
	}
}
