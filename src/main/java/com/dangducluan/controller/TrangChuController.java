package com.dangducluan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dangducluan.entity.DanhMucSanPham;
import com.dangducluan.entity.SanPham;
import com.dangducluan.service.DanhMucSanPhamService;
import com.dangducluan.service.SanPhamService;

@Controller
@RequestMapping(path="/")
@SessionAttributes(names= {"chucaidau","soluongsanpham"})
public class TrangChuController {
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DanhMucSanPhamService danhMucSanPhamService;
	@GetMapping
	public String Default(HttpSession httpSession,ModelMap modelMap)
	{
		if(httpSession.getAttribute("email") != null) {
			String email = (String) httpSession.getAttribute("email");
			String chucaidau = email.substring(0,1).toUpperCase();
			modelMap.addAttribute("chucaidau", chucaidau);
		}
		List<SanPham> danhSachSanPham = sanPhamService.layTatCaSanPham();
		List<DanhMucSanPham> dmSanPham = danhMucSanPhamService.layDanhSachDanhMucSanPham();
		modelMap.addAttribute("dsSanPham", danhSachSanPham);
		modelMap.addAttribute("dmSanPham", dmSanPham);
		return "trangchu";
	}
}
