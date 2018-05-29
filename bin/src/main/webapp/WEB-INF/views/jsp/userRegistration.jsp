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
        <h2>REGISTEER NOW !</h2>
        <div class="col-md-7 account-top">
            <%--<form:form commandName="newUser">
--%>
            <form:form id="regForm" modelAttribute="newUser" commandName="newUser" method="post">

                <div>
                    <c:if test="${not empty errorMessage}">
                        <span> ${errorMessage.toString()} </span>
                    </c:if>
                </div>

                <div>
                    <span>Username<label>*</label></span>
                    <form:input path='username' type="text" placeholder="Enter username"/>
                </div>

                <div>
                    <span>Email Address<label>*</label></span>
                    <form:input path='email' type="text" placeholder="Enter email"/>
                </div><!--
                <div class="clearfix"> </div>-->

                <div>
                    <span>Password<label>*</label></span>
                    <form:password path='password' placeholder="Enter password"/>
                </div>
                <!--
                <div class="clearfix"> </div>-->
                <div>
                    <span>Confirm Password<label>*</label></span>
                    <form:password path='confirmPassword' placeholder="Confirm password"/>
                </div>
                <!--
                <div class="clearfix"> </div>-->

                    <input type="submit" value="register" name = "register">
        </form:form>
    </div>
</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>