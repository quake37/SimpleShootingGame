package client.engine;

import java.util.Iterator;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.ClientUI;
import client.models.EnemyMissile;
import client.models.EnemyPlane;
import client.models.Missile;
import client.models.UserPlane;

public class ClientEngine extends Thread {
	ClientUI ui;
	Point userp, partnerp, enemyp, enemymissilep;
	public List<EnemyPlane> enemeylist;

	public int point = 0;
	Rectangle size;
	public boolean flag;
	public UserPlane userPlane;
	public UserPlane partnerPlane;
	public boolean p1;
	public boolean solo;
	Rectangle enemybounds;
	Rectangle userbounds;
	Rectangle enemymissilebounds;
	public int threadcount = 0;
	EnemyPlane e;

	public ClientEngine(ClientUI ui) {
		this.ui = ui;
		size = ui.pnGame.gameboard.getBounds();
		size.setBounds(65, 50, 840, 530);
		enemeylist = new Vector<>();
		flag = true;
		solo = true;
	}

	public ClientEngine(ClientUI ui, String string) {
		if (string.equals("true")) {
			p1 = true;
		} else {
			p1 = false;
		}
		this.ui = ui;
		size = ui.pnGame.gameboard.getBounds();
		size.setBounds(65, 50, 840, 530);
		enemeylist = new Vector<>();
		flag = true;
		solo = false;
	}

	@Override
	public void run() {
		if (solo) {
			userPlane = new UserPlane(true);
			ui.pnGame.gameboard.add(userPlane);
			ui.pnGame.p1UserName.setText(ui.net.playusername);
			ui.pnGame.p1UserScore.setText(point + " Point");

		} else {
			if (p1) {
				userPlane = new UserPlane(true);
				partnerPlane = new UserPlane(false);
			} else {
				userPlane = new UserPlane(false);
				partnerPlane = new UserPlane(true);
			}
			ui.pnGame.gameboard.add(userPlane);
			ui.pnGame.gameboard.add(partnerPlane);

			if (p1) {
				ui.pnGame.p1UserName.setText(ui.net.playusername);
				ui.pnGame.p1UserScore.setText(point + " Point");
				ui.pnGame.p2UserName.setText(ui.net.partnerusername);
				ui.pnGame.p2UserScore.setText(point + " Point");
			} else {
				ui.pnGame.p2UserName.setText(ui.net.playusername);
				ui.pnGame.p2UserScore.setText(point + " Point");
				ui.pnGame.p1UserName.setText(ui.net.partnerusername);
				ui.pnGame.p1UserScore.setText(point + " Point");
			}
		}
//		
		ui.setVisible(false);
		ui.pnGame.setVisible(true);
		ui.pnGame.requestFocus();
		ui.pnGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void keypress(int player_x, int player_y) {
		if (flag) {
			userPlane.setLocation(player_x, player_y);
			if (size.intersects(userPlane.getBounds())) {
				userPlane.x = player_x;
				userPlane.y = player_y;
			}
			ui.pnGame.gameboard.repaint();
		}
	}

	public void keyrelease(int player_x, int player_y) {
		if (flag) {
			userPlane.setLocation(player_x, player_y);
			if (size.intersects(userPlane.getBounds())) {
				userPlane.x = player_x;
				userPlane.y = player_y;
			}
			ui.pnGame.gameboard.repaint();
		}
	}

	public void missilemake(int missile_x, int missile_y) {
		if (flag) {
			Missile e = new Missile(missile_x, missile_y, true);
			ui.pnGame.gameboard.add(e);
			ui.pnGame.gameboard.repaint();
			missilemove(e);
		}
	}

	public void Partnerkeypress(int player2_x, int player2_y) {
		if (flag) {
			partnerPlane.setLocation(player2_x, player2_y);
			if (size.intersects(partnerPlane.getBounds())) {
				partnerPlane.x = player2_x;
				partnerPlane.y = player2_y;
			}
			ui.pnGame.gameboard.repaint();
		}
	}

	public void Partnerkeyrelease(int player2_x, int player2_y) {
		if (flag) {
			partnerPlane.setLocation(player2_x, player2_y);
			if (size.intersects(partnerPlane.getBounds())) {
				partnerPlane.x = player2_x;
				partnerPlane.y = player2_y;
			}
			ui.pnGame.gameboard.repaint();
		}
	}

	public void Partnermissilemake(int missile2_x, int missile2_y) {
		if (flag) {
			Missile e = new Missile(missile2_x, missile2_y, false);
			ui.pnGame.gameboard.add(e);
			ui.pnGame.gameboard.repaint();
			missilemove(e);
		}
	}

	public void enemymake(int enemy1_x, int enemy1_y, int num) {
		if (flag) {
			e = new EnemyPlane(enemy1_x, enemy1_y, num);

			synchronized (enemeylist) {
				if (enemeylist.add(e) && flag) {
					synchronized (e) {
						ui.pnGame.gameboard.add(e);
						ui.pnGame.gameboard.repaint();

						enemymove(e);

					}

				}
			}
		}

	}

	public void partnerenemymake(int enemy1_2_x, int enemy1_2_y, int num) {
		if (flag) {
			e = new EnemyPlane(enemy1_2_x, enemy1_2_y, num);

			synchronized (enemeylist) {
				if (enemeylist.add(e) && flag) {
					synchronized (e) {
						ui.pnGame.gameboard.add(e);
						ui.pnGame.gameboard.repaint();

						enemymove(e);

					}

				}
			}
		}
	}

	private void enemymove(EnemyPlane enemy) {

		// EnemyPlane enemy = e;
		synchronized (enemy) {

			new Timer().schedule(new TimerTask() {

				boolean missileflag = true;
				boolean missileflag2 = true;

				@Override
				public void run() {
					userbounds = userPlane.getBounds();
					userbounds.setSize(30, 30);
					if (flag) {
						if (enemy.check()) {
							try {
								enemyp = enemy.getLocation();
								if (enemy.getkind() == 1) {
									enemy.set_x(enemyp.x + 1);
									enemy.set_y(enemyp.y + 5);
								} else if (enemy.getkind() == 2) {
									enemy.set_x(enemyp.x);
									enemy.set_y(enemyp.y + 8);
								} else if (enemy.getkind() == 3) {
									enemy.set_x(enemyp.x);
									enemy.set_y(enemyp.y + 1);

								} else if (enemy.getkind() == 4) {
									enemy.set_x(enemyp.x - 1);
									enemy.set_y(enemyp.y + 5);
								}
								if (enemy.get_y() > 100
										&& (enemy.getkind() == 1 || enemy.getkind() == 4 || enemy.getkind() == 3)) {
									if (missileflag) {
										EnemyMissile e_missile = new EnemyMissile(enemy.get_x(), enemy.get_y(), 1);
										ui.pnGame.gameboard.add(e_missile);
										enemymissilemove(e_missile);
										missileflag = false;
									}

								}
								if (enemy.get_y() > 300 && enemy.getkind() == 3) {
									if (missileflag2) {
										EnemyMissile e_missile = new EnemyMissile(enemy.get_x(), enemy.get_y(), 1);
										ui.pnGame.gameboard.add(e_missile);
										enemymissilemove(e_missile);
										missileflag2 = false;
									}
								}
								enemybounds = enemy.getBounds();
								enemybounds.setSize(15, 15);
								synchronized (enemy) {
									enemy.setLocation(enemy.get_x(), enemy.get_y());

								}
								ui.pnGame.gameboard.repaint();

								if (userbounds.intersects(enemybounds)) {

									enemy.setLocation(1000, 580);
									userPlane.setLocation(1200, 300);
									ui.pnGame.gameboard.remove(userPlane);
									if (solo) {
										//JOptionPane.showMessageDialog(ui.pnGame, "죽었습니다 게임이종료됩니다.");
										ui.pnGame.btnexit.doClick(0);
									} else {
										ui.net.sendDualgameend();
									}
									flag = false;
								}

							} catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("ERROR!!!!");
							}
						} else {

							ui.pnGame.gameboard.remove(enemy);
							synchronized (enemy) {
								if (enemeylist.contains(enemy))
									enemeylist.remove(enemy);
							}
							ui.pnGame.gameboard.repaint();
							this.cancel();
						}

					}

				}
			}, 1000, 30);
		}
	}

	public void missilemove(Missile m) {
		new Timer().schedule(new TimerTask() {
			Missile missile = m;

			@Override
			public void run() {
				if (missile.check()) {
					try {
						userp = missile.getLocation();
						missile.set_x(userp.x);
						missile.set_y(userp.y - 10);
						missile.setLocation(missile.get_x(), missile.get_y());

						if (!enemeylist.isEmpty()) {
							enemeylist.iterator();
							for (Iterator<EnemyPlane> j = enemeylist.iterator(); j.hasNext();) {
								EnemyPlane i = j.next();
								if (missile.getBounds().intersects(i.getBounds())) {

									i.set_life(i.get_life() - 1);
									missile.setLocation(1000, -10);

									if (i.get_life() < 0) {
										if (missile.getflag()) {
											if (i.getkind() == 1) {
												point += 20;
											} else if (i.getkind() == 2) {
												point += 50;
											} else if (i.getkind() == 3) {
												point += 80;
											} else if (i.getkind() == 4) {
												point += 20;
											}
											if (userPlane.get_flag()) {
												ui.pnGame.p1UserScore.setText(point + " Point");
											} else {
												ui.pnGame.p2UserScore.setText(point + " Point");
											}
										}
										if (!solo) {
											ui.net.sendPointtoPartner(point);
										}
										i.setLocation(1000, 400);
									}
									break;

								}
							}
						}
						ui.pnGame.gameboard.repaint();

					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("나니이이이이이잇!!!!");
					}
				} else {
					ui.pnGame.gameboard.remove(missile);
					ui.pnGame.gameboard.repaint();
					this.cancel();

				}

			}
		}, 5, 5);

	}

	public void enemymissilemove(EnemyMissile e) {
		new Timer().schedule(new TimerTask() {
			EnemyMissile enemymissile = e;

			@Override
			public void run() {
				if (flag) {
					if (enemymissile.check()) {
						try {
							enemymissilep = enemymissile.getLocation();
							enemymissile.set_x(enemymissilep.x);
							enemymissile.set_y(enemymissilep.y + 3);
							enemymissile.setLocation(enemymissile.get_x(), enemymissile.get_y());

							userbounds = userPlane.getBounds();
							userbounds.setSize(30, 10);
							enemymissilebounds = enemymissile.getBounds();
							enemymissilebounds.setSize(5, 5);

							if (enemymissilebounds.intersects(userbounds)) {
								flag = false;
								enemymissile.setLocation(1000, 580);
								userPlane.setLocation(1200, 300);
								ui.pnGame.gameboard.remove(userPlane);
								if (solo) {
									ui.pnGame.btnexit.doClick(0);

								} else {
									ui.net.sendDualgameend();
								}
							}

							ui.pnGame.gameboard.repaint();

						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("나니이이이이이잇!!!!");
						}
					} else {
						ui.pnGame.gameboard.remove(enemymissile);
						ui.pnGame.gameboard.repaint();
						this.cancel();
					}

				}

			}
		}, 5, 15);

	}

}
