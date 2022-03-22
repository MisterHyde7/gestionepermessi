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
@RequestMapping(value = "/backoffice")
public class MessaggioController {

	@Autowired
	private MessaggioService messaggioService;

	@GetMapping
	public ModelAndView listAllMessaggi() {
		ModelAndView mv = new ModelAndView();
		List<Messaggio> messaggi = messaggioService.listAll();
		mv.addObject("messaggio_list_attribute", messaggi);
		mv.setViewName("backoffice/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchMessaggi(Model model) {
		return "backoffice/search";
	}

	@PostMapping("/list")
	public String listMessaggi(MessaggioDTO messaggioExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Messaggio> messaggi = messaggioService
				.findByExampleWithPagination(messaggioExample.buildMessaggioModel(), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("messaggio_list_attribute", MessaggioDTO.createMessaggioDTOListFromModelList(messaggi));
		return "backoffice/list";
	}

	@GetMapping("/show/{idMessaggio}")
	public String showMessaggio(@PathVariable(required = true) Long idMessaggio, Model model) {
		model.addAttribute("show_messaggio_attr",
				MessaggioDTO.buildMessaggioDTOFromModel(messaggioService.caricaSingoloElemento(idMessaggio)));
		return "backoffice/show";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_messaggio_attr", new MessaggioDTO());
		return "backoffice/insert";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("insert_messaggio_attr") MessaggioDTO messaggioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "backoffice/insert";
		}
		messaggioService.inserisciNuovo(messaggioDTO.buildMessaggioModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice";
	}

	@GetMapping("/edit/{idMessaggio}")
	public String edit(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messaggioModel = messaggioService.caricaSingoloElemento(idMessaggio);
		model.addAttribute("edit_messaggio_attr", MessaggioDTO.buildMessaggioDTOFromModel(messaggioModel));
		return "backoffice/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("edit_messaggio_attr") MessaggioDTO messaggioDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "backoffice/edit";
		}
		messaggioService.aggiorna(messaggioDTO.buildMessaggioModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice";
	}
}
