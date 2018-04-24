package server.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import server.model.LoginModel;
import server.service.ServerMain;
import server.vo.Manager;


public class LoginView extends JFrame implements ActionListener{
	
	JTextField tfManId;											//Manager 아이디, 패스워드 입력 필드
	JPasswordField tfManPw;
	JButton bManLogin;										//Manager  로그인 버튼
	
	LoginModel lmodel;										//DB연동을 위한 LoginModel 객체
	ServerMain sm;												//로그인 성공시 구동하기 위한 Server Main
	
	BufferedImage img = null;								//	이미지를 담는 버퍼드 이미지 객체 선언
	
	
	
	public LoginView() {
		addLayout();
		eventProc();
		connectDB();
	}
	
	//LoginModel객체를 이용하여 DB에 연결하고 DB에 접근하기 위함
	public void connectDB() {
		try {
			lmodel = new LoginModel();
			System.out.println("서버 로그인 DB연동 완료");
		} catch (Exception e) {
			System.out.println("서버 로그인 DB연동 실패");
			e.printStackTrace();
		}							
		
	}
	
	public void addLayout(){
		tfManId = new JTextField(20);
		tfManPw = new JPasswordField(20);
		
		bManLogin = new JButton("로 그 인");
		
		
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
		
		
		tfManId.setBounds(150, 96, 160, 30);
		panel.add(tfManId);
		tfManId.setOpaque(false);
		tfManId.setForeground(new Color(36, 205, 198));
		tfManId.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		tfManPw.setBounds(150, 223, 160, 30);
		panel.add(tfManPw);
		tfManPw.setOpaque(false);
		tfManPw.setForeground(new Color(36, 205, 198));
		tfManPw.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		bManLogin.setBounds(105, 330, 200, 50);
		panel.add(bManLogin);
		bManLogin.setForeground(new Color(36, 205, 198));
		bManLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
        bManLogin.setFocusPainted(false);
        bManLogin.setContentAreaFilled(false);
		
		panel.add(mp);
		add(panel);
		setVisible(true);
		
		
	}
	
	public void eventProc(){
		bManLogin.addActionListener(this);
		tfManPw.addActionListener(this);
		bManLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bManLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE));;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bManLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bManLogin.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bManLogin || evt == tfManPw){
			try {
				login();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	//로그인 버튼이 눌렸을 때 실행되는 메소드
	public void login() throws SQLException{
		int status = 0;
		Manager man = new Manager();
		man.setM_id(tfManId.getText());
		man.setM_pw(tfManPw.getText());
		
		status = lmodel.loginManager(man);
		
		if(status == 0){
			tfManId.setText("");
			tfManPw.setText("");
			new MyDialog(null, "ID를 다시 확인하세요");
		}else if(status == 1){
			tfManPw.setText("");
			new MyDialog(null, "PW를 확인하세요");
		}else if(status == 2){
			new MyDialog(null, "로그인 성공!");
			sm = new ServerMain();			//로그인 성공시 ServerMain 구동
			dispose();
		}
		
		
		
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
