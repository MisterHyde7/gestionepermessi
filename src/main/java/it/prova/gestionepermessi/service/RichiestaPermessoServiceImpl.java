package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;
import it.prova.gestionepermessi.repository.richiestapermesso.RichiestaPermessoRepository;
import it.prova.gestionepermessi.repository.utente.UtenteRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;

	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Autowired
	private UtenteRepository utenteRepository;

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

	@Override
	public Page<RichiestaPermesso> findByExampleWithPagination(RichiestaPermesso example, Integer pageNo,
			Integer pageSize, String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(cb.equal(cb.upper(root.get("tipoPermesso")), example.getTipoPermesso()));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));

			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));

			predicates.add(cb.equal(cb.upper(root.get("approvato")), example.isApprovato()));

			if (StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates.add(cb.like(cb.upper(root.get("codiceCertificato")),
						"%" + example.getCodiceCertificato().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getNote()))
				predicates.add(cb.like(cb.upper(root.get("note")), "%" + example.getNote().toUpperCase() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public void inserisciNuovoConDipendente(RichiestaPermesso permessoInstance, Authentication authentication) {
		Utente utente = utenteRepository.findByUsername(authentication.getName()).orElse(null);
		Dipendente dipendente = dipendenteRepository.findByUtente_Id(utente.getId());
		permessoInstance.setDipendente(dipendente);
		repository.save(permessoInstance);
	}
}
