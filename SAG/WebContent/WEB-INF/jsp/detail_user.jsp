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
    <title>SAG - Profil <c:out value=${user.nom}</c:out></title>
    <link rel="stylesheet" href="css/foundation.css" />
    <script src="js/vendor/modernizr.js"></script>
  </head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
	    <h3>Profil <c:out value=${user.nom}</c:out></h3>
	    <div class="row">
			<dl class="tabs" data-tab>
			  <dd class="active"><a href="#panel2-1">Informations personnelles</a></dd>
			  <dd><a href="#panel2-2">Domaines</a></dd>
			</dl>
			<div class="tabs-content">
			  <div class="content active" id="panel2-1">
				<form:form method="post" commandName="user" accept-charset="utf-8">
				<form:errors path="*" cssClass="errorblock" element="div" />
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
						<form:input path="ent" placeholder="Identifiant ENT"/>
						<form:errors path="ent" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de naissance
						<input id="datee" type="Date" />
						<form:input path="dateNaiss" placeholder="Date de naissance"/>
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
						<form:select path="formation">
						    <form:option value="Licence 1"/>
						    <form:option value="Licence 2"/>
						    <form:option value="Licence 3"/>
						    <form:option value="Master 1"/>
						    <form:option value="Master 2"/>
						</form:select>
					  </label>
					</div>
				  </div>
				</form:form>
			  </div>
			  <div class="content" id="panel2-2">
				  <div class="row">
					<div class="large-6 columns">
					  <label>Sélectionnez les domaines suivis :</label>
					  <form:select path="user.domaine">
			              <form:option value="-" label="-- Sélectionnez les domaines --"/>
			              <form:options items="${user.domaine}" itemLabel="nom"/>
			          </form:select>
					</div>
				  </div>
			  </div>
			</div>
		</div>
		<div class="row">
		    <a class="button" onclick="document.forms[0].submit(); return false;" href="#" data-reveal-id="myModal" data-reveal>Enregistrer</a>
		    <a class="button" onclick="window.history.back();" href="#">Annuler</a>
		</div>
	   </div>
      </div>
    </div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Profil sauvegardé.</h2>
		<p class="lead">Les modifications ont bien été prises en compte.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	<script src="js/vendor/jquery.js"></script>
	<script src="js/foundation.min.js"></script>
	<script src="js/foundation/foundation.topbar.js"></script>
	<script>
	  $(document).foundation();
	</script>
	<script type="text/javascript" >
		document.getElementById("search_in").onkeydown = function(event) {
		  if(event.keyCode == '13') {
			search_key();
			return false;
		  }
		};
		function search_key(){
			var chaine = document.getElementById("search_in").value;
			chaine = chaine.toUpperCase();
			console.log(chaine);
			if(chaine.match("^.*(OREILLET|MEMOIRE|FORME|LITERIE|MEMOIRE).*$")){
				$(location).attr('href',"detail_offre1.html");
			}
			if(chaine.match("^.*(DAFT ??PUNK|DAFT|PUNK|RAM|MEMORIE|ACCESS|ALBUM).*$")){
				$(location).attr('href',"detail_offre2.html");
			}
			if(chaine.match("^.*(NOEL|SAPIN|NATUREL|NOEL).*$")){
				$(location).attr('href',"detail_offre3.html");
			}
		}
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>
