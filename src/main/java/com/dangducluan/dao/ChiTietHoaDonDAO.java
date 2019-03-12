package com.dangducluan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.ChiTietHoaDonImp;
import com.dangducluan.entity.ChiTietHoaDon;
import com.dangducluan.entity.ChiTietHoaDonId;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ChiTietHoaDonDAO implements ChiTietHoaDonImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		ChiTietHoaDonId chiTietHoaDonId = (ChiTietHoaDonId) session.save(chiTietHoaDon);
		if(chiTietHoaDonId != null) {
			return true;
		}
		return false;
	}
}
