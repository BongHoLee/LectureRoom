package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.view.UseInfoView.Mypanel;

public class OrderView extends JPanel implements ActionListener {
	BufferedImage img = null;
	JLabel lTotalPrice;
	JTextArea taTotalMenu;
	JTextField tfTotalPrice;
	JButton bCharge, bCancel;
	
	
	public OrderView(){
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		taTotalMenu = new JTextArea();
		lTotalPrice = new JLabel("총 금액");
		tfTotalPrice = new JTextField();
		bCharge = new JButton("결제");
		bCancel = new JButton("취소");
		
		try {
			img = ImageIO.read(new File("src/img/Order.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setLayout(null);
		setBounds(0, 0, 400, 300);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 400, 300);
		panel.setLayout(null);
		
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 400, 300);
		
		JPanel p_order = new JPanel();
		p_order.setBounds(0, 0, 400, 300);
		p_order.setLayout(null);
		p_order.setOpaque(false);
		
		taTotalMenu.setBounds(25, 20, 350, 180);
		taTotalMenu.setOpaque(false);
		taTotalMenu.setForeground(new Color(36, 205, 198));
		taTotalMenu.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_order.add(taTotalMenu);
		
		lTotalPrice.setBounds(100, 210, 50, 30);
		lTotalPrice.setOpaque(false);
		lTotalPrice.setForeground(new Color(36, 205, 198));
		lTotalPrice.setFont(new Font("배달의민족 한나", 1, 15));
		p_order.add(lTotalPrice);
		
		tfTotalPrice.setBounds(180, 210, 195, 30);
		tfTotalPrice.setOpaque(false);
		tfTotalPrice.setForeground(new Color(36, 205, 198));
		tfTotalPrice.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_order.add(tfTotalPrice);
		
		bCharge.setBounds(200, 260, 80, 30);
		bCharge.setForeground(new Color(36, 205, 198));
		bCharge.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bCharge.setFocusPainted(false);
		bCharge.setContentAreaFilled(false);
		p_order.add(bCharge);
		
		bCancel.setBounds(295, 260, 80, 30);
		bCancel.setForeground(new Color(36, 205, 198));
		bCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bCancel.setFocusPainted(false);
		bCancel.setContentAreaFilled(false);
		p_order.add(bCancel);
		
		panel.add(mp, 1);
		panel.add(p_order, 0);
		add(panel);
		
	}
	
	public void eventProc(){
		bCharge.addActionListener(this);
		bCancel.addActionListener(this);
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
