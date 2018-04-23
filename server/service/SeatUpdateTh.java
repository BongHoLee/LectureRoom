package server.service;

import java.sql.SQLException;

import server.model.UsePcModel;
import server.view.PanSeat;
import server.vo.UsePc;


//좌석 사용정보를 업데이트 하기위한 스레드
public class SeatUpdateTh implements Runnable{
	PanSeat pan;							//업데이트할 좌석
	UsePc usepc;
	UsePcModel usepcmodel;
	
	public SeatUpdateTh(PanSeat pan, UsePc usepc) throws Exception {
		usepcmodel = new UsePcModel();
		this.pan = pan;
		this.usepc = usepc;
		//new Thread(this).start();
	}
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()){
			try {
				usepcmodel.updateUI(usepc);
				int min = usepc.getUsetime();
				int hour = 0;
				
				if(min/60 != 0){
					hour = min/60;
					min = min%60;
				}
				pan.label[1].setText(hour+"시간 "+min+"분");
				pan.label[2].setText(String.valueOf(usepc.getUsecharge()+100) + "원");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pan.label[1].setText("");
		pan.label[2].setText("");
		System.out.println("좌석 갱신 스레드 종료됨");
		
		
	}

}
