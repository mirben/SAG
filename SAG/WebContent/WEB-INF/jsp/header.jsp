<?xml version="1.0" encoding="UTF-8" ?>
<!-- Encodé en UTF-8, génère une page xhtml strict -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:sec="http://www.springframework.org/security/tags" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<!-- Génère les liens pour accéder à l'annuaire, se connecter ou se déconnecter et à une page d'administration -->
	<div class="row">
		<div class="large-12 columns">
			<h1>
				<b>SAG</b> - Site d'Achat GroupÃ©
			</h1>
		</div>
		<div class="right">
			<div class="row collapse">
				<div class="large-8 small-9 columns">
					<input type="text" id="search_in"
						placeholder="Rechercher une offre">
				</div>
				<div class="large-4 small-3 columns">
					<a href="#" onClick="search_key()" class="button expand postfix">Rechercher</a>
				</div>
			</div>
		</div>
	</div>
	<div class="contain-to-grid sticky">
		<nav class="top-bar" data-topbar>
			<ul class="title-area">
				<li class="name"></li>
				<li class="toggle-topbar menu-icon"><a href="#">Menu</a></li>
			</ul>
			<section class="middle tab-bar-section">
				<section class="top-bar-section">
					<!-- Left Nav Section -->
					<ul class="left">
						<li><a href="home.html">Accueil</a></li>
						<li class="divider"></li>
						<li><a href="list.html">Toutes les offres</a></li>
						<li class="divider"></li>
						<li class="has-dropdown"><a href="#">Domaines</a>
							<ul class="dropdown">
								<li><a href="domain_Musique.html">Musique</a></li>
								<li><a href="domain_Decoration.html">Décoration</a></li>
								<li><a href="domain_Literie.html">Litterie</a></li>
								<li><a href="domain_Jardin.html">Jardin</a></li>
							</ul></li>
					</ul>
					<!-- Right Nav Section -->
					<ul id="rightmenu" class="right">
						<li class="has-dropdown"><a id="username" href="#">Joël
								Karcher</a>
							<ul class="dropdown">
								<li id="infos"><a href="detail_user1.html">Modifier mes
										informations</a></li>
								<li id="infosc"><a href="detail_company1.html">Modifier
										mes informations</a></li>
								<li id="offers"><a href="list_propose1.html">Consulter
										ses offres</a></li>
								<li><a href="login.html" onClick="clear_session()">Se
										déconnecter</a></li>
							</ul>
						</li>
						<li class="divider"></li>
						<li><a href="admin.html">Administrer</a></li>
					</ul>
				</section>
			</section>
		</nav>
	</div>
</jsp:root>
