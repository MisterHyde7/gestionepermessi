package it.prova.gestionepermessi.repository.messaggio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository
		extends PagingAndSortingRepository<Messaggio, Long>, JpaSpecificationExecutor<Messaggio> {

	List<Messaggio> findAllByLetto(boolean b);

	Messaggio findByRichiestaPermesso_Id(Long idPermesso);

}
