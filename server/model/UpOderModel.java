package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import server.vo.*;

//Order_pro 테이블에 접근에서 데이터를 처리
public class UpOderModel {
	private Connection con;
	
	//DB에 연동시킴
	public UpOderModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	//주문객체를 받아서 테이블에 삽입 
	public void updateOrder(UpOrder upor) throws SQLException{
		String sql = "INSERT INTO order_pro(order_no, use_no, pro_no, order_flag) VALUES(order_seq.nextval, ?, ?, 0)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, upor.getUse_no());
		ps.setInt(2, upor.getPro_no());
		ps.executeUpdate();
		ps.close();
	}
	
	

}
