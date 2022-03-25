<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_permesso_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo elemento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
							<form:form modelAttribute="insert_permesso_attr" method="post" action="save" novalidate="novalidate" class="row g-3">
					
								<div class="col-md-6">
									<label for="tipoPermesso" class="form-label">Tipo di Permesso <span class="text-danger">*</span></label>
								    <spring:bind path="tipoPermesso">
										<select class="form-select ${status.error ? 'is-invalid' : ''}" id="tipoPermesso" name="tipoPermesso" required>
										    <option value="" selected> - Selezionare - </option>
										    <option value="FERIE" ${insert_permesso_attr.tipoPermesso == 'FERIE'?'selected':''} >FERIE</option>
										    <option value="MALATTIA" ${insert_permesso_attr.tipoPermesso == 'MALATTIA'?'selected':''} >MALATTIA</option>
										</select>
								    </spring:bind>
								    <form:errors  path="tipoPermesso" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="note" class="form-label">Note <span class="text-danger">*</span></label>
									<spring:bind path="note">
										<input type="text" name="note" id="note" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire le note" value="${insert_permesso_attr.note }">
									</spring:bind>
									<form:errors  path="note" cssClass="error_field" />
								</div>
								
								<div class="col-md-12 form-check">
									<div class="form-check">
										  <input class="form-check-input" name="check" type="checkbox" id="check">
										  <label class="form-check-label" for="check" >
										    Giorno Singolo
										  </label>
									</div>
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_permesso_attr.dataInizio}' />
								<div class="col-md-6">
									<label for="dataInizio" class="form-label">Data di Inizio <span class="text-danger">*</span></label>
                        			<spring:bind path="dataInizio">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataInizio" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataInizio" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataInizio" cssClass="error_field" />
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_permesso_attr.dataFine}' />
								<div class="col-md-6" id="dataFine">
									<label for="dataFine" class="form-label">Data di Fine <span class="text-danger">*</span></label>
                        			<spring:bind path="dataFine">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataFine" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataFine" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataFine" cssClass="error_field" />
								</div>
								
								<div class="col-md-6 d-none" id="codice">
									<label for="codiceCertificato" class="form-label">Codice di Certificato <span class="text-danger">*</span></label>
									<spring:bind path="codiceCertificato">
										<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice di certificato" value="${insert_permesso_attr.codiceCertificato }" required>
									</spring:bind>
									<form:errors  path="codiceCertificato" cssClass="error_field" />
								</div>
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>	
				
				<script>
				$(document).ready(function(){
					$('.form-select').click(function(){
						if($('#tipoPermesso :selected').text()=== 'MALATTIA'){
							//console.log("MALATTIA");
							$("#codice").removeClass('d-none');
						}else{
							//console.log("FERIE");
							$("#codice").addClass('d-none');
						}
					});
					
					$('#check').click(function(){
						$("#dataFine").toggle();
					});
				});
				</script>	
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>