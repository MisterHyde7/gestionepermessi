package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {

	public List<RichiestaPermesso> listAll();

	public RichiestaPermesso caricaSingoloElemento(Long id);

	public void aggiorna(RichiestaPermesso permessoInstance);

	public void inserisciNuovo(RichiestaPermesso permessoInstance);

	public void rimuovi(RichiestaPermesso permessoInstance);

	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso buildRichiestaPermessoModel,
			Integer pageNo, Integer pageSize, String sortBy);

	public void inserisciNuovoConDipendente(RichiestaPermesso buildRichiestaPermessoModel,
			Authentication authentication);

	public RichiestaPermesso caricaSingoloElementoConFile(Long idPermesso);

	public void inserisciNuovoConDipendenteEFile(RichiestaPermesso buildRichiestaPermessoModel,
			Authentication authentication, Attachment attachment);
}
