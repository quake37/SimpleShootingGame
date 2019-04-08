package client.models;

import java.awt.Point;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.GameUI;

public class UserPlane extends JLabel implements Serializable {
	public int x;
	public int y;
	boolean flag;
	public UserPlane(boolean flag) {
		if (flag) {
			this.flag=flag;
			setIcon(new ImageIcon(GameUI.class.getResource("/images/p1.png")));
			setBounds(200, 500, 80, 80);
			x = 200;
			y = 500;
		} else {
			this.flag=flag;
			setIcon(new ImageIcon(GameUI.class.getResource("/images/p2.png")));
			setBounds(600, 500, 80, 80);
			x = 600;
			y = 500;
		}
	}
	public boolean get_flag() {
		return flag;
	}

}
