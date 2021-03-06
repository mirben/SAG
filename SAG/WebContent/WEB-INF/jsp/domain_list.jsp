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
    <title>SAG - Liste des offres <c:out value="${domaine_courant.nom}" /></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp" />
    <div class="row">
      <div class="large-12 columns">
      	<div class="panel">
        <h3>Liste des offres - <c:out value="${domaine_courant.nom}" /></h3>
		<div class="row">
			 <table>
			 	<c:choose>
					<!-- Si la liste des offres est vide -->
					<c:when test="${empty offers_domaine}">
						<tr>
							<td colspan="3">Aucune offre dans le domaine <c:out value="${domaine_courant.nom}" />.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<thead>
							<tr>
							  <th width="300">Offre</th>
							  <th width="150">Prix</th>
							  <th width="150">Date de clôture</th>
							</tr>
						</thead>
						<tbody>
							<!-- On parcours toutes les offres  -->
							<c:forEach items="${offers_domaine}" var="offerd">
								<tr>
									<td>${offerd.titre}</td>
									<td>${offerd.prix}</td>
									<td>${offerd.dateFin}</td>
									<td><a href="${pageContext.request.contextPath}/detail_offer?id=${offerd.id}" class="tiny button" onclick="this.target='_blank'">Accéder à la fiche</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:otherwise>
				</c:choose>
			</table>
		  </div>
		</div>
	   </div>
	</div>

    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
    <script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
    <script>
      $(document).foundation();
    </script>
     <script type="text/javascript" >
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

