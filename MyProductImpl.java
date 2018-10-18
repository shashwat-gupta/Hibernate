package com.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.service.model.Product;
import com.service.utility.ConnectionProvider;

public class MyProductImpl implements MyProduct {

	@Override
	public void addProduct(Product p) throws Exception {
		Connection con = ConnectionProvider.getConnection();
		String query="Insert into product_info values ?,?,?,?,?";
		PreparedStatement prs = con.prepareStatement(query);
		prs.setInt(1, p.getProductId());
		prs.setString(2, p.getProductName());
		prs.setInt(3,p.getProductPrice());
		prs.setInt(4, p.getTotalQuantity());
		prs.setString(5, p.getMfgDate());
		prs.executeQuery();
	}

	@Override
	public List<Product> products() throws Exception {
		List<Product> plist = new ArrayList<>();
		Connection con = ConnectionProvider.getConnection();
		String query ="Select * from product_info";
		PreparedStatement prs = con.prepareStatement(query);
		ResultSet rst = prs.executeQuery();
		while(rst.next())
		{
			Product p = new Product(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getInt(4), rst.getString(5));
			plist.add(p);
		}
		return plist;
	}

	@Override
	public List<Product> byNameOrPrice(String name, int price) throws Exception {
		Connection con = ConnectionProvider.getConnection();
		List<Product> plist = new ArrayList<>();
		String query = "Select * from product_info where name=? and price>?";
		PreparedStatement prs = con.prepareStatement(query);
		prs.setString(1,name);
		prs.setInt(2,price);
		ResultSet rst = prs.executeQuery();
		while(rst.next())
		{
			Product p = new Product(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getInt(4), rst.getString(5));
			plist.add(p);
		}
		return plist;
	}

	@Override
	public void updateProduct(Product p) throws Exception {
		Connection con = ConnectionProvider.getConnection();
		String query = "Update product_info set productname=?,productprice=?,totalquantity=?,mfgdate=? where productid=?";
		PreparedStatement prs= con.prepareStatement(query);
		prs.setString(1,p.getProductName());
		prs.setInt(2,p.getProductPrice());
		prs.setInt(3,p.getTotalQuantity());
		prs.setString(4, p.getMfgDate());
		prs.setInt(5, p.getProductId());
		
		prs.executeUpdate();
	}

	@Override
	public void deleteProduct(int productId) throws Exception {
		Connection con = ConnectionProvider.getConnection();
		String query = "Delete * from product_info where productId =?";
		PreparedStatement prs = con.prepareStatement(query);
		prs.executeUpdate();
				
	}

	
}
