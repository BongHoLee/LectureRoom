package server.view;

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
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.model.ProductModel;
import server.model.SalesModel;

public class SalesView extends JPanel implements ActionListener{
	BufferedImage img = null;	//	이미지를 담는 버퍼드 이미지 객체 선언
	JLabel lSalesManage;
	
	JLabel lDate, lDailySales, lMonthlySales, lTotalSales;
	JTextField tfDailySales, tfMonthlySales, tfTotalSales;
	JComboBox cDate;
	
	SalesModel sm;
	
	
	public SalesView() {
		connectDB();
		addLayout();
		eventProc();
		
	}
	
	public void connectDB(){
		try {
			sm = new SalesModel();
			System.out.println("매출관리 연결 성공");
		} catch (Exception e) {
			System.out.println("매출관리 연결 실패 : " + e.getMessage());
		}
	}
	
	public void addLayout(){
		lSalesManage = new JLabel("매출관리");
		
		lDate = new JLabel("날짜선택");
		lDailySales = new JLabel("일일매출");
		lMonthlySales = new JLabel("월별매출");
		lTotalSales = new JLabel("총매출");
		
		cDate = new JComboBox<>(ComboInput());
		tfDailySales = new JTextField();
		tfMonthlySales = new JTextField();
		tfTotalSales = new JTextField();
		
		
		try {
			img = ImageIO.read(new File("src/img/Seat.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setSize(1250,740);
		setLocation(0, 0);
		
		setLayout(null);
		
		// 레이어드판 선언 및 바운드 지정 -> 이미지와 나머지 버튼등을 층으로 나눠주기 위해서
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 1250, 740);
		panel.setLayout(null);
		
		//이미지가 들어갈 패널 이너클래스 호출
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 1250, 740);
		
		//좌석패널이 들어갈 패널
		JPanel p_sales = new JPanel();
		p_sales.setLayout(null);
		p_sales.setOpaque(false);
		p_sales.setBounds(115, 80, 1100, 600);
		
		
		lSalesManage.setBounds(20, 20, 150, 70);
		lSalesManage.setOpaque(false);
		lSalesManage.setForeground(new Color(36, 205, 198));
		lSalesManage.setFont(new Font("배달의민족 한나", 1, 25));
		p_sales.add(lSalesManage);
		
		lDate.setBounds(20, 150, 150, 30);
		lDate.setOpaque(false);
		lDate.setForeground(new Color(36, 205, 198));
		lDate.setFont(new Font("배달의민족 한나", 1, 20));
		p_sales.add(lDate);
		
		lDailySales.setBounds(20, 220, 150, 30);
		lDailySales.setOpaque(false);
		lDailySales.setForeground(new Color(36, 205, 198));
		lDailySales.setFont(new Font("배달의민족 한나", 1, 20));
		p_sales.add(lDailySales);
		
		lMonthlySales.setBounds(20, 290, 150, 30);
		lMonthlySales.setOpaque(false);
		lMonthlySales.setForeground(new Color(36, 205, 198));
		lMonthlySales.setFont(new Font("배달의민족 한나", 1, 20));
		p_sales.add(lMonthlySales);
		
		lTotalSales.setBounds(20, 360, 150, 30);
		lTotalSales.setOpaque(false);
		lTotalSales.setForeground(new Color(36, 205, 198));
		lTotalSales.setFont(new Font("배달의민족 한나", 1, 20));
		p_sales.add(lTotalSales);
		
		
		cDate.setBounds(200, 150, 150, 30);
		cDate.setOpaque(false);
		cDate.setBackground(Color.BLACK);
		cDate.setForeground(new Color(36, 205, 198));
		cDate.setFont(new Font("배달의민족 한나", 1, 20));
		cDate.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_sales.add(cDate);
		
		tfDailySales.setBounds(200, 220, 150, 30);
		tfDailySales.setOpaque(false);
		tfDailySales.setForeground(new Color(36, 205, 198));
		tfDailySales.setFont(new Font("배달의민족 한나", 1, 20));
		tfDailySales.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_sales.add(tfDailySales);
		
		tfMonthlySales.setBounds(200, 290, 150, 30);
		tfMonthlySales.setOpaque(false);
		tfMonthlySales.setForeground(new Color(36, 205, 198));
		tfMonthlySales.setFont(new Font("배달의민족 한나", 1, 20));
		tfMonthlySales.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_sales.add(tfMonthlySales);
		
		tfTotalSales.setBounds(200, 360, 150, 30);
		tfTotalSales.setOpaque(false);
		tfTotalSales.setForeground(new Color(36, 205, 198));
		tfTotalSales.setFont(new Font("배달의민족 한나", 1, 20));
		tfTotalSales.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_sales.add(tfTotalSales);
		
		
		JPanel p_chart = new JPanel();
		p_chart.setBounds(550, 20, 500, 500);
		p_chart.setOpaque(false);
		p_chart.setLayout(null);
		p_chart.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_sales.add(p_chart);
		
		
		
		panel.add(mp, 1);	//	레이어드판에 이미지패널 붙여줌
		panel.add(p_sales, 0);	//	레이어드판에 좌석패널 붙여줌
		add(panel);
		setVisible(true);
		
	}
	
	public Vector ComboInput(){
		Vector comboData = new Vector();
		try {
			comboData = sm.comboInputByDate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comboData;
	}
	
	
	public void eventProc(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}
}
