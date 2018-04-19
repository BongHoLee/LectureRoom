package server.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.vo.Customer;

public class SeatView extends JPanel implements ActionListener {
	BufferedImage img = null;	//	이미지를 담는 버퍼드 이미지 객체 선언
	int posX, posY;	//	좌석패널의 좌표를 계산하기 위한 인트값
	PanSeat[] pan;		//pan[0] == 좌석번호 1번.... pan[29] == 좌석번호 30
							//pan을 어떻게 넘겨줄것인지 생각해야한다.
	
	
	public SeatView() {
		pan = new PanSeat[30];	//	좌석패널 배열
		addLayout();
		eventProc();
		
	}
	
	public PanSeat[] getPan() {
		return pan;
	}

	public void setPan(PanSeat[] pan) {
		this.pan = pan;
	}

	public void addLayout(){
		
		
		try {
			img = ImageIO.read(new File("src/img/Seat.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setSize(1250,740);
		setLocation(0, 0);
		
		setLayout(null);
		
		// 레이어드판 선언 및 바운드 지정 -> 이미지와 나머지 버튼등을 층으로 나눠주기 위해서
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 1250, 740);
		panel.setLayout(null);
		
		//이미지가 들어갈 패널 이너클래스 호출
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 1250, 740);
		
		//좌석패널이 들어갈 패널
		JPanel seat30 = new JPanel();
		seat30.setLayout(null);
		seat30.setOpaque(false);
		seat30.setBounds(115, 80, 1100, 600);
		
		//좌석패널의 좌표값을 계산해주기 위한 반복문
		for (int seat = 0; seat < 30; seat++) {
		    pan[seat] = new PanSeat(seat + 1);
		    if (seat % 6 == 0 && seat != 0) {
		        posX = 0;
		        posY += 110;
		    }
		    pan[seat].setBounds(posX, posY, 99, 99);
		    if (seat%2 == 0){
		    	posX += 150;
		    }else{
		    	posX += 250;
		    }
		    seat30.add(pan[seat]);
		}
		
		panel.add(mp, 1);	//	레이어드판에 이미지패널 붙여줌
		panel.add(seat30, 0);	//	레이어드판에 좌석패널 붙여줌
		add(panel);
		setVisible(true);
		
	}
	
	
	public void eventProc(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}
}
