package com.dangducluan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangducluan.daoimp.HoaDonImp;
import com.dangducluan.entity.HoaDon;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class HoaDonDAO implements HoaDonImp{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public int themHoaDon(HoaDon hoaDon) {
		Session session = sessionFactory.getCurrentSession();
		int mahoadon = (Integer) session.save(hoaDon);
		if(mahoadon > 0) {
			return mahoadon;
		}
		return 0;
	}
	
}
