package client.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

public class MenuTabView extends JTabbedPane{
	FoodView FoodV;
	DrinkView DrinkV;
	
	
	public MenuTabView() {
		addLayout();
	}
	
	public void addLayout(){
		FoodV = new FoodView();
		DrinkV = new DrinkView();
		
		this.setBounds(0, 0, 400, 200);
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.setForeground(new Color(36, 205, 198));
		
		this.addTab("음식", FoodV);
		this.addTab("음료", DrinkV);
	}
}
