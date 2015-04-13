<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>success page</title>
<style type="text/css">
h2{ color: green; 
}
</style>
</head>
<body>
<h2> Logged in successfully using the below email</h2>
<%=request.getAttribute("email") %>
</body>
</html>