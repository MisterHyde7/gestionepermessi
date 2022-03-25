package it.prova.gestionepermessi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("BackOffice", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("BackOffice", "ROLE_BO_USER"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente", "ROLE_DIPENDENTE_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Dipendente", "ROLE_DIPENDENTE_USER"));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("M.Rossi") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			Dipendente dipendente = new Dipendente("Mario", "Rossi", "asdqwertyzxc", new Date(), new Date(),
					Sesso.MASCHIO);
			admin.setDipendente(dipendente);
			dipendente.setUtente(admin);
			utenteServiceInstance.inserisciNuovoConDipendente(admin, dipendente);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("L.Martucci") == null) {
			Utente backoffice = new Utente("backoffice", "backoffice", "Luca", "Martucci", new Date());
			backoffice.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("BackOffice", "ROLE_BO_USER"));
			Dipendente dipendente = new Dipendente("Luca", "Martucci", "asdqwertyzxc", new Date(), new Date(),
					Sesso.MASCHIO);
			backoffice.setDipendente(dipendente);
			dipendente.setUtente(backoffice);
			utenteServiceInstance.inserisciNuovoConDipendente(backoffice, dipendente);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(backoffice.getId());
		}
	}

}
