package com.dangducluan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.SanPhamImp;
import com.dangducluan.entity.ChiTietHoaDon;
import com.dangducluan.entity.ChiTietHoaDonId;
import com.dangducluan.entity.ChiTietSanPham;
import com.dangducluan.entity.SanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SanPhamDAO implements SanPhamImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public List<SanPham> layDanhSachSanPhamLimit(int sanPhamBatDau) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> danhSachSanPham = (List<SanPham>)session.createQuery("from sanpham").setFirstResult(sanPhamBatDau).setMaxResults(5).getResultList();
		return danhSachSanPham;
	}
	@Transactional
	public SanPham layThongTinSanPhamTheoMa(int maSanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "from sanpham where masanpham = "+maSanPham;
		SanPham sanPham = (SanPham)session.createQuery(query).getSingleResult();
		return sanPham;
	}
	@Transactional
	public List<SanPham> laySanPhamTheoMaDanhMuc(int madanhmuc) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> danhSachSanPham = (List<SanPham>)session.createQuery("from sanpham sp where sp.danhmucsanpham.madanhmuc="+madanhmuc).getResultList();
		return danhSachSanPham;
	}
	@Transactional
	public List<SanPham> layTatCaSanPham() {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> danhSachSanPham = session.createQuery("from sanpham").getResultList();
		return danhSachSanPham;
	}
	@Transactional
	public boolean xoaSanPhamTheoMaSanPham(int masanpham) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();
			SanPham sanPham = session.get(SanPham.class,masanpham);
			for(ChiTietSanPham chiTietSanPham : sanPham.getDschitietsanpham()) {
				session.createQuery("delete chitiethoadon where machitietsanpham="+chiTietSanPham.getMachitietsanpham()).executeUpdate();
			}
			session.createQuery("delete chitietsanpham where masanpham="+masanpham).executeUpdate();
			session.createQuery("delect chitietkhuyenmai where masanpham="+masanpham).executeUpdate();
			session.createQuery("delete sanpham where masanpham="+masanpham).executeUpdate();
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	@Transactional
	public boolean themSanPhamMoi(SanPham sanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int kiemTraThemSanPham = (Integer) session.save(sanPham);
		if(kiemTraThemSanPham > 0) {
			return true;
		}
		return false;
	}
	@Transactional
	public boolean capNhatThongTinSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(sanPham);
			return true;
		}catch(Exception ex) {
			ex.getMessage();
		}
		return false;
	}

}
