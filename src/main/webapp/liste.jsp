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
    <body>
        <header class="bg-dark py-5">
            <div class="container px-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-6">
                        <div class="text-center my-5">
                            <h1 class="display-5 fw-bolder text-white mb-2">Gestion des formations</h1>
                        </div>
                    </div>
                </div>
            </div>
        </header>  

         <div class="container mt-3 py-5">
          <h2>Liste des formateurs</h2>    
                    <div class="text-right">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Ajouter une nouvelle formation</a>
			</div>   
          <table class="table table-striped py-5 text-center">
            <thead>
              <tr>
              	<th>Id</th>
                <th>CIN</th>
                <th>Nom</th>
                <th>Age</th>             
                <th>Modifier</th>
                <th>Supprimer</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="formateur" items="${listFormateur}">

						<tr>
						
							<td><c:out value="${formateur.id}" /></td>
							<td><c:out value="${formateur.cin}" /></td>
							<td><c:out value="${formateur.nom}" /></td>
							<td><c:out value="${formateur.age}" /></td>
							<td><a href="modifier?id=<c:out value='${formateur.id}' />"><i class="bi bi-pen"></i></a></td> 
							<td><a href="supprimer?id=<c:out value='${formateur.id}' />"><i class="bi bi-trash"></i></a></td>
						</tr>
			</c:forEach>
          
            </tbody>
          </table>
        </div> 
        <div class="container mt-3 py-5">
          <h2>Liste des formations</h2>    
                    <div class="text-left">

				<a href="<%=request.getContextPath()%>/newF" class="btn btn-success">Ajouter une nouvelle formation</a>
			</div>   
          <table class="table table-striped py-5 text-center">
            <thead>
              <tr>
              	<th>Id</th>
                <th>Thème</th>
                <th>Lieu</th>         
                <th>Modifier</th>
                <th>Supprimer</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="formation" items="${listFormation}">

						<tr>
						
							<td><c:out value="${formation.id}" /></td>
							<td><c:out value="${formation.theme}" /></td>
							<td><c:out value="${formation.lieu}" /></td>
							<td><a href="modifierF?id=<c:out value='${formation.id}' />"><i class="bi bi-pen"></i></a></td> 
							<td><a href="supprimerF?id=<c:out value='${formation.id}' />"><i class="bi bi-trash"></i></a></td>
						</tr>
			</c:forEach>
          
            </tbody>
          </table>
        </div> 
         <div class="container mt-3 py-5">
          <h2>Liste des lieux</h2> 
          <div class="text-left">

				<a href="<%=request.getContextPath()%>/newL" class="btn btn-success">Ajouter un nouveau lieu</a>
			</div>        
          <table class="table table-striped py-5 text-center">
            <thead>
              <tr>
                <th>ID</th>
                <th>Adresse</th>
                <th>Ville</th>
                <th>Modifier</th>
                <th>Supprimer</th>
              </tr>
            </thead>
            <c:forEach var="lieu" items="${listLieu}">

						<tr>
						
							<td><c:out value="${lieu.id}" /></td>
							<td><c:out value="${lieu.adresse}" /></td>
							<td><c:out value="${lieu.ville}" /></td>
							<td><a href="modifierL?id=<c:out value='${lieu.id}' />"><i class="bi bi-pen"></i></a></td> 
							<td><a href="supprimerL?id=<c:out value='${lieu.id}' />"><i class="bi bi-trash"></i></a></td>
						</tr>
			</c:forEach>
          </table>
        </div>
         <div class="container mt-2 py-5">
          <h2>Liste des lignes formations - formateurs</h2> 
          <div class="text-right">

				<a href="<%=request.getContextPath()%>/newFF" class="btn btn-success">Ajouter une nouvelle ligne de formation</a>
			</div>        
          <table class="table table-striped py-5 text-center">
            <thead>
              <tr>
                <th>ID</th>
                <th>Formation</th>
                <th>Formateur</th>
                <th>Modifier</th>
                <th>Supprimer</th>
              </tr>
            </thead>
            <c:forEach var="ff" items="${listFF}">
						<tr>
							<td><c:out value="${ff.id}" /></td>
							<td><c:out value="${ff.theme}" /></td>
							<td><c:out value="${ff.nom}" /></td>
							<td><a href="modifierFF?id=<c:out value='${ff.id}' />"><i class="bi bi-pen"></i></a></td> 
							<td><a href="supprimerFF?id=<c:out value='${ff.id}' />"><i class="bi bi-trash"></i></a></td>
						</tr>
			</c:forEach>
          </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

