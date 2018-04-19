package server.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class StockView extends JPanel implements ActionListener {
	BufferedImage img = null;	//	이미지를 담는 버퍼드 이미지 객체 선언
	JLabel lStockManage;
	JComboBox cMenu;
	
	JTable tbStockList;
	StockTableModel tbModelStock;
	
	JLabel lMenuNo, lMenuName, lStock;
	JTextField tfMenuNo, tfMenuName, tfStock;
	JButton bModify, bCancel;
	
	
	public StockView() {
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		lStockManage = new JLabel("재고 관리");
		cMenu = new JComboBox<>();
		
		tbModelStock = new StockTableModel();
		tbStockList = new JTable(tbModelStock);
		
		lMenuNo = new JLabel("메뉴 번호");
		lMenuName = new JLabel("메뉴 이름");
		lStock = new JLabel("재고");
		
		tfMenuNo = new JTextField();
		tfMenuName = new JTextField();
		tfStock = new JTextField();
		
		bModify = new JButton("변경");
		bCancel = new JButton("취소");
		
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
		
		JPanel p_stock = new JPanel();
		p_stock.setLayout(null);
		p_stock.setOpaque(false);
		p_stock.setBounds(115, 80, 1100, 600);
		
		lStockManage.setBounds(20, 20, 150, 70);
		lStockManage.setOpaque(false);
		lStockManage.setForeground(new Color(36, 205, 198));
		lStockManage.setFont(new Font("배달의민족 한나", 1, 25));
		p_stock.add(lStockManage);
		
		cMenu.setBounds(20, 100, 120, 30);
		cMenu.setOpaque(false);
		cMenu.setBackground(Color.BLACK);
		cMenu.setForeground(new Color(36, 205, 198));
		cMenu.setFont(new Font("배달의민족 한나", 1, 15));
		p_stock.add(cMenu);
		
		lMenuNo.setBounds(20, 200, 100, 30);
		lMenuNo.setOpaque(false);
		lMenuNo.setForeground(new Color(36, 205, 198));
		lMenuNo.setFont(new Font("배달의민족 한나", 1, 20));
		p_stock.add(lMenuNo);
		
		lMenuName.setBounds(20, 250, 100, 30);
		lMenuName.setOpaque(false);
		lMenuName.setForeground(new Color(36, 205, 198));
		lMenuName.setFont(new Font("배달의민족 한나", 1, 20));
		p_stock.add(lMenuName);
		
		lStock.setBounds(20, 300, 100, 30);
		lStock.setOpaque(false);
		lStock.setForeground(new Color(36, 205, 198));
		lStock.setFont(new Font("배달의민족 한나", 1, 20));
		p_stock.add(lStock);
		
		tfMenuNo.setBounds(130, 200, 250, 30);
		tfMenuNo.setOpaque(false);
		tfMenuNo.setForeground(new Color(36, 205, 198));
		tfMenuNo.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_stock.add(tfMenuNo);
		
		tfMenuName.setBounds(130, 250, 250, 30);
		tfMenuName.setOpaque(false);
		tfMenuName.setForeground(new Color(36, 205, 198));
		tfMenuName.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_stock.add(tfMenuName);
		
		tfStock.setBounds(130, 300, 250, 30);
		tfStock.setOpaque(false);
		tfStock.setForeground(new Color(36, 205, 198));
		tfStock.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_stock.add(tfStock);
		
		bModify.setBounds(130, 400, 100, 40);
		bModify.setFocusPainted(false);
	    bModify.setContentAreaFilled(false);
		bModify.setForeground(new Color(36, 205, 198));
		bModify.setFont(new Font("배달의민족 한나", 1, 17));
		bModify.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_stock.add(bModify);
		
		bCancel.setBounds(260, 400, 100, 40);
		bCancel.setFocusPainted(false);
		bCancel.setContentAreaFilled(false);
		bCancel.setForeground(new Color(36, 205, 198));
		bCancel.setFont(new Font("배달의민족 한나", 1, 17));
		bCancel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		p_stock.add(bCancel);
		
		
		tbStockList.setBounds(0, 0, 500, 500);
		tbStockList.setOpaque(false);
		tbStockList.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		tbStockList.setFont(new Font("배달의민족 한나", 1, 15));
		tbStockList.setForeground(new Color(36, 205, 198));
		tbStockList.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
	
		
		
		JScrollPane p_scroll = new JScrollPane(tbStockList);
		p_scroll.setBounds(550, 20, 500, 500);
		p_scroll.setOpaque(false);
		p_scroll.getViewport().setOpaque(false);
		p_scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		
		p_stock.add(p_scroll);
		
		
		
		
		panel.add(mp, 1);
		panel.add(p_stock, 0);
		add(panel);
		setVisible(true);
		
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
	
	class StockTableModel extends AbstractTableModel { 
		  
		ArrayList data = new ArrayList();
		String [] columnNames = {"메뉴번호","메뉴이름","재고","가격"};

		//=============================================================
		// 1. 기본적인 TabelModel  만들기
		// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
		// AbstractTabelModel에서 구현되지 않았기에...
		// 반드시 사용자 구현 필수!!!!

		    public int getColumnCount() { 
		        return columnNames.length; 
		    } 
		     
		    public int getRowCount() { 
		        return data.size(); 
		    } 

		    public Object getValueAt(int row, int col) { 
				ArrayList temp = (ArrayList)data.get( row );
		        return temp.get( col ); 
		    }
		    
		    public String getColumnName(int col){
		    	return columnNames[col];
		    }
	}
	
	class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
		 
	    public SimpleHeaderRenderer() {
	        setFont(new Font("배달의민족 한나", Font.BOLD, 15));
	        setOpaque(true);
	        setForeground(new Color(36, 205, 198));
	        setBackground(Color.BLACK);
	        setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
	    }
	     
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(value.toString());
	        return this;
	    }
	 
	}
}
