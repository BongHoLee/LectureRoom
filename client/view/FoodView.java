package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.service.OrderModel;
import protocol.*;

public class FoodView extends JPanel implements ActionListener {
	
	BufferedImage img = null;
	JButton bFood_1, bFood_2, bFood_3, bFood_4;
	JLabel lFood_1, lFood_2, lFood_3, lFood_4;
	
	OrderModel om;													//Order와 관련된 데이터베이스 접근
	
	
	public FoodView(){
		connectDB();
		addLayout();
		eventProc();
	}
	
	public void connectDB(){
		try {
			om = new OrderModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addLayout(){
		bFood_1 = new JButton("라면");
		bFood_2 = new JButton("볶음밥");
		bFood_3 = new JButton("햄버거");
		bFood_4 = new JButton("핫도그");
		
		lFood_1 = new JLabel("음식1");
		lFood_2 = new JLabel("음식2");
		lFood_3 = new JLabel("음식3");
		lFood_4 = new JLabel("음식4");
		
		
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
		
		JPanel p_food = new JPanel();
		p_food.setBounds(0, 0, 400, 200);
		p_food.setLayout(null);
		p_food.setOpaque(false);
		
		bFood_1.setBounds(30, 30, 70, 70);
		bFood_1.setForeground(new Color(36, 205, 198));
		bFood_1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bFood_1.setFocusPainted(false);
		bFood_1.setContentAreaFilled(false);
		p_food.add(bFood_1);
		
		bFood_2.setBounds(120, 30, 70, 70);
		bFood_2.setForeground(new Color(36, 205, 198));
		bFood_2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bFood_2.setFocusPainted(false);
		bFood_2.setContentAreaFilled(false);
		p_food.add(bFood_2);
		
		bFood_3.setBounds(210, 30, 70, 70);
		bFood_3.setForeground(new Color(36, 205, 198));
		bFood_3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bFood_3.setFocusPainted(false);
		bFood_3.setContentAreaFilled(false);
		p_food.add(bFood_3);
		
		bFood_4.setBounds(300, 30, 70, 70);
		bFood_4.setForeground(new Color(36, 205, 198));
		bFood_4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bFood_4.setFocusPainted(false);
		bFood_4.setContentAreaFilled(false);
		p_food.add(bFood_4);
		
		lFood_1.setBounds(45, 110, 60, 30);
		lFood_1.setOpaque(false);
		lFood_1.setForeground(new Color(36, 205, 198));
		lFood_1.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lFood_1);
		
		lFood_2.setBounds(135, 110, 60, 30);
		lFood_2.setOpaque(false);
		lFood_2.setForeground(new Color(36, 205, 198));
		lFood_2.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lFood_2);
		
		lFood_3.setBounds(225, 110, 60, 30);
		lFood_3.setOpaque(false);
		lFood_3.setForeground(new Color(36, 205, 198));
		lFood_3.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lFood_3);
		
		lFood_4.setBounds(315, 110, 60, 30);
		lFood_4.setOpaque(false);
		lFood_4.setForeground(new Color(36, 205, 198));
		lFood_4.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lFood_4);
		
		panel.add(mp, 1);
		panel.add(p_food, 0);
		add(panel);
		
	}
	
	public void eventProc(){
		bFood_1.addActionListener(this);
		bFood_2.addActionListener(this);
		bFood_3.addActionListener(this);
		bFood_4.addActionListener(this);
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bFood_1){
			int pro_no = 102;
			try {
				boolean check = om.ChooseMenu(pro_no);
				if(check == false){
					JOptionPane.showMessageDialog(null, "재고가 없습니다.");
				}else{									//주문한 목록을 ArrayList에 담는다.
					Order order = new Order();
					order.setPro_no(pro_no);
					om.selectUseNo(order);		//order 객체의 pro_no, use_no이 세팅이 됨
					OrderView.orderList.add(order);			//세팅된 order 객체를 orderList에 삽입
					System.out.println(OrderView.orderList.size());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bFood_2){
			int pro_no = 103;
			try {
				boolean check = om.ChooseMenu(pro_no);
				if(check == false){
					JOptionPane.showMessageDialog(null, "재고가 없습니다.");
				}else{									//주문한 목록을 ArrayList에 담는다.
					Order order = new Order();
					order.setPro_no(pro_no);
					om.selectUseNo(order);		//order 객체의 pro_no, use_no이 세팅이 됨
					OrderView.orderList.add(order);			//세팅된 order 객체를 orderList에 삽입
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bFood_3){
			int pro_no = 104;
			try {
				boolean check = om.ChooseMenu(pro_no);
				if(check == false){
					JOptionPane.showMessageDialog(null, "재고가 없습니다.");
				}else{									//주문한 목록을 ArrayList에 담는다.
					Order order = new Order();
					order.setPro_no(pro_no);
					om.selectUseNo(order);		//order 객체의 pro_no, use_no이 세팅이 됨
					OrderView.orderList.add(order);			//세팅된 order 객체를 orderList에 삽입
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bFood_4){
			int pro_no = 105;
			try {
				boolean check = om.ChooseMenu(pro_no);
				if(check == false){
					JOptionPane.showMessageDialog(null, "재고가 없습니다.");
				}else{									//주문한 목록을 ArrayList에 담는다.
					Order order = new Order();
					order.setPro_no(pro_no);
					om.selectUseNo(order);		//order 객체의 pro_no, use_no이 세팅이 됨
					OrderView.orderList.add(order);			//세팅된 order 객체를 orderList에 삽입
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
