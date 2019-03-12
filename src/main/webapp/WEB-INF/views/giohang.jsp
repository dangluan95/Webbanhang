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
			        <li id="giohang"><a href="#"><img src="<c:url value='/resources/images/ic_shopping_cart_white.png'/>"/>
				        <c:choose>
				        	<c:when test="${soluongsanpham != null && soluongsanpham > 0}">
				        		<div class="circle-giohang"><span>${soluongsanpham}</span></span></div>
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
		<div class="row">
			<div class="col-sm-12 col-md-7">
				<h3>Danh Sách Sản Phẩm</h3>
				<table class="table">
					<thead>
						<tr>
							<td>Tên sản phẩm</td>
							<td>Màu</td>
							<td>Size</td>
							<td>Số lượng</td>
							<td>Giá tiền</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sanpham" items="${giohang}">
							<tr data-machitiet="${sanpham.getMachitiet()}">
								<td class="ten" data-sanpham="${sanpham.getMasp()}">${sanpham.getTensp()}</td>
								<td class="mau" data-mamau="${sanpham.getMamau()}">${sanpham.getTenmau()}</td>
								<td class="size" data-masize="${sanpham.getMasize()}">${sanpham.getTensize()}</td>
								<td class="soluong"><input min="1" class="soluong-giohang" type="number" value="${sanpham.getSoluong()}"/></td>
								<td class="giatien" data-giatien="${sanpham.getSotien()}">${sanpham.getSotien()}</td>
								<td class="btn btn-danger btn-xoagiohang">Xóa</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<h4>Tổng tiền : <span style="color: red;" id="tongtiengiohang">0 VNĐ</span></h4>
			</div>
			<div class="col-sm-12 col-md-5">
				<h3>Thông tin người nhận/mua</h3>
				<form action="" method="POST">
					<div class="form-group">
						<label for="tenkhachhang">Tên người mua/nhận : </label>
						<input name="tenkhachhang" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="sodt">Điện thoại liên lạc : </label>
						<input name="sodt" type="text" class="form-control">
					</div>
					<div class="radio">
						<label><input type="radio" name="hinhthucgiaohang" checked value="Giao hàng tận nơi">Giao hàng tận nơi</label>
					</div>
					<div class="radio">
					 	<label><input type="radio" name="hinhthucgiaohang" value="Nhận hàng tại cửa hàng">Nhận hàng tại cửa hàng</label>
					</div>
					<div class="form-group">
						<label for="diachigiaohang">Địa chỉ : </label>
						<input name="diachigiaohang" type="text" class="form-control">
					</div>
					<div class="form-group">
					  <label for="ghichu">Ghi chú:</label>
					  <textarea class="form-control" rows="5" name="ghichu"></textarea>
					</div>
					<div>
						<input type="submit" value="Đặt hàng" class="btn btn-primary">
					</div>
				</form>
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