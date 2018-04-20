package server.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class SalesModel {
private Connection con;
	
	public SalesModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public Vector comboInputByDate() throws SQLException{
		Vector arr = new Vector();
		int count = 0;
		String sql = "SELECT to_char(sales_date, 'YYYY-MM-DD') FROM sales";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			arr.add(String.valueOf(rs.getDate(1)));
		}
		
		
		return arr;
	}
}
