<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_permesso_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Tipo di Permesso:</dt>
					  <dd class="col-sm-9">${show_permesso_attr.tipoPermesso}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di Inizio:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_permesso_attr.dataInizio}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di Fine:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_permesso_attr.dataFine}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Approvato:</dt>
					  <dd class="col-sm-9">${show_permesso_attr.approvato}</dd>
			    	</dl>
			    	
			    	<c:if test="${show_permesso_attr.tipoPermesso == 'MALATTIA'}">
				    	<dl class="row">
						  <dt class="col-sm-3 text-right">Codice di Certificato:</dt>
						  <dd class="col-sm-9">${show_permesso_attr.codiceCertificato}</dd>
				    	</dl>
			    	</c:if>
			    	
			    	<c:if test="${show_permesso_attr.note!=null}">
				    	<dl class="row">
						  <dt class="col-sm-3 text-right">Note:</dt>
						  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_permesso_attr.note}" /></dd>
				    	</dl>
			    	</c:if>
			    	
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Dipendente
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Nome:</dt>
						  <dd class="col-sm-9">${show_permesso_attr.dipendente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${show_permesso_attr.dipendente.cognome}</dd>
					   	</dl>
					   	<c:if test="${show_permesso_attr.dipendente.codFis!=null}">
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">CodiceFiscale:</dt>
							  <dd class="col-sm-9">${show_permesso_attr.dipendente.codFis}</dd>
						   	</dl>
						</c:if>
					  </div>
					</div>
					
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/permesso/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>