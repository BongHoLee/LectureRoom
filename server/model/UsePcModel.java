package server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.vo.Sales;
import server.vo.UsePc;

//접속한 클라이언트의 정보를 받아서 USE_PC 테이블을 조작하는 모델.
//넘겨받는 인자는 주로 UsePc 객체를 넘겨받아서 갱신
public class UsePcModel {
	private Connection con;
	Sales sales;
	//DB에 연동시킴
	public UsePcModel() throws SQLException {
		con = DBCon.getConnection();
	}
	
	//종료시 해당 use_no의 pc가 주문한 메뉴의 총 합을 저장.
	public void calPc(UsePc uc) throws SQLException{
		
		//use_charge의 값을 uc에 저장
		String sql2 = "SELECT use_charge FROM use_pc WHERE use_no=?";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, uc.getUse_no());
		ResultSet rs2 = ps2.executeQuery();
		if(rs2.next()){
			uc.setUsecharge(rs2.getInt("USE_CHARGE"));
		}
		rs2.close();
		ps2.close();

		//use_no에 해당하는 product 가격을 모두 구해와서 use_charge와 합한 값을 uc에 저장
		String sql1 = "SELECT SUM(product.pro_price) FROM product, order_pro, use_pc WHERE order_pro.order_flag=0 AND product.pro_no = order_pro.pro_no AND order_pro.use_no=? AND use_pc.use_flag=0";
		PreparedStatement ps = con.prepareStatement(sql1);
		ps.setInt(1, uc.getUse_no());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			uc.setUsetotal(uc.getUsecharge() + rs.getInt(1));
		}
		rs.close();
		ps.close();
		
		//use_total의 값을 갱신
		String sql3 = "UPDATE use_pc SET use_total=? WHERE use_no=?";
		PreparedStatement ps3 = con.prepareStatement(sql3);
		ps3.setInt(1, uc.getUsetotal());
		ps3.setInt(2, uc.getUse_no());
		ps3.executeUpdate();
		ps3.close();
		
		//PC 테이블의 flag를 0으로 바꿈
		String sql4 = "UPDATE pc SET pc_flag=0 WHERE pc_no=?";
		PreparedStatement ps4 = con.prepareStatement(sql4);
		ps4.setInt(1, uc.getPc_no());
		ps4.executeUpdate();
		ps4.close();
		
		//order_pro의 flag를 1로 바꿈
		String sql5 = "UPDATE order_pro SET order_flag=1 WHERE use_no=? AND order_flag=0";
		PreparedStatement ps5 = con.prepareStatement(sql5);
		ps5.setInt(1, uc.getUse_no());
		ps5.executeUpdate();
		ps5.close();
		
		//sales_daily값 구해와서 daily에 저장
		String sql6 = "SELECT sales_daily FROM sales WHERE to_char(sales_date, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD')";
		PreparedStatement ps6 = con.prepareStatement(sql6);
		ResultSet rs6 = ps6.executeQuery();
		sales = new Sales();
		if(rs6.next()){
			sales.setSales_daily(rs6.getInt(1));
		}
		ps6.close();
		System.out.println(uc.getUsetotal());
		System.out.println(sales.getSales_daily());
		//구해온 일일매출에 use_total값을 더해서 다시 sales_daily의 값을 갱신
		String sql7 = "UPDATE sales SET sales_daily = ? WHERE to_char(sales_date, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD')";
		PreparedStatement ps7 = con.prepareStatement(sql7);
		ps7.setInt(1, uc.getUsetotal() + sales.getSales_daily());
		ps7.executeUpdate();
		ps7.close();
		
	}
	
	//use_time과 use_charge를 검색
	public void updateUI(UsePc usepc) throws SQLException{
		String sql = "SELECT use_time, use_charge FROM use_pc WHERE use_flag=0 AND c_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usepc.getC_id());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			usepc.setUsecharge(rs.getInt("use_charge"));
			usepc.setUsetime(rs.getInt("use_time"));
		}
		rs.close();
		ps.close();
		
		
		
	}
	
	public void updateFlag(UsePc usepc) throws SQLException{
		String sql = "UPDATE use_pc SET use_flag=1 WHERE use_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, usepc.getUse_no());
		ps.executeUpdate();
		ps.close();
	}
	
	public void insertByVo(UsePc usepc){
		String sql = "INSERT INTO USE_PC(use_no, c_id, pc_no, m_id, use_time, use_charge, use_total, use_flag) VALUES(usepc_seq.nextval, ?, ?, ?, 0, 0, 0, 0)";
		String sql2 = "SELECT use_no FROM USE_PC WHERE use_flag=0 AND c_id=?";
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
			
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, usepc.getC_id());
			ResultSet rs = ps2.executeQuery();
			if(rs.next()){
				usepc.setUse_no(rs.getInt("USE_NO"));
			}
			rs.close();
			ps2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
