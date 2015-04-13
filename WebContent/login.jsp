<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login page</title>
<style type="text/css">
#error{
 color: red;
}
</style>
</head>
<body>
<h2>Login Page</h2>
<form action="/DataBaseServlets/DBQueryingController?action=dologin" method="post">
<table>
	<tr>
		<td>Email</td>
		<td><input type="text" name="email" value="<%=request.getAttribute("email")%>"/></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="text" name="password" value="<%=request.getAttribute("password")%>"/></td>
	</tr>
	<tr><td><input type="submit" value="Login"/><td></tr>
</table>

<span id="error"><%=request.getAttribute("errormsg") %></span>
</form>
</body>
</html>