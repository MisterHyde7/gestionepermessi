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

	@Override
	public Page<Messaggio> findByExampleWithPagination(Messaggio example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Messaggio> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getTesto()))
				predicates.add(cb.like(cb.upper(root.get("testo")), "%" + example.getTesto().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getOggetto()))
				predicates.add(cb.like(cb.upper(root.get("oggetto")), "%" + example.getOggetto().toUpperCase() + "%"));

			predicates.add(cb.equal(cb.upper(root.get("letto")), example.isLetto()));

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
	public boolean listNonLetti() {
		if (repository.findByLetto(false) != null)
			return true;
		return false;
	}

}
