package com.dangducluan.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dangducluan.entity.ChiTietSanPham;
import com.dangducluan.entity.DanhMucSanPham;
import com.dangducluan.entity.GioHang;
import com.dangducluan.entity.JSonSanPham;
import com.dangducluan.entity.MauSanPham;
import com.dangducluan.entity.SanPham;
import com.dangducluan.entity.SizeSanPham;
import com.dangducluan.service.NhanVienService;
import com.dangducluan.service.SanPhamService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(path="api/")
@SessionAttributes(names= {"email"})
public class ApiController {
	
	private List<GioHang> gioHangs = new ArrayList<GioHang>();
	private int demSoLuongHang = 0;
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	NhanVienService nhanVienService;
	@Autowired
	ServletContext servletContext;
	
	@GetMapping(path="kiemtradangnhap")	
	@ResponseBody
	public String kiemTraDangNhap(@RequestParam String email,@RequestParam String matkhau,ModelMap modelMap) {
		boolean kiemtra = nhanVienService.kiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("email",email);
		return String.valueOf(kiemtra);
	}
	@GetMapping(path="themgiohang")
	@ResponseBody
	public String themGioHang(@ModelAttribute GioHang gioHang, HttpSession httpSession) {
		if(gioHangs.size() == 0) {
			gioHang.setSoluong(1);
			gioHangs.add(gioHang);
		}else {
			int viTriSanPham = timViTriSanPhamDaThem(gioHangs, gioHang);
			if(viTriSanPham == -1) {
				gioHang.setSoluong(1);
				gioHangs.add(gioHang);
			}else {
				GioHang item = gioHangs.get(viTriSanPham);
				int soLuongHienTai = item.getSoluong();
				if(soLuongHienTai >= gioHang.getSoluong()) {
					return String.valueOf(false);
				}
				int soLuongMoi = soLuongHienTai + 1;
				item.setSoluong(soLuongMoi);
			}
		}
		httpSession.setAttribute("giohang",gioHangs);
		httpSession.setAttribute("soluongsanpham", demSoLuongHang(gioHangs));
		return String.valueOf(true);
	}
	@GetMapping(path="capnhatgiohang")
	@ResponseBody
	public void capNhatGioHang(HttpSession httpSession,@ModelAttribute GioHang gioHang) {
		if(httpSession.getAttribute("giohang") != null) {
			int vitri = timViTriSanPhamDaThem(gioHangs, gioHang);
			GioHang item = gioHangs.get(vitri);
			item.setSoluong(gioHang.getSoluong());
			httpSession.setAttribute("soluongsanpham", demSoLuongHang(gioHangs));
		}
	}
	@GetMapping(path="xoagiohang")
	@ResponseBody
	public void xoaGioHang(HttpSession httpSession,@ModelAttribute GioHang gioHang) {
		if(httpSession.getAttribute("giohang") != null) {
			int vitri = timViTriSanPhamDaThem(gioHangs, gioHang);
			gioHangs.remove(vitri);
			httpSession.setAttribute("soluongsanpham", demSoLuongHang(gioHangs));
		}
	}
	public int timViTriSanPhamDaThem(List<GioHang> gioHangs,GioHang gioHang) {
		for(GioHang item : gioHangs) {
			if(item.equals(gioHang)) {
				return gioHangs.indexOf(item);
			}
		}
		return -1;
	}
	@GetMapping(path="laysoluonggiohang")
	@ResponseBody
	public String laySoLuongGioHang() {
		int soLuong = demSoLuongHang(gioHangs);
		if(soLuong != 0) {
			return String.valueOf(soLuong);
		}
		return "";
	}
	public int demSoLuongHang(List<GioHang> gioHangs) {
		int tongSoLuong = 0;
		for(GioHang gioHang:gioHangs) {
			tongSoLuong += gioHang.getSoluong();
		}
		return tongSoLuong;
	}
	@GetMapping(path="laysanphamlimit",produces="text/html;charset=utf-8")
	@ResponseBody
	public String laySanPhamLimit(@RequestParam int sanPhamBatDau) {
		String html = "";
		List<SanPham> danhSachSanPham = sanPhamService.layDanhSachSanPhamLimit(sanPhamBatDau);
		for(SanPham sp : danhSachSanPham) {
			html+="<tr>";
			html+="<td><div class='checkbox'><label><input class='checkboxsanpham' type='checkbox' value='"+sp.getMasanpham()+"'></label></div></td>";
			html+="<td class='tensanpham'>"+sp.getTensanpham()+"</td>";
			html+="<td class='giatien'"+">"+sp.getGiatien()+"</td>";
			html+="<td class='gianhcho'"+">"+sp.getGianhcho()+"</td>";
			html+="<td class='suasanpham' data-masp='"+sp.getMasanpham()+"'><button class='capnhatsanpham btn btn-danger'>Sửa</button></td>";
			html+="</tr>";
		}
		return html;
	}
	@GetMapping(path="xoasanpham",produces="text/html;charset=utf-8")
	@ResponseBody
	public String xoaSanPhamTheoMaSanPham(@RequestParam int masanpham) {
		boolean kiemTraXoaSanPham = sanPhamService.xoaSanPhamTheoMaSanPham(masanpham);
		if(kiemTraXoaSanPham) {
			return "true";
		}
		return "false";
	}
	@PostMapping(path="uploadhinhsanpham")
	@ResponseBody
	public String uploadHinhSanPham(MultipartHttpServletRequest multipartHttpServletRequest) {
		Iterator<String> danhSachTen = multipartHttpServletRequest.getFileNames(); // file name
		MultipartFile multipartFile = multipartHttpServletRequest.getFile(danhSachTen.next());
		String pathSaveFile = servletContext.getRealPath("/resources/images/sanpham/");
		File file = new File(pathSaveFile+multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping(path="themsanpham")
	@ResponseBody
	public String themSanPham(@RequestParam String dataSanPham) {
		System.out.println(dataSanPham);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			SanPham sanPham = objectMapper.readValue(dataSanPham,SanPham.class);
			boolean kiemTraThemSanPham = sanPhamService.themSanPhamMoi(sanPham);
			if(kiemTraThemSanPham) {
				System.out.println("Them san pham thanh cong");
			}else {
				System.out.println("Them san pham khong thanh cong");
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping(path="laythongtinsanphamtheoma",produces="application/json;charset=utf-8")
	@ResponseBody
	public JSonSanPham layDanhSachSanPhamTheoMa(@RequestParam int maSanPham){
		SanPham sanPham = sanPhamService.layThongTinSanPhamTheoMa(maSanPham);
		JSonSanPham jSonSanPham = new JSonSanPham();
		jSonSanPham.setMasanpham(sanPham.getMasanpham());
		jSonSanPham.setTensanpham(sanPham.getTensanpham());
		jSonSanPham.setGiatien(sanPham.getGiatien());
		jSonSanPham.setMota(sanPham.getMota());
		jSonSanPham.setHinhsanpham(sanPham.getHinhsanpham());
		jSonSanPham.setGianhcho(sanPham.getGianhcho());
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sanPham.getDanhmucsanpham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sanPham.getDanhmucsanpham().getTendanhmuc());
		jSonSanPham.setDanhmucsanpham(danhMucSanPham);
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for(ChiTietSanPham chiTietSanPham : sanPham.getDschitietsanpham()) {
			ChiTietSanPham chiTietSanPham2 = new ChiTietSanPham();
			chiTietSanPham2.setMachitietsanpham(chiTietSanPham.getMachitietsanpham());
			MauSanPham mausanpham = new MauSanPham();
			mausanpham.setMamau(chiTietSanPham.getMausanpham().getMamau());
			mausanpham.setTenmau(chiTietSanPham.getMausanpham().getTenmau());
			chiTietSanPham2.setMausanpham(mausanpham);
			SizeSanPham sizesanpham = new SizeSanPham();
			sizesanpham.setMasize(chiTietSanPham.getSizesanpham().getMasize());
			sizesanpham.setSize(chiTietSanPham.getSizesanpham().getSize());
			chiTietSanPham2.setSizesanpham(sizesanpham);
			chiTietSanPham2.setSoluong(chiTietSanPham.getSoluong());
			chiTietSanPham2.setNgaynhap(chiTietSanPham.getNgaynhap());
			chiTietSanPhams.add(chiTietSanPham2);
		}
		jSonSanPham.setDschitietsanpham(chiTietSanPhams);
		return jSonSanPham;
	}
	@PostMapping(path="capnhatsanpham",produces="text/html;charset=utf-8")
	@ResponseBody
	public String catNhatThongTinSanPham(@RequestParam String dataSanPham) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			SanPham sanPham = objectMapper.readValue(dataSanPham,SanPham.class);
			boolean kiemTraCapNhat = sanPhamService.capNhatThongTinSanPham(sanPham);
			if(kiemTraCapNhat) {
				return "Cập nhật thành công";
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Cập nhật thất bại";
	}
}
