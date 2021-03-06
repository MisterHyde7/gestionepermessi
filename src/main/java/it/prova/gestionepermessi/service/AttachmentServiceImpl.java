package it.prova.gestionepermessi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository repository;

	@Override
	@Transactional
	public List<Attachment> listAllElements() {
		return (List<Attachment>) repository.findAll();
	}

	@Override
	@Transactional
	public Attachment caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Attachment entityInstance) {
		repository.save(entityInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Attachment entityInstance) {
		repository.save(entityInstance);
	}

	@Override
	@Transactional
	public void rimuoviById(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

}
