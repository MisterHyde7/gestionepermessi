package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.repository.messaggio.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {

	@Autowired
	private MessaggioRepository repository;

	@Override
	public List<Messaggio> listAll() {
		return (List<Messaggio>) repository.findAll();
	}

	@Override
	public Messaggio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Messaggio messaggioInstance) {
		Messaggio messaggioReloaded = repository.findById(messaggioInstance.getId()).orElse(null);
		if (messaggioReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		messaggioReloaded.setTesto(messaggioInstance.getTesto());
		messaggioReloaded.setOggetto(messaggioInstance.getOggetto());
		messaggioReloaded.setLetto(messaggioInstance.isLetto());
		repository.save(messaggioReloaded);
	}

	@Override
	public void inserisciNuovo(Messaggio messaggioInstance) {
		repository.save(messaggioInstance);
	}

	@Override
	public void rimuovi(Messaggio messaggioInstance) {
		repository.delete(messaggioInstance);
	}

}
