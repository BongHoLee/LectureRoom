package server.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductModel {
	private Connection con;
	
	public ProductModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public ArrayList searchTable() throws SQLException{
		ArrayList data = new ArrayList();
		String sql = "SELECT pro_no, pro_name, pro_stock, pro_price FROM product";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getInt(3));
			temp.add(rs.getInt(4));
			data.add(temp);
		}
		rs.close();
		st.close();
		return data;
	}
}
