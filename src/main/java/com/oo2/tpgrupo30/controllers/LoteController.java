package com.oo2.tpgrupo30.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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

import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.entities.Producto;
import com.oo2.tpgrupo30.helpers.ViewRouteHelper;
import com.oo2.tpgrupo30.services.ILoteService;
import com.oo2.tpgrupo30.services.IProductService;

import jakarta.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/lotes")
public class LoteController {

	private final ILoteService loteService;
	private final IProductService productService;

	public LoteController(ILoteService loteService, IProductService productoService) {
		this.loteService = loteService;
		this.productService = productoService;
	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		model.addObject("lotes", loteService.getAll());
		return model;
	}

	@GetMapping("/form/{productoId}")
	public String loteForm(@PathVariable("productoId") int productoId, Model model) {
		Producto producto = productService.findByidProducto(productoId);
		if (producto != null) {
			Lote lote = new Lote();
			lote.setProducto(producto);
			model.addAttribute("lote", lote);
			return ViewRouteHelper.LOTE_FORM;
		}
		return "redirect:/productos/";
	}

	@PostMapping("/create")
	public RedirectView createLote(@Valid @ModelAttribute("lote") Lote lote, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("lote", lote);
			return new RedirectView(ViewRouteHelper.LOTE_FORM);
		}

		// Verificar si el producto asociado al lote existe en la base de datos
		Producto producto = productService.findByidProducto(lote.getProducto().getIdProducto());
		if (producto == null) {
			return new RedirectView(ViewRouteHelper.LOTE_FORM);
		}

		// Establecer el producto en el lote
		lote.setProducto(producto);

		// Guardar el lote
		loteService.insertOrUpdate(lote);

		return new RedirectView("/productos/");
	}

	@GetMapping("/list/{productoId}")
	public String listLotes(@PathVariable("productoId") int productoId, Model model) {
		List<Lote> lotes = loteService.findByProductoIdProducto(productoId);
		model.addAttribute("lotes", lotes);
		return ViewRouteHelper.LOTE_LIST;
	}

}