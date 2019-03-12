package com.dangducluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangducluan.dao.ChiTietHoaDonDAO;
import com.dangducluan.daoimp.ChiTietHoaDonImp;
import com.dangducluan.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImp{
	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		// TODO Auto-generated method stub
		boolean kiemTra = chiTietHoaDonDAO.themChiTietHoaDon(chiTietHoaDon);
		return kiemTra;
	}

}
