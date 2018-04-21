package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import protocol.*;
import server.model.DBCon;

public class OrderModel {
	private Connection con;
	
	public OrderModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public void selectUseNo(Order order) throws SQLException{
		String sql = "SELECT use_no FROM use_pc WHERE c_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ClientMain.c_id);								//c_id를 이용해 USE_PC테이블 참조
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			order.setUse_no(rs.getInt("use_no"));					//order 객체에 use_no을 세팅
		}
		rs.close();
		ps.close();
	}
	
	public boolean ChooseMenu(int pro_no) throws SQLException{
		boolean check = false;
		
		String sql1 = "SELECT pro_stock FROM product WHERE pro_no = ?";
		PreparedStatement ps2 = con.prepareStatement(sql1);
		ps2.setInt(1, pro_no);
		ResultSet rs = ps2.executeQuery();
		if(rs.next()){
			int stock = rs.getInt(1);
			if(stock <= 0){
				check = false;
			}else{
				String sql2 = "UPDATE product SET pro_stock = pro_stock - 1 WHERE pro_no = ?";
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setInt(1, pro_no);
				ps.executeUpdate();
				
				check = true;
			}
		}
		
		return check;
		
		
		
	}
}
