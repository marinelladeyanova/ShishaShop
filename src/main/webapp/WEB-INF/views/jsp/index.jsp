
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page session="false"%>--%>
<%@ page errorPage="error.jsp" %>


<jsp:include page="header.jsp"></jsp:include>



<div class="container">
	<ul class="content-home">

		<c:if test="${not empty categories}">
				<c:forEach items="${categories}" var="category" varStatus="loop">

					<li class="col-sm-4">
						<a href="/categories/${category.name}" class="item-link" title="">
							<div class="bannerBox">
								<img src="/uploaded/16.jpg" class="item-img" title="" alt="" width="100%" height="100%">
								<div class="item-html">
									<h3> ${category.name}</h3>
									<button>Shop now!</button>
								</div>
							</div>
						</a>
					</li>


				</c:forEach>
			</ul>
		</c:if>

		<div class="clearfix"> </div>
	</ul>
</div>


<div class="middle_content">
	<div class="container">
	</div>
</div>

<div class="middle_content">
	<div class="container">
		<h2>Welcome</h2>
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>