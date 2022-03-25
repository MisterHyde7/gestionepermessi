<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca elementi</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/permesso/list" class="row g-3">
							
							<div class="col-md-6">
								<label for="tipoPermesso" class="form-label">Tipo di Permesso</label>
								<select class="form-select ${status.error ? 'is-invalid' : ''}" id="tipoPermesso" name="tipoPermesso" required>
								    <option value="" selected> - Selezionare - </option>
								    <option value="FERIE" ${insert_permesso_attr.tipoPermesso == 'FERIE'?'selected':''} >FERIE</option>
								    <option value="MALATTIA" ${insert_permesso_attr.tipoPermesso == 'MALATTIA'?'selected':''} >MALATTIA</option>
								</select>
							</div>
							
							<div class="col-md-6">
								<label for="dataInizio" class="form-label">Data di Inizio</label>
                        		<input class="form-control" id="dataInizio" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataInizio" >
							</div>
							
							<div class="col-md-6">
								<label for="dataFine" class="form-label">Data di Fine</label>
                        		<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataFine" >
							</div>
							
							<div class="col-md-6">
								<label for="approvato" class="form-label">Approvazione</label>
								<select class="form-select ${status.error ? 'is-invalid' : ''}" id="approvato" name="approvato" required>
								    <option value="" selected> - Selezionare - </option>
								    <option value=TRUE >ACCETTATO</option>
								    <option value=FALSE >RIFIUTATO</option>
								</select>
							</div>
							
							<div class="col-md-6 d-none" id="codice">
								<label for="codiceCertificato" class="form-label">Codice di Certificato</label>
								<input type="text" class="form-control" name="codiceCertificato" id="codiceCertificato" placeholder="Inserire il codice di certificato" >
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/permesso/insert">Add New</a>
							</div>
	
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
			
		<script>
			$('.form-select').click(function(){
				if($('#tipoPermesso :selected').text()=== 'MALATTIA'){
					//console.log("MALATTIA");
					$("#codice").removeClass('d-none');
				}else{
					//console.log("FERIE");
					$("#codice").addClass('d-none');
				}
			});
		</script>
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>