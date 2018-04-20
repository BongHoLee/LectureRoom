package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import server.vo.Product;

public class ProductModel {
	private Connection con;
	
	public ProductModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public Product searchByNo(int no) throws SQLException{
		Product pro = new Product();
		String sql = "SELECT pro_no, pro_name, pro_stock, pro_price FROM product WHERE pro_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			pro.setPro_no(rs.getInt(1));
			pro.setPro_name(rs.getString(2));
			pro.setPro_stock(rs.getInt(3));
			pro.setPro_price(rs.getInt(4));
			
		}
		
		return pro;
	}
	
	public ArrayList searchTable() throws SQLException{
		ArrayList data = new ArrayList();
		String sql = "SELECT pro_no, pro_name, pro_stock, pro_price FROM product";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getInt(3));
			temp.add(rs.getInt(4));
			data.add(temp);
		}
		rs.close();
		st.close();
		return data;
	}
	
	public ArrayList searchTable(int num) throws SQLException{
		ArrayList data = new ArrayList();
		String sql = "SELECT pro_no, pro_name, pro_stock, pro_price FROM product WHERE pro_no between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setInt(2, num+100);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getInt(3));
			temp.add(rs.getInt(4));
			data.add(temp);
		}
		rs.close();
		ps.close();
		return data;
	}
}
