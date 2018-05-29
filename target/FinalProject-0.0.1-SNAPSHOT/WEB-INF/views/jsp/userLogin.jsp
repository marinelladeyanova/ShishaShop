<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags" %>
<%@page errorPage="error.jsp" %>

<jsp:include page="header.jsp"></jsp:include>


<div class="account-in">
    <div class="container">
        <div class="col-md-7 account-top">
           <h2>LOG IN !</h2>
            <%--<div id="User">--%>


        <form:form commandName="user">
                <div>
                    <c:if test="${not empty errorMessage}">
                        <span> <%=request.getAttribute("errorMessage") %> </span>
                    </c:if>
                </div>

                    <div>
                    <span>Username<label>*</label></span>
                    <form:input path='username' type="text" placeholder="Enter username"/>
                    </div>

                    <div>
                        <span>Password<label>*</label></span>
                    <form:password path='password' placeholder="Enter password"/>
                    </div>

                <input type="submit" value="login" name = "login">

        </form:form>

            <br/>

                <h2>NO REGISTRATION ?</h2>
            <%--    <a href="userRegistration.jsp">
                    <input type="submit" value="register" />
                </a>
--%>
<!--
            <input type="submit" value="register" name = "register">
-->

            <a href="registration">
                <input type="submit" value="register" name = "register">
            </a>




            </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>