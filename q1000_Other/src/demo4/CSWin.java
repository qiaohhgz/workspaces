package demo4;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class CSWin extends JPanel implements ActionListener,KeyListener,Runnable,MouseListener {
	int targets=0;
    CSAct act=new CSAct();
    JButton startgame=new JButton("开始");
    JButton stopgame=new JButton("结束");
    int score=0;
    Random rdm=new Random();
    boolean start=false;
    Thread nThread;
    //游戏结束的成员变量
    JDialog jDialog=new JDialog();
    JButton ok=new JButton("OK");
    JLabel jLabel=new JLabel("游戏结束,共"+targets+"个目标,您击中了"+score+"个!");
	public CSWin()
	{
		this.addMouseListener(this);
		this.add(startgame);
		this.add(stopgame);	
		startgame.addActionListener(this);
		
		 jDialog.setLayout(new GridLayout(2,1));
    	 jDialog.add(jLabel);
    	 jDialog.add(ok);
    	 ok.addActionListener(this);
    	 jDialog.setSize(400,300);
    	 jDialog.setLocation(200, 200);
    	 jDialog.setVisible(false);
	}
	public void  paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawRect(15, 130, 800, 500);
		g.drawString("级别:", 15, 40);
		g.drawString("分数:"+score, 15, 70);
		g.setColor(new Color(0,255,0));
		if(start)
		{
			g.fillRect(15+act.getX()*10, 130+act.getY()*10, 10, 10);
		}
	}
	public static void main(String[] args) {
		
	}

	@Override
	public void run() {
		while(start){
			if(targets>=10)
			{
				nThread=null;
				jLabel.setText("游戏结束,共"+targets+"个目标,您击中了"+score+"个!");
				jDialog.setVisible(true);
				return;
			}
		act.setX(rdm.nextInt(80));
		act.setY(rdm.nextInt(50));
		repaint();
		targets=targets+1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startgame)
		{
			start=true;
			requestFocus();
		    nThread=new Thread(this);
			nThread.start();
			act.setX(rdm.nextInt(80));
			act.setY(rdm.nextInt(50));
			repaint();
		}
		else if(e.getSource()==ok)
		{
			start=false;
			jDialog.setVisible(false);
			targets=0;
			score=0;
			repaint();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=15+act.getX()*10&&e.getX()<=25+act.getX()*10&&e.getY()>=130+act.getY()*10&&e.getY()<=140+act.getY()*10)
		{
			score+=1;
		}
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
