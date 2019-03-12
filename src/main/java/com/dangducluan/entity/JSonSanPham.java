package com.dangducluan.entity;

import java.util.Set;

public class JSonSanPham {
	private int masanpham;
	private DanhMucSanPham danhmucsanpham;
	private String tensanpham;
	private String giatien;
	private String mota;
	private String hinhsanpham;
	private String gianhcho;
	private Set<ChiTietSanPham> dschitietsanpham;
	private Set<KhuyenMai> dskhuyenmai;
	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}
	public DanhMucSanPham getDanhmucsanpham() {
		return danhmucsanpham;
	}
	public void setDanhmucsanpham(DanhMucSanPham danhmucsanpham) {
		this.danhmucsanpham = danhmucsanpham;
	}
	public String getTensanpham() {
		return tensanpham;
	}
	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}
	public String getGiatien() {
		return giatien;
	}
	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinhsanpham() {
		return hinhsanpham;
	}
	public void setHinhsanpham(String hinhsanpham) {
		this.hinhsanpham = hinhsanpham;
	}
	public String getGianhcho() {
		return gianhcho;
	}
	public void setGianhcho(String gianhcho) {
		this.gianhcho = gianhcho;
	}
	public Set<ChiTietSanPham> getDschitietsanpham() {
		return dschitietsanpham;
	}
	public void setDschitietsanpham(Set<ChiTietSanPham> dschitietsanpham) {
		this.dschitietsanpham = dschitietsanpham;
	}
	public Set<KhuyenMai> getDskhuyenmai() {
		return dskhuyenmai;
	}
	public void setDskhuyenmai(Set<KhuyenMai> dskhuyenmai) {
		this.dskhuyenmai = dskhuyenmai;
	}
	
}
