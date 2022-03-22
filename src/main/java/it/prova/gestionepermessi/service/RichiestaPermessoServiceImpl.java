package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.richiestapermesso.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;

	@Override
	public List<RichiestaPermesso> listAll() {
		return (List<RichiestaPermesso>) repository.findAll();
	}

	@Override
	public RichiestaPermesso caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(RichiestaPermesso permessoInstance) {
		RichiestaPermesso richiestaReloaded = repository.findById(permessoInstance.getId()).orElse(null);
		if (richiestaReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		richiestaReloaded.setTipoPermesso(permessoInstance.getTipoPermesso());
		richiestaReloaded.setDataInizio(permessoInstance.getDataInizio());
		richiestaReloaded.setDataFine(permessoInstance.getDataFine());
		richiestaReloaded.setApprovato(permessoInstance.isApprovato());
		richiestaReloaded.setCodiceCertificato(permessoInstance.getCodiceCertificato());
		richiestaReloaded.setNote(permessoInstance.getNote());
		repository.save(richiestaReloaded);
	}

	@Override
	public void inserisciNuovo(RichiestaPermesso permessoInstance) {
		repository.save(permessoInstance);
	}

	@Override
	public void rimuovi(RichiestaPermesso permessoInstance) {
		repository.delete(permessoInstance);
	}
}
