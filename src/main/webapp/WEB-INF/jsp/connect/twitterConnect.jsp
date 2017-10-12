<%--
  Created by IntelliJ IDEA.
  User: jordi.viladoms
  Date: 12/10/17
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Twitter</title>
</head>
<body>
<h3>Connect to Twitter</h3>
<span th:if="${social_provider_error}">Provider error (maybe you need to configure the app id and secret?)</span>
<form action="/connect/twitter" method="POST">
    <div class="formInfo">
        <p>You aren't connected to Twitter yet. Click the button to connect this application with your Twitter account.</p>
    </div>
    <p><button type="submit">Connect to Twitter</button></p>
</form>
</body>
</html>
