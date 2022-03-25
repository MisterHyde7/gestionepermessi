package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/permesso")
public class RichiestaPermessoController {

	@Autowired
	private RichiestaPermessoService richiestaPermessoService;

	@Autowired
	private MessaggioService messaggioService;

	@GetMapping
	public ModelAndView listAllPermessi() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermesso> permessi = richiestaPermessoService.listAll();
		mv.addObject("permesso_list_attribute", permessi);
		mv.setViewName("permesso/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchPermesso(Model model) {
		return "permesso/search";
	}

	@GetMapping("/insert")
	public String createPermesso(Model model) {
		model.addAttribute("insert_permesso_attr", new RichiestaPermessoDTO());
		return "permesso/insert";
	}

	@PostMapping("/save")
	public String savePermesso(@ModelAttribute("insert_permesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "permesso/insert";
		}
		richiestaPermessoService.inserisciNuovoConDipendente(richiestaPermessoDTO.buildRichiestaPermessoModel(),
				SecurityContextHolder.getContext().getAuthentication());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/permesso";
	}

	@GetMapping("/edit/{idPermesso}")
	public String editPermesso(@PathVariable(required = true) Long idPermesso, Model model) {
		RichiestaPermesso permessoModel = richiestaPermessoService.caricaSingoloElemento(idPermesso);
		model.addAttribute("edit_permesso_attr",
				RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(permessoModel));
		return "permesso/edit";
	}

	@PostMapping("/update")
	public String updatePermesso(@ModelAttribute("edit_permesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "permesso/edit";
		}
		richiestaPermessoService.aggiorna(richiestaPermessoDTO.buildRichiestaPermessoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/permesso";
	}

	@GetMapping("/show/{idPermesso}")
	public String showPermesso(@PathVariable(required = true) Long idPermesso, Model model) {
		model.addAttribute("show_permesso_attr", richiestaPermessoService.caricaSingoloElementoConFile(idPermesso));
		return "permesso/show";
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
		return "permesso/list";
	}

	@GetMapping("/delete/{idPermesso}")
	public String deletePermesso(@PathVariable(required = true) Long idPermesso, Model model,
			RedirectAttributes redirectAttrs) {

		model.addAttribute("delete_permesso_attr", richiestaPermessoService.caricaSingoloElemento(idPermesso));
		return "permesso/delete";
	}

	@GetMapping("/remove/{idPermesso}")
	public String removePermesso(@PathVariable(required = true) Long idPermesso, RedirectAttributes redirectAttrs) {

		RichiestaPermesso permesso = richiestaPermessoService.caricaSingoloElemento(idPermesso);
		Messaggio msg = messaggioService.caricaSingoloElementoConIdPermesso(idPermesso);
		messaggioService.rimuovi(msg);
		richiestaPermessoService.rimuovi(permesso);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/permesso";
	}

	@GetMapping("/autorizza/{idPermesso}")
	public String autorizzaPermesso(@PathVariable(required = true) Long idPermesso, RedirectAttributes redirectAttrs) {

		RichiestaPermesso permesso = richiestaPermessoService.caricaSingoloElemento(idPermesso);
		permesso.setApprovato(true);
		richiestaPermessoService.aggiorna(permesso);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/messaggio";
	}

	@GetMapping("/nega/{idPermesso}")
	public String negaPermesso(@PathVariable(required = true) Long idPermesso, RedirectAttributes redirectAttrs) {

		RichiestaPermesso permesso = richiestaPermessoService.caricaSingoloElemento(idPermesso);
		permesso.setApprovato(false);
		richiestaPermessoService.aggiorna(permesso);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/messaggio";
	}

}
