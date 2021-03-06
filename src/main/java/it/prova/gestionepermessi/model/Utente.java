package it.prova.gestionepermessi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "dateCreated")
	private Date dateCreated;

	@OneToOne(mappedBy = "utente")
	private Dipendente dipendente;

	// se non uso questa annotation viene gestito come un intero
	@Enumerated(EnumType.STRING)
	private StatoUtente stato;

	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);

	public Utente() {
	}

	public Utente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		if (!(nome.isBlank() && cognome.isBlank()))
			this.username = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase();
		this.password = "Password@01";
		this.dateCreated = new Date();
		this.stato = StatoUtente.CREATO;
	}

	public Utente(Long id, String nome, String cognome, StatoUtente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		if (!(nome.isBlank() && cognome.isBlank()))
			this.username = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase();
		this.password = "Password@01";
		this.stato = StatoUtente.CREATO;
	}

	public Utente(String nome, String cognome, Set<Ruolo> ruoli) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ruoli = ruoli;
		this.username = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase();
	}

	public Utente(Long id, String nome, String cognome, Date dateCreated) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}

	public Utente(Long id, String username, String nome, String cognome, StatoUtente stato, Date dateCreated) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
		this.dateCreated = dateCreated;
	}

	public Utente(String username, String password, String nome, String cognome, Date dateCreated, StatoUtente stato) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public Utente(String username, String password, String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}

	public Utente(String nome, String cognome, String username, StatoUtente stato, Date dateCreated) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.stato = stato;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public boolean isBackOffice() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_BO_USER))
				return true;
		}
		return false;
	}

	public boolean isAdmin() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
				return true;
		}
		return false;
	}

	public boolean isAttivo() {
		return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
	}

	public boolean isDisabilitato() {
		return this.stato != null && this.stato.equals(StatoUtente.DISABILITATO);
	}

}
