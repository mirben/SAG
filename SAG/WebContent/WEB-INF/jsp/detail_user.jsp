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
    <title>SAG - Profil <c:out value="${etudiant.nom}" /></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
  </head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
	    <h3>Profil <c:out value="${etudiant.nom}" /></h3>
	    <div class="row">
	    	<form:form method="post" commandName="etudiant" accept-charset="utf-8">
			<dl class="tabs" data-tab="">
			  <dd class="active"><a href="#panel2-1">Informations personnelles</a></dd>
			  <dd><a href="#panel2-2">Domaines</a></dd>
			</dl>
			<div class="tabs-content">
			  <div class="content active" id="panel2-1">
				  <div class="row">
					<div class="large-4 columns">
					  <label>Nom
						<form:input path="nom" placeholder="Nom"/>
						<form:errors path="nom" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Prénom
						<form:input path="prenom" placeholder="Prénom"/>
						<form:errors path="prenom" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Identifiant ENT
						<form:input path="logENT" placeholder="Identifiant ENT"/>
						<form:errors path="logENT" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de naissance
						<form:input type="date" path="dateNaiss" placeholder="Date de naissance"/>
						<form:errors path="dateNaiss" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-8 columns">
					  <label>Adresse postale
						<form:textarea path="adresse" placeholder="Adresse postale complète"/>
						<form:errors path="adresse" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Adresse Email
						<form:input path="email" placeholder="Adresse Email"/>
						<form:errors path="email" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Site internet
						<form:input path="siteWeb" placeholder="Url du site internet"/>
						<form:errors path="siteWeb" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Formation
						<form:input path="formation" placeholder="Formation de l'étudiant"/>
						<form:errors path="formation" cssClass="error" />
					  </label>
					</div>
				  </div>
			  </div>
			  <div class="content" id="panel2-2">
				  <div class="row">
					<div class="large-6 columns">
					  <label>Sélectionnez les domaines suivis :</label>
					  <form:select path="domaines" class="domlist">
			              <form:options items="${domains}" itemValue="id" itemLabel="nom"/>
			          </form:select>
					</div>
				  </div>
			  </div>
			</div>
			</form:form>
		</div>
		<div class="row">
		    <a class="button" data-reveal-id="myModal" data-reveal="">Enregistrer</a>
		    <a class="button" onclick="window.history.back(); return false;">Annuler</a>
		</div>
	   </div>
      </div>
    </div>
    <div id="myModal" class="reveal-modal" data-reveal="">
		<h2>Profil sauvegardé.</h2>
		<p class="lead">Les modifications ont bien été prises en compte.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	<script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
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
		$(document).on('closed', '#myModal', function () {
			document.forms[0].submit(); return false;
		});
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
