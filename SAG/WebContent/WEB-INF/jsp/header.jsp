<?xml version="1.0" encoding="UTF-8" ?>
<!-- Encodé en UTF-8, génère une page xhtml strict -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<!-- Génère les liens pour accéder aux différentes page du site, se connecter ou se déconnecter et à une page d'administration .. -->
	<div class="row">
		<div class="large-12 columns">
			<h1>
				<b>SAG</b> - Site d'Achat Groupé
			</h1>
		</div>
		<div class="right">
			<div class="row collapse">
				<div class="large-8 small-9 columns">
					<input type="text" id="search_in"
						placeholder="Rechercher une offre" />
				</div>
				<div class="large-4 small-3 columns">
					<a href="#" onclick="search_key()" class="button expand postfix">Rechercher</a>
				</div>
			</div>
		</div>
	</div>
	<div class="contain-to-grid sticky">
			<nav class="top-bar" data-topbar="" >
			<ul class="title-area">
				<li class="name"></li>
				<li class="toggle-topbar menu-icon"><a href="#">Menu</a></li>
			</ul>
			<section class="middle tab-bar-section">
				<section class="top-bar-section">
					<!-- Left Nav Section -->
					<ul class="left">
						<li><a href="${pageContext.request.contextPath}/home">Accueil</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/list_offer">Toutes les offres</a></li>
						<c:if test="${not empty domains}">
							<li class="divider"></li>
							<li class="has-dropdown"><a href="#" id="dom_menu">Domaines</a>
								<ul class="dropdown">
									<c:forEach items="${domains}" var="dom">
										<li><a href="${pageContext.request.contextPath}/domain_list?idd=${dom.id}">${dom.nom}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</ul>
					<!-- Right Nav Section -->
					<ul id="rightmenu" class="right">
						<c:if test="${ not empty user_co.nom}">
							<li class="has-dropdown"><a id="username" href="#"><c:out value="${user_co.nom}" /></a>
								<ul class="dropdown">
									<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
											<li id="infos"><a href="${pageContext.request.contextPath}/detail_user?id=${user_co.id}">Modifier mes informations</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ENTR')">
											<li id="infosc"><a href="${pageContext.request.contextPath}/detail_company?id=${user_co.id}">Modifier mes informations</a></li>
									</sec:authorize>
									<li id="offers"><a href="${pageContext.request.contextPath}/offer_proposed?id=${user_co.id}">Consulter ses offres</a></li>
									<li><a href="${pageContext.request.contextPath}/propose_offre">Proposer une offre</a></li>
									<li><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></li>
								</ul>
							</li>
						</c:if>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/admin">Administrer</a></li>
						</sec:authorize>
					</ul>
				</section>
			</section>
		</nav>
	</div>
</jsp:root>
