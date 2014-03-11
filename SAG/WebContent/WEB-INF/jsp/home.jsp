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
  <body onload="refresh_account()">
    
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
				  <li class="active"><a href="home.html">Accueil</a></li>
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
	        <h3 id="welcome">Bienvenue JoÃ«l Karcher !</h3>
	        <p>Sur ce site, vous pourrez participer aux diffÃ©rentes offres disponible et ainsi bÃ©nÃ©ficier de tarifs avantageux.</p>
	        <p>DiffÃ©rentes fonctionnalitÃ©s sont mises Ã  votre disposition:</p>
	        <div class="row">
	        	<div class="large-4 medium-4 columns">
	    		<p><a href="list.html">Consulter les offres.</a><br />Consultez l'ensemble des offres disponibles et participez y si vous Ãªtes interessÃ©.</p>
	    	</div>
	        	<div class="large-4 medium-4 columns">
	        		<p><a href="propose_offer.html">Proposer une offre.</a><br />Vous dÃ©sirez effectuer un achat, cela pourrait interesser d'autres Ã©tudiant, n'hÃ©sitez pas et proposer votre offre.</p>
	        	</div>
	        	<div class="large-4 medium-4 columns">
	        		<p><a href="#">DiffÃ©rents domaines.</a><br />Vous Ãªtes intÃ©ressÃ© par un domaine en particulier ? Parcourez les offres d'un domaine en particulier.</p>
	        	</div>        
					</div>
      	</div>
      </div>
    </div>
	<script type="text/javascript">
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
				$("#welcome").text("Bienvenue "+window.sessionStorage.getItem('prenomE')+" "+window.sessionStorage.getItem('nomE'));
				$("#username").text(window.sessionStorage.getItem('prenomE')+" "+window.sessionStorage.getItem('nomE'));
			}
		}
		function clear_session(){
			window.sessionStorage.clear();
		}
	</script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
	<script src="js/foundation/foundation.topbar.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
</jsp:root>
