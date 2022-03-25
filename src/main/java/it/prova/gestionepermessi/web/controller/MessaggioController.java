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

import it.prova.gestionepermessi.dto.MessaggioDTO;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.service.MessaggioService;

@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {

	@Autowired
	private MessaggioService messaggioService;

	@GetMapping
	public ModelAndView listAllMessaggi() {
		ModelAndView mv = new ModelAndView();
		List<Messaggio> messaggi = messaggioService.listAll();
		mv.addObject("messaggio_list_attribute", messaggi);
		mv.setViewName("messaggio/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchMessaggi(Model model) {
		return "messaggio/search";
	}

	@PostMapping("/list")
	public String listMessaggi(MessaggioDTO messaggioExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Messaggio> messaggi = messaggioService
				.findByExampleWithPagination(messaggioExample.buildMessaggioModel(), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("messaggio_list_attribute", MessaggioDTO.createMessaggioDTOListFromModelList(messaggi));
		return "messaggio/list";
	}

	@GetMapping("/show/{idMessaggio}")
	public String showMessaggio(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messaggio = messaggioService.caricaSingoloElemento(idMessaggio);
		model.addAttribute("show_messaggio_attr", MessaggioDTO
				.buildMessaggioDTOFromModelWithPermesso(messaggio));
		messaggio.setLetto(true);
		messaggioService.aggiorna(messaggio);
		return "messaggio/show";
	}

	@GetMapping("/insert")
	public String createMessaggio(Model model) {
		model.addAttribute("insert_messaggio_attr", new MessaggioDTO());
		return "messaggio/insert";
	}

	@PostMapping("/save")
	public String saveMessaggio(@ModelAttribute("insert_messaggio_attr") MessaggioDTO messaggioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "messaggio/insert";
		}
		messaggioService.inserisciNuovo(messaggioDTO.buildMessaggioModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/messaggio";
	}

	@GetMapping("/edit/{idMessaggio}")
	public String editMessaggio(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messaggioModel = messaggioService.caricaSingoloElemento(idMessaggio);
		model.addAttribute("edit_messaggio_attr", MessaggioDTO.buildMessaggioDTOFromModel(messaggioModel));
		return "messaggio/edit";
	}

	@PostMapping("/update")
	public String updateMessaggio(@ModelAttribute("edit_messaggio_attr") MessaggioDTO messaggioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "messaggio/edit";
		}
		messaggioService.aggiorna(messaggioDTO.buildMessaggioModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/messaggio";
	}
	
	@GetMapping("/listMessaggi")
	public String listMessaggi(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Messaggio> messaggi = messaggioService
				.findByExampleWithPagination(new Messaggio(false), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("messaggio_list_attribute", MessaggioDTO.createMessaggioDTOListFromModelList(messaggi));
		return "messaggio/list";
	}
	
}
