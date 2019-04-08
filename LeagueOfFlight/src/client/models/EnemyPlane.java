package client.models;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.GameUI;

public class EnemyPlane extends JLabel implements Serializable {
	int x;
	int y;
	int life;
	int kind;
	public EnemyPlane(int x, int y, int num) {
		this.x = x;
		this.y = y;
		if (num == 1) {
			kind =num;
			setBounds(x, y, 50, 60);
			setIcon(new ImageIcon(GameUI.class.getResource("/images/enemy_1.png")));
			life = 5;
		}else if(num==2) {
			kind =num;
			setBounds(x, y, 50, 65);
			setIcon(new ImageIcon(GameUI.class.getResource("/images/enemy_2.png")));
			life=10;
		}else if(num==3) {
			kind =num;
			setBounds(x, y, 60, 60);
			setIcon(new ImageIcon(GameUI.class.getResource("/images/enemy_3.png")));
			life=20;
		}else if(num==4) {
			kind =num;
			setBounds(x, y, 50, 60);
			setIcon(new ImageIcon(GameUI.class.getResource("/images/enemy_1.png")));
			life = 5;
		}
	}

	public int getkind() {
		return kind;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public int get_life() {
		return life;
	}
	public void set_x(int x) {
		this.x=x;
	}
	public void set_y(int y) {
		this.y=y;
	}
	public void set_life(int life) {
		this.life=life;
	}
	public int get_type() {
		return kind;
	}

	public boolean check() {
		if (y > 570) {
			return false;
		}
		return true;

	}
}