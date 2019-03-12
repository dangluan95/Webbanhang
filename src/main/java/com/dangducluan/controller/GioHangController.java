package com.dangducluan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dangducluan.entity.ChiTietHoaDon;
import com.dangducluan.entity.ChiTietHoaDonId;
import com.dangducluan.entity.GioHang;
import com.dangducluan.entity.HoaDon;
import com.dangducluan.service.ChiTietHoaDonService;
import com.dangducluan.service.HoaDonService;

@Controller
@RequestMapping(path="giohang")
@SessionAttributes(names="soluongsanpham")
public class GioHangController {
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	@GetMapping
	public String Default(HttpSession httpSession,ModelMap modelMap) {
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("giohang", gioHangs);
		}
		return "giohang";
	}
	@PostMapping
	public String themHoaDon(HttpSession httpSession,@ModelAttribute HoaDon hoaDon) {
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			sdf.format(date);
			hoaDon.setNgaylap(sdf.format(date));
			hoaDon.setTinhtrang(0);
			int mahoadon = hoaDonService.themHoaDon(hoaDon);
			if(mahoadon > 0) {
				Set<ChiTietHoaDon> dschitiethoadon = new HashSet<ChiTietHoaDon>();
				for(GioHang gioHang : gioHangs) {
					
					ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();
					chiTietHoaDonId.setMachitietsanpham(gioHang.getMachitiet());
					chiTietHoaDonId.setMahoadon(hoaDon.getMahoadon());
					
					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
					chiTietHoaDon.setChitiethoadonid(chiTietHoaDonId);
					chiTietHoaDon.setGiatien(gioHang.getSotien());
					dschitiethoadon.add(chiTietHoaDon);
					chiTietHoaDon.setSoluong(gioHang.getSoluong());
					chiTietHoaDonService.themChiTietHoaDon(chiTietHoaDon);
				}
			}else {
				System.out.println("Them that bai");
			}
		}
		return "giohang";
	}
}
