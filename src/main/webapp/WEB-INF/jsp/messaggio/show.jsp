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
					  <dd class="col-sm-9">${show_messaggio_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Oggetto:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.oggetto}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Testo:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.testo}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Letto:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.letto}</dd>
			    	</dl>
					
					<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Utente
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Nome:</dt>
						  <dd class="col-sm-9">${show_dipendente_attr.utente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${show_dipendente_attr.utente.cognome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Username:</dt>
						  <dd class="col-sm-9">${show_dipendente_attr.utente.username}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Stato:</dt>
						  <dd class="col-sm-9">${show_dipendente_attr.utente.stato}</dd>
					   	</dl>
					    
					  </div>
					
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/utente/" class='btn btn-outline-secondary' style='width:80px'>
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