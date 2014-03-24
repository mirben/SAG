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
<title>SAG - Administrateur</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
<script
	type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div class="row">
		<div class="large-12 columns">
			<div class="panel">
				<h3>
					Administration du <b>SAG</b>
				</h3>
				<div class="row">
					<dl class="tabs" data-tab="">
						<dd class="active">
							<a href="#panel1">Gestion des utilisateurs</a>
						</dd>
						<dd>
							<a href="#panel2">Gestion des entreprises</a>
						</dd>
						<dd>
							<a href="#panel3">Gestion des offres</a>
						</dd>
						<dd>
							<a href="#panel4">Gestion des domaines</a>
						</dd>
					</dl>
					<div class="tabs-content">
						<div class="content active" id="panel1">
							<dl class="tabs vertical" data-tab = "">
								<dd class="active">
									<a href="#panel1-1">Actifs</a>
								</dd>
								<dd>
									<a href="#panel1-2">En attente</a>
								</dd>
							</dl>
							<div class="tabs-content vertical">
								<div class="content active" id="panel1-1">
									<table>
										<c:choose>
											<!-- Si la liste des utilisateurs est vide -->
											<c:when test="${empty users_enabled}">
												<tr>
													<td colspan="3">Aucun utilisateur actif.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Utilisateur</th>
														<th>Rôle</th>
														<th>Statut</th>
														<th>Nom</th>
														<th>Prénom</th>
													</tr>
												</thead>
												<tbody>
												<!-- On parcours tous les utilisateurs actifs -->
												<c:forEach items="${users_enabled}" var="user">
													<tr>
														<td>${user.logENT}</td>
														<td>${user.role.nom}</td>
														<td>${user.statut }</td>
														<td>${user.nom}</td>
														<td>${user.prenom}</td>
														<td><a href="${pageContext.request.contextPath}/detail_user?id=${user.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
																data-dropdown="drop1-${user.id}"></span></a><br/>
														<ul id="drop1-${user.id}" class="f-dropdown" data-dropdown-content = "">
																<li><a href="${pageContext.request.contextPath}/switch_role_user?id=${user.id}">Modifier rôle</a></li>
																<li><a href="${pageContext.request.contextPath}/edit_user?id=${user.id}">Modifier informations</a></li>
																<li><a href="${pageContext.request.contextPath}/disable_user?id=${user.id}">Désactiver</a></li>
																<li><a href="${pageContext.request.contextPath}/delete_user?id=${user.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer l\'utilisateur ${user.nom} ?'));">Supprimer</a></li>
															</ul>
														</td>
													</tr>
												</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
								<div class="content" id="panel1-2">
									<table>
										<c:choose>
											<!-- Si la liste des utilisateurs est vide -->
											<c:when test="${empty users_waiting}">
												<tr>
													<td colspan="3">Aucun utilisateur en attente.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Utilisateur</th>
														<th>Rôle</th>
														<th>Statut</th>
														<th>Nom</th>
														<th>Prénom</th>
													</tr>
												</thead>
												<tbody>
												<!-- On parcours tous les utilisateurs en attente -->
												<c:forEach items="${users_waiting}" var="userw">
													<tr>
														<td>${userw.logENT}</td>
														<td>${userw.role.nom}</td>
														<td>${userw.statut }</td>
														<td>${userw.nom}</td>
														<td>${userw.prenom}</td>
														<td><a href="${pageContext.request.contextPath}/detail_user?id=${userw.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
																data-dropdown="drop1-${userw.id}"></span></a><br/>
														<ul id="drop1-${userw.id}" class="f-dropdown" data-dropdown-content = "">
																<li><a href="${pageContext.request.contextPath}/switch_role_user?id=${userw.id}">Modifier rôle</a></li>
																<li><a href="${pageContext.request.contextPath}/edit_user?id=${userw.id}">Modifier informations</a></li>
																<li><a href="${pageContext.request.contextPath}/enable_user?id=${userw.id}">Activer</a></li>
																<li><a href="${pageContext.request.contextPath}/delete_user?id=${userw.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer l\'utilisateur ${userw.nom} ?'));">Supprimer</a></li>
															</ul></td>
													</tr>
												</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
							</div>
						</div>
						<div class="content" id="panel2">
							<table>
								<c:choose>
									<!-- Si la liste des entreprises est vide -->
									<c:when test="${empty companys}">
										<tr>
											<td colspan="3">Aucune entreprise.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<thead>
											<tr>
												<th>Entreprise</th>
												<th>SIRET</th>
												<th>Site internet</th>
												<th>Statut</th>
											</tr>
										</thead>
										<tbody>
										<!-- On parcours toutes les entreprises -->
										<c:forEach items="${companys}" var="comp">
											<tr>
												<td>${comp.nom}</td>
												<td>${comp.siret}</td>
												<td>${comp.siteWeb}</td>											
												<td>${comp.statut}</td>
												<td><a href="${pageContext.request.contextPath}/detail_company?id=${comp.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
													data-dropdown="drop3-${comp.id}"></span></a><br/>
													<ul id="drop3-${comp.id}" class="f-dropdown" data-dropdown-content = "">
														<li><a href="${pageContext.request.contextPath}/edit_company?id=${comp.id}">Modifier</a></li>
														<c:choose>
															<!-- Si la liste des utilisateurs est vide -->
															<c:when test="${comp.statut=='ACTIF'}">
																<li><a href="${pageContext.request.contextPath}/disable_company?id=${comp.id}">Désactiver</a></li>
															</c:when>
															<c:otherwise>
																<li><a href="${pageContext.request.contextPath}/enable_company?id=${comp.id}">Activer</a></li>
															</c:otherwise>
														</c:choose>
														<li><a href="${pageContext.request.contextPath}/delete_company?id=${comp.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer l\'entreprise ${comp.nom} ?'));">Supprimer</a></li>
													</ul></td>
											</tr>
										</c:forEach>
										</tbody>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
						<div class="content" id="panel3">
							<dl class="tabs vertical" data-tab="">
								<dd class="active">
									<a href="#panel3-1">Validées</a>
								</dd>
								<dd>
									<a href="#panel3-2">En attente</a>
								</dd>
							</dl>
							<div class="tabs-content vertical">
								<div class="content active" id="panel3-1">
									<table>
										<c:choose>
											<!-- Si la liste des offres validées est vide -->
											<c:when test="${empty offers_validated}">
												<tr>
													<td colspan="3">Aucune offre validée.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Offre</th>
														<th>Prix</th>
														<th>Date d'expiration</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${offers_validated}" var="offv">
														<tr>
															<td>${offv.titre}</td>
															<td>${offv.prix}</td>
															<td>${offv.dateFin}</td>
															<td><a href="${pageContext.request.contextPath}/detail_offer?id=${offv.id}"
																class="tiny button split" onclick="this.target='_blank'">Détail<span
																	data-dropdown="drop4-${offv.id}"></span></a><br/>
																<ul id="drop4-${offv.id}" class="f-dropdown" data-dropdown-content = "">
																	<li><a href="${pageContext.request.contextPath}/edit_offer?id=${offv.id}">Modifier</a></li>
																	<li><a href="${pageContext.request.contextPath}/disable_offer?id=${offv.id}">Désactiver</a></li>
																	<li><a href="${pageContext.request.contextPath}/delete_offer?id=${offv.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer l\'offre ${offv.titre} ?'));">Supprimer</a></li>
																</ul></td>
														</tr>
													</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
								<div class="content" id="panel3-2">
									<table>
										<c:choose>
											<!-- Si la liste des offres en attente est vide -->
											<c:when test="${empty offers_waiting}">
												<tr>
													<td colspan="3">Aucune offre en attente.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Offre</th>
														<th>Prix</th>
														<th>Date d'expiration</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${offers_waiting}" var="offw">
														<tr>
															<td>${offw.titre}</td>
															<td>${offw.prix}</td>
															<td>${offw.dateFin}</td>
															<td><a href="${pageContext.request.contextPath}/detail_offer?id=${offw.id}"
																class="tiny button split" onclick="this.target='_blank'">Détail<span
																	data-dropdown="drop4-${offw.id}"></span></a><br/>
																<ul id="drop4-${offw.id}" class="f-dropdown" data-dropdown-content = "">
																	<li><a href="${pageContext.request.contextPath}/edit_offer?id=${offw.id}">Modifier</a></li>
																	<li><a href="${pageContext.request.contextPath}/valid_offer?id=${offw.id}">Valider</a></li>
																	<li><a href="${pageContext.request.contextPath}/delete_offer?id=${offw.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer l\'offre ${offw.titre} ?'));">Supprimer</a></li>
																</ul></td>
														</tr>
													</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
							</div>
						</div>
						<div class="content" id="panel4">
							<table>
								<c:choose>
									<!-- Si la liste des offres en attente est vide -->
									<c:when test="${empty domains}">
										<tr>
											<td colspan="3">Aucun domaine.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<thead>
											<tr>
												<th>Domaine</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${domains}" var="dom">
												<tr>
													<td>${dom.nom}</td>
													<td><a href="${pageContext.request.contextPath}/domain_list?idd=${dom.id}"
														class="tiny button split" onclick="this.target='_blank'">Offres<span
															data-dropdown="drop7-${dom.id}"></span></a><br/>
														<ul id="drop7-${dom.id}" class="f-dropdown" data-dropdown-content = "">
															<li><a href="${pageContext.request.contextPath}/edit_domain?id=${dom.id}">Modifier</a></li>
															<li><a href="${pageContext.request.contextPath}/delete_domain?id=${dom.id}" onclick="return(confirm('Etes vous certain de vouloir supprimer le domaine ${dom.nom} ?'));">Supprimer</a></li>
														</ul></td>
												</tr>
											</c:forEach>
										</tbody>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<div class="right">
						<a href="#" data-dropdown="hover1" data-options="is_hover:true; align:right" class="button">Ajouter une entitée</a>
						<ul id="hover1" class="f-dropdown" data-dropdown-content="">
	  						<li><a href="${pageContext.request.contextPath}/edit_user">un nouvel utilisateur</a></li>
	  						<li><a href="${pageContext.request.contextPath}/edit_company">une nouvelle entreprise</a></li>
	  						<li><a href="${pageContext.request.contextPath}/edit_offer">une nouvelle offre</a></li>
	  						<li><a href="${pageContext.request.contextPath}/edit_domain">un nouveau domaine</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.dropdown.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.topbar.js"><jsp:text /></script>
	<script type="text/javascript">
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