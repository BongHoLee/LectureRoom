package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.service.PcInfoModel;
import client.service.ScWithServer;
import client.vo.PcInfo;
import protocol.ClientProtocol;

public class UseInfoView extends JPanel implements ActionListener {
	BufferedImage img = null;
	public JLabel lUseNo, lUseTime, lUseCharge;
	public JTextField tfUseNo, tfUseTime, tfUseCharge;
	public JButton bChat, bLogout;
	public PcInfoModel pcinfomodel;
	public ChatView cv;
	
	
	public UseInfoView() throws SQLException{
		pcinfomodel = new PcInfoModel();
		addLayout();
		getMyIp();
		eventProc();
	}
	
	public void addLayout(){
		lUseNo = new JLabel("PC번호");
		lUseTime = new JLabel("사용시간");
		lUseCharge = new JLabel("사용요금");
		
		tfUseNo = new JTextField();
		tfUseTime = new JTextField();
		tfUseCharge = new JTextField();
		
		bChat = new JButton("관리자채팅");
		bLogout = new JButton("로그아웃");
		
		
		
		try {
			img = ImageIO.read(new File("src/img/UseInfo.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setLayout(null);
		setBounds(0, 0, 400, 200);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 400, 200);
		panel.setLayout(null);
		
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 400, 200);
		
		JPanel p_info = new JPanel();
		p_info.setBounds(0, 0, 400, 200);
		p_info.setLayout(null);
		p_info.setOpaque(false);
		
		lUseNo.setBounds(200, 10, 60, 30);
		lUseNo.setOpaque(false);
		lUseNo.setForeground(new Color(36, 205, 198));
		lUseNo.setFont(new Font("배달의민족 한나", 1, 15));
		p_info.add(lUseNo);
		
		lUseTime.setBounds(200, 50, 60, 30);
		lUseTime.setOpaque(false);
		lUseTime.setForeground(new Color(36, 205, 198));
		lUseTime.setFont(new Font("배달의민족 한나", 1, 15));
		p_info.add(lUseTime);
		
		lUseCharge.setBounds(200, 90, 60, 30);
		lUseCharge.setOpaque(false);
		lUseCharge.setForeground(new Color(36, 205, 198));
		lUseCharge.setFont(new Font("배달의민족 한나", 1, 15));
		p_info.add(lUseCharge);
		
		tfUseNo.setBounds(280, 10, 100, 30);
		tfUseNo.setOpaque(false);
		tfUseNo.setForeground(new Color(36, 205, 198));
		tfUseNo.setFont(new Font("배달의민족 한나", 1, 15));
		tfUseNo.setBorder(BorderFactory.createEmptyBorder());
		tfUseNo.setEditable(false);
		p_info.add(tfUseNo);
		
		tfUseTime.setBounds(280, 50, 100, 30);
		tfUseTime.setOpaque(false);
		tfUseTime.setForeground(new Color(36, 205, 198));
		tfUseTime.setFont(new Font("배달의민족 한나", 1, 15));
		tfUseTime.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		tfUseTime.setEditable(false);
		p_info.add(tfUseTime);
		
		tfUseCharge.setBounds(280, 90, 100, 30);
		tfUseCharge.setOpaque(false);
		tfUseCharge.setForeground(new Color(36, 205, 198));
		tfUseCharge.setFont(new Font("배달의민족 한나", 1, 15));
		tfUseCharge.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		tfUseCharge.setEditable(false);
		p_info.add(tfUseCharge);
		
		bChat.setBounds(200, 140, 80, 30);
		bChat.setForeground(new Color(36, 205, 198));
		bChat.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bChat.setFocusPainted(false);
		bChat.setContentAreaFilled(false);
		p_info.add(bChat);
		
		bLogout.setBounds(300, 140, 80, 30);
		bLogout.setForeground(new Color(36, 205, 198));
		bLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bLogout.setFocusPainted(false);
		bLogout.setContentAreaFilled(false);
		p_info.add(bLogout);
		
		panel.add(mp, 1);
		panel.add(p_info, 0);
		add(panel);
		
	}
	
	//내 IP를 얻어와서 pc번호를 가져옴
	public void getMyIp(){
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String myip = ip.getHostAddress();
			PcInfo pcinfo = new PcInfo();
			pcinfo.setIp("/"+myip);
			int pc_no = pcinfomodel.returnPcNo(pcinfo);
			tfUseNo.setText(String.valueOf(pc_no));
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eventProc(){
		bChat.addActionListener(this);
		bLogout.addActionListener(this);
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bChat){
			cv = AccessChat.chat();
		}else if(obj == bLogout){
			ClientProtocol proto = new ClientProtocol();
			proto.setState(ClientProtocol.EXIT);
			ScWithServer.sendProtocol(proto);
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			System.exit(0);
		}
		
	}
}
