package it.prova.gestionepermessi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dipendente")
public class Dipendente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codFis")
	private String codFis;
	@Column(name = "email")
	private String email;
	@Column(name = "dataNascita")
	private Date dataNascita;
	@Column(name = "dataAssunzione")
	private Date dataAssunzione;
	@Column(name = "dataDimissioni")
	private Date dataDimissioni;

	@Column(name = "sesso")
	@Enumerated(EnumType.STRING)
	private Sesso sesso;

	@OneToOne
	private Utente utente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<RichiestaPermesso> richiestePermesso = new HashSet<RichiestaPermesso>(0);

	public Dipendente() {
		super();
	}

	public Dipendente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		if (!(nome.isBlank() && cognome.isBlank()))
			this.email = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase() + "@solvingteam.it";
	}

	public Dipendente(String nome, String cognome, String codFis, Date dataNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		if (!(nome.isBlank() && cognome.isBlank()))
			this.email = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase() + "@solvingteam.it";
		this.dataNascita = dataNascita;
	}

	public Dipendente(Long id, String nome, String cognome, String codFis, Date dataNascita, Date dataAssunzione,
			Sesso sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		if (!(nome.isBlank() && cognome.isBlank()))
			this.email = nome.toLowerCase().charAt(0) + "." + cognome.toLowerCase() + "@solvingteam.it";
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codFis, Date dataNascita, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codFis, Date dataNascita, Date dataAssunzione, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Dipendente(Long id, String nome, String cognome, String codFis, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
	}

	public Dipendente(String nome, String cognome, String codFis, String email, Date dataNascita, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione,
			Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione,
			Date dataDimissioni, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
	}

	public Dipendente(String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione,
			Date dataDimissioni, Sesso sesso, Utente utente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
		this.utente = utente;
	}

	public Dipendente(Long id, String nome, String cognome, String codFis, String email, Date dataNascita,
			Date dataAssunzione, Date dataDimissioni, Sesso sesso, Utente utente,
			Set<RichiestaPermesso> richiestePermesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
		this.utente = utente;
		this.richiestePermesso = richiestePermesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<RichiestaPermesso> getRichiestePermesso() {
		return richiestePermesso;
	}

	public void setRichiestePermesso(Set<RichiestaPermesso> richiestePermesso) {
		this.richiestePermesso = richiestePermesso;
	}

}
