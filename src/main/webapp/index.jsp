<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="es.pedronanez.clubevenres.i18n.strings" />
<!doctype html>
<html lang="${language}">
<head>
<meta charset="utf-8">

<title>Clube venres</title>
<link rel="stylesheet" href="index.css">

</head>
<body>
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="gl" ${language == 'gl' ? 'selected' : ''}>Galego</option>
            <option value="es" ${language == 'es' ? 'selected' : ''}>Castellano</option>
        </select>
    </form>
<div class="form-style-1">
    <h1><fmt:message key="title" /></h1>
    <h2><fmt:message key="punchline" /></h2>
    <h3>Aviso: Actualmente non hai activa ningunha convocatoria do Clube Venres.</h3>
    </div>
    
    <form>
        <ul class="form-style-1">
            <li><label for="nome" class="formLabel"><fmt:message key="form.nametitle" /></label> <input
                type="text" placeholder="<fmt:message key="form.nameplaceholder" />" name="nome" id="nome" class="field-long"/><input
                type="button" value="Recuperar &uacute;ltimo pedido" class="field-long" /></li>
            <li><label for="prato" class="formLabel"><fmt:message key="form.food" /></label> <select name="prato" id="prato" class="field-select">
                    <option disabled></option>
            </select></li>
            <li>
                <fieldset id="ingredientes" style="display:none;">
                    <legend><fmt:message key="form.removeingredient" /></legend>
                </fieldset>
            </li>

            <li>
            <input type="checkbox" id="patacas" /> <label
                for="patatas"><fmt:message key="form.potatos" /></label></li>
            <li>
                <fieldset id="sauces" style="display:none;">
                    <legend>Algunha salsa nas patacas?</legend>
                    <input type="radio" name="sauce" value="alioli">
                    Ali-oli 
                    <input type="radio" name="sauce"
                        value="picanting"> Salsa picante 
                    <input
                        type="radio" name="sauce" value="cheese">
                    Queixo 
                    <input type="radio" name="sauce"
                        value="nomatters"> Tanto me da 
                    <input
                        type="radio" name="sauce" value="noway">
                    Ni de coña
                </fieldset>
            </li>

        </ul>
    </form>

    <script src="index.js"></script>
</body>
</html>