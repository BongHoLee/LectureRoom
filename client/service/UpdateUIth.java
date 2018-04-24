package client.service;

import java.sql.SQLException;

import client.view.AccessUseInfo;
import client.view.UseInfoView;
import client.vo.Customer;
import client.vo.UsePc;

public class UpdateUIth implements Runnable {
	UseInfoView uv;
	UsePcModel usepcmodel;
	UsePc usepc;
	Customer cus;

	public UpdateUIth(){}
	public UpdateUIth(Customer cus) throws SQLException {
		uv = AccessUseInfo.useInfo();
		this.cus = cus;
		usepcmodel = new UsePcModel();
		
		//new Thread(this).start();
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted())
		{
			try {
				
				usepc = new UsePc();
				usepc.setC_id(cus.getC_id());
				usepcmodel.updateUI(usepc);
				System.out.println(usepc.getC_id() + " ID입니다");
				System.out.println(usepc.getUsecharge()+" 사용요금 입니다.");
				System.out.println(usepc.getUsetime() + " 사용시간 입니다");
				int hour = 0;
				int min = usepc.getUsetime();
				
				if(min/60 != 0){
					hour = min/60;
					min = min%60;
				}
				
				
				uv.tfUseTime.setText(hour + "시간 " + min + "분");
				uv.tfUseCharge.setText(String.valueOf(usepc.getUsecharge()+100) + " 원");
				Thread.sleep(60000);							//60초간 대기후 실행
			} catch (SQLException e) {
				System.out.println("좌석 갱신 스레드가 참조하는 SQL Connection이 종료되었습니다.");
			}  					
			catch (InterruptedException e) {
				System.out.println("좌석 갱신 스레드가 종료되었습니다.");
			}

		}

	}

}
