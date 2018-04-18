package client.view;


import java.awt.event.*;
import javax.swing.*;


public class LoginView extends JFrame implements ActionListener{
	
	JTextField tfCustId;											//고객 아이디, 패스워드 입력 필드
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCustLogin;						//고객 회원가입, 로그인 버튼
	
	JLabel LCustId;
	JLabel LCustPw;
	
	
	public LoginView() {
		addLayout();
	}
	
	public void addLayout(){
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);
		
		bCustLogin = new JButton("로 그 인");
		bCustSignIn = new JButton("회 원 가 입");
		
		LCustId = new JLabel("ID");
		LCustPw = new JLabel("Password");
		
		setTitle("Sign In");
		setSize(280,150);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		LCustId.setBounds(10,10,80,25);
		panel.add(LCustId);
		
		LCustPw.setBounds(10, 40, 80, 25);
		panel.add(LCustPw);
		
		tfCustId.setBounds(100, 10, 160, 25);
		panel.add(tfCustId);
		
		tfCustPw.setBounds(100, 40, 160, 25);
		panel.add(tfCustPw);
		
		bCustSignIn.setBounds(10, 80, 100, 25);
		panel.add(bCustSignIn);
		
		bCustLogin.setBounds(160, 80, 100, 25);
		panel.add(bCustLogin);
		
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
}
