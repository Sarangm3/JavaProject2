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
    <form action="MaterialServlet" method="post">
        <label for="subjectCode">Subject Code:</label>
        <input type="text" id="subjectCode" name="subjectCode"><br><br>
        
        <label for="subName">Subject Name:</label>
        <input type="text" id="subName" name="subName"><br><br>
        
        <label for="paperDetail">Paper Detail:</label>
        <input type="text" id="paperDetail" name="paperDetail"><br><br>
        
        <label for="url">URL:</label>
        <input type="text" id="url" name="url"><br><br>
        
        <label for="url">Search:</label>
        <input type="text" id="search" name="search"><br><br>
        
        <label for="operation">Operation:</label>
        <select name="operation" id="operation">
            <option value="add">Add</option>
            <option value="view">View</option>
            <option value="modify">Modify</option>
            <option value="delete">Delete</option>
        </select><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
