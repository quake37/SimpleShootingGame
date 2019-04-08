package client.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MachingWaitpanel extends JFrame {
	public JButton btnCancel;
	public MachingWaitpanel() {
		setBackground(new Color(204, 204, 204));
		setLayout(null);
		setUndecorated(true);
		JLabel label = new JLabel("\uC0C1\uB300\uBC29\uC744 \uAE30\uB2E4\uB9AC\uACE0 \uC788\uC2B5\uB2C8\uB2E4...");
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		label.setForeground(Color.BLACK);
		label.setBounds(94, 24, 218, 42);
		add(label);
		
		btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		btnCancel.setBackground(new Color(102, 153, 153));
		btnCancel.setBounds(155, 109, 97, 31);
		add(btnCancel);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - 400 / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - 200 / 2);
		setLocation(f_xpos, f_ypos);
		this.setSize(400, 200);
		this.setVisible(true);
	}

}
