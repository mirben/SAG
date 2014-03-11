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
    <title>SAG - Accueil</title>
    <link rel="stylesheet" href="css/foundation.css" />
    <script src="js/vendor/modernizr.js"></script>
  </head>
  <body>
	<div class="row">
		<div class="large-8 columns">
			<h1><b>SAG</b> - Site d'Achat GroupÃ©</h1>
			<div class="row">
			  <div class="large-12 columns">
				<h3>Inscription - <b>SAG</b></h3>
				<div class="panel">
					<div class="row">
						<dl class="tabs" data-tab>
						  <dd class="active"><a href="#panel2-1">Informations personnelles</a></dd>
						  <dd><a href="#panel2-2">Domaines</a></dd>
						</dl>
						<div class="tabs-content">
						  <div class="content active" id="panel2-1">
							<form>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Nom
									<input id="nome" type="text" placeholder="Nom"/>
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>PrÃ©nom
									<input id="prenome" type="text" placeholder="PrÃ©nom" />
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Identifiant ENT
									<input id="ente" type="text" placeholder="Identifiant ENT" />
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Date de naissance
									<input id="datee" type="Date" />
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-8 columns">
								  <label>Adresse Postale
									<textarea id="adressee" placeholder="Adresse postale complÃ¨te"></textarea>
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Adresse Email
									<input id="adresseme" type="text" placeholder="Adresse Email"/>
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Site internet
									<input id="sitee" type="text" placeholder="Url du site internet" />
								  </label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-4 columns">
								  <label>Formation
									<select id="forme">
									  <option value="l1">Licence 1</option>
									  <option value="l2">Licence 2</option>
									  <option value="l3">Licence 3</option>
									  <option value="m1">Master 1</option>
									  <option value="m2">Master 2</option>
									</select>
								  </label>
								</div>
							  </div>
							</form>
						  </div>
						  <div class="content" id="panel2-2">
							 <div class="row">
								<div class="large-6 columns">
								  <label>Vous abonner aux newsletters :</label>
								  <input type="radio" name="newsletters" value="yes" id="ynews"><label for="ynews">Oui</label>
								  <input type="radio" name="newsletters" value="no" id="nnews"><label for="nnews">Non</label>
								</div>
							  </div>
							  <div class="row">
								<div class="large-6 columns">
								  <label>SÃ©lectionnez les domaines suivis :</label>
								  <input id="checkbox1" type="checkbox"><label for="checkbox1">Musique</label>
								  <input id="checkbox2" type="checkbox"><label for="checkbox2">Literie</label>
								  <input id="checkbox3" type="checkbox"><label for="checkbox3">Jardin</label>
								</div>
							  </div>
						  </div>
						</div>
					</div>
					<div class="row">
						<a class="button" href="login.html" data-reveal-id="myModal" data-reveal>Envoyer</a>
						<a class="button" href="login.html">Annuler</a>
					</div>
				 </div>
			</div>
		</div>
	</div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Demande d'inscription envoyÃ©e.</h2>
		<p class="lead">Votre inscription a bien Ã©tÃ© prise en compte.</p>
		<p>Un email vous sera envoyÃ© Ã  l'adresse renseignÃ©e dÃ¨s que votre inscription aura Ã©tÃ© validÃ©e.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	<script src="js/vendor/jquery.js"></script>
	<script src="js/foundation.min.js"></script>
	<script src="js/foundation/foundation.topbar.js"></script>
	<script>
	  $(document).foundation();
	</script>
  </body>
</html>
</jsp:root>