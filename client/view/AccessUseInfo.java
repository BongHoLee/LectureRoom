package client.view;

import java.sql.SQLException;

//UseInfoView 싱글톤 패턴
public class AccessUseInfo {
	private static UseInfoView us;
	
	private AccessUseInfo(){
		
	}
	
	public static UseInfoView useInfo() throws SQLException {
		if(us == null)
			us = new UseInfoView();
		return us;
	}
}
