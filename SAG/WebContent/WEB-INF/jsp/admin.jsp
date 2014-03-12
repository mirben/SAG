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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/public-resources/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
<script
	src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"></script>
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
					<dl class="tabs" data-tab>
						<dd class="active">
							<a href="#panel2-1">Gestion des utilisateurs</a>
						</dd>
						<dd>
							<a href="#panel2-2">Gestion des entreprises</a>
						</dd>
						<dd>
							<a href="#panel2-3">Gestion des offres</a>
						</dd>
						<dd>
							<a href="#panel2-4">Gestion des domaines</a>
						</dd>
					</dl>
					<div class="tabs-content">
						<div class="content active" id="panel2-1">
							<dl class="tabs vertical" data-tab>
								<dd class="active">
									<a href="#panel1a">Actifs</a>
								</dd>
								<dd>
									<a href="#panel2a">En attente</a>
								</dd>
							</dl>
							<div class="tabs-content vertical">
								<div class="content active" id="panel1a">
									<table>
										<c:choose>
											<!-- Si la liste des utilisateurs est vide -->
											<c:when test="${empty users_enabled}">
												<tr>
													<td colspan="3">Aucun utilisateur.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Utilisateur</th>
														<th>Rôle</th>
														<th>Nom</th>
														<th>Prénom</th>
													</tr>
												</thead>
												<tbody>
												<!-- On parcours tous les utilisateurs actifs -->
												<c:forEach items="${users_ebabled}" var="user">
													<tr>
														<td>${user.ent}</td>
														<td>${user.role.nom}</td>
														<td>${user.nom}</td>
														<td>${user.prenom}</td>
														<td><a href="/detail_user?id=${user.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
																data-dropdown="drop"></span></a><br>
														<ul id="drop" class="f-dropdown" data-dropdown-content>
																<li><a href="/switch_role_user?id=${user.id}">Modifier rôle</a></li>
																<li><a href="/edit_user?id=${user.id}">Modifier informations</a></li>
																<li><a href="/disable_user?id=${user.id}">Désactiver</a></li>
																<li><a href="/delete_user?id=${user.id}">Supprimer</a></li>
															</ul></td>
													</tr>
												</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
								<div class="content" id="panel2a">
									<table>
										<c:choose>
											<!-- Si la liste des utilisateurs est vide -->
											<c:when test="${empty users_waiting}">
												<tr>
													<td colspan="3">Aucun utilisateur.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<thead>
													<tr>
														<th>Utilisateur</th>
														<th>Rôle</th>
														<th>Nom</th>
														<th>Prénom</th>
													</tr>
												</thead>
												<tbody>
												<!-- On parcours tous les utilisateurs en attente -->
												<c:forEach items="${users_waiting}" var="userw">
													<tr>
														<td>${userw.ent}</td>
														<td>${userw.role.nom}</td>
														<td>${userw.nom}</td>
														<td>${userw.prenom}</td>
														<td><a href="/detail_user?id=${userw.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
																data-dropdown="drop"></span></a><br>
														<ul id="drop" class="f-dropdown" data-dropdown-content>
																<li><a href="/switch_role_user?id=${userw.id}">Modifier rôle</a></li>
																<li><a href="/edit_user?id=${userw.id}">Modifier informations</a></li>
																<li><a href="/enable_user?id=${userw.id}">Activer</a></li>
																<li><a href="/delete_user?id=${userw.id}">Supprimer</a></li>
															</ul></td>
													</tr>
												</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
							</div>
							<div class="left">
								<a href="/add_user" class="button">Ajouter un nouvel utilisateur</a>
							</div>
						</div>
						<div class="content" id="panel2-2">
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
											</tr>
										</thead>
										<tbody>
										<!-- On parcours toutes les entreprises -->
										<c:forEach items="${companys}" var="comp">
											<tr>
												<td>${comp.nom}</td>
												<td>${comp.siret}</td>
												<td>${comp.site}</td>
												<td><a href="detail_company?id=${comp.id}" class="tiny button split" onclick="this.target='_blank'">Profil<span
													data-dropdown="drop3"></span></a><br>
													<ul id="drop3" class="f-dropdown" data-dropdown-content>
														<li><a href="edit_company?id=${comp.id}">Modifier</a></li>
														<c:choose>
															<!-- Si la liste des utilisateurs est vide -->
															<c:when test="${company.actif==1}">
																<li><a href="disable_company?id=${comp.id}">Désactiver</a></li>
															</c:when>
															<c:otherwise>
																<li><a href="enable_company?id=${comp.id}">Activer</a></li>
															</c:otherwise>
														</c:choose>
														<li><a href="delete_company?id=${comp.id}">Supprimer</a></li>
													</ul></td>
											</tr>
										</c:forEach>
										</tbody>
									</c:otherwise>
								</c:choose>
							</table>
							<div class="left">
								<a href="/add_company" class="button">Ajouter une nouvelle entreprise</a>
							</div>
						</div>
						<div class="content" id="panel2-3">
							<dl class="tabs vertical" data-tab>
								<dd class="active">
									<a href="#panel3a">Validées</a>
								</dd>
								<dd>
									<a href="#panel4a">En attente</a>
								</dd>
							</dl>
							<div class="tabs-content vertical">
								<div class="content active" id="panel3a">
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
															<td><a href="detail_offer?id=${offv.id}"
																class="tiny button split" onclick="this.target='_blank'">Détail<span
																	data-dropdown="drop4"></span></a><br>
																<ul id="drop4" class="f-dropdown" data-dropdown-content>
																	<li><a href="/edit_offer?id=${offv.id}">Modifier</a></li>
																	<li><a href="/disable_offer?id=${offv.id}">Désactiver</a></li>
																	<li><a href="/delete_offer?id=${offv.id}">Supprimer</a></li>
																</ul></td>
														</tr>
													</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
								<div class="content" id="panel4a">
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
															<td><a href="detail_offer?id=${offw.id}"
																class="tiny button split" onclick="this.target='_blank'">Détail<span
																	data-dropdown="drop4"></span></a><br>
																<ul id="drop4" class="f-dropdown" data-dropdown-content>
																	<li><a href="/edit_offer?id=${offw.id}">Modifier</a></li>
																	<li><a href="/valid_offer?id=${offw.id}">Valider</a></li>
																	<li><a href="/delete_offer?id=${offw.id}">Supprimer</a></li>
																</ul></td>
														</tr>
													</c:forEach>
												</tbody>
											</c:otherwise>
										</c:choose>
									</table>
								</div>
							</div>
							<div class="left">
								<a href="/add_offer" class="button">Ajouter une nouvelle offre</a>
							</div>
						</div>
						<div class="content" id="panel2-4">
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
													<td><a href="/domain_list?id=${dom.id}"
														class="tiny button split" onclick="this.target='_blank'">Offres<span
															data-dropdown="drop7"></span></a><br>
														<ul id="drop7" class="f-dropdown" data-dropdown-content>
															<li><a href="/edit_domain?id=${dom.id}">Modifier</a></li>
															<li><a href="/delete_domain?id=${dom.id}">Supprimer</a></li>
														</ul></td>
												</tr>
											</c:forEach>
										</tbody>
									</c:otherwise>
								</c:choose>
							</table>
							<div class="left">
								<a href="/add_domain" class="button">Ajouter un nouveau domaine</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/public-resources/js/vendor/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/public-resources/js/foundation.min.js"></script>
	<script src="${pageContext.request.contextPath}/public-resources/js/foundation/foundation.topbar.js"></script>
	<script>
	  $(document).foundation();
	</script>
	<script type="text/javascript">
		document.getElementById("search_in").onkeydown = function(event) {
			if (event.keyCode == '13') {
				search_key();
				return false;
			}
		};
		function search_key() {
			var chaine = document.getElementById("search_in").value;
			chaine = chaine.toUpperCase();
			console.log(chaine);
			if (chaine.match("^.*(OREILLET|MEMOIRE|FORME|LITERIE|MEMOIRE).*$")) {
				$(location).attr('href', "detail_offre1.html");
			}
			if (chaine
					.match("^.*(DAFT ??PUNK|DAFT|PUNK|RAM|MEMORIE|ACCESS|ALBUM).*$")) {
				$(location).attr('href', "detail_offre2.html");
			}
			if (chaine.match("^.*(NOEL|SAPIN|NATUREL).*$")) {
				$(location).attr('href', "detail_offre3.html");
			}
		}
		function clear_session(){
			window.sessionStorage.clear();
		}
	</script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
	</html>
</jsp:root>