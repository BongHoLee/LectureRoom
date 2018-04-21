package server.service;

import java.sql.SQLException;
import java.util.ArrayList;

import protocol.*;
import server.model.UpOderModel;
import server.vo.UpOrder;

//클라이언트로부터 들어온 주문을 처리하는 스레드
public class OrderTh implements Runnable {
	ArrayList<Order> list;
	UpOderModel om;
	
	
	public OrderTh(){}
	public OrderTh(ArrayList<Order> list) throws SQLException {
		this.list = list;									//주문목록을 list에 삽입.
		om = new UpOderModel();
		new Thread(this).start(); 						//호출되자마자 실행
	}

	@Override
	public void run() {
		for(Order or : list){

			try {			
				UpOrder upor = new UpOrder();			//list에 담긴 order 객체의 정보를 upor VO객체로 복사
				upor.setPro_no(or.getPro_no());
				upor.setUse_no(or.getUse_no());
				om.updateOrder(upor);						//객체 하나하나를 om객체가 처리하도록 보내줌
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		
	}

}
