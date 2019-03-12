package com.dangducluan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dangducluan.entity.DanhMucSanPham;
import com.dangducluan.entity.GioHang;
import com.dangducluan.entity.SanPham;
import com.dangducluan.service.DanhMucSanPhamService;
import com.dangducluan.service.SanPhamService;

@Controller
@RequestMapping(path="/chitiet")
@SessionAttributes(names={"soluongsanpham"})
public class ChiTietController {
	@Autowired
	SanPhamService sanPhamService;
	@Autowired	
	DanhMucSanPhamService danhMucSanPhamService;
	@GetMapping("/{masanpham}")
	public String Default(@PathVariable int masanpham,ModelMap modelMap,HttpSession httpSession) {
		SanPham sanPham = sanPhamService.layThongTinSanPhamTheoMa(masanpham);
		modelMap.addAttribute("sanPham",sanPham);
		List<DanhMucSanPham> dmSanPham = danhMucSanPhamService.layDanhSachDanhMucSanPham();
		modelMap.addAttribute("dmSanPham", dmSanPham);
		return "chitiet";
	}
}
