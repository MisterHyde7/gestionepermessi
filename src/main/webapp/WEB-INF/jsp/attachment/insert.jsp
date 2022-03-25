<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
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
			
								<form enctype="multipart/form-data"  method="post" action="save" validate class="row g-3">
								
									<div class="col-md-6 datiMalattia ">
										<label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
											<input type="text" class="form-control " name="descrizione" id="descrizione" placeholder="Inserire descrizione" required>
									</div>
									
									<div class="col-md-6 datiMalattia  ">
									  <label for="allegato" class="form-label">Allegato <span class="text-danger">*</span></label>
									  <input class="form-control" type="file" id="allegato" name="file" required>
									</div>
								
									<div class="col-12">
										<button type="submit" name="confirmButton"  id="confirmButton" class="btn btn-primary"  >Conferma</button>
									</div>
			
							</form>
							
						<!-- end card-body -->			   
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