package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;

@Controller
@RequestMapping(value = "/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	@GetMapping
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		List<Utente> utenti = utenteService.listAllUtenti();
		mv.addObject("utente_list_attribute", utenti);
		mv.setViewName("utente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("ruoli", ruoloService.listAll());
		return "utente/search";
	}

	@GetMapping("/insert")
	public String createUtente(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("insert_utente_attr", new UtenteDTO());
		return "utente/insert";
	}

	@PostMapping("/save")
	public String saveUtente(@ModelAttribute("insert_utente_attr") UtenteDTO utenteDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "utente/insert";
		}
		Dipendente dipendenteDaUtente = new Dipendente(utenteDTO.getNome(), utenteDTO.getCognome());
		utenteService.inserisciNuovoConDipendente(utenteDTO.buildUtenteModel(true), dipendenteDaUtente);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@GetMapping("/edit/{idUtente}")
	public String editUtente(@PathVariable(required = true) Long idUtente, Model model) {
		Utente utenteModel = utenteService.caricaSingoloUtenteConRuoli(idUtente);
		model.addAttribute("edit_utente_attr", UtenteDTO.buildUtenteDTOFromModel(utenteModel));
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		return "utente/edit";
	}

	@PostMapping("/update")
	public String updateUtente(
			@Validated(ValidationNoPassword.class) @ModelAttribute("edit_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "utente/edit";
		}
		utenteService.aggiorna(utenteDTO.buildUtenteModelConId(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}

	@PostMapping("/cambiaStato")
	public String cambiaStato(@RequestParam(name = "idUtenteForChangingStato", required = true) Long idUtente) {
		utenteService.changeUserAbilitation(idUtente);
		return "redirect:/utente";
	}

	@GetMapping("/show/{idUtente}")
	public String showUtente(@PathVariable(required = true) Long idUtente, Model model) {
		model.addAttribute("show_utente_attr", utenteService.caricaSingoloUtenteConRuoli(idUtente));
		return "utente/show";
	}

	@PostMapping("/list")
	public String listUtenti(UtenteDTO utenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Utente> utenti = utenteService
				.findByExampleWithPagination(utenteExample.buildUtenteModel(true), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("utente_list_attribute", UtenteDTO.createUtenteDTOListFromModelList(utenti));
		return "utente/list";
	}

	@GetMapping(value = "/searchUtentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchUtente(@RequestParam String term) {

		List<Utente> listaUtenteByTerm = utenteService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaUtenteByTerm);
	}

	private String buildJsonResponse(List<Utente> listaUtenti) {
		JsonArray ja = new JsonArray();

		for (Utente utenteItem : listaUtenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", utenteItem.getId());
			jo.addProperty("label", utenteItem.getNome() + " " + utenteItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
