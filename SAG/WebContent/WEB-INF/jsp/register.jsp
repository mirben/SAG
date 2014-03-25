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
    <title>SAG - Formulaire d'inscription</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
  </head>
  <body>
	<div class="row">
		<div class="large-8 columns">
			<h1><b>SAG</b> - Site d'Achat Groupé</h1>
			<div class="row">
			  <div class="large-12 columns">
				<h3>Inscription - <b>SAG</b></h3>
				<div class="panel">
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
									<form:input path="dateNaiss" placeholder="Date de naissance"/>
									<form:errors path="dateNaiss" cssClass="error" />
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-8 columns">
								  <label>Adresse Postale
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
						  </div>
						  <div class="content" id="panel2-2">
							  <div class="row">
								<div class="large-6 columns">
								  <label>Sélectionnez les domaines suivis :</label>
								  <form:select path="domaines">
			              			<form:option value="-" label="-- Sélectionnez les domaines --"/>
						            <form:options items="${domaines}" itemLabel="domaines.nom"/>
						          </form:select>
								</div>
							  </div>
						  </div>
						</div>
						
						</form:form>
					</div>
					<div class="row">
						<a class="button" data-reveal-id="myModal" data-reveal="">Envoyer</a>
		    			<a class="button" onclick="window.history.back(); return false;">Annuler</a>
					</div>
				 </div>
			</div>
		</div>
	</div>
   	<div id="myModal" class="reveal-modal" data-reveal="">
		<h2>Demande d'inscription envoyée.</h2>
		<p class="lead">Votre inscription a bien été prise en compte.</p>
		<p>Un email vous sera envoyé à  l'adresse renseignée dès que votre inscription aura été validée.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	</div>
	<script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
	<script>
	  $(document).foundation();
	</script>
	<script type="text/javascript">
		$(document).on('closed', '#myModal', function () {
			document.forms[0].submit(); return false;
		});
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>