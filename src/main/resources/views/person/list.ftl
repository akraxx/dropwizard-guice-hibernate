<#-- @ftlvariable name="" type="fr.mmarie.views.person.PersonListView" -->
<html>
<body>
<!-- calls getPerson().getName() and sanitizes it -->
<h1>Persons : </h1>

<ul>
<#list persons as person>
    <li>${person.firstName} ${person.lastName}</li>
</#list>
</ul>

</body>
</html>