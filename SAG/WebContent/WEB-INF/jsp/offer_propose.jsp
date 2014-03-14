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
    <title>SAG - Proposer une offre</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/js/vendor/modernizr.js"></script>
  </head>
  <body>
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
			<h3>Nouvelle proposition d'offre</h3>
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
<!-- 						<label>Type -->
<%-- 							<form:select path="type"> --%>
<%-- 							    <form:option value="Théorique" /> --%>
<%-- 							    <form:option value="Concrète"/> --%>
<%-- 							</form:select> --%>
<!-- 					 	 </label> -->
						<c:choose>
							<c:when test=${ entreprise!=null }>
								<form:hidden path="type" value="Concrète" />
							</c:when>
							<c:otherwise>
								<form:hidden path="type" value="Théorique" />
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				  <c:choose>
					<c:when test=${ entreprise!=null }>
					  <div class="row">
						<div class="large-4 columns">
						  <label>Fournisseur
							<form:input path="fournisseur" value="${entreprise.nom}" placeholder="Entreprise fournisseur de l'offre"/>
							<form:errors path="fournisseur" cssClass="error" />
						  </label>
						</div>
					  </div>
					 </c:when>
					 <c:otherwise>
					 	<form:hidden path="fournisseur" value="null" />
					 </c:otherwise>
					</c:choose>
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
				<a class="button" href="${pageContext.request.contextPath}/save_offer" data-reveal-id="myModal1" data-reveal>Enregistrer</a>
				<a class="button" href="${pageContext.request.contextPath}/propose_offer" data-reveal-id="myModal2" data-reveal>Envoyer</a>
				<a class="button" onclick="window.history.back();" href="#">Annuler</a>
			  </div>
			</div>
		</div>
	  </div>
	</div>
    <div id="myModal1" class="reveal-modal" data-reveal>
		<h2>Nouvelle proposition d'offre enregistrée.</h2>
		<p class="lead">Votre proposition d'offre a bien été sauvegardée.</p>
		<p>Vous pourrez la modifier et l'envoyer plus tard.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
    <div id="myModal2" class="reveal-modal" data-reveal>
		<h2>Nouvelle proposition d'offre envoyée.</h2>
		<p class="lead">Votre proposition d'offre a bien été envoyée.</p>
		<p>Un email vous sera envoyé dès que votre offre aura été activée.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
	<script src="${pageContext.request.contextPath}/js/vendor/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/foundation.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/foundation/foundation.topbar.js"></script>
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