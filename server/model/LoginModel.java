package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.vo.Manager;

public class LoginModel {
	private Connection con;
	private ArrayList<Manager> manArr;				//Customer 객체를 담기 위한 List
	
	public LoginModel() throws Exception{						//생성자로써 Model 객체 생성시 자동 연결
		con = DBCon.getConnection();
		manArr = new ArrayList();
	}
	
	public int loginManager(Manager man) throws SQLException{
		int status = 0;
		String sql = "SELECT M_ID, M_PW FROM manager WHERE M_ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, man.getM_id());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			status = 1;						//ID가 일치하면 상태값 1
			if(rs.getString("M_PW").equals(man.getM_pw()))
				status = 2;
		}

		return status;
	}
	
	
}
