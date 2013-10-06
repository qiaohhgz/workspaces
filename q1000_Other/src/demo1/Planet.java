package demo1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class Planet extends Panel implements Runnable {
	Thread moon;
	Mycanvas yellowBall;
	double pointX[] = new double[360], pointY[] = new double[360];
	int w = 100, h = 100;
	int radius = 30;

	Planet() {
		setSize(w, h);
		setLayout(null);
		yellowBall = new Mycanvas();
		yellowBall.setColor(Color.yellow);
		add(yellowBall);
		yellowBall.setSize(12, 12);
		yellowBall.setR(12 / 2);
		pointX[0] = 0;
		pointY[0] = -radius;
		double angle = 1 * Math.PI / 180;
		for (int i = 0; i < 359; i++) {
			pointX[i + 1] = pointX[i] * Math.cos(angle) - Math.sin(angle)
					* pointY[i];
			pointY[i + 1] = pointY[i] * Math.cos(angle) + pointX[i]
					* Math.sin(angle);
		}
		for (int i = 0; i < 360; i++) {
			pointX[i] = pointX[i] + w / 2;
			pointY[i] = pointY[i] + h / 2;
		}
		yellowBall.setLocation((int) pointX[0] - yellowBall.getR(),
				(int) pointY[0] - yellowBall.getR());
		moon = new Thread(this);

	}

	public void start() {
		try {
			moon.start();
		} catch (Exception exe) {
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(w / 2 - 9, h / 2 - 9, 18, 18);
	}

	public void run() {
		int i = 0;
		while (true) {
			i = (i + 1) % 360;
			yellowBall.setLocation((int) pointX[i] - yellowBall.getR(),
					(int) pointY[i] - yellowBall.getR());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

}
