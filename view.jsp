<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Material Details</title>
</head>
<body>
    <h2>Material Details</h2>
    <% if(request.getAttribute("searchResult") != null) { %>
        <pre><%= request.getAttribute("searchResult") %></pre>
    <% } %>
    <br>
    <a href="MaterialServlet">Back to Material</a>
</body>
</html>
