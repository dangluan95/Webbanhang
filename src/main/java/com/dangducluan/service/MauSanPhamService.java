package com.dangducluan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangducluan.dao.MauSanPhamDAO;
import com.dangducluan.daoimp.MauSanPhamImp;
import com.dangducluan.entity.MauSanPham;

@Service
public class MauSanPhamService implements MauSanPhamImp{
	@Autowired
	MauSanPhamDAO mauSanPhamDAO;
	public List<MauSanPham> layDanhSachMauSanPham() {
		// TODO Auto-generated method stub
		List<MauSanPham> danhSachMauSanPham = mauSanPhamDAO.layDanhSachMauSanPham();
		return danhSachMauSanPham;
	}

}
