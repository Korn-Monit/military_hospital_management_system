<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Data Display</title>
</head>
<body>
    <h1>Data Retrieved:</h1>
    <p>${data}</p> 
    <p>Data: <%= request.getAttribute("data1") %></p>
</body>
</html>