package com.dangducluan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.DanhMucSanPhamImp;
import com.dangducluan.entity.DanhMucSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DanhMucSanPhamDAO implements DanhMucSanPhamImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public List<DanhMucSanPham> layDanhSachDanhMucSanPham() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<DanhMucSanPham> dmSanPham = (List<DanhMucSanPham>)session.createQuery("from danhmucsanpham").getResultList();
		return dmSanPham;
	}

}
