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
<title>Authentification au SAG</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-resources/css/foundation.css" />
    <script src="${pageContext.request.contextPath}/public-resources/js/vendor/modernizr.js"></script>
</head>
  <body>
	<div class="row">
		<div class="large-8 columns">
			<h1><b>SAG</b> - Site d'Achat Groupé</h1>
			<div class="row">
				<form id="form_login" action="${auth}" method="post" data-abide="ajax">
					<fieldset>
						<legend>ENT - Authentifiez vous</legend>
						<div class="name-field">
							<label>Identifiant *
							  <input type="text" id="login" name='username' placeholder="Identifiant" required="required" />
							</label>
							<small class="error">Identifiant incorrect</small>
						</div>
						<div class="password-field">
							<label>Mot de passe *
							  <input type="password" name='password' placeholder="Mot de passe" required="required" />
							</label>
							<small class="error">Mot de passe incorrect</small>
						</div>
						<input id="checkbox1" type="checkbox" /><label for="checkbox1">Se connecter en tant qu'entreprise.</label>
					</fieldset>
					<div class="left">
						<small>* : Champs obligatoires.</small>
					</div>
					<div class="right">
							<span data-tooltip data-options="disable_for_touch:true" class="has-tip" 
								title="<dl>
											<dt>Etudiants et entreprises</dt>
											<dd>- Entreprise : cochez la case correspondante,</dd>
											<dd>- Etudiant : renseignez vos identifiants ENT.</dd>
										</dl>">
								<input id="submit_button" type="submit" class="small button" value="Se connecter" />
							</span>
					</div>
				</form>
			</div>
			<div class="row">
				<p>Vous n'êtes pas encore inscrit ? <a id="register_button" href="${pageContext.request.contextPath}/register">S'inscrire</a>.</p>
				
			</div>
		</div>
    </div>
    <script src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.js"></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.abide.js"></script>
	<script src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.tooltip.js"></script>
    <script>
      $(document).foundation();
    </script>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
  </body>
</html>
</jsp:root>
