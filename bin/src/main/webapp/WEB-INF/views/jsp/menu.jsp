<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="error.jsp" %>

<div class="menu">
	<ul class="megamenu skyblue">

		<c:forEach items="${categories}" var="category" varStatus="loop">
<%--			<li><a href="./patki/${loop.index}">${patka.name}</a> peche se na ${patka.gradusi} gradusa!</li>--%>
			<li><a class="color2" href="/categories/${category.name}">${category.name}</a>
				<div class="megapanel">
					<%--<div class="row">--%>
							<%--<div class="col1">--%>
                                <div class="h_nav">
								<ul>
									<c:if test="${not empty category.subcategories}">
										<c:forEach items="${category.subcategories}" var="subcategory" varStatus="loop">
											<li>
												<a href="/categories/${category.name}?sub=${subcategory.name}">
														${subcategory.name}
												</a>
											</li>
											<%--<hr  size="1">--%>
										</c:forEach>
									</c:if>
								</ul>
							</div>
									<%--	</div>
                                        <div class="col2">--%>
						<%--	<div class="h_nav">
								<h4>New products</h4>
								<ul>
									<li class>
										<div class="p_left">
											<img src="/images/p1.jpg" class="img-responsive" alt=""/>
										</div>
										<div class="p_right">
											<h4><a href="#">Denim shirt</a></h4>
											<span class="item-cat"><small><a href="#">watches</a></small></span>
											<span class="price">29.99 $</span>
										</div>
										<div class="clearfix"> </div>
									</li>
									<li>
										<div class="p_left">
											<img src="/images/p2.jpg" class="img-responsive" alt=""/>
										</div>
										<div class="p_right">
											<h4><a href="#">Denim shirt</a></h4>
											<span class="item-cat"><small><a href="#">watches</a></small></span>
											<span class="price">29.99 $</span>
										</div>
										<div class="clearfix"> </div>
									</li>
									<li>
										<div class="p_left">
											<img src="/images/p3.jpg" class="img-responsive" alt=""/>
										</div>
										<div class="p_right">
											<h4><a href="#">Denim shirt</a></h4>
											<span class="item-cat"><small><a href="#">watches</a></small></span>
											<span class="price">29.99 $</span>
										</div>
										<div class="clearfix"> </div>
									</li>
								</ul>
							</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>
			</li>


		</c:forEach>


		<%--
		<li><a class="color4" href="#">womens</a>
			<div class="megapanel">
				<div class="row">
					<div class="col1">
						<div class="h_nav">
							<h4>Men</h4>
							<ul>
								<li><a href="men.html">Watches</a></li>
								<li><a href="men.html">watches</a></li>
								<li><a href="men.html">Blazers</a></li>
								<li><a href="men.html">Suits</a></li>
								<li><a href="men.html">Trousers</a></li>
								<li><a href="men.html">Jeans</a></li>
								<li><a href="men.html">Shirts</a></li>
								<li><a href="men.html">Sweatshirts & Hoodies</a></li>
								<li><a href="men.html">Swim Wear</a></li>
								<li><a href="men.html">Accessories</a></li>
							</ul>
						</div>
					</div>
					<div class="col1">
						<div class="h_nav">
							<h4>Women</h4>
							<ul>
								<li><a href="men.html">Watches</a></li>
								<li><a href="men.html">Outerwear</a></li>
								<li><a href="men.html">Dresses</a></li>
								<li><a href="men.html">Handbags</a></li>
								<li><a href="men.html">Trousers</a></li>
								<li><a href="men.html">Jeans</a></li>
								<li><a href="men.html">T-Shirts</a></li>
								<li><a href="men.html">Shoes</a></li>
								<li><a href="men.html">Coats</a></li>
								<li><a href="men.html">Accessories</a></li>
							</ul>
						</div>
					</div>
					<div class="col2">
						<div class="h_nav">
							<h4>Trends</h4>
							<ul>
								<li class>
									<div class="p_left">
										<img src="/images/p1.jpg" class="img-responsive" alt=""/>
									</div>
									<div class="p_right">
										<h4><a href="#">Denim shirt</a></h4>
										<span class="item-cat"><small><a href="#">watches</a></small></span>
										<span class="price">29.99 $</span>
									</div>
									<div class="clearfix"> </div>
								</li>
								<li>
									<div class="p_left">
										<img src="/images/p2.jpg" class="img-responsive" alt=""/>
									</div>
									<div class="p_right">
										<h4><a href="#">Denim shirt</a></h4>
										<span class="item-cat"><small><a href="#">watches</a></small></span>
										<span class="price">29.99 $</span>
									</div>
									<div class="clearfix"> </div>
								</li>
								<li>
									<div class="p_left">
										<img src="/images/p3.jpg" class="img-responsive" alt=""/>
									</div>
									<div class="p_right">
										<h4><a href="#">Denim shirt</a></h4>
										<span class="item-cat"><small><a href="#">watches</a></small></span>
										<span class="price">29.99 $</span>
									</div>
									<div class="clearfix"> </div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</li>
		<li><a class="color10" href="brands.html">Brands</a></li>
		<li><a class="color3" href="index.html">Sale</a></li>
		<li><a class="color7" href="404.html">News</a></li>--%>
		<div class="clearfix"> </div>
	</ul>
</div>
