package server;


public class GameEngine {
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGHT = 4;
	public static final int DOWN = 8;
	public static final int LEFTUP = LEFT | UP;
	public static final int LEFTDOWN = LEFT | DOWN;
	public static final int RIGHTUP = RIGHT | UP;
	public static final int RIGHTDOWN = RIGHT | DOWN;
	PersonalServer server;
	public int gan = 10;
	public GameEngine(PersonalServer server) {
		this.server = server;
	}

	public String keyPressed(int key, int px, int py) {
		// TODO Auto-generated method stub

		if (key == LEFTUP) {
			px -= gan;
			py -= gan;
		} else if (key == LEFTDOWN) {
			px -= gan;
			py += gan;
		} else if (key == RIGHTUP) {
			px += gan;
			py -= gan;
		} else if (key == RIGHTDOWN) {
			px += gan;
			py += gan;
		} else if (key == LEFT) {
			px -= gan;
		} else if (key == UP) {
			py -= gan;
		} else if (key == RIGHT) {
			px += gan;
		} else if (key == DOWN) {
			py += gan;
		}
		return px + "#" + py;
	}

	public String keyReleased(int key, int px, int py) {

		if (key == LEFTUP) {
			px -= gan;
			py -= gan;
		} else if (key == LEFTDOWN) {
			px -= gan;
			py += gan;
		} else if (key == RIGHTUP) {
			px += gan;
			py -= gan;
		} else if (key == RIGHTDOWN) {
			px += gan;
			py += gan;
		} else if (key == LEFT) {
			px -= gan;
		} else if (key == UP) {
			py -= gan;
		} else if (key == RIGHT) {
			px += gan;
		} else if (key == DOWN) {
			py += gan;
		}
		return px + "#" + py;
	}

	public String missilepos(int px, int py) {
		// TODO Auto-generated method stub
		return (px + 25) + "#" + py;
	}

}
