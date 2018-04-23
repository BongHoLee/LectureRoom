package client.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyDialog extends JDialog implements ActionListener {
	JLabel lText;
	JButton bOk;
	public MyDialog(Frame parent, String title){
		super(parent, title);
		setBounds(300, 300, 300, 150);
		setVisible(true);
		lText = new JLabel(title);
		bOk = new JButton("확인");
		lText.setBounds(50, 50, 200, 30);
		bOk.setBounds(100, 100, 100, 50);
		
	}
	
	public void eventProc(){
		bOk.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bOk){
			dispose();
		}	
	}
	public static void main(String[] args) {
		
		MyDialog md = new MyDialog(null, "성공");
	}
}
