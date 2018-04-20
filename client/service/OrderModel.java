package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.DBCon;

public class OrderModel {
	private Connection con;
	
	public OrderModel() throws SQLException{
		con = DBCon.getConnection();
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
