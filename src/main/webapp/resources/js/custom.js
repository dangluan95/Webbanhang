$(document).ready(function(){
	var masanpham = 0;
	$("body").on("click",".paging-item",function(){
		$(".paging-item").removeClass("active");
		$(this).addClass("active");
		$("#checkall").prop("checked",false);
		var sotrang = $(this).text();
		var spbatdau = (sotrang - 1) * 5;
		$.ajax({
			url : "/webbanhang/api/laysanphamlimit",
			type : "GET",
			data :{
				sanPhamBatDau : spbatdau
			},
			success : function(value){
				var tbodysanpham = $("#table-sanpham").find("tbody");
				tbodysanpham.empty();
				tbodysanpham.append(value);
				
			}
		});
	});
	$("#xoa-sanpham").click(function(){
		$("#table-sanpham").find("input.checkboxsanpham:checked").each(function(){
			var masanpham = $(this).val();
			var thisme = $(this);
			thisme.closest("tr").remove();
			$.ajax({
				url : "/webbanhang/api/xoasanpham",
				type : "GET",
				data : {
					masanpham : masanpham,
				},
				success : function(value){
					if(value == "true"){
						thisme.closest("tr").remove();
					}
				}
			})
		});
	});
	$("#checkall").change(function(){
		if(this.checked){
			$("#table-sanpham").find("input[type='checkbox']").each(function(){
				$(this).prop('checked',true);
			});
		}else{
			$("#table-sanpham").find("input[type='checkbox']").each(function(){
				$(this).prop('checked',false);
			});
		}
	});
	var files = [];
	var tenhinh = "";
	$("#hinhsanpham").change(function(event){
		files = event.target.files;
		tenhinh = files[0].name;
		var forms = new FormData();
		forms.append("file",files[0]);
		$.ajax({
			url : "/webbanhang/api/uploadhinhsanpham",
			type : "POST",
			data : forms,
			contentType : false,
			processData : false,
			enctype: "multipart/form-data",
			success : function(value){
				
			}
		});
	});
	$("body").on("click",".btn-chitiet",function(){
		$(this).remove();
		var chitietclone = $("#themchitietsanpham").clone().removeAttr("id");
		$("#containerchitietsanpham").append(chitietclone);
	});
	$("#btn-themsanpham").click(function(event){
		event.preventDefault();
		var date = new Date();
		var ngaynhap = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
		var formdata = $("#form-sanpham").serializeArray();
		json = {};
		arrayChiTiet = [];
		objectDanhMuc = {};
		$.each(formdata,function(key,data){
			if(data.name == "danhmucsanpham"){
				objectDanhMuc["madanhmuc"] = data.value;
			}else{
				json[data.name] = data.value;
			}
		});
		$("#containerchitietsanpham > .themchitietsanpham").each(function(){
			objectChiTiet = {};
			objectMau = {};
			objectSize = {};
			var mamau = $(this).find("select[name='mau']").val();
			objectMau["mamau"] = mamau;
			var masize = $(this).find("select[name='size']").val();
			objectSize["masize"] = masize;
			var soluong = $(this).find("input[name='soluong']").val();
			objectChiTiet["mausanpham"] = objectMau;
			objectChiTiet["sizesanpham"] = objectSize;
			objectChiTiet["soluong"] = soluong;
			objectChiTiet["ngaynhap"] = ngaynhap;
			arrayChiTiet.push(objectChiTiet);
		});
		json["hinhsanpham"] = tenhinh;
		json["danhmucsanpham"] = objectDanhMuc;
		json["dschitietsanpham"] = arrayChiTiet;
		var dataSanPham = JSON.stringify(json);
		$.ajax({
			url : "/webbanhang/api/themsanpham",
			type : "POST",
			data : {
				dataSanPham : dataSanPham
			},
			success : function(){
				
			}
		});
	});
	$("#btn-capnhatsanpham").click(function(event){
		event.preventDefault();
		var date = new Date();
		var ngaynhap = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
		var formdata = $("#form-sanpham").serializeArray();
		json = {};
		arrayChiTiet = [];
		objectDanhMuc = {};
		$.each(formdata,function(key,data){
			if(data.name == "danhmucsanpham"){
				objectDanhMuc["madanhmuc"] = data.value;
			}else{
				json[data.name] = data.value;
			}
		});
		$("#containerchitietsanpham > .themchitietsanpham").each(function(){
			objectChiTiet = {};
			objectMau = {};
			objectSize = {};
			var mamau = $(this).find("select[name='mau']").val();
			objectMau["mamau"] = mamau;
			var masize = $(this).find("select[name='size']").val();
			objectSize["masize"] = masize;
			var soluong = $(this).find("input[name='soluong']").val();
			objectChiTiet["mausanpham"] = objectMau;
			objectChiTiet["sizesanpham"] = objectSize;
			objectChiTiet["soluong"] = soluong;
			objectChiTiet["ngaynhap"] = ngaynhap;
			arrayChiTiet.push(objectChiTiet);
		});
		json["masanpham"] = masanpham;
		json["hinhsanpham"] = tenhinh;
		json["danhmucsanpham"] = objectDanhMuc;
		json["dschitietsanpham"] = arrayChiTiet;
		var dataSanPham = JSON.stringify(json);
		$.ajax({
			url : "/webbanhang/api/capnhatsanpham",
			type : "POST",
			data : {
				dataSanPham : dataSanPham
			},
			success : function(value){
				alert(value);
			}
		});
	});
	$("body").on("click",".capnhatsanpham",function(){
		$("#btn-themsanpham").addClass("btn-hidden");
		$("#btn-capnhatsanpham").removeClass("btn-hidden");
		$("#btn-thoat").removeClass("btn-hidden");
		masanpham = $(this).closest("tr").find(".suasanpham").attr("data-masp");
		$.ajax({
			url : "/webbanhang/api/laythongtinsanphamtheoma",
			type : "POST",
			data : {
				maSanPham : masanpham,
			},
			success : function(value){
				$("#tensanpham").val(value.tensanpham);
				$("#giatien").val(value.giatien);
				$("#mota").val(value.mota);
				if(value.gianhcho == "nam"){
					$("#radnam").prop("checked",true);
				}else{
					$("#radnu").prop("checked",true);
				}
				
				$("#containerchitietsanpham").empty();
				$("#danhmucsanpham").val(value.danhmucsanpham.madanhmuc);
				var countchitiet = value.dschitietsanpham.length;
				for(var i = 0; i < countchitiet;i++){
					var chitietclone = $("#themchitietsanpham").clone().removeAttr("id");
					if(i < (countchitiet - 1)){
						chitietclone.find("button").remove();
					}
					chitietclone.find("#mau").val(value.dschitietsanpham[i].mausanpham.mamau);
					chitietclone.find("#size").val(value.dschitietsanpham[i].sizesanpham.masize);
					chitietclone.find("#soluong").val(value.dschitietsanpham[i].soluong);
					$("#containerchitietsanpham").append(chitietclone);
				}
			}
		});
	});
	$("#btn-thoat").click(function(){
		$("#btn-themsanpham").removeClass("btn-hidden");
		$("#btn-capnhatsanpham").addClass("btn-hidden");
		$(this).addClass("btn-hidden");
	});
	$("#cot1").click(function(){
		var x = $(this).attr("data-text");
		alert(x);
	});
	$("#btnDangNhap").click(function(){
		var email = $("#email").val();
		var matkhau = $("#matkhau").val();
		$.ajax({
			url : "/webbanhang/api/kiemtradangnhap",
			type : "GET",
			data : {
				email : email,
				matkhau : matkhau
			},
			success : function(value){
				if(value == "true"){
					var duongDanHienTai = window.location.href;
					var duongDan = duongDanHienTai.replace("/dangnhap","");
					window.location = duongDan;
				}else{
					$("#ketqua").text("Đăng nhập thất bại");
				}
			}
		})
	});
	$("#dangnhap").click(function(){
		$(this).addClass("actived");
		$("#dangky").removeClass("actived");
		$(".container-login-form").show();
		$(".container-signup-form").hide();
	});
	$("#dangky").click(function(){
		$(this).addClass("actived");
		$("#dangnhap").removeClass("actived");
		$(".container-login-form").hide();
		$(".container-signup-form").show();
	});
	$(".btn-giohang").click(function(){
		var machitiet = $(this).attr("data-machitiet");
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var tenmau = $(this).closest("tr").find(".mau").text();
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var tensize = $(this).closest("tr").find(".size").text();
		var tensp = $("#tensp").text();
		var sotien = $("#giatien").attr("data-giatien");
		var masp = $("#tensp").attr("data-masp");
		var soluong = $(this).closest("tr").find(".soluong").text();
		$.ajax({
			url : "/webbanhang/api/themgiohang",
			type : "GET",
			data : {
				machitiet : machitiet,
				masp : masp,
				masize : masize,
				mamau : mamau,
				tensp : tensp,
				tenmau : tenmau,
				tensize : tensize,
				soluong : soluong,
				sotien : sotien
			},
			success : function(value){
				if(value == "true"){
					swal("Success !","Sản phẩm đã được thêm vào giỏ hàng","success");
				}else{
					swal("Error !","Sản phẩm này hiện tại hết, vui lòng lựa chọn sản phẩm khác","error")
				}
			}
		}).done(function(){
			$.ajax({
				url : "/webbanhang/api/laysoluonggiohang",
				type : "GET",
				success : function(soluong){
					$("#giohang").find("div").addClass("circle-giohang");
					$(".circle-giohang").html("<span>"+soluong+"</span>");
				}
			})
		})
	});
	ganTongTienGioHang();
	function ganTongTienGioHang(isEventChange){
		var tongtiengiohang = 0;
		$(".giatien").each(function(){
			if(!isEventChange){
				var giaTien = $(this).closest("tr").find(".giatien").attr("data-giatien");
				var soLuong = $(this).closest("tr").find(".soluong-giohang").val();
				var giaTienFm = xuLyDinhDangGiaTien(giaTien);
				var tongTienSanPham = soLuong * parseFloat(giaTienFm);
				var formatCurrency = parseFloat(tongTienSanPham).toFixed(3).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
				$(this).html(formatCurrency);
			}
			var giaTien = $(this).text();
			var giaTienFm = xuLyDinhDangGiaTien(giaTien);
			tongtiengiohang = tongtiengiohang + parseFloat(giaTienFm);
			var tongtiengiohangFormat = tongtiengiohang.toFixed(3).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
			$("#tongtiengiohang").text(tongtiengiohangFormat + " VNĐ");
			
		});
	}
	$(".soluong-giohang").change(function(){
		var soluong = $(this).val();
		if(soluong <= 0){
			swal("Error !","Số lượng bạn thay đổi không hợp lệ, vui lòng thử lại","error");
			var giatien = $(this).closest("tr").find(".giatien").attr("data-giatien");
			$(this).closest("tr").find(".giatien").html(giatien);
			$(this).closest("tr").find(".soluong-giohang").val(1);
		}else{
			var giaTien = $(this).closest("tr").find(".giatien").attr("data-giatien");
			var giaTienFm = xuLyDinhDangGiaTien(giaTien);
			var tongTien = soluong * giaTienFm;
			var formatCurrency = tongTien.toFixed(3).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
			$(this).closest("tr").find(".giatien").text(formatCurrency);
			ganTongTienGioHang(true);
			var masp = $(this).closest("tr").find(".ten").attr("data-sanpham");
			var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
			var masize = $(this).closest("tr").find(".size").attr("data-masize");
			$.ajax({
				url : "/webbanhang/api/capnhatgiohang",
				type : "GET",
				data : {
					masp : masp,
					masize : masize,
					mamau : mamau,
					soluong : soluong
				},
				success : function(){
					$.ajax({
						url : "/webbanhang/api/laysoluonggiohang",
						type : "GET",
						success : function(value){
							$("#giohang").find("div").addClass("circle-giohang");
							$(".circle-giohang").html("<span>"+value+"</span>");
						}
					})
				}
			})
		}
	});
	function xuLyDinhDangGiaTien(giaTien){
		if(giaTien.indexOf(",")){
			giaTien = giaTien.replace(/,/g,"");
		}
		if(giaTien.indexOf(".")){
			giaTien = giaTien.replace(/\./g,"");
		}
		var strLength = giaTien.length;
		giaTien = giaTien.substring(0,strLength-3);
		return giaTien;
	}
	$(".btn-xoagiohang").click(function(){
		swal({
			  title: "Bạn có chắc chắn muốn xóa sản phẩm này?",
			  text: "Lưu ý, sản phẩm sẽ bị xóa vinh viễn trong giỏ hàng !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				var self = $(this);
				var masp = $(this).closest("tr").find(".ten").attr("data-sanpham");
				var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
				var masize = $(this).closest("tr").find(".size").attr("data-masize");
				$.ajax({
					url : "/webbanhang/api/xoagiohang",
					type : "GET",
					data : {
						masp : masp,
						masize : masize,
						mamau : mamau
					},
					success : function(){
						self.closest("tr").remove();
						ganTongTienGioHang(true);
					}
				}).done(function(){
					$.ajax({
						url : "/webbanhang/api/laysoluonggiohang",
						type : "GET",
						success : function(value){
							if(value > 0 ){
								$("#giohang").find("div").addClass("circle-giohang");
								$(".circle-giohang").html("<span>"+value+"</span>");
							}else{
								$("#giohang").find("div").removeClass("circle-giohang");
								$("#giohang").find("span").text("");
								$("#tongtiengiohang").text("0 VNĐ");
							}
						}
					});
				});
			    swal("Xong ! Xóa sản phẩm trong giỏ hàng thành công !", {
			      icon: "success",
			    });
			  }
		});
	});
})