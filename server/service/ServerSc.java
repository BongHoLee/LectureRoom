package server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.SQLException;
import java.util.HashMap;

import server.model.PcInfoModel;
import server.view.PanSeat;
import server.view.SeatView;
import server.vo.PcInfo;


//클라이언트의 접속을 대기하는 스레드
public class ServerSc implements Runnable{
	ServerSocket serverSocket;							//클라이언트와의 통신을 위한 서버 소켓
	Socket connection;										//클라이언트 접속시 생성되는 소켓
	SeatView sv;												//SeatView를 넘겨받음
	PanSeat[] panArr;
	PcInfoModel pcinfomodel;
	PcInfo  pcinfo;
	PanSeat pan;
	
	public ServerSc(SeatView sv){
		try {

			this.sv = sv;
			panArr = sv.getPan();												//좌석 객체배열 가져오
			serverSocket = new ServerSocket(6789);
			new Thread(this).start();										//소켓 전달 후 실행
				
		} catch (IOException e) {
			e.printStackTrace();
		} 
					
	}
	
	//좌석 번호에 해당하는 pan객체를 가져오기 위함.
	public void setPanSeat() throws SQLException{
		this.pcinfomodel = new PcInfoModel();
		this.pcinfo = new PcInfo();
		pcinfo.setPc_ip(connection.getInetAddress().toString());
		pcinfo.setPc_no(pcinfomodel.selectPcNo(connection.getInetAddress()));
		this.pan = panArr[pcinfo.getPc_no()-1];
		System.out.println(this.pan.getNumSeat());
	}
	


	@Override
	public void run() {				//스레드의 메인 실행 부
		while(true){
			try{
				connection = serverSocket.accept();							//클라이언트의 접속을 대기
				setPanSeat();
				ScWithClient sc = new ScWithClient(connection, this.pan);		//클라이언트 접속시 
																								//실행 스레드에게 소켓, 좌석 객체 전달
			}catch(Exception ex){
				System.out.println("일단 뭔가 잘못된거긴 함");
			}
		}
	}

}
