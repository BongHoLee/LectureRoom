package client.view;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainView extends JDialog {
	UseInfoView UseInfoV;
	MenuTabView MenuV;
	OrderView OrderV;
	
			
	public MainView(){
		addLayout();
	}
	
	public void addLayout(){
		
		try {
			OrderV = new OrderView();
			MenuV = new MenuTabView(OrderV);
			UseInfoV = new UseInfoView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setLayout(null);
		setSize(415,735);
		setLocation(880, 0);
		setUndecorated(true);
		setVisible( true );
		
		JPanel p_up = new JPanel();
		p_up.setLayout(null);
		p_up.setBounds(0, 0, 400, 200);
		p_up.add(UseInfoV);
		this.add(p_up);
		
		MenuV.setBounds(0, 200, 400, 200);
		this.add(MenuV);
		
		JPanel p_bottom = new JPanel();
		p_bottom.setLayout(null);
		p_bottom.setBounds(0, 400, 400, 700);
		p_bottom.add(OrderV);
		this.add(p_bottom);
		
		
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	}

	
	

}
