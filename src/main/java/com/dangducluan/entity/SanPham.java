package com.dangducluan.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="sanpham")
public class SanPham {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int masanpham;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="madanhmuc")
	private DanhMucSanPham danhmucsanpham;
	private String tensanpham;
	private String giatien;
	private String mota;
	private String hinhsanpham;
	private String gianhcho;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="masanpham")
	private Set<ChiTietSanPham> dschitietsanpham;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="chitietkhuyenmai",joinColumns={@JoinColumn(name="masanpham",referencedColumnName="masanpham")},
	inverseJoinColumns={@JoinColumn(name="makhuyenmai",referencedColumnName="makhuyenmai")})
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
