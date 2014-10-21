<#-- @ftlvariable name="" type="fr.mmarie.views.person.PersonView" -->
<html>
<body>
<!-- calls getPerson().getName() and sanitizes it -->
<h1>Hello, ${person.lastName?html?capitalize} ${person.firstName?html?upper_case}!</h1>
<#if person.department??>
<h2>Your department : ${person.department.name?html}</h2>
</#if>
</body>
</html>