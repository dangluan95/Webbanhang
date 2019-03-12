<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="header.jsp"/>
<title>Insert title here</title>
</head>
<body id="body-login">

	<div id="body-flex-login">
		<div id="container-login">
			<div id="container-login-left">
				<div id="header-login-left" class="header-login">
					<span id="text-logo">Welecome</span><br>
					<span id="hint-text-logo">Hãy tạo nên phong cách của bạn cùng Minishop !</span>
				</div>
				<div id="footer-login-left">
					<p><img alt="icon_oval" src="<c:url value="/resources/images/icon_oval.png"></c:url>"/><span>Luôn cập nhật xu hướng thời trang mới nhất</span></p>
					<p><img alt="icon_oval" src="<c:url value="/resources/images/icon_oval.png"></c:url>"/><span>Giảm hơn 50% các mặt hàng dành cho khác VIP</span></p>
					<p><img alt="icon_oval" src="<c:url value="/resources/images/icon_oval.png"></c:url>"/><span>Tận tình tư vấn để tạo nên phong cách của bạn</span></p>
				</div>
			</div>
			<div id="container-login-right">
				<c:choose>
					<c:when test="${ketquadangky == null}">
						<div id="header-login-right" class="header-login">
							<span class="actived" id="dangnhap">Đăng nhập</span> / <span id="dangky">Đăng ký</span>
						</div>
						<div id="body-login-right">
							<div id="body-login-right" class="container-login-form">
								<input id="email" class="material-textinput input-icon-email" name="email" type="text" placeholder="Email"/><br><br>
								<input id="matkhau" class="material-textinput input-icon-password" name="matkhau" type="password" placeholder="Mật khẩu"><br>
								<input id="btnDangNhap" class="material-primary-button" type="submit" value="ĐĂNG NHẬP">
								<p id="ketqua" style="color: red"></p>
							</div>
							<div id="body-login-right" class="container-signup-form">
								<form action="" method="POST">
									<input id="email" class="material-textinput input-icon-email" name="email" type="text" placeholder="Email"/><br><br>
									<input id="matkhau" class="material-textinput input-icon-password" name="matkhau" type="password" placeholder="Mật khẩu"><br><br>
									<input id="nhaplaimatkhau" class="material-textinput input-icon-password" name="nhaplaimatkhau" type="password" placeholder="Nhập lại mật khẩu"><br>						
									<input id="btnDangNhap" class="material-primary-button" type="submit" value="ĐĂNG KÝ">
								</form>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div id="header-login-right" class="header-login">
							<span id="dangnhap">Đăng nhập</span> / <span class="actived" id="dangky">Đăng ký</span>
						</div>
						<div id="body-login-right">
							<div id="body-login-right" class="container-login-form" style="display: none;">
								<input id="email" class="material-textinput input-icon-email" name="email" type="text" placeholder="Email"/><br><br>
								<input id="matkhau" class="material-textinput input-icon-password" name="matkhau" type="password" placeholder="Mật khẩu"><br>
								<input id="btnDangNhap" class="material-primary-button" type="submit" value="ĐĂNG NHẬP">
								<p id="ketqua" style="color: red"></p>
							</div>
							<div id="body-login-right" class="container-signup-form" style="display: block;">
								<form action="" method="POST">
									<input id="email" class="material-textinput input-icon-email" name="email" type="text" placeholder="Email"/><br><br>
									<input id="matkhau" class="material-textinput input-icon-password" name="matkhau" type="password" placeholder="Mật khẩu"><br><br>
									<input id="nhaplaimatkhau" class="material-textinput input-icon-password" name="nhaplaimatkhau" type="password" placeholder="Nhập lại mật khẩu"><br>						
									<input id="btnDangNhap" class="material-primary-button" type="submit" value="ĐĂNG KÝ">
								</form>
								<p style="color: red">${ketquadangky}</p>
							</div>
						</div>						
					</c:otherwise>
				</c:choose>
				<div id="footer-login-right">
					<img alt="icon_facebook" src='<c:url value="/resources/images/icon_facebook.png"></c:url>'>
					<img alt="icon_google" src='<c:url value="/resources/images/icon_google.png"></c:url>'/>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"/>
</body>
</html>