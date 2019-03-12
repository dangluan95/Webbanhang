package com.dangducluan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.MauSanPhamImp;
import com.dangducluan.entity.MauSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MauSanPhamDAO implements MauSanPhamImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public List<MauSanPham> layDanhSachMauSanPham() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<MauSanPham> danhSachMauSanPham = session.createQuery("from mausanpham").getResultList();
		return danhSachMauSanPham;
	}
}
