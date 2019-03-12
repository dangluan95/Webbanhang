package com.dangducluan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangducluan.dao.SanPhamDAO;
import com.dangducluan.daoimp.SanPhamImp;
import com.dangducluan.entity.SanPham;
@Service
public class SanPhamService implements SanPhamImp{

	@Autowired
	SanPhamDAO sanPhamDAO;
	public List<SanPham> layDanhSachSanPhamLimit(int sanPhamBatDau) {
		// TODO Auto-generated method stub
		List<SanPham> danhSachSanPham = sanPhamDAO.layDanhSachSanPhamLimit(sanPhamBatDau);
		return danhSachSanPham;
	}
	public SanPham layThongTinSanPhamTheoMa(int maSanPham) {
		SanPham sanPham = sanPhamDAO.layThongTinSanPhamTheoMa(maSanPham);
		return sanPham;
	}
	public List<SanPham> laySanPhamTheoMaDanhMuc(int madanhmuc) {
		List<SanPham> danhSachSanPham = sanPhamDAO.laySanPhamTheoMaDanhMuc(madanhmuc);
		return danhSachSanPham;
	}
	public List<SanPham> layTatCaSanPham() {
		List<SanPham> danhSachSanPham = sanPhamDAO.layTatCaSanPham();
		return danhSachSanPham;
	}
	public boolean xoaSanPhamTheoMaSanPham(int masanpham) {
		// TODO Auto-generated method stub
		boolean kiemTraXoaSanPhamTheoMa = sanPhamDAO.xoaSanPhamTheoMaSanPham(masanpham);
		return kiemTraXoaSanPhamTheoMa;
	}
	public boolean themSanPhamMoi(SanPham sanPham) {
		// TODO Auto-generated method stub
		boolean kiemTraThemSanPham = sanPhamDAO.themSanPhamMoi(sanPham);
		return kiemTraThemSanPham;
	}
	public boolean capNhatThongTinSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		boolean kiemTraCapNhatSanPham = sanPhamDAO.capNhatThongTinSanPham(sanPham);
		return kiemTraCapNhatSanPham;
	}

}
