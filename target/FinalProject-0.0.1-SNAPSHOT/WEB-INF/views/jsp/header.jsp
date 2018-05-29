<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="/css/style.css" rel='stylesheet' type='text/css' />
	<link href='//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Dorsa' rel='stylesheet' type='text/css'>
	<link href="/css/component.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<!-- start menu -->
	<link href="/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="/js/megamenu.js"></script>
	<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
	<script src="/js/jquery.easydropdown.js"></script>
	<script src="/js/simpleCart.min.js"> </script>
	<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>

	<%-- productView --%>
	<script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
        });
	</script>
	<script src="js/simpleCart.min.js"> </script>
	<title>Demo page</title>
</head>

<body>

<div class="men_banner">
	<div class="container">
		<div class="header_top">
			<%--<div class="header_top_left">
				<div class="box_11"><a href="checkout.html">
					<h4><p>Cart:
						<span class="simpleCart_total"></span>
						(<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)
					</p>
						<img src="/images/bag.png" alt=""/><div class="clearfix"> </div></h4>
				</a></div>
				<p class="empty"><a href="/javascript:;" class="simpleCart_empty">Empty Cart</a></p>
				<div class="clearfix"> </div>
			</div>--%>
			<div class="header_top_right">

				<div class="lang_list">
				<%--	<select tabindex="4" class="dropdown">
						<option value="" class="label" value="">$ Us</option>
						<option value="1">Dollar</option>
						<option value="2">Euro</option>
					</select>--%>
				</div>
				

				<!-- start search-->
				<div class="search-box">
				<%--	<div id="sb-search" class="sb-search">
						<form>
							<input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search" id="search">
							<input class="sb-search-submit" type="submit" value="">
							<span class="sb-icon-search"> </span>
						</form>
					</div>--%>
				</div>
				<!----search-scripts---->
				<%--<script src="/js/classie1.js"></script>
				<script src="/js/uisearch.js"></script>
				<script>
                    new UISearch( document.getElementById( 'sb-search' ) );
				</script>--%>
				<!----//search-scripts---->
				<div class="clearfix"> </div>
                <ul class="header_user_info">
                    <ul class="header_user_info">
                        <a class="login" href="/login">
                            <i class="user"> </i>
                            <li class="user_desc">My Account</li>
                        </a>
                        <div class="clearfix"> </div>
                    </ul>
                </ul>

			</div>
			<div class="clearfix"> </div>
		</div>
		<div class="header_bottom">
			<div class="logo">
				<h1><a href="/index"><span class="m_1">H</span>ookahs</a></h1>
			</div>

			<jsp:include page='menu.jsp'/>
		<%--
			<jsp:include page="${menu}"/>

			--%>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
