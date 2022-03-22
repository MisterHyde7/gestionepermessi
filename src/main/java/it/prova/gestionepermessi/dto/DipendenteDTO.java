package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;

public class DipendenteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri")
	private String codiceFiscale;

	@NotBlank(message = "{email.notblank}")
	private String email;

	private Date dataNascita;
	private Date dataAssunzione;
	private Date dataDimissioni;

	private Sesso sesso;

	@NotBlank(message = "{utente.notblank}")
	private Utente utente;

	private Long[] permessiIds;

	public DipendenteDTO() {
		super();
	}

	public DipendenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email,
			@NotBlank(message = "{utente.notblank}") Utente utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.utente = utente;
	}

	public DipendenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
	}

	public DipendenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email,
			@NotBlank(message = "{utente.notblank}") Utente utente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.utente = utente;
	}

	public DipendenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{email.notblank}") String email, Date dataNascita, Date dataAssunzione,
			Date dataDimissioni, Sesso sesso, @NotBlank(message = "{utente.notblank}") Utente utente,
			Long[] permessiIds) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
		this.utente = utente;
		this.permessiIds = permessiIds;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public Long[] getPermessiIds() {
		return permessiIds;
	}

	public void setPermessiIds(Long[] permessiIds) {
		this.permessiIds = permessiIds;
	}

	public Dipendente buildDipendenteModel(boolean includeIdPermessi) {
		Dipendente result = new Dipendente(this.id, this.nome, this.cognome, this.codiceFiscale, this.email);
		if (includeIdPermessi && permessiIds != null)
			result.setRichiestePermesso(Arrays.asList(permessiIds).stream().map(id -> new RichiestaPermesso(id))
					.collect(Collectors.toSet()));

		return result;
	}

	public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
		DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(),
				dipendenteModel.getCognome(), dipendenteModel.getCodFis(), dipendenteModel.getEmail(),
				dipendenteModel.getUtente());

		if (!dipendenteModel.getRichiestePermesso().isEmpty())
			result.permessiIds = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId())
					.collect(Collectors.toList()).toArray(new Long[] {});

		return result;
	}

	public static List<DipendenteDTO> createDipendenteDTOListFromModelList(List<Dipendente> modelListInput) {
		return modelListInput.stream().map(dipendenteEntity -> {
			return DipendenteDTO.buildDipendenteDTOFromModel(dipendenteEntity);
		}).collect(Collectors.toList());
	}

}
