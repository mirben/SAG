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
<title>SAG - Détail <c:out value="${offer.titre }" /></title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
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
				<div class="row">
					<c:if test="${not empty offer.images}">
						<ul class="clearing-thumbs" data-clearing="">
							<c:forEach items="${offer.images}" var="imgo">
								<li><a href="${imgo.url}"><img src="${imgo.url}" width="200" /></a></li>
							</c:forEach>
						</ul>
					</c:if>
					<ul class="pricing-table">
						<li class="title">${offer.titre}</li>
						<li class="price">${offer.prix}€</li>
						<li class="description">${offer.description}</li>
						<li class="bullet-item">${offer.dateFin}</li>
						<li class="bullet-item">${offer.participants.size()}
							participants</li>
						<c:if test="${offer.fournisseur!=null}">
							<li class="bullet-item"><a href="${offer.siteWeb}">${offer.fournisseur}</a></li>
						</c:if>
						<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
							<c:choose>
								<c:when test="${participe}">
									<li class="cta-button"><a class="button"
										href="${pageContext.request.contextPath}/giveup_offer?ido=${offer.id}"
										data-reveal-id="myModal2" data-reveal = "">Annuler ma participation</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${offer.participants.size()>=offer.participantsMax}"> 
											<li class="description">Le nombre de participants maximum est atteint.</li>
										</c:when>
										<c:otherwise>
											<li class="cta-button">
											<a class="button"
											href="${pageContext.request.contextPath}/join_offer?ido=${offer.id}"
											data-reveal-id="myModal1" data-reveal = "">Participer</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</sec:authorize>
						<li class="description"> ${message} </li>
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal1" class="reveal-modal" data-reveal="">
		<h2>Participation effectuée.</h2>
		<p class="lead">Votre participation à  l'offre a bien été prise en
			compte.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>
	<div id="myModal2" class="reveal-modal" data-reveal="">
		<h2>Participation annulée.</h2>
		<p class="lead">Votre participation à  l'offre a bien été annulée.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
	</div>
	<script
		src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js">
		<jsp:text />
	</script>
	<script
		src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js">
		<jsp:text />
	</script>
	<script
		src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js">
		<jsp:text />
	</script>
	<script
		src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.clearing.js">
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
		$(document).on('closed', '#myModal1', function () {
			window.location.href="${pageContext.request.contextPath}/join_offer?ido=${offer.id}";
		});
		$(document).on('closed', '#myModal2', function () {
			window.location.href="${pageContext.request.contextPath}/giveup_offer?ido=${offer.id}";
		});
		
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

