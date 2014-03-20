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
	<spring:url value="/j_spring_security_check" var="auth" />
	<html xmlns="http://www.w3.org/1999/xhtml" class="no-js" lang="en">
<head>
	<title>Authentification au SAG</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public-ressources/css/foundation.css" />
    <script type='text/javascript' src="${pageContext.request.contextPath}/public-ressources/js/vendor/modernizr.js"><jsp:text /></script>
</head>
  <body>
	<div class="row">
		<div class="large-8 columns">
			<h1><b>SAG</b> - Site d'Achat Groupé</h1>
			<div class="row">
				<form id="form_login" action="${auth}" method="post">
					<fieldset>
						<legend>ENT - Authentifiez vous</legend>
						<div class="input-wrapper">
							<label>Identifiant *
						  		<input type="text" id="login" name="username" placeholder="Identifiant" required="required" />
							</label>
						</div>
						<div class="input-wrapper">
							<label>Mot de passe *
						  		<input type="password" name="password" placeholder="Mot de passe" required="required" />
							</label>
						</div>
						<input id="checkbox1" type="checkbox" /><label for="checkbox1">Se connecter en tant qu'entreprise.</label>
					</fieldset>
					<div class="left">
						<small>* : Champs obligatoires.</small>
					</div>
					<div class="right">
							<![CDATA[
								<span data-tooltip="" data-options='disable_for_touch:true' class="has-tip" 
									title="<dl>
										<dt>Etudiants et entreprises</dt>
										<dd>- Entreprise : cochez la case correspondante,</dd>
										<dd>- Etudiant : renseignez vos identifiants ENT.</dd>
									</dl>">
									<input id="submit_button" type="submit" class="small button" value="Se connecter" />
								</span>
							]]>
					</div>
				</form>
			</div>
			<div class="row">
				<p>Vous n'êtes pas encore inscrit ? <a id="register_button" href="${pageContext.request.contextPath}/register">S'inscrire</a>.</p>
				
			</div>
		</div>
    </div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<script type='text/javascript' src="${pageContext.request.contextPath}/public-ressources/js/vendor/jquery.js"><jsp:text /></script>
	<script type='text/javascript' src="${pageContext.request.contextPath}/public-ressources/js/foundation.min.js"><jsp:text /></script>
	<script type='text/javascript' src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.js"><jsp:text /></script>
	<script type='text/javascript' src="${pageContext.request.contextPath}/public-ressources/js/foundation/foundation.tooltip.js"><jsp:text /></script>
	<script type='text/javascript'>
		$(document).foundation(); 
	</script>
  </body>
</html>
</jsp:root>