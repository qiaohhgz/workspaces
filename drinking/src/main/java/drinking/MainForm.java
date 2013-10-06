package drinking;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/29/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends JDialog implements MouseListener {
    public static final Logger log = Logger.getLogger(MainForm.class);
    private int x, y;
    private Dimension dim;
    private Insets screenInsets;

    private String message;
    private JLabel messageLabel;
    private JPanel headPan;

    public MainForm() {
    }

    private void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.screenInsets = toolkit.getScreenInsets(this.getGraphicsConfiguration());
        this.dim = toolkit.getScreenSize();
        this.initComponents();

        this.messageLabel = new JLabel(getMessage());
//        this.messageLabel.setPreferredSize(new Dimension(260, 26));
//        this.messageLabel.setVerticalTextPosition(JLabel.CENTER);
        this.messageLabel.setLocation(100, 0);
//        this.messageLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.messageLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        this.messageLabel.setForeground(Color.black);

//        this.headPan = new JPanel();
//        this.headPan.setBounds(100, 100, 200, 50);
//        this.headPan.setOpaque(false);

//        this.headPan.add(this.messageLabel);
        this.add(this.messageLabel);

//        this.add(this.headPan);
    }

    private void initComponents() {
        this.addMouseListener(this);
        this.setBackground(Color.black);
        this.setSize(dim.getSize());
        //设置为透明
//        ((JPanel) this.getContentPane()).setOpaque(false);
        //设置置顶
        this.setAlwaysOnTop(true);
        //不绘制边框
        this.setUndecorated(true);
        //不能改变大小
        this.setResizable(false);
        //设置半透明
//        AWTUtilities.setWindowOpacity(this, 0.5F);
    }

    public void run() {
        this.init();
        this.setLocation(0, 0);
        this.setVisible(true);
    }

    public void close() {
        for (int i = 0; i <= dim.getHeight(); i += 10) {
            try {
                this.setLocation(x, y - i);
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }
        }
        this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.setMessage("休息下喝口水");
        form.run();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        close();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
