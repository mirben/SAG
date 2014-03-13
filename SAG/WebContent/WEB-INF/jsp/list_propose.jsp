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
			<title>SAG - Liste des offres de <c:if test=${ user_co.prenom!=null }>
			<c:out value="${user.prenom} "</c:out></c:if>
			<c:out value=${user_co.nom}</c:out>
			</title>
			<link rel="stylesheet" href="css/foundation.css" />
			<script src="js/vendor/modernizr.js"></script>
		</head>
  		<body>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		    <div class="row">
		      <div class="large-12 columns">
		      	<div class="panel">
		        <h3>Liste des propositions d'offre de <c:if test=${ user_co.prenom!=null }>
		        <c:out value="${user.prenom} "</c:out></c:if>
		        <c:out value=${ user_co.nom }</c:out></h3>
				<div class="row">
					<table>
					  <c:choose>
							<!-- Si la liste des offres est vide -->
							<c:when test="${empty offer_propose}">
								<tr>
									<td colspan="3">Aucune offre proposée.</td>
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
									<!-- On parcours toutes les offres -->
									<c:forEach items="${offer_propose}" var="offerp">
										<tr>
											<td>${offerp.titre}</td>
											<td>${offerp.prix}</td>
											<td>${offerp.dateFin}</td>
											<td>
												<a href="${pageContext.request.contextPath}/detail_offer?id=${offerp.id}" class="tiny button split">Détail<span data-dropdown="drop1"></span></a><br>
												<ul id="drop1" class="f-dropdown" data-dropdown-content>
												  <li><a href="${pageContext.request.contextPath}/edit_offer?id=${offerp.id}">Modifier</a></li>
												  <li><a href="${pageContext.request.contextPath}/delete_offer?id=${offerp.id}">Supprimer</a></li>
												</ul>
						   					</td>
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
		    <script src="js/vendor/jquery.js"></script>
		    <script src="js/foundation.min.js"></script>
			<script src="js/foundation/foundation.topbar.js"></script>
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
						window.location.href="detail_offre1.html";
					}
					if(chaine.match("^.*(DAFT ??PUNK|DAFT|PUNK|RAM|MEMORIE|ACCESS|ALBUM).*$")){
						window.location.href="detail_offre2.html";
					}
					if(chaine.match("^.*(NOEL|SAPIN|NATUREL).*$")){
						window.location.href="detail_offre3.html";
					}
				}
			</script>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</body>
	</html>
</jsp:root>