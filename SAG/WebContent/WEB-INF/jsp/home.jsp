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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"></script>
  </head>
  <body>
  	<jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
	        <h3 id="welcome">Bienvenue 
	        <c:if test=${ user_co.prenom!=null }><c:out value="${user.prenom} " /></c:if>
	        <c:out value=${user_co.nom} /> !</h3>
	        <p>Sur ce site, vous pourrez participer aux différentes offres disponibles et ainsi bénéficier de tarifs avantageux.</p>
	        <p>Différentes fonctionnalités sont mises à votre disposition:</p>
	        <div class="row">
	        	<div class="large-4 medium-4 columns">
	    			<p><a href="${pageContext.request.contextPath}/list_offer">Consulter les offres.</a><br />Consultez l'ensemble des offres disponibles et participez y si vous êtes interessé.</p>
	    		</div>
	        	<div class="large-4 medium-4 columns">
	        		<p><a href="${pageContext.request.contextPath}/offer_propose">Proposer une offre.</a><br />Vous désirez effectuer un achat, cela pourrait interesser d'autres étudiants, n'hésitez pas et proposez votre offre.</p>
	        	</div>
	        	<div class="large-4 medium-4 columns">
	        		<p><a href="#dom_menu">Différents domaines.</a><br />Vous êtes intéressé par un domaine en particulier ? Parcourez les offres d'un domaine en particulier.</p>
	        	</div>        
			</div>
      	</div>
      </div>
    </div>
	<script type="text/javascript">
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
			if(chaine.match("^.*(NOEL|SAPIN|NATUREL).*$")){
				$(location).attr('href',"detail_offre3.html");
			}
		}
	</script>
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"></script>
    <script>
      $(document).foundation();
    </script>
    <jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>
