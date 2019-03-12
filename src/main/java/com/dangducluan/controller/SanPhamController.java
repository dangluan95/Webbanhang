package com.dangducluan.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dangducluan.entity.DanhMucSanPham;
import com.dangducluan.entity.SanPham;
import com.dangducluan.service.DanhMucSanPhamService;
import com.dangducluan.service.SanPhamService;

@Controller
@RequestMapping(path="/sanpham")
public class SanPhamController {
	@Autowired	
	DanhMucSanPhamService danhMucSanPhamService;
	@Autowired
	SanPhamService sanPhamService;
	@GetMapping("/{idDanhMuc}/{tenDanhMuc}")
	public String Default(ModelMap modelMap,@PathVariable int idDanhMuc,@PathVariable String tenDanhMuc) {
		List<DanhMucSanPham> dmSanPham = danhMucSanPhamService.layDanhSachDanhMucSanPham();
		List<SanPham> danhSachSanPham = sanPhamService.laySanPhamTheoMaDanhMuc(idDanhMuc);
		modelMap.addAttribute("dmSanPham", dmSanPham);
		modelMap.addAttribute("dsSanPham", danhSachSanPham);
		modelMap.addAttribute("tenDanhMuc", tenDanhMuc);
		return "sanpham";
	}
}
