package com.dangducluan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dangducluan.entity.DanhMucSanPham;
import com.dangducluan.entity.MauSanPham;
import com.dangducluan.entity.SanPham;
import com.dangducluan.entity.SizeSanPham;
import com.dangducluan.service.DanhMucSanPhamService;
import com.dangducluan.service.MauSanPhamService;
import com.dangducluan.service.SanPhamService;
import com.dangducluan.service.SizeSanPhamService;

@Controller
@RequestMapping(path="/themsanpham")
public class ThemSanPhamController {
	@Autowired
	SanPhamService sanPhamSerive;
	@Autowired
	DanhMucSanPhamService danhMucSanPhamService;
	@Autowired
	MauSanPhamService mauSanPhamService;
	@Autowired
	SizeSanPhamService sizeSanPhamService;
	@GetMapping
	public String Default(ModelMap modelMap) {
		List<SanPham> dsSanPham = sanPhamSerive.layDanhSachSanPhamLimit(0);
		List<SanPham> tatCaSanPham = sanPhamSerive.layTatCaSanPham();
		List<DanhMucSanPham> danhSachDanhMuc = danhMucSanPhamService.layDanhSachDanhMucSanPham();
		List<MauSanPham> danhSachMauSanPham = mauSanPhamService.layDanhSachMauSanPham();
		List<SizeSanPham> danhSachSizeSanPham = sizeSanPhamService.layDanhSachSizeSanPham();
		int tongSoPage = (int) Math.ceil((float)tatCaSanPham.size() / 5);
		modelMap.addAttribute("dsSanPham", dsSanPham);
		modelMap.addAttribute("tatCaSanPham", tatCaSanPham);
		modelMap.addAttribute("tongSoPage", tongSoPage);
		modelMap.addAttribute("dsDanhMuc", danhSachDanhMuc);
		modelMap.addAttribute("dsSize", danhSachSizeSanPham);
		modelMap.addAttribute("dsMau", danhSachMauSanPham);
		return "themsanpham";
	}
}
