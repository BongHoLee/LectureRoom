package server.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {
	SeatView sv;
	
	public MainView(){
		addLayout();
	}
	
	public void addLayout(){
		JTabbedPane  pane = new JTabbedPane();
		sv = new SeatView();

		

		// 화면크기지정
		add("Center", pane );
		setSize(1270,765);
		setLocation(0, 0);
		setVisible( true );
		pane.addTab("좌석관리", sv);
		
		
		

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public static void main(String[] args) {
		MainView mainView = new MainView();
	}
}
