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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.service.OrderModel;
import client.vo.Product;
import protocol.Order;

public class DrinkView extends JPanel implements ActionListener {
	BufferedImage img = null;
	JButton bDrink_1, bDrink_2, bDrink_3, bDrink_4;
	JLabel lDrink_1, lDrink_2, lDrink_3, lDrink_4;
	
	OrderModel om;
	
	OrderView OrderV;
	
	
	public DrinkView(OrderView OrderV){
		this.OrderV = OrderV;
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
		bDrink_1 = new JButton("커피");
		bDrink_2 = new JButton("콜라");
		bDrink_3 = new JButton("사이다");
		bDrink_4 = new JButton("환타");
		
		lDrink_1 = new JLabel("음료1");
		lDrink_2 = new JLabel("음료2");
		lDrink_3 = new JLabel("음료3");
		lDrink_4 = new JLabel("음료4");
		
		
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
		
		bDrink_1.setBounds(30, 30, 70, 70);
		bDrink_1.setForeground(new Color(36, 205, 198));
		bDrink_1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bDrink_1.setFocusPainted(false);
		bDrink_1.setContentAreaFilled(false);
		p_food.add(bDrink_1);
		
		bDrink_2.setBounds(120, 30, 70, 70);
		bDrink_2.setForeground(new Color(36, 205, 198));
		bDrink_2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bDrink_2.setFocusPainted(false);
		bDrink_2.setContentAreaFilled(false);
		p_food.add(bDrink_2);
		
		bDrink_3.setBounds(210, 30, 70, 70);
		bDrink_3.setForeground(new Color(36, 205, 198));
		bDrink_3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bDrink_3.setFocusPainted(false);
		bDrink_3.setContentAreaFilled(false);
		p_food.add(bDrink_3);
		
		bDrink_4.setBounds(300, 30, 70, 70);
		bDrink_4.setForeground(new Color(36, 205, 198));
		bDrink_4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bDrink_4.setFocusPainted(false);
		bDrink_4.setContentAreaFilled(false);
		p_food.add(bDrink_4);
		
		lDrink_1.setBounds(45, 110, 60, 30);
		lDrink_1.setOpaque(false);
		lDrink_1.setForeground(new Color(36, 205, 198));
		lDrink_1.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lDrink_1);
		
		lDrink_2.setBounds(135, 110, 60, 30);
		lDrink_2.setOpaque(false);
		lDrink_2.setForeground(new Color(36, 205, 198));
		lDrink_2.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lDrink_2);
		
		lDrink_3.setBounds(225, 110, 60, 30);
		lDrink_3.setOpaque(false);
		lDrink_3.setForeground(new Color(36, 205, 198));
		lDrink_3.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lDrink_3);
		
		lDrink_4.setBounds(315, 110, 60, 30);
		lDrink_4.setOpaque(false);
		lDrink_4.setForeground(new Color(36, 205, 198));
		lDrink_4.setFont(new Font("배달의민족 한나", 1, 15));
		p_food.add(lDrink_4);
		
		panel.add(mp, 1);
		panel.add(p_food, 0);
		add(panel);
		
	}
	
	public void eventProc(){
		bDrink_1.addActionListener(this);
		bDrink_2.addActionListener(this);
		bDrink_3.addActionListener(this);
		bDrink_4.addActionListener(this);
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
		if(evt == bDrink_1){
			int pro_no = 201;
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
					Product pro = om.addTotalMenu(pro_no);		//pro_no을 통해 Product 객체에 값 넣어줌
					OrderV.addTotalMenu(pro);		//Product 객체에 담긴 값으로 OrderView 에 내용 띄워줌
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bDrink_2){
			int pro_no = 202;
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
					Product pro = om.addTotalMenu(pro_no);		//pro_no을 통해 Product 객체에 값 넣어줌
					OrderV.addTotalMenu(pro);		//Product 객체에 담긴 값으로 OrderView 에 내용 띄워줌
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bDrink_3){
			int pro_no = 203;
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
					Product pro = om.addTotalMenu(pro_no);		//pro_no을 통해 Product 객체에 값 넣어줌
					OrderV.addTotalMenu(pro);		//Product 객체에 담긴 값으로 OrderView 에 내용 띄워줌
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(evt == bDrink_4){
			int pro_no = 204;
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
					Product pro = om.addTotalMenu(pro_no);		//pro_no을 통해 Product 객체에 값 넣어줌
					OrderV.addTotalMenu(pro);		//Product 객체에 담긴 값으로 OrderView 에 내용 띄워줌
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
