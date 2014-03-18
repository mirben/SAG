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
<title>404 - Ressource inexistante</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
<script
	src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js" type='text/javascript'></script>
<script type="text/javascript">
	//Redirige vers la liste de l'annuaire après 5 secondes
	window.setTimeout("location=('${pageContext.request.contextPath}/home');",
			5000);
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<!-- Message visible avant la redirection -->
	<div class="row">
		<p>La ressource demandée est inexistante, vous allez être
			redirigé.</p>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
	</html>
</jsp:root>
