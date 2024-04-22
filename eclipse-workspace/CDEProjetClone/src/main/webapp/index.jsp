<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="java.util.List" %>
<%@ page import="main.model.StockObject" %>
<head>
    <title>Data Display</title>
</head>
<body>
    <h1>Data Retrieved:</h1>
    <p>List<StockObject> dataList = (List<StockObject>) request.getAttribute("dataList");</p>
    
    			    <ul>
			        <% 
			        List<StockObject> dataList = (List<StockObject>) request.getAttribute("data");

			        if (dataList != null) {
			            for (StockObject data : dataList) {
			        %>
			        <li id="todo_<%= data.getModule() %>">

			                <%= data.getModule() %>
			        </li>
			        <% 
			            }
			        } else {
			        %>
			            <li>No new todo added</li>
			        <% } %>
			    </ul>
</body>
</html>