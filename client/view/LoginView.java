package client.view;


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


public class LoginView extends JFrame implements ActionListener{
	
	JTextField tfCustId;											//고객 아이디, 패스워드 입력 필드
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCustLogin;						//고객 회원가입, 로그인 버튼
	
	JLabel LCustId;
	JLabel LCustPw;
	
	BufferedImage img = null;
	
	
	public LoginView() {
		addLayout();
	}
	
	public void addLayout(){
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);
		
		bCustLogin = new JButton("로 그 인");
		bCustSignIn = new JButton("회 원 가 입");
		
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
		
		bCustSignIn.setBounds(70, 330, 100, 50);
		panel.add(bCustSignIn);
		bCustSignIn.setForeground(Color.WHITE);
        bCustSignIn.setFocusPainted(false);
        bCustSignIn.setContentAreaFilled(false);
		
		bCustLogin.setBounds(220, 330, 100, 50);
		panel.add(bCustLogin);
		bCustLogin.setForeground(Color.WHITE);
        bCustLogin.setFocusPainted(false);
        bCustLogin.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		LoginView view = new LoginView();
	}
	
	
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}
	
}
