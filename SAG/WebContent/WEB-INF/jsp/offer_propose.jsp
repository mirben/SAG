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
  <body onload="setOffer()">
    <div class="row">
      <div class="large-12 columns">
        <h1><b>SAG</b> - Site d'Achat GroupÃ©</h1>
	   </div>
		<div class="right">
		 <div class="row collapse">
			<div class="large-8 small-9 columns">
			  <input type="text" id="search_in" placeholder="Rechercher une offre">
			</div>
			<div class="large-4 small-3 columns">
			  <a href="#" onClick="search_key()" class="button expand postfix" >Rechercher</a>
			</div>
		  </div>
         </div>
    </div>
	<div class="contain-to-grid sticky">
		<nav class="top-bar" data-topbar>
			<ul class="title-area">
				<li class="name"></li>
				<li class="toggle-topbar menu-icon"><a href="#">Menu</a></li>
			</ul>
		   <section class="middle tab-bar-section">
				<section class="top-bar-section">
				<!-- Left Nav Section -->
				<ul class="left">
				  <li><a href="home.html">Accueil</a></li>
				  <li class="divider"></li>
				  <li><a href="list.html">Toutes les offres</a></li>
				  <li class="divider"></li>
				  <li class="has-dropdown">
					<a href="#">Domaines</a>
					<ul class="dropdown">
					  <li><a href="domain_Musique.html">Musique</a></li>
					  <li><a href="domain_Decoration.html">DÃ©coration</a></li>
					  <li><a href="domain_Literie.html">Litterie</a></li>
					  <li><a href="domain_Jardin.html">Jardin</a></li>
					</ul>
				  </li>
				</ul>
				<!-- Right Nav Section -->
				<ul id="rightmenu" class="right">
					<li class="has-dropdown">
						<a id="username" href="#">JoÃ«l Karcher</a>
						<ul class="dropdown">
						  <li id="infos"><a href="detail_user1.html">Modifier mes informations</a></li>
						  <li id="infosc"><a href="detail_company1.html">Modifier mes informations</a></li>
						  <li id="offers"><a href="list_propose1.html">Consulter ses offres</a></li>
						  <li><a href="login.html" onClick="clear_session()" >Se dÃ©connecter</a></li>
						</ul>
					</li>
				</ul>
			  </section>
		</section>
		</nav>
	</div>

    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
			<h3>Nouvelle proposition d'offre</h3>
			<div class="row">
				<form>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Titre
						<input id="titreo" type="text" placeholder="Titre de l'offre"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-8 columns">
					  <label>Description
						<textarea id="desco" placeholder="Description de l'offre"></textarea>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Type
						<select id="typeo">
						  <option value="theo">ThÃ©orique</option>
						  <option value="conc">ConcrÃ¨te</option>
						</select>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Fournisseur
						<input id="fournio" type="text" placeholder="Entreprise fournisseur de l'offre"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Participants minimum
						<input id="partmin" type="number" min="0" placeholder="Nombre de participants minimum"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Participants maximum
						<input id="partmax" type="number" min="0" placeholder="Nombre de participants maximum"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Prix (â¬)
						<input id="prixo" type="number" min="0" placeholder="Prix de l'offre"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de dÃ©but
						<input id="datedo" type="date" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Date de fin
						<input id="datefo" type="date" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Site internet
						<input id="siteo" type="text" placeholder="Url du site internet" />
					  </label>
					</div>
				  </div>
				</form>
			  </div>
			  <div class="row">
				<a class="button" href="home.html" onClick="saveOffer()" data-reveal-id="myModal1" data-reveal>Enregistrer</a>
				<a class="button" href="home.html" onClick="clearOffer()" data-reveal-id="myModal2" data-reveal>Envoyer</a>
				<a class="button" href="home.html">Annuler</a>
			  </div>
			</div>
		</div>
	  </div>
	</div>
    <div id="myModal1" class="reveal-modal" data-reveal>
		<h2>Nouvelle proposition d'offre enregistrÃ©e.</h2>
		<p class="lead">Votre proposition d'offre a bien Ã©tÃ© sauvegardÃ©e.</p>
		<p>Vous pourrez la modifier et l'envoyer plus tard.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
    <div id="myModal2" class="reveal-modal" data-reveal>
		<h2>Nouvelle proposition d'offre envoyÃ©e.</h2>
		<p class="lead">Votre proposition d'offre a bien Ã©tÃ© envoyÃ©e.</p>
		<p>Un email vous sera envoyÃ© dÃ¨s que votre offre aura Ã©tÃ© activÃ©e.</p>
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
			if(chaine.match("^.*(OREILLET|MÃMOIRE|FORME|LITERIE|MEMOIRE).*$")){
				$(location).attr('href',"detail_offre1.html");
			}
			if(chaine.match("^.*(DAFT ??PUNK|DAFT|PUNK|RAM|MEMORIE|ACCESS|ALBUM).*$")){
				$(location).attr('href',"detail_offre2.html");
			}
			if(chaine.match("^.*(NOEL|SAPIN|NATUREL|NOÃL).*$")){
				$(location).attr('href',"detail_offre3.html");
			}
		}
	    
	    function saveOffer(){
			if(window.sessionStorage){
				//Formulaire
				window.sessionStorage.setItem('titreO', document.getElementById("titreo").value);
				window.sessionStorage.setItem('descO', document.getElementById("desco").value);
				window.sessionStorage.setItem('typeO', document.getElementById("typeo").value);
				window.sessionStorage.setItem('fourniO', document.getElementById("fournio").value);
				window.sessionStorage.setItem('partminO', document.getElementById("partmin").value);
				window.sessionStorage.setItem('partmaxO', document.getElementById("partmax").value);
				window.sessionStorage.setItem('prixO', document.getElementById("prixo").value);
				window.sessionStorage.setItem('datedO', document.getElementById("datedo").value);
				window.sessionStorage.setItem('datefO', document.getElementById("datefo").value);
				window.sessionStorage.setItem('siteO', document.getElementById("siteo").value);
			}
			else{
				alert('sessionStorage non gÃ©rÃ©');
			}
	    }
	    
	    function setOffer(){
			refresh_account();
			if(window.sessionStorage){
				if(window.sessionStorage.length > 0){
					//Formulaire
					document.getElementById("titreo").value = window.sessionStorage.getItem('titreO');
					document.getElementById("desco").value = window.sessionStorage.getItem('descO');
					document.getElementById("fournio").value  = window.sessionStorage.getItem('fourniO');
					document.getElementById("partmin").value = window.sessionStorage.getItem('partminO');
					document.getElementById("partmax").value = window.sessionStorage.getItem('partmaxO');
					document.getElementById("prixo").value = window.sessionStorage.getItem('prixO');
					document.getElementById("datedo").value  = window.sessionStorage.getItem('datedO');
					document.getElementById("datefo").value  = window.sessionStorage.getItem('datefO');
					document.getElementById("siteo").value = window.sessionStorage.getItem('siteO');
					if(window.sessionStorage.getItem('typeO')!=null) {
						document.getElementById("typeo").value  = window.sessionStorage.getItem('typeO');
					}
				}
			}
			else{
				alert('sessionStorage non gÃ©rÃ©');
			}
	    }
	    
	    function clearOffer(){
			if(window.sessionStorage){
				window.sessionStorage.removeItem('titreO');
				window.sessionStorage.removeItem('descO');
				window.sessionStorage.removeItem('typeO');
				window.sessionStorage.removeItem('fourniO');
				window.sessionStorage.removeItem('partminO');
				window.sessionStorage.removeItem('partmaxO');
				window.sessionStorage.removeItem('prixO');
				window.sessionStorage.removeItem('datedO');
				window.sessionStorage.removeItem('datefO');
				window.sessionStorage.removeItem('siteO');
			}
			else{
				alert('sessionStorage non gÃ©rÃ©');
			}
		}
		
	    function refresh_account(){
			if(window.sessionStorage.getItem('role')!='Etudiant'){
				$("#infos").remove();
			}
			if(window.sessionStorage.getItem('role')!='Entreprise'){
				$("#infosc").remove();
			}
			if(window.sessionStorage.getItem('role')=='Administrateur'){
				$("#rightmenu").append('<li class="divider"></li><li><a href="admin.html">Administrer</a></li>');
			}
		    if(window.sessionStorage.getItem('nomE')!=null && window.sessionStorage.getItem('prenomE')!=null){
			    $("#username").text(window.sessionStorage.getItem('prenomE')+" "+window.sessionStorage.getItem('nomE'));
		    }
	    }
	    
		function clear_session(){
			window.sessionStorage.clear();
		}
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>