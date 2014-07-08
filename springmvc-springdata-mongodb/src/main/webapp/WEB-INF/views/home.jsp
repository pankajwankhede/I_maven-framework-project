<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
   <head>
      <title>spring-mvc-showcase</title>
      <link href="<c:url value="/resources/form.css" />" rel="stylesheet" type="text/css"/>
      <link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.all.css" />" rel="stylesheet" type="text/css"/>
   </head>

   <body>
      <h1><a href="<c:url value="/" />">springmvc-springdata-mongodb</a></h1>

      <p>Spring MVC application mixed with Spring Data and MongoDB database.</p>

      <div id="tabs">
         <ul>
            <li><a href="#save">Save Person</a></li>
            <li><a href="#find">Find Person</a></li>
            <li><a href="#list">List all Persons</a></li>
            <li><a href="#remove">Remove Persons</a></li>
         </ul>

         <div id="save">
            <h2>Save an Person</h2>

            <p>
               The following form allows to save an Person instance in MongoDB.
            </p>
            <ul>
               <form id="savePersonForm" action="<c:url value="/person" />" method="post">
                  <li>
                     <label id="emailLabel" for="emailInput">Email*:</label>
                     <input id="emailInput" name="email" type="text" size="30"/>
                  </li>
                  <li>
                     <label id="firstNameLabel" for="firstNameInput">First Name:</label>
                     <input id="firstNameInput" name="firstName" type="text" size="30"/>
                  </li>
                  <li>
                     <label id="lastNameLabel" for="lastNameInput">Last Name:</label>
                     <input id="lastNameInput" name="lastName" type="text" size="30"/>
                  </li>
                  <li>
                     <label id="ageLabel" for="ageInput">Age: </label>
                     <input id="ageInput" name="age" type="text" size="30"/>
                  </li>
                  <input id="savePersonSubmit" type="submit"/>
               </form>
            </ul>
         </div>
         <div id="find">
            <h2>Find an Person</h2>

            <p>
               The following form allows to find by her email, any Person in MongoDB.
            </p>
            <ul>
               <li>
                  <form id="findPersonByEmailForm" action="<c:url value="/person" />">
                     <label id="emailLabel" for="emailInput">Email: </label>
                     <input id="emailInput" name="email" type="text" size="30"/>
                     <input id="findPersonByEmailSubmit" type="submit"/>
                  </form>
               </li>
            </ul>
         </div>
         <div id="list">
            <h2>List all Persons</h2>

            <p>
               The following form allows to list all Persons in MongoDB.
            </p>
            <ul>
               <li>
                  <form id="listAllPersonForm" action="<c:url value="/person/list" />">
                     <input id="listAllPersonButtonInput" type="submit" value="List all Persons"/>
                  </form>
               </li>
            </ul>
         </div>
         <div id="remove">
            <h2>Remove all Persons</h2>

            <p>
               The following form removes to delete all Persons in MongoDB.
            </p>
            <ul>
               <li>
                  <form id="removePersonForm" action="<c:url value="/person/delete/all" />" method="post">
                     <input id="removeButtonInput" type="submit" value="Remove all Persons"/>
                  </form>
               </li>
            </ul>
         </div>
      </div>

      <script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
      <script type="text/javascript" src="<c:url value="/resources/jqueryform/2.8/jquery.form.js" />"></script>
      <script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.core.js" />"></script>
      <script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.widget.js" />"></script>
      <script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.tabs.js" />"></script>
      <script type="text/javascript" src="<c:url value="/resources/json2.js" />"></script>

      <script type="text/javascript">
         $(document).ready(function () {
            $("#tabs").tabs();
         });
      </script>
   </body>
</html>
