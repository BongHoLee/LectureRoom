package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static Connection con;
	
	private DBCon() throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@70.12.115.72:1521:orcl";
		String user = "pc";
		String pw = "pc";
		
		con = DriverManager.getConnection(url, user, pw);
	}
	
	public static Connection getConnection() throws SQLException{
		if(con == null )
			new DBCon();
		return con;
	}
}
