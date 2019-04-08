package client.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.ClientUI;
import client.models.UserPlane;


public class GameKeyHandler implements KeyListener {
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGHT = 4;
	public static final int DOWN = 8;
	public static final int LEFTUP = LEFT | UP;
	public static final int LEFTDOWN = LEFT | DOWN;
	public static final int RIGHTUP = RIGHT | UP;
	public static final int RIGHTDOWN = RIGHT | DOWN;
	ClientUI ui;
	int key_state = 0;

	public GameKeyHandler(ClientUI ui) {
		// TODO Auto-generated constructor stub
		this.ui = ui;
		System.out.println("check dual process@@@@@@ intro keyHandler");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			System.out.println("pressed");

			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				key_state = key_state | LEFT;
				ui.net.sendPressKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_UP) {
				key_state = key_state | UP;
				ui.net.sendPressKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_RIGHT) {
				key_state = key_state | RIGHT;
				ui.net.sendPressKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_DOWN) {
				key_state = key_state | DOWN;
				ui.net.sendPressKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_SPACE) {
				ui.net.sendMisslieLaunchRequest(ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			}
		}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
			System.out.println("release");
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				key_state = key_state ^ LEFT;
				ui.net.sendReleaseKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_UP) {
				key_state = key_state ^ UP;
				ui.net.sendReleaseKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_RIGHT) {
				key_state = key_state ^ RIGHT;
				ui.net.sendReleaseKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_DOWN) {
				key_state = key_state ^ DOWN;
				ui.net.sendReleaseKeyRequest(key_state, ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
			} else if (key == KeyEvent.VK_SPACE)
				ui.net.sendMisslieLaunchRequest(ui.net.engine.userPlane.x, ui.net.engine.userPlane.y);
		}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
