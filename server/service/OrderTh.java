package server.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import protocol.*;
import server.model.UpOderModel;
import server.view.MyDialog2;
import server.vo.UpOrder;

//클라이언트로부터 들어온 주문을 처리하는 스레드
public class OrderTh implements Runnable {
	ArrayList<Order> list;
	UpOderModel om;
	int pc_no;
	
	
	public OrderTh(){}
	public OrderTh(ArrayList<Order> list, int pc_no) throws SQLException {
		this.list = list;									//주문목록을 list에 삽입.
		om = new UpOderModel();
		this.pc_no = pc_no;
		new Thread(this).start(); 						//호출되자마자 실행
	}

	@Override
	public void run() {
		HashMap<String, Integer> upmap = new HashMap();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(Order or : list){

			try {			
				UpOrder upor = new UpOrder();			//list에 담긴 order 객체의 정보를 upor VO객체로 복사
				upor.setPro_no(or.getPro_no());
				upor.setUse_no(or.getUse_no());
				om.updateOrder(upor);						//객체 하나하나를 om객체가 처리하도록 보내줌
				
				if(upmap.containsKey(upor.getPro_name())){
					upmap.put(upor.getPro_name(), upmap.get(upor.getPro_name())+1);
				}else{
					upmap.put(upor.getPro_name(), 1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		for(String key : upmap.keySet()){
			int count = upmap.get(key);
			sb1.append(key + " " + count +"개 ");
		}
		new MyDialog2(null, pc_no+"번 PC의 주문", sb1.toString());
		
		
	}

}
