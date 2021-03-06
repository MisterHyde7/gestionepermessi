package it.prova.gestionepermessi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class Messaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "testo")
	private String testo;

	@Column(name = "oggetto")
	private String oggetto;

	@Column(name = "letto")
	private Boolean letto;

	@OneToOne
	@JoinColumn(name = "permesso_id")
	private RichiestaPermesso richiestaPermesso;

	public Messaggio() {
		super();
	}

	public Messaggio(Boolean letto) {
		super();
		this.letto = letto;
	}

	public Messaggio(String testo, String oggetto) {
		super();
		this.testo = testo;
		this.oggetto = oggetto;
	}

	public Messaggio(Long id, String testo, String oggetto, Boolean letto) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public Boolean isLetto() {
		return letto;
	}

	public void setLetto(Boolean letto) {
		this.letto = letto;
	}

	public RichiestaPermesso getRichiestaPermesso() {
		return richiestaPermesso;
	}

	public void setRichiestaPermesso(RichiestaPermesso richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}

}
