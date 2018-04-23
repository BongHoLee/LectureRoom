package client.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.view.OrderView;
import client.vo.Product;
import protocol.Order;
import server.model.DBCon;

public class OrderModel {
	private Connection con;
	
	OrderView orderV;
	
	public OrderModel() throws SQLException{
		con = DBCon.getConnection();
	}
	
	public void selectUseNo(Order order) throws SQLException{
		String sql = "SELECT use_no FROM use_pc WHERE use_flag=0 AND c_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ClientMain.c_id);								//c_id를 이용해 USE_PC테이블 참조
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			order.setUse_no(rs.getInt("use_no"));					//order 객체에 use_no을 세팅
		}
		rs.close();
		ps.close();
	}
	
	public boolean ChooseMenu(int pro_no) throws SQLException{
		boolean check = false;
		
		String sql1 = "SELECT pro_stock FROM product WHERE pro_no = ?";
		PreparedStatement ps2 = con.prepareStatement(sql1);
		ps2.setInt(1, pro_no);
		ResultSet rs = ps2.executeQuery();
		if(rs.next()){
			int stock = rs.getInt(1);
			if(stock <= 0){
				check = false;
			}else{
				String sql2 = "UPDATE product SET pro_stock = pro_stock - 1 WHERE pro_no = ?";
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setInt(1, pro_no);
				ps.executeUpdate();
				
				check = true;
			}
		}
		return check;		
	}
	
	public Product addTotalMenu(int pro_no) throws SQLException{
		Product pro = new Product();
		
		String sql1 = "SELECT pro_no, pro_name, pro_stock, pro_price FROM product WHERE pro_no = ?";
		PreparedStatement ps2 = con.prepareStatement(sql1);
		ps2.setInt(1, pro_no);
		ResultSet rs = ps2.executeQuery();
		if(rs.next()){
			int no = rs.getInt(1);
			String name = rs.getString(2);
			int stock = rs.getInt(3);
			int price = rs.getInt(4);
			if(stock > 0){
				pro.setPro_no(no);
				pro.setPro_name(name);
				pro.setPro_stock(stock);
				pro.setPro_price(price);
			}
		}
		return pro;		
	}
	
	//취소 버튼 클릭시 실행되는 메소드. orderList의 내용(Order객체)를 참조해서 테이블 갱신
	public void cancelOrder(ArrayList<Order> list) throws SQLException{
		for(Order order : list){
			String sql = "UPDATE product SET pro_stock=pro_stock+1 WHERE pro_no=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, order.getPro_no());
			ps.executeUpdate();
			ps.close();
		}
	}
}
