package server.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
 
public class PanSeat extends JPanel implements MouseListener {
    private BufferedImage img = null;
    public JLabel[] label;										//좌석에 붙는 텍스트
    private int numSeat;								// 좌석 번호
    JPanel panImg;
    JPanel panContent;
    JLayeredPane panLayered;
    ChatView ChatV;
    
     
    public PanSeat(int numSeat) {
    	label = new JLabel[4];
        this.numSeat = numSeat;
        img("gameOff");
        setLayout(null);
 
        panImg = new InnerPanel();
        panImg.setBounds(0, 0, 99, 99);
        panImg.setOpaque(false);
                 
        //상태정보 패널
        panContent = new JPanel();
        panContent.setLayout(null);
        panContent.setBounds(0, 0, 99, 99);
        int posLabel = 15;
        for (int i = 0; i < 4; i++) {
            if (i == 0)
                label[i] = new JLabel((numSeat) + ". 빈자리");
            else
                label[i] = new JLabel("");
 
            label[i].setBounds(20, posLabel, 80, 15);
            posLabel += 16;
            label[i].setForeground(new Color(36, 205, 198));
            label[i].setFont(new Font("배달의민족 한나", 1, 12));
            panContent.add(label[i]);
        }
        panContent.setOpaque(false);
         
         
        //제이레이어패널
        panLayered = new JLayeredPane();
        panLayered.setBounds(0, 0, 1250, 700);
        panLayered.setLayout(null);
        panLayered.setOpaque(false);
        panLayered.add(panImg, new Integer(0), 0);
        panLayered.add(panContent, new Integer(1), 0);
        add(panLayered);
         
        setVisible(true);
        setOpaque(false);
        setFocusable(true);
        eventProc();
    }
  
    public JLabel[] getLabel() {
		return label;
	}

	public void setLabel(JLabel[] label) {
		this.label = label;
	}

	public int getNumSeat() {
		return numSeat;
	}

	public void setNumSeat(int numSeat) {
		this.numSeat = numSeat;
	}

	class InnerPanel extends JPanel {
        private static final long serialVersionUID = 1547128190348749556L;
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(img, 0, 0, null);
        }
    }
	
	public void setSeatInfo(int status){
		if(status == 1){
			img("gameOn");
			label[0].setText((numSeat) + ". 사용중");
			label[0].setForeground(Color.RED);
		}else if(status == 0){
			img("gameOff");
			label[0].setText((numSeat) + ". 빈 자리");
			label[0].setForeground(new Color(36, 205, 198));
		}
	}
	
 
    public void img(String filename) {
        // 이미지 받아오기 - gameOn, gameOff (로그인, 로그오프)
        try {
            img = ImageIO.read(new File("src/img/" + filename + ".png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패!");
            System.exit(0);
        }
        repaint();
    }
    
    public void eventProc(){
    	panContent.addMouseListener(this);
    }
    
    //좌석 클릭시 채팅창 띄워진다.
	@Override
	public void mouseClicked(MouseEvent e) {
		Object evt = e.getSource();
		if(evt == panContent){
			ChatV = AccessChat.chat();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}