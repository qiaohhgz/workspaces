package demo1;
import java.awt.*;

public class Mycanvas extends Canvas {
	int r;
	Color c;

	public void setColor(Color c) {
		this.c = c;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval(0, 0, 2 * r, 2 * r);
	}

	public int getR() {
		return r;
	}

}