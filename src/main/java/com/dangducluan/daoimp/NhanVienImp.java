package com.dangducluan.daoimp;

import com.dangducluan.entity.NhanVien;

public interface NhanVienImp {
	public abstract boolean kiemTraDangNhap(String email,String matkhau);
	public abstract boolean themNhanVien(NhanVien nhanVien);
}
