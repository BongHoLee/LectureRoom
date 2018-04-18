package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.vo.Customer;

public class LoginModel {
	private Connection con;														//DB와 연결하기 위한 객체
	private ArrayList<Customer> cusArr;				//Customer 객체를 담기 위한 List
	
	public LoginModel() throws Exception{						//생성자로써 Model 객체 생성시 자동 연결
		con = DBCon.getConnection();
		cusArr = new ArrayList();
	}
	
	public boolean loginCustomer(Customer cus) throws SQLException{
		boolean check = false;
		String sql = "SELECT C_ID, C_PW FROM customer WHERE C_ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, cus.getC_id());
		ResultSet rs = ps.executeQuery();
		
		//고객이 입력한 ID와 일치하는 ID가 데이터베이스에 존재할 경우 
		if(rs.next()){
			String id="";
			String pw="";
			//고객이 입력한 비밀번호와 DB에 저장된 비밀번호가 일치한 경우 true 반환
			if(rs.getString("C_PW").equals(cus.getC_pw())){
				check = true;
			}
		}
		rs.close();
		ps.close();
		return check;
	}
}
