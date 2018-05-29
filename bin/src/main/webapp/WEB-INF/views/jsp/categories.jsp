	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%@ page errorPage="error.jsp" %>

	<jsp:include page="header.jsp"></jsp:include>


	<div class="men">
		<div class="container">
			<div class="col-md-4 sidebar_men">

					<%--
					Subcategores
					--%>

					<c:if test="${not empty category.subcategories}">
						<h3>Categories</h3>
						<ul class="product-categories color">
							<c:forEach items="${category.subcategories}" var="subcategory" varStatus="loop">
							<li class="cat-item cat-item-60">
								<a href="/categories/${category.name}?sub=${subcategory.name}">
									${subcategory.name}
								</a>
							</li>
							</c:forEach>
						</ul>
					</c:if>

					<%--
					Characteristics
					--%>

					<c:if test="${not empty characteristics}">
						<c:forEach items="${characteristics}" var="characteristic" varStatus="loop">
							<h3>${characteristic.name}</h3>
								<ul class="product-categories color">
									<c:if test="${not empty characteristic.values}">
										<c:forEach items="${characteristic.values}" var = "value" varStatus="loop">
											<li class="cat-item cat-item-42">
												<a href="/categories/${category.name}?characteristic=${value.id}">
                                                    ${value.value}
												</a>
											<%--	<span class="count">()</span>--%>
											</li>
										</c:forEach>
									</c:if>
								</ul>
						</c:forEach>
					</c:if>



            </div>


			<div class="col-md-8 mens_right" >

				<div class="dreamcrub">


					<ul class="breadcrumbs">
						<li class="home">
							<a href="index" title="Home Page">Home</a>&nbsp;
							<span>&gt;</span>
						</li>
						<li class="home">&nbsp;
							${category.name}&nbsp;
						</li>
					</ul>
                   <ul class="previous">
						<li><a href="index.html">Back to Previous Page</a></li>
					</ul>
					<div class="clearfix"></div>
                </div>


                <div class="mens-toolbar" id ="sortSelect">
					<div class="sort">
						<div class="sort-by">
							<label>Sort By</label>
							<select>
								<option>
									-
								</option>
                                <option value="${requestScope['javax.servlet.forward.request_uri']}?sort=priceDESC">
                                    Price DESC
                                </option>
								<%--"/categories/"${requestScope['javax.servlet.forward.request_uri']}?sort=priceASC"--%>
                                <option value="${requestScope['javax.servlet.forward.request_uri']}?sort=priceASC">
									Price ASC
                                </option>
							</select>
							<a href=""><img src="/images/arrow2.gif" alt="" class="v-middle"></a>
						</div>
					</div>
					<ul class="women_pagenation dc_paginationA dc_paginationA06">
						<li><a href="#" class="previous">Page : </a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
					</ul>
					<div class="clearfix"></div>
				</div>



                <div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-grid">
					<div class="cbp-vm-options">
						<a href="#" class="cbp-vm-icon cbp-vm-grid cbp-vm-selected" data-view="cbp-vm-view-grid" title="grid">Grid View</a>
						<a href="#" class="cbp-vm-icon cbp-vm-list" data-view="cbp-vm-view-list" title="list">List View</a>
					</div>
					<div class="pages">
						<div class="limiter visible-desktop">
							<label>Show</label>
							<select>
								<option value="" selected="selected">
									9                </option>
								<option value="">
									15                </option>
								<option value="">
									30                </option>
							</select> per page
						</div>
					</div>
					<div class="clearfix"></div>


					<%--
					Products
					--%>

                    <ul>
						<c:if test="${not empty products}">
							<c:forEach items="${products}" var="product" varStatus="loop">
								<c:choose>
									<c:when test="${loop.index % 3 == 0}">
										<li class="last simpleCart_shelfItem">
									</c:when>
									<c:otherwise>
										<li class="simpleCart_shelfItem">
									</c:otherwise>
								</c:choose>
									<a class="cbp-vm-image" href="/categories/${category.name}/${product.name}">
										<div class="view view-first">
											<div class="inner_content clearfix">
												<div class="product_image">
													<div class="mask1"><img src=${product.photoUrl} alt="image" class="img-responsive zoom-img"></div>
													<div class="mask">
														<div class="info">Quick View</div>
													</div>
													<div class="product_container">
														<h4>${product.name}</h4>
                                                        <p>${product.category.name} </p>
														<div class="price mount item_price">$${product.currentPrice}</div>
														<a class="button item_add cbp-vm-icon cbp-vm-add" href="#">Add to cart</a>
													</div>
												</div>
											</div>
										</div>
									</a>
								</li>

							</c:forEach>
						</c:if>



					</ul>
				</div>

                <script src="/js/cbpViewModeSwitch.js" type="text/javascript"></script>
				<script src="/js/classie.js" type="text/javascript"></script>
			</div>
		</div>
	</div>



    <script>
    $("select").click(function() {
    var open = $(this).data("isopen");
    if(open) {
    window.location.href = $(this).val()
    }
    //set isopen to opposite so next time when use clicked select box
    //it wont trigger this event
    $(this).data("isopen", !open);
    });
    </script>



	<jsp:include page="footer.jsp"></jsp:include>