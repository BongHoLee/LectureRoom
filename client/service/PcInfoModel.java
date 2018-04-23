package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.vo.PcInfo;

public class PcInfoModel {
	Connection con;
	
	public PcInfoModel() throws SQLException {
		con = DBCon.getConnection();
	}
	
	//PC_NO을 반환해주는 메소드
	public int returnPcNo(PcInfo pcinfo) throws SQLException{
		int pc_no = 0;
		String sql = "SELECT pc_no FROM PC WHERE pc_ip=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, pcinfo.getIp());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			pc_no = rs.getInt("pc_no");
		}
		return pc_no;
		
	}
}
