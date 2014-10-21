<#-- @ftlvariable name="" type="fr.mmarie.views.department.DepartmentView" -->
<html>
<body>
<!-- calls getPerson().getName() and sanitizes it -->
<h1>Department :${department.name?html?capitalize}</h1>
<ul>
<#list department.persons as person>
    <li>${person.firstName} ${person.lastName}</li>
</#list>
</ul>
</body>
</html>