package server.service;

import java.net.InetAddress;

import server.model.PcInfoModel;
import server.vo.PcInfo;

//PC 테이블을 조작하는 스레드
//
public class PcUpdateTh implements Runnable{
	PcInfoModel pcmodel;
	 
	public PcUpdateTh(){}
	
	//접속한 클라이언트의 IP주소와 C_ID를 넘겨받는 생성자
	public PcUpdateTh(InetAddress inetAddress, String c_id) {
		PcInfo pc = new PcInfo();										//pc정보를 담을 객체 생성
		pc.setPc_ip(inetAddress.toString());							//클라이언트가 접속한 PC의 IP 입력
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
