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
		<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
	<title>Déconnection du SAG</title>
    <script type="text/javascript">
		//Redirige vers la page d'authentification après 5 secondes
		window.setTimeout("location=('${pageContext.request.contextPath}/login');",
				10000);
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div class="panel callout radius">
		<p>Vous avez bien été déconnecté !</p>
		<iframe hidden="true" src="https://ident.univ-amu.fr:443/cas/logout" />
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
