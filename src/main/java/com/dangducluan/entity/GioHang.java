package com.dangducluan.entity;

public class GioHang {
	private int machitiet;
	private int masp;
	private int masize;
	private int mamau;
	private String tensp;
	private String tenmau;
	private String tensize;
	private int soluong;
	private String sotien;
	
	public int getMachitiet() {
		return machitiet;
	}
	public void setMachitiet(int machitiet) {
		this.machitiet = machitiet;
	}
	public int getMasp() {
		return masp;
	}
	public void setMasp(int masp) {
		this.masp = masp;
	}
	public int getMasize() {
		return masize;
	}
	public void setMasize(int masize) {
		this.masize = masize;
	}
	public int getMamau() {
		return mamau;
	}
	public void setMamau(int mamau) {
		this.mamau = mamau;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public String getTenmau() {
		return tenmau;
	}
	public void setTenmau(String tenmau) {
		this.tenmau = tenmau;
	}
	public String getTensize() {
		return tensize;
	}
	public void setTensize(String tensize) {
		this.tensize = tensize;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getSotien() {
		return sotien;
	}
	public void setSotien(String sotien) {
		this.sotien = sotien;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		GioHang gioHang = (GioHang) arg0;
		if(this.masp == gioHang.masp && this.mamau == gioHang.mamau && this.masize == gioHang.masize) {
			return true;
		}
		return false;
	}
	
}
