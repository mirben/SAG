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
	    <h3>Nouvel utilisateur</h3>
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
						<input id="ente" type="text" placeholder="PrÃ©nom" />
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
				  <div class="row">
					<div class="large-4 columns">
					  <label>RÃ´le
						<select id="forme">
						  <option value="l1">Etudiant</option>
						  <option value="l2">Aministrateur</option>
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
		    <a class="button" href="admin.html" data-reveal-id="myModal" data-reveal>Ajouter</a>
		    <a class="button" href="admin.html">Annuler</a>
		</div>
	   </div>
      </div>
    </div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Nouvel utilisateur sauvegardÃ©.</h2>
		<p class="lead">L'ajout a bien Ã©tÃ© pris en compte.</p>
		<p>Vous pouvez fermer cette fenÃªtre l'esprit tranquille.</p>
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
  </body>
</html>
</jsp:root>