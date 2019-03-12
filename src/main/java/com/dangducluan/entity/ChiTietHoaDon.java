package com.dangducluan.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="chitiethoadon")
public class ChiTietHoaDon {
	@EmbeddedId
	ChiTietHoaDonId chitiethoadonid;
	private int soluong;
	private String giatien;
	public ChiTietHoaDonId getChitiethoadonid() {
		return chitiethoadonid;
	}
	public void setChitiethoadonid(ChiTietHoaDonId chitiethoadonid) {
		this.chitiethoadonid = chitiethoadonid;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getGiatien() {
		return giatien;
	}
	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}
	
}
