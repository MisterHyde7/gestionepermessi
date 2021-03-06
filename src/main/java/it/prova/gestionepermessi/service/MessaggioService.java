package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {

	public List<Messaggio> listAll();

	public Messaggio caricaSingoloElemento(Long id);

	public void aggiorna(Messaggio messaggioInstance);

	public void inserisciNuovo(Messaggio messaggioInstance);

	public void rimuovi(Messaggio messaggioInstance);

	public Page<Messaggio> findByExampleWithPagination(Messaggio buildMessaggioModel, Integer pageNo, Integer pageSize,
			String sortBy);

	public boolean listNonLetti();

	public Messaggio caricaSingoloElementoConIdPermesso(Long idPermesso);
}
