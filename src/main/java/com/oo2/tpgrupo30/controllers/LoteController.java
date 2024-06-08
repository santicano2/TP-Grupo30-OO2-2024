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

import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.helpers.ViewRouteHelper;
import com.oo2.tpgrupo30.services.ILoteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lotes")
public class LoteController {

	private ILoteService loteService;

	public LoteController(ILoteService loteService) {
		this.loteService = loteService;
	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		model.addObject("lotes", loteService.getAll());
		model.addObject("lote", new Lote());
		return model;
	}

	@PostMapping("/")
	public RedirectView create(@ModelAttribute("lote") Lote lote) {
		loteService.insertOrUpdate(lote);
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}

	@GetMapping("/form")
	public String lote(Model model) {
		model.addAttribute("lote", new Lote());
		return ViewRouteHelper.LOTE_FORM;
	}

	@PostMapping("/new")
	public ModelAndView newLote(@Valid @ModelAttribute("lote") Lote lote, BindingResult bindingResult) {
		ModelAndView m = new ModelAndView();
		if (bindingResult.hasErrors()) {
			m.setViewName(ViewRouteHelper.LOTE_FORM);
		} else {
			m.setViewName(ViewRouteHelper.LOTE_NEW);
			m.addObject("lote", lote);
		}
		return m;
	}

	@GetMapping("/edit/{idLote}")
	public String editLote(@PathVariable("idLote") Long idLote, Model model) {
		Lote lote = loteService.findById(idLote);
		if (lote != null) {
			model.addAttribute("lote", lote);
			return ViewRouteHelper.LOTE_UPDATE;
		} else {
			return "redirect:/lotes/";
		}
	}



	@PostMapping("/delete/{idLote}")
	public String deleteLote(@PathVariable("idLote") Long idLote) {
		loteService.remove(idLote);
		return "redirect:/lotes/";
	}
}
