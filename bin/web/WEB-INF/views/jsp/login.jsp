<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<h1> Sign in </h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <tags:argument value=error ></tags:message><br />
        </div>
    </c:if>



    <form:form method="post">
    <table>
        <tr>
            <td>User Name :</td>
            <td><form:input path='username' type="text" placeholder="Enter username"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:password path='password' type="password" placeholder="Enter password"/></td>
        </tr>
        <tr>
            <td>Email :</td>
            <td> <form:input path='email' type="text" placeholder="Enter email"/></td>
        </tr>
        <tr>
            <td colspan="2"><button>register</button></td>
        </tr>
    </table>
	</form:form>

	
</body>
</html>