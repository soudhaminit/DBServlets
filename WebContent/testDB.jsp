<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<sql:query var="rs" dataSource="jdbc/webshop">
select id, email, password from users1
</sql:query> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test DB</title>
</head>
<body>


  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    ${row.id}<br/>
    ${row.email}<br/>
    ${row.password}<br/>
</c:forEach>






</body>
</html>