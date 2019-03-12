package com.dangducluan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.SizeSanPhamImp;
import com.dangducluan.entity.SizeSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SizeSanPhamDAO implements SizeSanPhamImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public List<SizeSanPham> layDanhSachSizeSanPham() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<SizeSanPham> danhSachSizeSanPham = session.createQuery("from sizesanpham").getResultList();
		return danhSachSizeSanPham;
	}
}
