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
        <h2>EDIT YOUR PROFILE !</h2>
        <div class="col-md-7 account-top">
        <%-- <div id="User">--%>
            <form:form commandName="user">
                <div>
                    <c:if test="${errorMessage != null }">
                        <span> <%=request.getAttribute("errorMessage") %> </span>
                    </c:if>
                </div>
                <div>
                    <span>First name </span>
                    <form:input path='firstName' type="text" placeholder="Enter username"/>
                </div>
                <div>
                    <span>Last name </span>
                    <form:input path='lastName' type="text" />
                </div>
                <div>
                    <span>Phone number <label>*</label></span>
                    <form:password path='phoneNumber' placeholder="Enter password"/>
                </div>


                    <input type="submit" value="submit">

            </form:form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>