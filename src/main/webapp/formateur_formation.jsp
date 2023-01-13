<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>TP 2</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">    </head>
    <body class="bg-light">
        <section class="py-5">
            <div class=" container px-5 my-5">
                <div class="text-center mb-5">
                			<ul class="navbar-nav">
								<li><a href="<%=request.getContextPath()%>/list"
									class="nav-link">Formations</a></li>
							</ul>
                	<h2 class="fw-bolder">
						<c:if test="${formation != null}">
            			Modifier la ligne de formation
            		</c:if>
						<c:if test="${formation == null}">
            			Ajouter une ligne de formation 
            		</c:if>
					</h2>
                    <p class="lead mb-0">Veuillez remplir ce champ</p>
                </div>
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-6">
                        <c:if test="${formation != null}">
							<form action="updateFF" method="post">
						</c:if>
						
						<c:if test="${formation == null}">
							<form action="insererFF" method="post">
						</c:if>
                            <c:if test="${formation != null}">
								<input type="hidden" name="id" value="<c:out value='${formation.id}' />" />
							</c:if>
                             <div class="form-floating mb-3">                                              	
                            	<select name=theme class="form-control form-select">
                            		<option selected>
                            			Veuillez choisir la formation
                            		</option> 
                            		<c:forEach var="formation" items="${listFormation}">
                            			<option value="${formation.theme}" > <c:out value ="${formation.theme}"></c:out></option>
                            		</c:forEach>
                            	</select>             
                                <div class="invalid-feedback" data-sb-feedback="lieu:required">Champ requis</div>
                            </div>
                            <div class="form-floating mb-3">                                              	
                            	<select name="nom" class="form-control form-select">
                            		<option selected>
                            			Veuillez choisir le formateur
                            		</option> 
                            		<c:forEach var="formateur" items="${listFormateur}">
                            			<option value="${formateur.nom}" > <c:out value ="${formateur.nom}"></c:out></option>
                            		</c:forEach>
                            	</select>             
                                <div class="invalid-feedback" data-sb-feedback="lieu:required">Champ requis</div>
                            </div>
					      <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-3">
                                    <div class="fw-bolder">Formation ajoutée avec succès !</div>
                                </div>
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Enregistrer</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>      
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
