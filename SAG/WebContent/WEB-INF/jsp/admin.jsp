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
<body onload="refresh_account()">
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
										<thead>
											<tr>
												<th>Utilisateur</th>
												<th>Rôle</th>
												<th>Nom</th>
												<th>Prénom</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>admin</td>
												<td>Administrateur</td>
												<td>null</td>
												<td>null</td>
												<td><a href="" class="tiny button split">Actions<span
														data-dropdown="drop"></span></a><br>
												<ul id="drop" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier rôle</a></li>
														<li><a href="#">Modifier informations</a></li>
														<li><a href="#">Désactiver</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
											<tr>
												<td>k1104696</td>
												<td>Etudiant</td>
												<td>Karcher</td>
												<td>JoÃ«l</td>
												<td><a href="" class="tiny button split">Actions<span
														data-dropdown="drop2"></span></a><br>
												<ul id="drop2" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier rôle</a></li>
														<li><a href="#">Modifier informations</a></li>
														<li><a href="#">Désactiver</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="content" id="panel2a">
									<table>
										<thead>
											<tr>
												<th>Utilisateur</th>
												<th>Rôle</th>
												<th>Nom</th>
												<th>Prénom</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>m900707</td>
												<td>Etudiant</td>
												<td>Miretti</td>
												<td>Benjamin</td>
												<td><a href="" class="tiny button split">Actions<span
														data-dropdown="drop0"></span></a><br>
												<ul id="drop0" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier rôle</a></li>
														<li><a href="#">Modifier informations</a></li>
														<li><a href="#">Activer</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="left">
								<a href="newuser.html" class="button">Ajouter un nouvel
									utilisateur</a>
							</div>
						</div>
						<div class="content" id="panel2-2">
							<table>
								<thead>
									<tr>
										<th>Entreprise</th>
										<th>SIRET</th>
										<th>Site internet</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Fnac</td>
										<td>775-661-390 00739</td>
										<td>http://www.fnac.com</td>
										<td><a href="" class="tiny button split">Actions<span
												data-dropdown="drop3"></span></a><br>
											<ul id="drop3" class="f-dropdown" data-dropdown-content>
												<li><a href="#">Modifier</a></li>
												<li><a href="#">Désactiver</a></li>
												<li><a href="#">Supprimer</a></li>
											</ul></td>
									</tr>
								</tbody>
							</table>
							<div class="left">
								<a href="newcompany.html" class="button">Ajouter une
									nouvelle entreprise</a>
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
										<thead>
											<tr>
												<th>Offre</th>
												<th>Domaine</th>
												<th>Prix</th>
												<th>Date d'expiration</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Oreillets mémoire de forme</td>
												<td>Literie</td>
												<td>59€¬</td>
												<td>02/03/14</td>
												<td><a href="detail_offre1.html"
													class="tiny button split">Actions<span
														data-dropdown="drop4"></span></a><br>
													<ul id="drop4" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier</a></li>
														<li><a href="#">Désactiver</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
											<tr>
												<td>Daft Punk : RAM - Album</td>
												<td>Musique</td>
												<td>9,99€¬</td>
												<td>12/03/14</td>
												<td><a href="detail_offre2.html"
													class="tiny button split">Actions<span
														data-dropdown="drop5"></span></a><br>
													<ul id="drop5" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier</a></li>
														<li><a href="#">Désactiver</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
											<tr>
												<td>Sapin de noÃ«l</td>
												<td>Jardin</td>
												<td>20€¬</td>
												<td>25/12/14</td>
												<td><a href="detail_offre3.html"
													class="tiny button split">Actions<span
														data-dropdown="drop6"></span></a><br>
													<ul id="drop6" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier</a></li>
														<li><a href="#">Désactiver</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="content" id="panel4a">
									<table>
										<thead>
											<tr>
												<th>Offre</th>
												<th>Domaine</th>
												<th>Prix</th>
												<th>Date d'expiration</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tuyau d'arosage</td>
												<td>Jardin</td>
												<td>20€¬</td>
												<td>20/03/14</td>
												<td><a href="#" class="tiny button split">Actions<span
														data-dropdown="dropa"></span></a><br>
													<ul id="dropa" class="f-dropdown" data-dropdown-content>
														<li><a href="#">Modifier</a></li>
														<li><a href="#">Valider</a></li>
														<li><a href="#">Supprimer</a></li>
													</ul></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="left">
								<a href="newoffer.html" class="button">Ajouter une nouvelle
									offre</a>
							</div>
						</div>
						<div class="content" id="panel2-4">
							<table>
								<thead>
									<tr>
										<th>Domaine</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Musique</td>
										<td><a href="domain_Musique.html"
											class="tiny button split">Actions<span
												data-dropdown="drop7"></span></a><br>
											<ul id="drop7" class="f-dropdown" data-dropdown-content>
												<li><a href="#">Modifier</a></li>
												<li><a href="#">Supprimer</a></li>
											</ul></td>
									</tr>
									<tr>
										<td>Décoration</td>
										<td><a href="domain_Decoration.html"
											class="tiny button split">Actions<span
												data-dropdown="drop8"></span></a><br>
											<ul id="drop8" class="f-dropdown" data-dropdown-content>
												<li><a href="#">Modifier</a></li>
												<li><a href="#">Supprimer</a></li>
											</ul></td>
									</tr>
									<tr>
										<td>Litterie</td>
										<td><a href="domain_Literie.html"
											class="tiny button split">Actions<span
												data-dropdown="drop9"></span></a><br>
											<ul id="drop9" class="f-dropdown" data-dropdown-content>
												<li><a href="#">Modifier</a></li>
												<li><a href="#">Supprimer</a></li>
											</ul></td>
									</tr>
									<tr>
										<td>Jardin</td>
										<td><a href="domain_Jardin.html"
											class="tiny button split">Actions<span
												data-dropdown="drop10"></span></a><br>
											<ul id="drop10" class="f-dropdown" data-dropdown-content>
												<li><a href="#">Modifier</a></li>
												<li><a href="#">Supprimer</a></li>
											</ul></td>
									</tr>
								</tbody>
							</table>
							<div class="left">
								<a href="newdomain.html" class="button">Ajouter un nouveau
									domaine</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal" class="reveal-modal xlarge" data-reveal>
		<h2>AccÃ¨s non authorisé.</h2>
		<p class="lead">Vous tentez d'accéder Ã  une page dont l'accÃ¨s
			vous est interdit.</p>
		<p>Vous allez Ãªtre redirigé vers l'accueil.</p>
		<a class="close-reveal-modal" href="home.html">&#215;</a>
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
			if (chaine.match("^.*(OREILLET|MÃMOIRE|FORME|LITERIE|MEMOIRE).*$")) {
				$(location).attr('href', "detail_offre1.html");
			}
			if (chaine
					.match("^.*(DAFT ??PUNK|DAFT|PUNK|RAM|MEMORIE|ACCESS|ALBUM).*$")) {
				$(location).attr('href', "detail_offre2.html");
			}
			if (chaine.match("^.*(NOEL|SAPIN|NATUREL|NOÃL).*$")) {
				$(location).attr('href', "detail_offre3.html");
			}
		}
		if (window.sessionStorage.getItem('role') != 'Administrateur') {
			$('#myModal').foundation('reveal', 'open');
			$(document).on('closed', '[data-reveal]', function() {
				window.location.href = "home.html";
			});
		}
		function refresh_account() {
			if (window.sessionStorage.getItem('role') != 'Etudiant') {
				$("#infos").remove();
			}
			if (window.sessionStorage.getItem('role') != 'Entreprise') {
				$("#infosc").remove();
			}
			if (window.sessionStorage.getItem('role') == 'Administrateur') {
				("#rightmenu").append('<li class="divider"></li><li><a href="admin.html">Administrer</a></li>');
			}
			if(window.sessionStorage.getItem('nomE')!=null && window.sessionStorage.getItem('prenomE')!=null){
			    $("#username").text(window.sessionStorage.getItem('prenomE')+" "+window.sessionStorage.getItem('nomE'));
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