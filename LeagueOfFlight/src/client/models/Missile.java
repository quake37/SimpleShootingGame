package client.models;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.GameUI;



public class Missile extends JLabel implements Serializable {
	int x;
	int y;
	boolean flag;
	public Missile(int x, int y ,boolean f) {
		flag=f;
		this.x =x;
		this.y= y;
		setBounds(x, y, 5, 5);
		setIcon(new ImageIcon(GameUI.class.getResource("/images/missile.jpg")));
	}
	public boolean check() {
		if(y<0) {
			return false;
		}
		return true;
	}
	public void set_x(int x) {
		this.x=x;
	}
	public void set_y(int y) {
		this.y=y;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public boolean getflag() {
		return flag;
	}
	
}
