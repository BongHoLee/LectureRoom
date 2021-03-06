package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.service.ScWithServer;
import client.vo.Customer;
import protocol.ClientProtocol;

public class ChatView extends JFrame implements ActionListener {

	public JTextField tfChat, tfPCno;											//고객 아이디, 패스워드 입력 필드
	public JTextArea taChatAll;
	public JButton bChatSend;						//고객 회원가입, 로그인 버튼
	
	public JLabel LPCno;
	public JLabel LChat;
	
	BufferedImage img = null;	//	이미지를 담는 버퍼드 이미지 객체 선언
	
	
	
	public ChatView() {
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		tfChat = new JTextField(50);
		tfPCno = new JTextField(20);
		taChatAll = new JTextArea();
		
		bChatSend = new JButton("보내기");
		
		LPCno = new JLabel("To 관리자");
		LChat = new JLabel("입력 : ");
		
		try {
			img = ImageIO.read(new File("src/img/Chat.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setTitle("Log In");
		setSize(517,376);
		setLocation(200, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(null);
		
		// 레이어드판 선언 및 바운드 지정 -> 이미지와 나머지 버튼등을 층으로 나눠주기 위해서
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 500, 336);
		panel.setLayout(null);
		
		//이미지가 들어갈 패널 이너클래스 호출
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 500, 336);
		
		
		
		LPCno.setBounds(10, 5, 100, 30);
		panel.add(LPCno);
		LPCno.setForeground(Color.WHITE);
		
		tfPCno.setBounds(70, 5, 50, 30);
		panel.add(tfPCno);
		tfPCno.setOpaque(false);
		tfPCno.setForeground(Color.WHITE);
		tfPCno.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfPCno.setEditable(false);
		
		taChatAll.setBounds(10, 40, 480, 250);
		panel.add(taChatAll);
		taChatAll.setOpaque(false);
		taChatAll.setForeground(Color.WHITE);
		taChatAll.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
		taChatAll.setEditable(false);
		
		LChat.setBounds(10, 297, 50, 30);
		panel.add(LChat);
		LChat.setForeground(Color.WHITE);
		
		tfChat.setBounds(50, 297, 350, 30);
		panel.add(tfChat);
		tfChat.setOpaque(false);
		tfChat.setForeground(Color.WHITE);
		tfChat.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
		
		bChatSend.setBounds(410, 297, 80, 30);
		panel.add(bChatSend);
		bChatSend.setForeground(Color.WHITE);
        bChatSend.setFocusPainted(false);
        bChatSend.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
	}
	
	public void eventProc(){
		bChatSend.addActionListener(this);
		tfChat.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bChatSend){
			send();
		}else if(evt == tfChat){
			send();
		}
		
	}
	
	//보내기 버튼이 눌렸을 때 실행되는 메소드
	public void send(){
		ClientProtocol proto = new ClientProtocol();					//프로토콜을 생성
		String str = tfChat.getText();
		proto.setData(str);
		proto.setState(ClientProtocol.Chatting_Message);  			//채팅 메시지임을 알리는 상태
		ScWithServer.sendProtocol(proto); 								//채팅 전송
		tfChat.setText("");
		taChatAll.append("Me  - : " + str + " \n");

	}

	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}
}
