package com.dangducluan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.NhanVienImp;
import com.dangducluan.entity.NhanVien;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NhanVienDAO implements NhanVienImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public boolean kiemTraDangNhap(String email,String matkhau) {
		try {
			Session session = sessionFactory.getCurrentSession();
			NhanVien nhanVien = (NhanVien) session.createQuery("from nhanvien where email='"+email+"' and matkhau='"+matkhau+"'").getSingleResult();
			if(nhanVien != null) {
				return true;
			}
		}catch(Exception ex) {
			return false;
		}
		return false;
	}
	@Transactional
	public boolean themNhanVien(NhanVien nhanVien) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int maNhanVien = (Integer) session.save(nhanVien);
		if(maNhanVien > 0) {
			return true;
		}
		return false;
	}

}
