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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
<script
	src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js">
	<jsp:text />
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div class="row">
		<div class="large-12 columns">
			<div class="panel">
				<h3 id="welcome">
					Bienvenue
					<c:if test="${ not empty user_co.prenom }">
						<c:out value="${user_co.prenom} " />
					</c:if>
					<c:out value="${user_co.nom}" />
					!
				</h3>
				<c:if test="${not empty erreur}">
					<h2>
						<c:out value="${erreur}" />
					</h2>
				</c:if>
				<p>Sur ce site, vous pourrez participer aux différentes offres
					disponibles et ainsi bénéficier de tarifs avantageux.</p>
				<p>Différentes fonctionnalités sont mises à votre disposition:</p>
				<div class="row">
					<div class="large-4 medium-4 columns">
						<p>
							<a href="${pageContext.request.contextPath}/list_offer">Consulter
								les offres.</a><br />Consultez l'ensemble des offres disponibles et
							participez y si vous êtes interessé.
						</p>
					</div>
					<div class="large-4 medium-4 columns">
						<p>
							<a href="${pageContext.request.contextPath}/propose_offre">Proposer
								une offre.</a><br />Vous désirez effectuer un achat, cela pourrait
							interesser d'autres étudiants, n'hésitez pas et proposez votre
							offre.
						</p>
					</div>
					<div class="large-4 medium-4 columns">
						<![CDATA[<span data-tooltip="" class="has-tip" title="]]>
						<c:forEach items="${domains}" var="dom">
							${dom.nom}<br />
						</c:forEach>
						<![CDATA[">]]>
						<p>
							<a>Différents domaines.</a>
							<![CDATA[</span>]]>
							<br />Vous êtes intéressé par un domaine en particulier ?
							Parcourez les offres d'un domaine en particulier.
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js">
		<jsp:text />
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js">
		<jsp:text />
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.js">
		<jsp:text />
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js">
		<jsp:text />
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.tooltip.js">
		<jsp:text />
	</script>
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
