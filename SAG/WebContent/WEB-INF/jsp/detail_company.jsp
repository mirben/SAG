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
    <title>SAG - Profil <c:out value=${company.nom} /></title>
    <link type="text/css" rel="stylesheet"
				href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-resources/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/public-resources/js/vendor/modernizr.js"><jsp:text /></script>
  </head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
			<h3>Profil <c:out value=${company.nom} /></h3>
			<div class="row">
				<form:form method="post" commandName="company" accept-charset="utf-8">
				<form:errors path="*" cssClass="errorblock" element="div" />
				  <div class="row">
					<div class="large-4 columns">
					  <label>SIRET
						<form:input path="siret" placeholder="Numéro SIRET"/>
						<form:errors path="siret" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-4 columns">
					  <label>Raison sociale
					  	<form:input path="nom" placeholder="Nom/Raison sociale"/>
						<form:errors path="nom" cssClass="error" />
					  </label>
					</div>
				  </div>
				  <div class="row">
					<div class="large-8 columns">
					  <label>Adresse Postale
					  	<form:textarea path="adresse" placeholder="Adresse postale du siège social"/>
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
					  	<form:input path="site" placeholder="Url du site internet"/>
						<form:errors path="site" cssClass="error" />
					  </label>
					</div>
				  </div>
				</form:form>
			  </div>
			  <div class="row">
				<a class="button" onclick="document.forms[0].submit(); return false;" href="#" data-reveal-id="myModal" data-reveal>Enregistrer</a>
				<a class="button" onclick="window.history.back(); return false;" href="#">Annuler</a>
			  </div>
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
	<script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
	<script>
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


