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
<title>SAG - Nouvelle offre</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js">
	<jsp:text />
</script>
<script
	src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js">
	<jsp:text />
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js">
	<jsp:text />
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js">
	<jsp:text />
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<div class="row">
		<div class="large-12 columns">
			<div class="panel">
				<h3>Nouvelle proposition d'offre</h3>
				<div class="row">
					<form:form method="post" commandName="offre" accept-charset="utf-8">
						<div class="row">
							<div class="large-4 columns">
								<label>Titre <form:input path="titre"
										placeholder="Titre de l'offre" /> <form:errors path="titre"
										cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-8 columns">
								<label>Description <form:textarea path="description"
										placeholder="Description de l'offre" /> <form:errors
										path="description" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">

								<c:choose>
									<c:when test="${ user_co.role.nom=='ROLE_ENTR' }">
										<form:hidden path="type" value="CONCRET" />
									</c:when>
									<c:otherwise>
										<form:hidden path="type" value="THEORIQUE" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when test="${user_co.role.nom=='ROLE_ENTR'}">
								<div class="row">
									<div class="large-4 columns">
										<label>Fournisseur <form:input path="fournisseur"
												value="${entreprise.id}"
												placeholder="Entreprise fournisseur de l'offre" /> <form:errors
												path="fournisseur" cssClass="error" />
										</label>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<form:hidden path="fournisseur" value="" />
							</c:otherwise>
						</c:choose>
						<div class="row">
							<div class="large-4 columns">
								<label>Participants minimum <form:input
										path="participantsMin"
										placeholder="Nombre de participants minimum" /> <form:errors
										path="participantsMin" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">
								<label>Participants maximum <form:input
										path="participantsMax"
										placeholder="Nombre de participants maximum" /> <form:errors
										path="participantsMax" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">
								<label>Prix (€) <form:input path="prix"
										placeholder="Prix de l'offre" /> <form:errors path="prix"
										cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">
								<label>Date de début <form:input path="dateDebut"
										type="date" /> <form:errors path="dateDebut" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">
								<label>Date de fin <form:input path="dateFin"
										type="date" /> <form:errors path="dateFin" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-4 columns">
								<label>Site internet <form:input path="siteWeb"
										placeholder="Url du site internet" /> <form:errors
										path="siteWeb" cssClass="error" />
								</label>
							</div>
						</div>
						<div class="row">
							<div class="large-6 columns">
								<label>Sélectionnez les domaines auxquels l'offre appartient :</label>
								<form:select path="domaines" class="domlist">
									<form:options items="${domains}" itemValue="id" itemLabel="nom" />
								</form:select>
							</div>
						</div>
						<FIELDSET id="fieldimg">
							<LEGEND>Images de l'offre</LEGEND>
							<div class="row">
								<a class="small button" onclick="add_image()">Ajouter une image</a>
								<a class="small button" onclick="del_image()">Supprimer une image</a>
							</div>
							<div id="imginput">
								<c:forEach items="${offre.images}" var="image" varStatus="status">
									<label>Image <c:out value="${status.index}" />
									<form:input path="images" value="${image.url}" />
									</label>
								</c:forEach>
							</div>
						</FIELDSET>
						<div class="row">
							<div class="large-6 columns">
								<label> 
								<input id="bsave" class="button" type="submit" name="action" data-reveal-id="myModal1" data-reveal="" value="Enregistrer" /> 
								<input id="bsend" class="button" type="submit" name="action" data-reveal-id="myModal2" data-reveal="" value="Envoyer" />
								<input class="button" type="button" onclick="window.history.back(); return false;" value="Annuler" />
								</label>
							</div>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
	<div id="myModal1" class="reveal-modal" data-reveal="">
		<h2>Nouvelle proposition d'offre enregistrée.</h2>
		<p class="lead">Votre proposition d'offre a bien été sauvegardée.</p>
		<p>Vous pourrez la modifier et l'envoyer plus tard.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>
	<div id="myModal2" class="reveal-modal" data-reveal="">
		<h2>Nouvelle proposition d'offre envoyée.</h2>
		<p class="lead">Votre proposition d'offre a bien été envoyée.</p>
		<p>Un email vous sera envoyé dès que votre offre aura été activée.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>
	<script type="text/javascript">
		$(document).foundation();
	</script>
	<script type="text/javascript">
		document.getElementById("search_in").onkeydown = function(event) {
			if (event.keyCode == '13') {
				search_key();
				return false;
			}
			return true;
		};
		
		function add_image() {
			var nbinput = $("#imginput").children().size();
			var numi = nbinput + 1;
			$("#imginput").append($('<label>Image '+numi+'<form:input type="text" path="offre.images" value="http://url_image_default" /></label>'));

			return true;
		}
		
		function del_image() {
			$("#imginput label:last-child").remove();
			
			return true;
		}
		
		function search_key() {
			var chaine = document.getElementById("search_in").value;
			chaine = chaine.toUpperCase();
			console.log(chaine);
			if (chaine.length != 0) {
				$(location).attr(
						'href',
						"${pageContext.request.contextPath}/search_offers?key="
								+ chaine);
			}
			return true;
		}
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
	</html>
</jsp:root>
