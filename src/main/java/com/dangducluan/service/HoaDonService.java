package com.dangducluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangducluan.dao.HoaDonDAO;
import com.dangducluan.daoimp.HoaDonImp;
import com.dangducluan.entity.HoaDon;
@Service
public class HoaDonService implements HoaDonImp{
	@Autowired
	HoaDonDAO hoaDonDAO;
	public int themHoaDon(HoaDon hoaDon) {
		int mahoadon = hoaDonDAO.themHoaDon(hoaDon);
		return mahoadon;
	}

}
