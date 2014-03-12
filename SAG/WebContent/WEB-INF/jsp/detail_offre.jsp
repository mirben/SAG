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
    <title>SAG - Détail <c:out value=${offer.titre}</c:out></title>
    <link rel="stylesheet" href="css/foundation.css" />
    <script src="js/vendor/modernizr.js"></script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
	        <div class="row">
		        <ul class="clearing-thumbs" data-clearing>
		        	<c:forEach items="${offer.image}" var="imgo">
				  		<li><a href="${imgo.url}"><img src="${imgo.url}"></a></li>
				 	</c:forEach>
				</ul>
				<ul class="pricing-table">
				  <li class="title">${offer.titre}</li>
				  <li class="price">${offer.prix}€</li>
				  <li class="description">${offer.description}</li>
				  <li class="bullet-item">${offer.dateFin}</li>
				  <li class="bullet-item">${offer.participants.size()} participants</li>
				  <c:if test="${offer.fournisseur!=null}">
				  	<li class="bullet-item"><a href="${offer.siteWeb}">${offer.fournisseur}</a></li>
				  </c:if>
				  <li class="cta-button"><a class="button" href="/join_offer?ido=${offer.id}&idu=${user.id}" data-reveal-id="myModal" data-reveal>Participer</a></li>
				</ul>
			</div>
      	</div>
      </div>
    </div>
    <div id="myModal" class="reveal-modal" data-reveal>
		<h2>Participation effectuée.</h2>
		<p class="lead">Votre participation Ã  l'offre a bien été prise en compte.</p>
		<p>Vous pouvez fermer cette fenêtre l'esprit tranquille.</p>
		<a class="close-reveal-modal">&#215;</a>
    </div>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
	<script src="js/foundation/foundation.topbar.js"></script>
	<script src="js/foundation/foundation.clearing.js"></script>
    <script>
      $(document).foundation();
    </script>
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
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>

