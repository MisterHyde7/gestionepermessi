package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;
import it.prova.gestionepermessi.repository.utente.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		Utente utenzaDelDipendente = new Utente(dipendenteInstance.getNome(), dipendenteInstance.getCognome());
		utenteRepository.save(utenzaDelDipendente);
		dipendenteInstance.setUtente(utenzaDelDipendente);
		repository.save(dipendenteInstance);
	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		repository.delete(dipendenteInstance);
	}

	@Override
	public Page<Dipendente> findByExampleWithPagination(Dipendente example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodFis()))
				predicates.add(cb.like(cb.upper(root.get("codFis")), "%" + example.getCodFis().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));

			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascita"), example.getDataNascita()));

			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataAssunzione"), example.getDataAssunzione()));

			if (example.getDataDimissioni() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDimissioni"), example.getDataDimissioni()));

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

	@Transactional
	public void inserisciNuovoConUtente(Dipendente dipendenteInstance, Utente utenteInstance) {
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteInstance.setDateCreated(new Date());
		utenteInstance.setDipendente(dipendenteInstance);
		dipendenteInstance.setUtente(utenteInstance);
		repository.save(dipendenteInstance);
		utenteRepository.save(utenteInstance);
	}
}
