package server;

import java.util.Timer;
import java.util.TimerTask;

public class MakeenemyEngine extends Thread {
	public int e1_x, e1_y, e2_x, e2_y, e3_x, e3_y;
	public boolean flag;
	PersonalServer server;
	

	public MakeenemyEngine(PersonalServer personalServer) {
		server = personalServer;
		flag = true;
	}

	@Override
	public void run() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				if (flag) {
					//System.out.println("server enging running");
					makeenemy1();
					makeenemy2();
					makeenemy3();
					makeenemy4();
				}
			}
		}, 0, 30000);
	}

	protected void makeenemy1() {

		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
		if (flag) {
					//System.out.println("적만드는중");
					e1_x = (int) (Math.random() * 400) + 1;
				e1_y = -70;
				server.makeenemyRequest(e1_x, e1_y,1);
			
		}
			}
		}, 1000, 3000);

	}
	
	protected void makeenemy4() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
		if (flag) {
				e1_x = (int) (Math.random() * 400) + 400;
				e1_y = -70;
				server.makeenemyRequest(e1_x, e1_y,4);
			
		}
			}
		}, 1000, 2900);

	}


	protected void makeenemy2() {

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
		
			if (flag) {
				e2_x = (int) (Math.random() * 800) + 1;
				e2_y = -70;
				server.makeenemyRequest(e2_x, e2_y,2);
			}
		

			}
		}, 4000, 8000);
	}

	protected void makeenemy3() {

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
	
			if (flag) {
				e3_x = (int) (Math.random() * 800) + 1;
				e3_y = -70;
				server.makeenemyRequest(e3_x, e3_y,3);
			}
		
			}
		}, 8000, 14000);

	}

}
