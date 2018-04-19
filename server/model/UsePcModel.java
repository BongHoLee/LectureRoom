package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.vo.UsePc;

//접속한 클라이언트의 정보를 받아서 USE_PC 테이블을 조작하는 모델.
//넘겨받는 인자는 주로 UsePc 객체를 넘겨받아서 갱신
public class UsePcModel {
	private Connection con;
	
	//DB에 연동시킴
	public UsePcModel() throws SQLException {
		con = DBCon.getConnection();
	}
	
	public void insertByVo(UsePc usepc){
		String sql = "INSERT INTO USE_PC(use_no, c_id, pc_no, m_id) VALUES(usepc_seq.nextval, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usepc.getC_id());
			ps.setInt(2, usepc.getPc_no());
			ps.setString(3, usepc.getM_id());
			System.out.println("cid : " + usepc.getC_id());
			System.out.println("pc_no : " + usepc.getPc_no());
			System.out.println("m_id : " + usepc.getM_id());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
