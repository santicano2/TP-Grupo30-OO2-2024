package com.oo2.tpgrupo30.controllers;

import com.oo2.tpgrupo30.entities.Lote;
import com.oo2.tpgrupo30.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lotes")
public class LoteController {

/*
    @Autowired
    private LoteService loteService;

    @GetMapping
    public String getAllLotes(Model model) {
        List<Lote> lotes = loteService.getAllLotes();
        model.addAttribute("lotes", lotes);
        return "lotes/list";
    }

    @GetMapping("/form")
    public String formLote(@RequestParam(required = false) Integer id, Model model) {
        Lote lote = new Lote();
        if (id != null) {
            Optional<Lote> optionalLote = loteService.getLoteById(id);
            if (optionalLote.isPresent()) {
                lote = optionalLote.get();
            }
        }
        model.addAttribute("lote", lote);
        return "lotes/form";
    }

    @PostMapping
    public String saveLote(@ModelAttribute Lote lote) {
        loteService.saveOrUpdateLote(lote);
        return "redirect:/lotes";
    }

    @PostMapping("/delete/{id}")
    public String deleteLote(@PathVariable Integer id) {
        loteService.deleteLoteById(id);
        return "redirect:/lotes";
    }
*/
}
