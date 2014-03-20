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
			<link type="text/css" rel="stylesheet"
				href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
			<script
				src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
			<title>Accès refusé</title>
		</head>
		<body>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
			<div class="row">
				<h3 id="banner">Accès non authorisé</h3>
		
				<c:if test="${not empty error}">
					<div>
						<p><jsp:text>Echec de l'authentification</jsp:text></p>
						<!-- Affiche l'erreur de sécurité -->
						<jsp:text>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</jsp:text>
					</div>
				</c:if>
		
				<p class="message">Accès refusé !</p>
				<div>
					<a href="${pageContext.request.contextPath}/login"><jsp:text>Retourner
				à la page d'authentification</jsp:text></a>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</body>
	</html>
</jsp:root>
