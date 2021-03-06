<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_permesso_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipo di Permesso:</dt>
							  <dd class="col-sm-9">${delete_permesso_attr.tipoPermesso}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Inizio:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_permesso_attr.dataInizio}" /></dd>
			    			</dl>
			    			
			    			<c:if test="${delete_permesso_attr.dataFine!=null}">
				    			<dl class="row">
								  <dt class="col-sm-3 text-right">Data di Fine:</dt>
								  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_permesso_attr.dataFine}" /></dd>
				    			</dl>
			    			</c:if>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Approvato:</dt>
							  <dd class="col-sm-9">${delete_permesso_attr.isApprovato()}</dd>
					    	</dl>
					    	
					    	<c:if test="${!delete_permesso_attr.codiceCertificato.isBlank()}">
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
								  <dd class="col-sm-9">${delete_permesso_attr.codiceCertificato}</dd>
						    	</dl>
					    	</c:if>
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					   		<a href="${pageContext.request.contextPath}/permesso/remove/${idPermesso}" class='btn btn-outline-primary' style='width:100px'>
					            <i class='fa fa-chevron-left'></i> Conferm
					        </a>
					        <a href="${pageContext.request.contextPath}/permesso" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>