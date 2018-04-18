package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import client.vo.Customer;

public class SignInModel {
	Connection con;
	ArrayList<Customer> cusArr;
	
	public SignInModel() throws Exception{
		con = DBCon.getConnection();
		cusArr = new ArrayList();
	}
	
	public void insertCustomer(Customer cus) throws SQLException{
		String sql = "INSERT INTO customer(C_ID, C_PW, C_TEL) VALUES(?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cus.getC_id());
		ps.setString(2, cus.getC_pw());
		ps.setString(3, cus.getC_tel());
		ps.executeQuery();
		
		ps.close();
		System.out.println("인설트 성공");
	}

}
