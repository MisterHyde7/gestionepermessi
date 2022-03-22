package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;

	@Override
	public List<Dipendente> listAll() {
		return (List<Dipendente>) repository.findAll();
	}

	@Override
	public Dipendente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Dipendente dipendenteInstance) {
		Dipendente dipendenteReloaded = repository.findById(dipendenteInstance.getId()).orElse(null);
		if (dipendenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		dipendenteReloaded.setNome(dipendenteInstance.getNome());
		dipendenteReloaded.setCognome(dipendenteInstance.getCognome());
		dipendenteReloaded.setCodFis(dipendenteInstance.getCodFis());
		dipendenteReloaded.setEmail(dipendenteInstance.getEmail());
		dipendenteReloaded.setDataNascita(dipendenteInstance.getDataNascita());
		dipendenteReloaded.setDataAssunzione(dipendenteInstance.getDataAssunzione());
		dipendenteReloaded.setDataDimissioni(dipendenteInstance.getDataDimissioni());
		repository.save(dipendenteReloaded);
	}

	@Override
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		repository.save(dipendenteInstance);
	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		repository.delete(dipendenteInstance);
	}
}
