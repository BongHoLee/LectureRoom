package client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.service.ClientMain;
import client.service.LoginModel;
import client.vo.Customer;

public class LoginView extends JFrame implements ActionListener {
	ClientMain cm;			//로그인 성공시 메인프로그램 실행
	
	JTextField tfCustId; // 고객 아이디, 패스워드 입력 필드
	JPasswordField tfCustPw;
	JButton bCustSignIn, bCustLogin; // 고객 회원가입, 로그인 버튼
	
	JLabel LCustId;
	JLabel LCustPw;

	LoginModel lmodel; // DB 연동을 위한 로그인 모델 객체

	BufferedImage img = null; // 이미지를 담는 버퍼드 이미지 객체 선언
	SignInView sv; // 회원가입뷰 객체

	public LoginView() {
		addLayout();
		eventProc();
		connectDB();
	}

	public void connectDB() {
		try {
			lmodel = new LoginModel();
			System.out.println("로그인 DB 연동 성공");
		} catch (Exception e) {
			System.out.println("로그인 DB 연동 실패");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addLayout() {
		tfCustId = new JTextField(20);
		tfCustPw = new JPasswordField(20);

		bCustLogin = new JButton("로 그 인");
		bCustSignIn = new JButton("회 원 가 입");


		try {
			img = ImageIO.read(new File("src/img/Login.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}

		setTitle("Log In");
		setSize(425, 550);
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(null);

		// 레이어드판 선언 및 바운드 지정 -> 이미지와 나머지 버튼등을 층으로 나눠주기 위해서
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 409, 514);
		panel.setLayout(null);

		// 이미지가 들어갈 패널 이너클래스 호출
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

		bCustSignIn.setBounds(70, 330, 100, 50);
		panel.add(bCustSignIn);
		bCustSignIn.setForeground(new Color(36, 205, 198));
		bCustSignIn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bCustSignIn.setFocusPainted(false);
		bCustSignIn.setContentAreaFilled(false);

		bCustLogin.setBounds(220, 330, 100, 50);
		panel.add(bCustLogin);
		bCustLogin.setForeground(new Color(36, 205, 198));
		bCustLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bCustLogin.setFocusPainted(false);
		bCustLogin.setContentAreaFilled(false);

		panel.add(mp);
		add(panel);
		setVisible(true);

	}

	public void eventProc() {
		bCustLogin.addActionListener(this);
		bCustSignIn.addActionListener(this);
		tfCustPw.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == bCustLogin || evt == tfCustPw) { // 로그인 버튼 클릭 및 패스워드 텍스트필드 엔터
			try {
				login(); // 로그인 메소드 실행
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (evt == bCustSignIn) {
			signin(); // 회원가입 창으로 넘어간다.
		}

	}

	// 로그인 버튼이 눌렸을 때 실행되는 메소드
	public void login() throws SQLException {
		int status = 0; // 로그인시 상태값을 반환받음
		Customer cus = new Customer();
		cus.setC_id(tfCustId.getText());
		cus.setC_pw(tfCustPw.getText());

		status = lmodel.loginCustomer(cus);

		if (status == 0){ // ID조차 없는 경우
			tfCustId.setText("");
			tfCustPw.setText("");
			JOptionPane.showMessageDialog(null, "없는 ID 입니다");
		}
		else if (status == 1){ // ID는 있지만 PW가 틀린경우
			tfCustPw.setText("");
			JOptionPane.showMessageDialog(null, "PW가 틀렸습니다.");
		}
		else if (status == 2){ // ID와 PW가 일치하는경우.
			JOptionPane.showMessageDialog(null, "로그인 성공!");
			dispose();
			cm = new ClientMain(cus.getC_id());
		}
			
	}

	// 회원가입 버튼이 눌렸을 때 실행되는 메소드
	public void signin() {
		sv = new SignInView();
		dispose();
	}

	public static void main(String[] args) {
		LoginView view = new LoginView();
	}

	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

}
