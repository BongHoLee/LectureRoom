package server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

import server.model.*;
import server.view.*;
import server.vo.*;
import protocol.ClientProtocol;
import protocol.Order;

//클라이언트가 접속시 호출되어서 실행되는 스레드
public class ScWithClient implements Runnable {
	PcInfoModel pcinfomodel;
	SCustomer cus;
	PcInfo pcinfo;
	UsePc usepc;
	UsePcModel usepcmodel;

	Socket connection; // 클라이언트와 연결시 생성되는 소켓
	PanSeat pan;
	ObjectOutputStream output; // 소켓 통신시 메시지를 전달할 스트림 객체
	ObjectInputStream input; // 소켓 통신시 메시지를 전달받을 스트림 객체

	public ScWithClient(Socket socket, PanSeat pan) throws IOException, SQLException {
		this.cus = new SCustomer(); // Customer의 정보를 담는 VO 객체
		this.pcinfo = new PcInfo(); // PC의 정보를 담는 VO 객체
		this.pcinfomodel = new PcInfoModel(); // PC테이블의 데이터를 처리할 모델
		this.usepc = new UsePc(); // USE_PC의 정보를 담는 VO 객체
		this.usepcmodel = new UsePcModel(); // USE_PC의 데이터를 처리할 모델

		this.pan = pan; // 넘겨받은 좌석의 객체
		this.connection = socket;

		new Thread(this).start(); // 클라이언트와 연결된 소켓을 넘겨받고 run
	}

	// Stream을 설정
	public void setUpStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("클라이언트와 스트림이 연결되었습니다.");
	}

	// 클라이언트의 C_ID를 받은 후 PcInfoModel을 호출해서 PC테이블 접근
	public void callPcUp() throws ClassNotFoundException, IOException {
		String c_id = (String) input.readObject(); // 클라이언트로부터 C_id를 받음
		pcinfo.setPc_no(pcinfomodel.selectPcNo(connection.getInetAddress())); // IP를
																				// 이용해서
																				// PC_NO를
																				// 얻어옴
		pcinfo.setPc_ip(connection.getInetAddress().toString()); // pcinfo 객체에
																	// pc_no,
																	// pc_ip 저장
		cus.setC_id(c_id); // 현재 사용중인 C_id 저장
	}

	// PC테이블의 FLAG를 업데이트 하기 위한 메소드
	public void updatePcFlag() {
		pcinfomodel.updatePcFlag(pcinfo);
		System.out.println("FLAG 갱신 완료");
	}

	// USE_PC 테이블에 Use_no, C_id, pc_no, m_id 입력(아직 나머지는 null)
	public void insertUsePc() {
		usepc.setC_id(cus.getC_id());
		usepc.setPc_no(pcinfo.getPc_no());
		usepc.setM_id("manager");
		usepcmodel.insertByVo(usepc);

	}

	public void updateSeat() {
		pan.setSeatInfo(1);
		System.out.println("좌석 바뀌었는지 확인");
	}

	// 클라이언트가 보낸 프로토콜을 전송받는다.
	public void receiveProtocol() {
		while (true) {
			try {
				
				ClientProtocol protocol = (ClientProtocol) input.readObject();
				System.out.println("클라이언트가 보낸 프로토콜 객체 : " +protocol.getData());
				//Order or = (Order)protocol.getData();
				ArrayList list = (ArrayList)protocol.getData();
				Order or = (Order)list.get(0);
				System.out.println("서버입니다. 클라이언트가 보낸 Order객체의 Pro_no: " + or.getPro_no());
			System.out.println("서버입니다. 클라이언트가 보낸 프로토콜을 받았어요 "+ protocol.getState());
				//System.out.println("서버입니다. 클라이언트가 보낸 프로토콜입니다 : + " +protocol);
			} catch (Exception  e) {
				System.out.println(e.getMessage());
				System.out.println("서버인데요.. 클라이언트가 보낸 프로토콜을 못받았어요..");
				System.exit(0);
			}
		}
	}

	public void closeSoc() {
	}

	@Override
	// 클라이언트와 연결이 되었으니 이제 통신을 하면 된다.
	// 먼저 클라이언트로부터 C_ID를 받아서
	public void run() {

		// System.out.println(connection.getInetAddress());
		try {
			setUpStreams();
			callPcUp(); // PC_NO을 받아오기 위해서 호출
			updatePcFlag(); // PC테이블의 Flag를 1로 갱신
			insertUsePc(); // USE_PC 테이블에 INSERT
			updateSeat();
			receiveProtocol();
			//Thread.sleep(10000);
			System.out.println("종료가 되었습니다.");
		}  catch (IOException e) {
			System.out.println("클라이언트와 스트림 연결 에러");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
