package demo4;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.html.Option;

public class CSMain extends JFrame {
	JMenuBar gameBar=new JMenuBar();
	JMenu game=new JMenu("游戏");
	JMenuItem startGame=new JMenuItem("开始");
	JMenuItem option=new JMenuItem("设置");
	JMenuItem exit=new JMenuItem("退出");
    public CSMain()
    {
    	//添加菜单栏
    	this.setJMenuBar(gameBar);
		gameBar.add(game);
		game.add(startGame);
		game.add(option);
		game.add(exit);
		//添加JPanel
    	CSWin win=new CSWin();
    	this.add(win);
    	this.setTitle("CS训练器");
    	this.setSize(850,725);
    	this.setLocation(510, 220);
    	this.setVisible(true);
    }
	public static void main(String[] args) {
		new CSMain();
	}

}
