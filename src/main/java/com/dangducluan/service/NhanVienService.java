package com.dangducluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangducluan.dao.NhanVienDAO;
import com.dangducluan.daoimp.NhanVienImp;
import com.dangducluan.entity.NhanVien;

@Service
public class NhanVienService implements NhanVienImp{
	@Autowired NhanVienDAO nhanVienDAO;

	public boolean kiemTraDangNhap(String email, String matkhau) {
		// TODO Auto-generated method stub
		boolean kiemTra = nhanVienDAO.kiemTraDangNhap(email, matkhau);
		return kiemTra;
	}

	public boolean themNhanVien(NhanVien nhanVien) {
		boolean kiemTraThemNhanVien = nhanVienDAO.themNhanVien(nhanVien);
		return kiemTraThemNhanVien;
	}
}
