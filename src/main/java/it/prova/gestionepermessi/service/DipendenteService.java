package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {

	public List<Dipendente> listAll();

	public Dipendente caricaSingoloElemento(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);

	public Page<Dipendente> findByExampleWithPagination(Dipendente buildDipendenteModel, Integer pageNo,
			Integer pageSize, String sortBy);
}
