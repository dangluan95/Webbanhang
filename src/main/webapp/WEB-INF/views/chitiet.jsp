<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="header.jsp"/>
<title>Trang Chủ</title>
</head>
<body>
	<!-- Start Header -->
	<div id="header-chitiet" class="container-fluid">
		<!-- Start Menu -->
		<div>
			<nav class="navbar navbar-inverse" id="non-navbar">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>                        
			      </button>
			      <a class="navbar-brand" href="#"><img src="<c:url value='/resources/images/icon_yame_shop.png'/>"/></a>
			    </div>
			    <div class="collapse navbar-collapse" id="myNavbar">
			      <ul class="nav navbar-nav navbar-center">
			        <li class="active"><a href="/webbanhang">TRANG CHỦ</a></li>
			      	<li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="#">THỜI TRANG<span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="#">ÁO NAM</a></li>
			            <li><a href="#">QUẦN NAM</a></li>
			          </ul>
			        </li>
			        <li><a href="#">DỊCH VỤ</a></li>
			        <li><a href="#">LIÊN HỆ</a></li>
			      </ul>
			      <ul class="nav navbar-nav navbar-right">
			      <li>
						<c:choose>
							<c:when test="${chucaidau != null}">
					        	<a class="circle-avatar" href="#"><span>${chucaidau}</span></a>
					        </c:when>
					        <c:otherwise>
					        	<a href="/webbanhang/dangnhap"><span class="glyphicon glyphicon-user"></span> ĐĂNG NHẬP</a>
					        </c:otherwise>
				        </c:choose>
			        </li>
			        <li id="giohang"><a href="/webbanhang/giohang"><img src="<c:url value='/resources/images/ic_shopping_cart_white.png'/>"/>
				        <c:choose>
				        	<c:when test="${soluongsanpham != null && soluongsanpham > 0}">
				        		<div class="circle-giohang"><span>${soluongsanpham}</span></div>
				        	</c:when>
				        	<c:otherwise>
				        		<div></div>		        		
				        	</c:otherwise>
				        </c:choose>
			        </a></li>
			      </ul>
			    </div>
			  </div>
			</nav>
		</div>
		<!-- End Menu -->
	</div>
	<!-- End Header -->
	<!-- Start Body -->
	<div class="container">
		<div class="row" style="margin-top: 15px;">
			<div class="col-sm-2 col-md-2">
				<h3>Danh mục sản phẩm</h3>
				<ul class="parent-menu">
					<c:forEach items="${dmSanPham}" var="danhmuc">
						<li><a href="#">${danhmuc.getTendanhmuc()}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-8 col-md-8">
				<div class="row">
					<div class="col-sm-4 col-md-4">
						<img style="max-width: 100%" alt="hinh-san-pham" src='<c:url value="/resources/images/sanpham/${sanPham.getHinhsanpham()}"/>'>
					</div>
					<div class="col-sm-8 col-md-8">
						<h3 id="tensp" data-masp="${sanPham.getMasanpham()}">${sanPham.getTensanpham()}</h3>
						<h4 class="gia" data-giatien="${sanPham.getGiatien()}" id="giatien">${sanPham.getGiatien()} VNĐ</h4>
						<table class="table">
							<thead>
								<tr>
									<td><h5>Màu sản phẩm</h5></td>
									<td><h5>Size</h5></td>
									<td><h5>Số lượng</h5></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="chiTietSanPham" items="${sanPham.getDschitietsanpham()}">
									<tr>
										<td class="mau" data-mamau="${chiTietSanPham.getMausanpham().getMamau()}">${chiTietSanPham.getMausanpham().getTenmau()}</td>
										<td class="size" data-masize="${chiTietSanPham.getSizesanpham().getMasize()}">${chiTietSanPham.getSizesanpham().getSize()}</td>
										<td class="soluong">${chiTietSanPham.getSoluong()}</td>
										<td><button data-machitiet="${chiTietSanPham.getMachitietsanpham()}" class="btn btn-success btn-giohang">Giỏ hàng</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<span id="thongbaothemgiohang"></span>
					</div>
				</div>
			</div>
			<div class="col-sm-2 col-md-2">
				<h3>Mô tả sản phẩm</h3>
				<span>
					${sanPham.getMota()}
				</span>
			</div>
		</div>
	</div>
	<!-- End Body -->
	<!-- Start Footer -->
	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-sm-4 col-md-4 wow tada">
				<span class="title-footer">THÔNG TIN SHOP</span><br>
				<span>YameShop là một thương hiệu nổi tiếng với chất lượng hoàn hảo</span>
			</div>
			<div class="col-sm-4 col-md-4 wow tada">
				<span class="title-footer">LIÊN HỆ</span><br>
				<span>Nguyễn Trãi - Thanh Xuân - Hà Nội</span>
				<span>Email : dangducluan@gmail.com</span>
			</div>
			<div class="col-sm-4 col-md-4 wow tada">
				<span class="title-footer">GÓP Ý</span><br>
				<form action="" method="POST">
					<input type="text" name="ten" placeholder="Email" class="material-textinput" style="margin-bottom: 8px;color: black;"/>
					<textarea rows="4" cols="50" id="footer-textarea" placeholder="Nội dung" name="tuoi">
					</textarea>
					<button class="material-primary-button">Đồng ý</button>
				</form>
			</div>
		</div>
	</div>
	<!-- End Footer -->
<jsp:include page="footer.jsp"/>
</body>
</html>