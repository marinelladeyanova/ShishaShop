<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page errorPage="error.jsp" %>


	<jsp:include page="header.jsp"></jsp:include>



	<div class="men">
		<div class="container">
			<div class="col-md-9 single_top">

				<div class="single_left">

					<div class="labout span_1_of_a1">
						<div class="flexslider">
							<c:if test="${not empty product.photos}">

								<ul class="slides">

								<c:forEach items="${product.photos}" var="photo" varStatus="loop">
								<c:set var="summary" value="summary${index}" />

									<li data-thumb=${photo}>
										<img src=${photo} />
									</li>

								</c:forEach>
							</ul>
							</c:if>
						</div>
						<div class="clearfix"></div>
                    </div>

					<div class="cont1 span_2_of_a1 simpleCart_shelfItem">
						<h1>${product.name}</h1>
						<p class="availability">Availability:
                            <c:choose>
                            <c:when test="${product.quantity > 0}">
                            <span class="color">In stock</span></p>
                                </c:when>
                                <c:otherwise>
                                    <%--TODO--%>
                                    <span class="color">Out of stock</span></p>
                                </c:otherwise>
                            </c:choose>



                        <div class="price_single">

                            <c:choose>
                                <c:when test="${product.price == product.currentPrice}">
                                    <span class="amount item_price actual">$${product.currentPrice}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="reducedfrom">$${product.price}</span>
                                    <span class="amount item_price actual">$${product.currentPrice}</span>
                                </c:otherwise>
                            </c:choose>

						</div>

                        <h2 class="quick">Quick Overview:</h2>

						<p class="quick_desc">
							Category: ${product.description} <br>
							Quantity: ${product.quantity}	<br>

						<c:if test="${not empty product.characteristics}">
							<c:forEach items="${product.characteristics}" var="characteristic" varStatus="loop">
								 ${characteristic.name} : ${characteristic.value} <br>
							</c:forEach>
						</c:if>

						</p>
						<div class="wish-list">
							<ul>
								<li class="wish"><a href="#">Add to Wishlist</a></li>
							</ul>
						</div>


						<p>


						<div class="quantity_box">
							<ul class="product-qty">
								<span>Quantity:</span>
                                <input type="number">

							</ul>

							<div class="clearfix"></div>
						</div>
						<a href="#" class="btn btn-primary btn-normal btn-inline btn_form button item_add item_1" target="_self">Add to cart</a>
					</div>
					<div class="clearfix"> </div>
				</div>





			</div>



            <div class="col-md-3 tabs">
				<h3 class="m_1">Related Products</h3>

				<c:if test="${not empty related}">
					<c:forEach items="${related}" var="relatedProduct" varStatus="loop">

						<ul class="product">
							<li class="product_img"><img src="${relatedProduct.photoUrl}" class="img-responsive" alt=""/></li>
							<li class="product_desc">
								<h4><a href="/categories/${relatedProduct.category.name}/${relatedProduct.name}"> ${relatedProduct.name}</a></h4>
								<p class="single_price">$${relatedProduct.price}</p>
								<a href="#" class="link-cart">Add to Wishlist</a>
								<a href="#" class="link-cart">Add to Cart</a>
							</li>
							<div class="clearfix"> </div>
						</ul>
					</c:forEach>
				</c:if>

					<div class="clearfix"> </div>
				</ul>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>


    <!-- FlexSlider -->
    <script defer src="/js/jquery.flexslider.js"></script>
    <link rel="stylesheet" href="/css/flexslider.css" type="text/css" media="screen" />
    <script>
        // Can also be used with $(document).ready()
        $(window).load(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        });

    </script>

<jsp:include page="footer.jsp"></jsp:include>