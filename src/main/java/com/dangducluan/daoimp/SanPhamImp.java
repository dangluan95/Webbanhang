package com.dangducluan.daoimp;
import java.util.List;

import com.dangducluan.entity.SanPham;
public interface SanPhamImp {
	public abstract List<SanPham> layDanhSachSanPhamLimit(int sanPhamBatDau);
	public abstract SanPham layThongTinSanPhamTheoMa(int maSanPham);
	public abstract List<SanPham> laySanPhamTheoMaDanhMuc(int madanhmuc);
	public abstract List<SanPham> layTatCaSanPham();
	public abstract boolean xoaSanPhamTheoMaSanPham(int masanpham);
	public abstract boolean themSanPhamMoi(SanPham sanPham);
	public abstract boolean capNhatThongTinSanPham(SanPham sanPham);
}
