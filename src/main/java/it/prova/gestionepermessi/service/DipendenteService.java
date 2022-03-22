package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {

	public List<Dipendente> listAll();

	public Dipendente caricaSingoloElemento(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);
}
