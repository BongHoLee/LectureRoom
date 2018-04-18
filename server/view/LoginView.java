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


public class LoginView extends JFrame implements ActionListener{
	
	JTextField tfCustId;											//고객 아이디, 패스워드 입력 필드
	JPasswordField tfCustPw;
	JButton bCustLogin;						//고객 회원가입, 로그인 버튼
	
	JLabel LCustId;
	JLabel LCustPw;
	
	BufferedImage img = null;	//	이미지를 담는 버퍼드 이미지 객체 선언
	
	
	public LoginView() {
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);
		
		bCustLogin = new JButton("로 그 인");
		
//		LCustId = new JLabel("ID");
//		LCustPw = new JLabel("Password");
		
		try {
			img = ImageIO.read(new File("src/img/Login.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setTitle("Log In");
		setSize(425,550);
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(null);
		
		// 레이어드판 선언 및 바운드 지정 -> 이미지와 나머지 버튼등을 층으로 나눠주기 위해서
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 409, 514);
		panel.setLayout(null);
		
		//이미지가 들어갈 패널 이너클래스 호출
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 409, 514);
		
//		LCustId.setBounds(10,10,80,25);
//		panel.add(LCustId);
//		
//		LCustPw.setBounds(10, 40, 80, 25);
//		panel.add(LCustPw);
		
		tfCustId.setBounds(150, 96, 160, 30);
		panel.add(tfCustId);
		tfCustId.setOpaque(false);
		tfCustId.setForeground(Color.WHITE);
		tfCustId.setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		tfCustPw.setBounds(150, 223, 160, 30);
		panel.add(tfCustPw);
		tfCustPw.setOpaque(false);
		tfCustPw.setForeground(Color.WHITE);
		tfCustPw.setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		bCustLogin.setBounds(105, 330, 200, 50);
		panel.add(bCustLogin);
		bCustLogin.setForeground(Color.WHITE);
        bCustLogin.setFocusPainted(false);
        bCustLogin.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
	}
	
	public void eventProc(){
		bCustLogin.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bCustLogin){
			login();
		}
		
	}
	
	//로그인 버튼이 눌렸을 때 실행되는 메소드
	public void login(){
		Customer cust = new Customer();
		String id = tfCustId.getText();
		String pw = String.valueOf(tfCustPw.getPassword());
		
		cust.setC_id(id);
		cust.setC_pw(pw);
		
		
	}
	
	
	public static void main(String[] args) {
		LoginView view = new LoginView();
	}
	
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}
	
}
