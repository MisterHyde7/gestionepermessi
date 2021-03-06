package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;

public class RichiestaPermessoDTO {

	private Long id;

	@NotBlank(message = "{tipoPermesso.notblank}")
	private TipoPermesso tipoPermesso;

	@NotNull(message = "{dataInizio.notblank}")
	private Date dataInizio;

	@NotNull(message = "{dataFine.notblank}")
	private Date dataFine;

	private Boolean approvato;

	@NotBlank(message = "{codiceCertificato.notblank}")
	private String codiceCertificato;

	private String note;

	private DipendenteDTO dipendenteDTO;

	private Attachment attachment;

	public RichiestaPermessoDTO() {
		super();
	}

	public RichiestaPermessoDTO(@NotBlank(message = "{tipoPermesso.notblank}") TipoPermesso tipoPermesso,
			@NotNull(message = "{dataInizio.notblank}") Date dataInizio,
			@NotNull(message = "{dataFine.notblank}") Date dataFine, Boolean approvato,
			@NotBlank(message = "{codiceCertificato.notblank}") String codiceCertificato) {
		super();
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
	}

	public RichiestaPermessoDTO(@NotBlank(message = "{tipoPermesso.notblank}") TipoPermesso tipoPermesso,
			@NotNull(message = "{dataInizio.notblank}") Date dataInizio,
			@NotNull(message = "{dataFine.notblank}") Date dataFine, Boolean approvato,
			@NotBlank(message = "{codiceCertificato.notblank}") String codiceCertificato, String note) {
		super();
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
	}

	public RichiestaPermessoDTO(Long id, @NotBlank(message = "{tipoPermesso.notblank}") TipoPermesso tipoPermesso,
			@NotNull(message = "{dataInizio.notblank}") Date dataInizio,
			@NotNull(message = "{dataFine.notblank}") Date dataFine, Boolean approvato,
			@NotBlank(message = "{codiceCertificato.notblank}") String codiceCertificato, String note) {
		super();
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(Boolean approvato) {
		this.approvato = approvato;
	}

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public DipendenteDTO getDipendenteDTO() {
		return dipendenteDTO;
	}

	public void setDipendenteDTO(DipendenteDTO dipendenteDTO) {
		this.dipendenteDTO = dipendenteDTO;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public RichiestaPermesso buildRichiestaPermessoModel() {
		RichiestaPermesso result = new RichiestaPermesso(this.id, this.tipoPermesso, this.dataInizio, this.dataFine,
				this.approvato, this.codiceCertificato, this.note);
		return result;
	}

	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestaPermesso permessoModel) {
		RichiestaPermessoDTO result = new RichiestaPermessoDTO(permessoModel.getId(), permessoModel.getTipoPermesso(),
				permessoModel.getDataInizio(), permessoModel.getDataFine(), permessoModel.isApprovato(),
				permessoModel.getCodiceCertificato(), permessoModel.getNote());

		if (permessoModel.getDipendente() != null)
			result.setDipendenteDTO(DipendenteDTO.buildDipendenteDTOFromModel(permessoModel.getDipendente()));

		return result;
	}

	public static List<RichiestaPermessoDTO> createRichiestaPermessoListFromModelList(
			List<RichiestaPermesso> modelListInput) {
		return modelListInput.stream().map(permessoEntity -> {
			return RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(permessoEntity);
		}).collect(Collectors.toList());
	}

}
