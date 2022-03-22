package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {

	public List<Messaggio> listAll();

	public Messaggio caricaSingoloElemento(Long id);

	public void aggiorna(Messaggio messaggioInstance);

	public void inserisciNuovo(Messaggio messaggioInstance);

	public void rimuovi(Messaggio messaggioInstance);
}
