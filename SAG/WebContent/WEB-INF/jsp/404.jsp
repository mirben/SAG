<?xml version="1.0" encoding="UTF-8" ?>
<!-- Encodé en UTF-8, génère une page xhtml strict -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
		omit-xml-declaration="true" />

	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404, redirection vers l'annuaire</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-resources/styles/header.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-resources/styles/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-resources/styles/footer.css" />
<script type="text/javascript">
	//Redirige vers la liste de l'annuaire après 5 secondes
	window
			.setTimeout(
					"location=('${pageContext.request.contextPath}/actions/annuaire/list');",
					5000);
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<!-- Message visible avant la redirection -->
	<div id='main'>
		<p>La ressource demandée est inexistante, vous allez être redirigé
			vers l'annuaire.</p>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>

	</html>
</jsp:root>
