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
        <div class="check_box">
            <div class="col-md-9 cart-items">

                <h1>My Shopping Bag (2)</h1>

                <c:if test="${not empty cart}">
                <c:forEach items="${cart.products}" var="product" varStatus="loop">

                <script>$(document).ready(function(c) {
                    $('.close1').on('click', function(c){
                        $('.cart-header').fadeOut('slow', function(c){
                            $('.cart-header').remove();
                        });
                    });
                });

                </script>
                <div class="cart-header">
                    <div class="close1"> </div>
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="${product.photoUrl}" class="img-responsive" alt=""/>
                        </div>
                        <div class="cart-item-info">
                            <h3><a href="#">${product.name}</a></h3>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                </c:forEach>
                </c:if>
            </div>

            <div class="col-md-3 cart-total">
               <%-- <a class="continue" href="#">Continue to basket</a>--%>

                <div class="price-details">
                    <h3>Price Details</h3>
                    <span>Total</span>
                    <span class="total1">${cart.totalPrice}</span>
                    <span>Discount</span>
                    <span class="total1">---</span>
                    <span>Delivery Charges</span>
                    <span class="total1">---/span>
                    <div class="clearfix"></div>
                </div>
                <ul class="total_price">
                    <li class="last_price"> <h4>TOTAL</h4></li>
                    <li class="last_price"><span>${cart.totalPrice}</span></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="clearfix"></div>
                <a class="order" href="#">Place Order</a>
                <div class="total-item">
                    <h3>OPTIONS</h3>
                    <h4>COUPONS</h4>
                    <a class="cpns" href="#">Apply Coupons</a>
                    <p><a href="#">Log In</a> to use accounts - linked coupons</p>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>




</body>
</html>