package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.service.OrderModel;
import client.service.ScWithServer;
import client.vo.Product;
import protocol.ClientProtocol;
import protocol.Order;


//
public class OrderView extends JPanel implements ActionListener {
	public static ArrayList<Order> orderList = new ArrayList<Order>();						//order 객체를 담는 주문리스트
	BufferedImage img = null;
	JLabel lTotalPrice;
	JTextArea taTotalMenu;
	JTextField tfTotalPrice;
	JButton bCharge, bCancel;
	
	OrderModel om;
	
	
	public OrderView() throws SQLException{
		om = new OrderModel();
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		taTotalMenu = new JTextArea();
		lTotalPrice = new JLabel("총 금액");
		tfTotalPrice = new JTextField("0");
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
		
		bCharge.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bCharge.setBorder(BorderFactory.createLineBorder(Color.WHITE));;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bCharge.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bCharge.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			}
		});
		
		bCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bCancel.setBorder(BorderFactory.createLineBorder(Color.WHITE));;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bCancel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			}
		});
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
		if(evt == bCharge){						//결제 버튼 눌렀을시 orderList를 전송한다.
			//프로토콜 설정
			ClientProtocol protocol = new ClientProtocol();		
			ArrayList<Order> list = new ArrayList();
			list.addAll(orderList);
			//System.out.println("주문 목록 크기 : " +orderList.size());
			Order or1 = new Order();
			protocol.setData(list);
			protocol.setState(ClientProtocol.Order_Send);
			System.out.println("프로토콜에 들어간 객체 : " +protocol.getData());
			System.out.println("프로토콜의 상태 : " +protocol.getState());
			ScWithServer.sendProtocol(protocol);				//스레드의 Protocol 전달 메소드 실행
			orderList.clear();
			
			new MyDialog(null, "결제가 완료되었습니다");
			taTotalMenu.setText("");
			tfTotalPrice.setText("0");
			
													
		}else if(evt == bCancel){				
			try {
				om.cancelOrder(orderList);		//취소 버튼을 눌렀을시 주문목록에 있는 수만큼 다시 update
				System.out.println("주문 취소 완료.");
				taTotalMenu.setText("");
				tfTotalPrice.setText("0");
			} catch (SQLException e1) {
			}
			orderList.clear();		//취소 버튼을 눌렀을시 주문목록 지워줌
		}
		
	}
	
	public void addTotalMenu(Product pro){
		taTotalMenu.append(pro.getPro_name() + "  :  " + pro.getPro_price() + " 원 \n");
		int totalPrice = Integer.parseInt(tfTotalPrice.getText());
		totalPrice += pro.getPro_price();
		tfTotalPrice.setText(String.valueOf(totalPrice));
	}

}
