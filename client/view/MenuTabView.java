package client.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

public class MenuTabView extends JTabbedPane{
	FoodView FoodV;
	DrinkView DrinkV;
	OrderView OrderV;
	
	
	public MenuTabView(OrderView OrderV) {
		this.OrderV = OrderV;
		addLayout();
	}
	
	public void addLayout(){
		FoodV = new FoodView(OrderV);
		DrinkV = new DrinkView(OrderV);
		
		this.setBounds(0, 0, 400, 200);
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.setForeground(new Color(36, 205, 198));
		
		this.addTab("음식", FoodV);
		this.addTab("음료", DrinkV);
	}
}
