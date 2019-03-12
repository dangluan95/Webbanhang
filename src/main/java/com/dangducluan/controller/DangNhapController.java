package com.dangducluan.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dangducluan.entity.NhanVien;
import com.dangducluan.service.NhanVienService;

@Controller
@RequestMapping(path = "/dangnhap")
@SessionAttributes(names= {"email"})
public class DangNhapController {
	@Autowired
	NhanVienService nhanVienService;
	@GetMapping
	public String Default() {
		return "dangnhap";
	}
	@PostMapping
	public String dangKy(@RequestParam String email,@RequestParam String matkhau,@RequestParam String nhaplaimatkhau,ModelMap modelMap) {
		boolean ktMail = validate(email);
		if(ktMail) {
			if(matkhau.equals(nhaplaimatkhau)) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setEmail(email);
				nhanVien.setTendangnhap(email);
				nhanVien.setMatkhau(matkhau);
				boolean kiemTraThemNhanVien = nhanVienService.themNhanVien(nhanVien);
				if(kiemTraThemNhanVien) {
					modelMap.addAttribute("ketquadangky","Tạo tài khoản thành công");
				}else {
					modelMap.addAttribute("ketquadangky","Tạo tài khoản thất bại");
				}
			}else {
				modelMap.addAttribute("ketquadangky","Mật khẩu không trùng khớp, vui lòng thử lại");
			}
		}else {
			modelMap.addAttribute("ketquadangky","Email không hợp lệ, vui lòng kiểm tra lại định dạng");
		}
		return "dangnhap";
	}
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
}
