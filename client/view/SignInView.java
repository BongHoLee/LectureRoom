package client.view;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.service.SignInModel;
import client.view.LoginView.Mypanel;
import client.vo.Customer;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SignInView extends JFrame implements ActionListener{
	
	JLabel LCustId, LCustPw, LCustTel;								//회원가입 라벨 정의
	JTextField tfCustTel, tfCustId;										//회원가입 필드 정의
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCancel;									//회원가입 버튼, 취소 버튼 정의
	
	Customer cus;														//회원의 정보를 담는 Customer 객체
	SignInModel smodel;												//SignIn모델 객체 생성
	
	BufferedImage img = null;
	LoginView lv; 														// 로그인뷰 객체
	
	
	//회원가입 뷰 생성자
	public SignInView(){
		addLayout();
		eventProc();
		connectDB();
		
	}
	
	//DB연동을 위해 SignInModel의 객체를 생성
	public void connectDB() {
		try{
			smodel = new SignInModel();
			System.out.println("회원가입뷰 DB 연결 성공");
		}catch(Exception ex){
			System.out.println("회원가입뷰 DB연결 실패");
		}
		
	}
	
	public void addLayout(){
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);
		tfCustTel = new JTextField(20);
		
		bCancel = new JButton("취 소");
		bCustSignIn = new JButton("회 원 가 입");
		
//		LCustId = new JLabel("ID");
//		LCustPw = new JLabel("Password");
//		LCustTel = new JLabel("Tel");
		
		try {
			img = ImageIO.read(new File("src/img/Signin.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setTitle("Log In");
		setSize(425,550);
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(null);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 409, 514);
		panel.setLayout(null);
		
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 409, 514);
		
		tfCustId.setBounds(150, 96, 160, 30);
		panel.add(tfCustId);
		tfCustId.setOpaque(false);
		tfCustId.setForeground(new Color(36, 205, 198));
		tfCustId.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		tfCustPw.setBounds(150, 223, 160, 30);
		panel.add(tfCustPw);
		tfCustPw.setOpaque(false);
		tfCustPw.setForeground(new Color(36, 205, 198));
		tfCustPw.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		tfCustTel.setBounds(150, 332, 160, 30);
		panel.add(tfCustTel);
		tfCustTel.setOpaque(false);
		tfCustTel.setForeground(new Color(36, 205, 198));
		tfCustTel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		bCustSignIn.setBounds(70, 400, 100, 50);
		panel.add(bCustSignIn);
		bCustSignIn.setForeground(new Color(36, 205, 198));
		bCustSignIn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
        bCustSignIn.setFocusPainted(false);
        bCustSignIn.setContentAreaFilled(false);
		
		bCancel.setBounds(220, 400, 100, 50);
		panel.add(bCancel);
		bCancel.setForeground(new Color(36, 205, 198));
		bCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
        bCancel.setFocusPainted(false);
        bCancel.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
		
		
	}
	
	public void eventProc(){
		bCancel.addActionListener(this);
		bCustSignIn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bCustSignIn){
			boolean signok = signin();
			if(signok){
				System.out.println("인설트 성공");
			}else{
				System.out.println("인설트 실패");
			}
		}else if(evt == bCancel){
			cancel();
		}
		
	}
	
	//회원가입 버튼 클릭시 수행되는 메소드로써 데이터베이스에 갱신
	//회원가입 성공시 true를 반환, 실패시 false 반환
	public boolean signin(){
		boolean check = false;
		cus = new Customer();
		cus.setC_id(tfCustId.getText());
		cus.setC_pw(tfCustPw.getText());
		cus.setC_tel(tfCustTel.getText());
		try {
			smodel.insertCustomer(cus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
		
	}
	
	public void cancel(){
		lv = new LoginView();
		dispose();
	}
	
	
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}


}
