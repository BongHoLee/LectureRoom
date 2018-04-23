package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.vo.UsePc;

public class UsePcModel {
	private Connection con;
	
	public UsePcModel() throws SQLException {
		con = DBCon.getConnection();
	}
	
	
	//고객 사용시간 및 사용금액을 가져오기 위한 메소드
	public void updateUI(UsePc usepc) throws SQLException{
		String sql = "SELECT use_time, use_charge FROM use_pc WHERE use_flag=0 AND c_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usepc.getC_id());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			usepc.setUsetime(rs.getInt("use_time"));
			usepc.setUsecharge(rs.getInt("use_charge"));
		}
		rs.close();
		ps.close();
	}
	

}
