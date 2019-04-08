package client.models;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.GameUI;



public class EnemyMissile extends JLabel implements Serializable {
	int x;
	int y;
	int type;
	public EnemyMissile(int x, int y ,int type) {
		this.type =type;
		this.x =x+25;
		this.y= y+30;
		setBounds(get_x(), get_y(), 15, 15);
		setIcon(new ImageIcon(GameUI.class.getResource("/images/enemymissile.png")));
	}
	public boolean check() {
		if(y>570) {
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
	public int gettype() {
		return type;
	}
	
}