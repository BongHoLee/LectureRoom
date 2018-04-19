package server.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {
	SeatView seatV;
	StockView stockV;
	
	public MainView(){
		addLayout();
	}
	
	public SeatView getSeatV() {
		return seatV;
	}

	public void setSeatV(SeatView seatV) {
		this.seatV = seatV;
	}

	public StockView getStockV() {
		return stockV;
	}

	public void setStockV(StockView stockV) {
		this.stockV = stockV;
	}

	public void addLayout(){
		JTabbedPane  pane = new JTabbedPane();
		seatV = new SeatView();											//좌석 탭
		stockV = new StockView();										//재고 탭
		

		// 화면크기지정
		add("Center", pane );
		setSize(1270,765);
		setLocation(0, 0);
		setVisible( true );
		pane.addTab("좌석관리", seatV);
		pane.addTab("재고관리", stockV);
		
		
		

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
}
