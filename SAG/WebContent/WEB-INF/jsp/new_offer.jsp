<?xml version="1.0" encoding="UTF-8" ?>
<!-- Encodé en UTF-8, génère une page xhtml transitional -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml" class="no-js" lang="fr">
	<head>
	    <meta charset="utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <title>SAG - Nouvelle offre</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.css" />
	    <script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"><jsp:text /></script>
		</head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
			<h3>Nouvelle offre</h3>
			<div class="row">
				<form:form method="post" commandName="offre" accept-charset="utf-8">
				<form:errors path="*" cssClass="errorblock" element="div" />
				  <div class="row">
					<div class="large-4 columns">
					  <label>Titre
						<form:input path="titre" placeholder="Titre de l'offre"/>
						<form:errors path="titre" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-8 columns">
					  <label>Description
						<form:textarea path="description" placeholder="Description de l'offre"/>
						<form:errors path="description" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Type
						<form:select path="type">
						    <form:option value="Théorique"/>
						    <form:option value="Concrète"/>
						</form:select>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Fournisseur
						<form:input path="fournisseur" placeholder="Entreprise fournisseur de l'offre"/>
						<form:errors path="fournisseur" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Participants minimum
						<form:input path="participantsMin" placeholder="Nombre de participants minimum"/>
						<form:errors path="participantsMin" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Participants maximum
						<form:input path="participantsMax" placeholder="Nombre de participants maximum"/>
						<form:errors path="participantsMax" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Prix (€)
						<form:input path="prix" placeholder="Prix de l'offre"/>
						<form:errors path="prix" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Statut
						<form:select path="statut">
						    <form:option value="Brouillon"/>
						    <form:option value="Envoyée"/>
						    <form:option value="Active"/>
						    <form:option value="Terminée"/>
						</form:select>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de début
						<form:input path="dateDebut"/>
						<form:errors path="dateDebut" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de fin
						<form:input path="dateFin"/>
						<form:errors path="dateFin" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Site internet
						<form:input path="siteWeb" placeholder="Url du site internet" />
						<form:errors path="siteWeb" cssClass="error" />
					  </label>
					</div>
				  </div>
				</form:form>
			  </div>
			  <div class="row">
				<a class="button" onclick="document.forms[0].submit(); return false;" href="#" data-reveal-id="myModal" data-reveal>Ajouter</a>
				<a class="button" onclick="window.history.back(); return false;" href="#">Annuler</a>
			  </div>
			</div>
		</div>
	  </div>
	</div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Nouvelle offre sauvegardée.</h2>
		<p class="lead">L'ajout a bien été pris en compte.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
	<script type="text/javascript">
	  $(document).foundation();
	</script>
	<script type="text/javascript">
		document.getElementById("search_in").onkeydown = function(event) {
		  if(event.keyCode == '13') {
			search_key();
			return false;
		  }
		  return true;
		};
		function search_key(){
			var chaine = document.getElementById("search_in").value;
			chaine = chaine.toUpperCase();
			console.log(chaine);
			if(chaine.length!=0){
				$(location).attr('href',"${pageContext.request.contextPath}/search_offers?key="+chaine);
			}
			return true;
		}
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>
