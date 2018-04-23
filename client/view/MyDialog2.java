package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog2 extends JDialog implements ActionListener {
	BufferedImage img = null;
	JLabel lText;
	JButton bOK;
	
	
	public MyDialog2(JFrame frame, String str){
		super(frame, str, true);
		addLayout(str);
		eventProc();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void addLayout(String str){
		lText = new JLabel(str, JLabel.CENTER);
		bOK = new JButton("확인");
		
		try {
			img = ImageIO.read(new File("src/img/MyDialog2.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패 : " + e.getMessage());
		}
		
		setLayout(null);
		setBounds(400, 400, 500, 290);
		
		
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 500, 300);
		panel.setLayout(null);
		
		Mypanel mp = new Mypanel();
		mp.setBounds(0, 0, 500, 300);
		
		JPanel p_info = new JPanel();
		p_info.setBounds(0, 0, 500, 300);
		p_info.setLayout(null);
		p_info.setOpaque(false);
		p_info.setBorder(BorderFactory.createLineBorder(new Color(36, 205, 198)));
		
		lText.setBounds(0, 50, 500, 100);
		lText.setOpaque(false);
		lText.setForeground(new Color(36, 205, 198));
		lText.setFont(new Font("배달의민족 한나", 1, 17));
		p_info.add(lText);
		
		bOK.setBounds(210, 180, 80, 30);
		bOK.setForeground(new Color(36, 205, 198));
		bOK.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(36, 205, 198)));
		bOK.setFocusPainted(false);
		bOK.setContentAreaFilled(false);
		p_info.add(bOK);
		
		
		panel.add(mp, 1);
		panel.add(p_info, 0);
		add(panel);
		
		setUndecorated(true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void eventProc(){
		bOK.addActionListener(this);
	}
	
	// 이미지가 들어갈 패널을 생성하는 이너클래스
	class Mypanel extends JPanel{
		public void paint(Graphics g){
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bOK){
			dispose();
		}
		
	}
	

}
