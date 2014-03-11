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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-resources/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/public-resources/js/vendor/modernizr.js"></script>
  </head>
  <body onload="setCompany()">
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
			<h3>Profil Fnac</h3>
			<div class="row">
				<form>
				  <div class="row">
					<div class="large-4 columns">
					  <label>SIRET
						<input id="sirete" type="text" placeholder="Numéro SIRET"/>
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Raison sociale
						<input id="nome" type="text" placeholder="Nom/Raison sociale" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-8 columns">
					  <label>Adresse Postale
						<textarea id="adressee" placeholder="Adresse postale du siÃ¨ge social"></textarea>
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
				</form>
			  </div>
			  <div class="row">
				<a class="button" onClick="saveCompany()" href="home.html" data-reveal-id="myModal" data-reveal>Enregistrer</a>
				<a class="button" href="home.html">Annuler</a>
			  </div>
			</div>
		</div>
	  </div>
	</div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Profil sauvegardé.</h2>
		<p class="lead">Les modifications ont bien été prises en compte.</p>
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
	    
	    function saveCompany(){
			if(window.sessionStorage){
				//Formulaire
				var nom = document.getElementById("nome").value;
				window.sessionStorage.setItem('nomE', nom);
				window.sessionStorage.setItem('siretE', document.getElementById("sirete").value);
				window.sessionStorage.setItem('adresseET', document.getElementById("adressee").value);
				window.sessionStorage.setItem('adresseMET', document.getElementById("adresseme").value);
				window.sessionStorage.setItem('siteET', document.getElementById("sitee").value);
				
				refresh_account();
			}
			else{
				alert('sessionStorage non géré');
			}
	    }
	    
	    function setCompany(){
			refresh_account();
			if(window.sessionStorage){
				if(window.sessionStorage.length > 0){
					//Formulaire
					var nom = window.sessionStorage.getItem('nomE');
					document.getElementById("nome").value = nom;
					document.getElementById("sirete").value = window.sessionStorage.getItem('siretE');
					document.getElementById("adressee").value  = window.sessionStorage.getItem('adresseET');
					document.getElementById("adresseme").value  = window.sessionStorage.getItem('adresseMET');
					document.getElementById("sitee").value = window.sessionStorage.getItem('siteET');
				}
			}
			else{
				alert('sessionStorage non géré');
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


