package com.oo2.tpgrupo30.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.tpgrupo30.entities.Compra;
import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.helpers.ViewRouteHelper;
import com.oo2.tpgrupo30.services.ICompraService;
import com.oo2.tpgrupo30.services.IProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/compras")
public class CompraController {

	private final ICompraService compraService;
	private final IProductService productService;

	public CompraController(ICompraService compraService, IProductService productService) {
		this.compraService = compraService;
		this.productService = productService;
	}

	@GetMapping("/form")
	public String compraForm(Model model) {
		model.addAttribute("compra", new Compra());
		model.addAttribute("productos", productService.getAll());
		return ViewRouteHelper.COMPRA_FORM;
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("compra") Compra compra, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("compra", compra);
			model.addAttribute("productos", productService.getAll());
			return ViewRouteHelper.COMPRA_FORM;
		}

		Producto producto = productService.findByidProducto(compra.getProducto().getIdProducto());
		if (compra.getCantidad() > producto.getCantidad_en_stock()) {
			bindingResult.rejectValue("cantidad", "cantidad.excedeStock",
					"La cantidad solicitada supera la cantidad en stock");
			model.addAttribute("compra", compra);
			model.addAttribute("productos", productService.getAll());
			return ViewRouteHelper.COMPRA_FORM;
		}

		compraService.insertOrUpdate(compra);
		return "redirect:/productos/";
	}
	
	@GetMapping("/list")
    public ModelAndView list() {

        ModelAndView model = new ModelAndView(ViewRouteHelper.COMPRA_LIST);
        model.addObject("compras", compraService.getAll());
        return model;
    }

}