package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;

@Controller
@RequestMapping(value = "/dipendente")
public class DipendenteController {

	@Autowired
	private DipendenteService dipendenteService;

	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Dipendente> utenti = dipendenteService.listAll();
		mv.addObject("dipendente_list_attribute", utenti);
		mv.setViewName("dipendente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchDipendente(Model model) {
		return "dipendente/search";
	}

	@GetMapping("/insert")
	public String createDipendente(Model model) {
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "dipendente/insert";
	}

	@PostMapping("/save")
	public String saveDipendente(@ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "dipendente/insert";
		}
		boolean hasPermessi = false;
		if (dipendenteDTO.getPermessiIds() != null && dipendenteDTO.getPermessiIds().length != 0)
			hasPermessi = true;
		dipendenteService.inserisciNuovoConUtente(dipendenteDTO.buildDipendenteModel(hasPermessi),
				new Utente(dipendenteDTO.getNome(), dipendenteDTO.getCognome()));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/dipendente";
	}

	@GetMapping("/edit/{idDipendente}")
	public String editDipendente(@PathVariable(required = true) Long idDipendente, Model model) {
		Dipendente dipendenteModel = dipendenteService.caricaSingoloElemento(idDipendente);
		model.addAttribute("edit_utente_attr", DipendenteDTO.buildDipendenteDTOFromModel(dipendenteModel));
		return "dipendente/edit";
	}

	@PostMapping("/update")
	public String updateDipendente(@ModelAttribute("edit_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "dipendente/edit";
		}

		boolean hasPermessi = false;
		if (dipendenteDTO.getPermessiIds().length != 0)
			hasPermessi = true;
		dipendenteService.aggiorna(dipendenteDTO.buildDipendenteModel(hasPermessi));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/dipendente";
	}

	@GetMapping("/show/{idDipendente}")
	public String showDipendente(@PathVariable(required = true) Long idDipendente, Model model) {

		Dipendente dipendentePerPagina = dipendenteService.caricaSingoloElemento(idDipendente);
		model.addAttribute("show_dipendente_attr", dipendentePerPagina);
		model.addAttribute("show_utente_attr", dipendentePerPagina.getUtente());
		return "dipendente/show";
	}

	@PostMapping("/list")
	public String listDipendenti(DipendenteDTO dipendenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		boolean hasPermessi = false;
		if (dipendenteExample.getPermessiIds() != null)
			hasPermessi = true;
		List<Dipendente> dipendenti = dipendenteService.findByExampleWithPagination(
				dipendenteExample.buildDipendenteModel(hasPermessi), pageNo, pageSize, sortBy).getContent();

		model.addAttribute("dipendente_list_attribute", DipendenteDTO.createDipendenteDTOListFromModelList(dipendenti));
		return "dipendente/list";
	}

}
