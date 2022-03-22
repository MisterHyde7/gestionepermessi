package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {

	public List<RichiestaPermesso> listAll();

	public RichiestaPermesso caricaSingoloElemento(Long id);

	public void aggiorna(RichiestaPermesso permessoInstance);

	public void inserisciNuovo(RichiestaPermesso permessoInstance);

	public void rimuovi(RichiestaPermesso permessoInstance);

	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso buildRichiestaPermessoModel,
			Integer pageNo, Integer pageSize, String sortBy);
}
