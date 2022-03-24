package it.prova.gestionepermessi.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public class MessaggioDTO {

	private Long id;

	@NotBlank(message = "{testo.notblank}")
	private String testo;

	@NotBlank(message = "{oggetto.notblank}")
	private String oggetto;

	@NotNull(message = "{letto.notblank}")
	private boolean letto;

	private RichiestaPermesso permesso;

	private boolean approvato;

	public MessaggioDTO() {
		super();
	}

	public MessaggioDTO(@NotBlank(message = "{testo.notblank}") String testo,
			@NotBlank(message = "{oggetto.notblank}") String oggetto) {
		super();
		this.testo = testo;
		this.oggetto = oggetto;
	}

	public MessaggioDTO(Long id, @NotBlank(message = "{testo.notblank}") String testo,
			@NotBlank(message = "{oggetto.notblank}") String oggetto) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
	}

	public MessaggioDTO(Long id, @NotBlank(message = "{testo.notblank}") String testo,
			@NotBlank(message = "{oggetto.notblank}") String oggetto,
			@NotNull(message = "{letto.notblank}") boolean letto) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
	}

	public MessaggioDTO(Long id, @NotBlank(message = "{testo.notblank}") String testo,
			@NotBlank(message = "{oggetto.notblank}") String oggetto,
			@NotNull(message = "{letto.notblank}") boolean letto, boolean approvato) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
		this.approvato = approvato;
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

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}

	public RichiestaPermesso getPermesso() {
		return permesso;
	}

	public void setPermesso(RichiestaPermesso permesso) {
		this.permesso = permesso;
	}

	public boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}

	public Messaggio buildMessaggioModel() {
		Messaggio result = new Messaggio(this.id, this.testo, this.oggetto, this.letto);
		return result;
	}

	public static MessaggioDTO buildMessaggioDTOFromModel(Messaggio messaggioModel) {
		MessaggioDTO result = new MessaggioDTO(messaggioModel.getId(), messaggioModel.getTesto(),
				messaggioModel.getOggetto(), messaggioModel.isLetto());

		return result;
	}

	public static MessaggioDTO buildMessaggioDTOFromModelWithPermesso(Messaggio messaggioModel) {
		MessaggioDTO result = new MessaggioDTO(messaggioModel.getId(), messaggioModel.getTesto(),
				messaggioModel.getOggetto(), messaggioModel.isLetto());

		return result;
	}

	public static List<MessaggioDTO> createMessaggioDTOListFromModelList(List<Messaggio> modelListInput) {
		return modelListInput.stream().map(messaggioEntity -> {
			return MessaggioDTO.buildMessaggioDTOFromModel(messaggioEntity);
		}).collect(Collectors.toList());
	}

}
