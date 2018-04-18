package client.view;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.view.LoginView.Mypanel;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignInView extends JFrame implements ActionListener{
	
	JLabel LCustId, LCustPw, LCustTel;								//회원가입 라벨 정의
	JTextField tfCustTel, tfCustId;										//회원가입 필드 정의
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCancel;									//회원가입 버튼, 취소 버튼 정의
	
	BufferedImage img = null;
	
	public SignInView(){
		addLayout();
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
		
		tfCustTel.setBounds(150, 332, 160, 30);
		panel.add(tfCustTel);
		tfCustTel.setOpaque(false);
		tfCustTel.setForeground(Color.WHITE);
		tfCustTel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		bCustSignIn.setBounds(70, 400, 100, 50);
		panel.add(bCustSignIn);
		bCustSignIn.setForeground(Color.WHITE);
        bCustSignIn.setFocusPainted(false);
        bCustSignIn.setContentAreaFilled(false);
		
		bCancel.setBounds(220, 400, 100, 50);
		panel.add(bCancel);
		bCancel.setForeground(Color.WHITE);
        bCancel.setFocusPainted(false);
        bCancel.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		SignInView view = new SignInView();
	}
	
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}

}
