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

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/utente")
public class RichiestaPermessoController {

	@Autowired
	private RichiestaPermessoService richiestaPermessoService;

	@GetMapping
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermesso> permessi = richiestaPermessoService.listAll();
		mv.addObject("permesso_list_attribute", permessi);
		mv.setViewName("utente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchPermesso(Model model) {
		return "utente/search";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_permesso_attr", new RichiestaPermessoDTO());
		return "utente/insert";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("insert_permesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "utente/insert";
		}
		richiestaPermessoService.inserisciNuovo(richiestaPermessoDTO.buildRichiestaPermessoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@GetMapping("/edit/{idPermesso}")
	public String edit(@PathVariable(required = true) Long idPermesso, Model model) {
		RichiestaPermesso permessoModel = richiestaPermessoService.caricaSingoloElemento(idPermesso);
		model.addAttribute("edit_permesso_attr",
				RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(permessoModel));
		return "utente/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("edit_permesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "utente/edit";
		}
		richiestaPermessoService.aggiorna(richiestaPermessoDTO.buildRichiestaPermessoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@GetMapping("/show/{idPermesso}")
	public String showPermesso(@PathVariable(required = true) Long idPermesso, Model model) {
		model.addAttribute("show_permesso_attr", richiestaPermessoService.caricaSingoloElemento(idPermesso));
		return "utente/show";
	}

	@PostMapping("/list")
	public String listPermessi(RichiestaPermessoDTO permessoExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<RichiestaPermesso> permessi = richiestaPermessoService
				.findByExampleWithPagination(permessoExample.buildRichiestaPermessoModel(), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("permesso_list_attribute",
				RichiestaPermessoDTO.createRichiestaPermessoListFromModelList(permessi));
		return "utente/list";
	}

}
