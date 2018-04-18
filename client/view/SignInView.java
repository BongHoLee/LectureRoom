package client.view;

import java.awt.*;
import javax.swing.*;


import java.awt.event.*;

public class SignInView extends JFrame implements ActionListener{
	
	JLabel LCustId, LCustPw, LCustTel;								//회원가입 라벨 정의
	JTextField tfCustTel, tfCustId;										//회원가입 필드 정의
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCancel;									//회원가입 버튼, 취소 버튼 정의
	
	
	public SignInView(){
		addLayout();
	}
	
	public void addLayout(){
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);
		tfCustTel = new JTextField(20);
		
		bCancel = new JButton("취 소");
		bCustSignIn = new JButton("회 원 가 입");
		
		LCustId = new JLabel("ID");
		LCustPw = new JLabel("Password");
		LCustTel = new JLabel("Tel");
		
		setTitle("Sign In");
		setSize(280,200);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		LCustId.setBounds(10,10,80,25);
		panel.add(LCustId);
		
		LCustPw.setBounds(10, 40, 80, 25);
		panel.add(LCustPw);
		
		LCustTel.setBounds(10, 70, 80, 25);
		panel.add(LCustTel);
		
		tfCustId.setBounds(100, 10, 160, 25);
		panel.add(tfCustId);
		
		tfCustPw.setBounds(100, 40, 160, 25);
		panel.add(tfCustPw);
		
		tfCustTel.setBounds(100, 70, 160, 25);
		panel.add(tfCustTel);
		
		bCustSignIn.setBounds(10, 120, 100, 25);
		panel.add(bCustSignIn);
		
		bCancel.setBounds(160, 120, 100, 25);
		panel.add(bCancel);
		
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

}
