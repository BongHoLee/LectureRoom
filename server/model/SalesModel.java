package server.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import server.vo.Sales;

public class SalesModel {
private Connection con;
	
	public SalesModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public int searchMonthlySalesByDate(String month) throws SQLException{
		int monthly = 0;
		String sql = "SELECT sum(sales_daily) FROM sales WHERE to_char(SALES_DATE, 'MM') = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			monthly = rs.getInt(1);
		}
		return monthly;
	}
	
	public Sales searchByDate(String date) throws SQLException{
		Sales sal = new Sales();
		String sql = "SELECT to_char(sales_date, 'YYYY-MM-DD'), sales_daily, sales_total "
				+ " FROM sales"
				+ " WHERE to_char(sales_date, 'YYYY-MM-DD') = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, date);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			sal.setSales_date(Date.valueOf(rs.getString(1)));
			sal.setSales_daily(rs.getInt(2));
			sal.setSales_total(rs.getInt(3));
		}
		
		return sal;
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
