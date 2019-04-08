package client;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.engine.ClientEngine;
import server.Account;

public class ClientNetWorker extends Thread {
	ClientUI ui;

	public Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Vector<Account> vc;
	DatagramSocket dataSocket;
	Point p;
	public String playusername;
	public String partnerusername;
	public ClientEngine engine;
	public boolean playsolo;

	public ClientNetWorker(String ip, ClientUI ui) {
		this.ui = ui;

		try {
			socket = new Socket(ip, 56789);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			dataSocket = new DatagramSocket(socket.getLocalPort());
			start();
		} catch (IOException e) {
			System.out.println("[client] network error");
			throw new RuntimeException();
		}
	}

	public void run() {

		while (!dataSocket.isClosed()) {
			try {
				DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
				dataSocket.receive(p);
				String alram = new String(p.getData(), 0, p.getLength());
				System.out.println("[client] packet received : " + alram);
				String[] alramstr = alram.split("#");
				int Player_x, Player_y, Missile_x, Missile_y, Enemy_x, Enemy_y, Player2_x, Player2_y, Missile2_x,
						Missile2_y, Enemy_2_x, Enemy_2_y, Enemy_kind, Enemy_2_kind;

				switch (alramstr[0]) {
				case "newConnector":
					ui.pnLounge.taLog.append(alramstr[1] + "\n");
					ui.pnLounge.taLog.setCaretPosition(ui.pnLounge.taLog.getText().length());
					break;
				case "LeaveConnector":
					ui.pnLounge.taLog.append(alramstr[1] + "\n");
					ui.pnLounge.taLog.setCaretPosition(ui.pnLounge.taLog.getText().length());
					break;
				case "newChat":
					ui.pnLounge.taLog.append(alramstr[1]);
					ui.pnLounge.taLog.setCaretPosition(ui.pnLounge.taLog.getText().length());
					break;
				case "changeUsers":
					sendUserListRequest();
					break;
				case "keypress":
					if (engine.flag) {
						Player_x = Integer.parseInt(alramstr[1]);
						Player_y = Integer.parseInt(alramstr[2]);
						engine.keypress(Player_x, Player_y);
					}
					break;
				case "keyrelease":
					if (engine.flag) {
						Player_x = Integer.parseInt(alramstr[1]);
						Player_y = Integer.parseInt(alramstr[2]);
						engine.keyrelease(Player_x, Player_y);
					}
					break;
				case "missile":
					if (engine.flag) {
					Missile_x = Integer.parseInt(alramstr[1]);
					Missile_y = Integer.parseInt(alramstr[2]);
					engine.missilemake(Missile_x, Missile_y);
					}
					break;
				case "changeRank":
					sendRankRequest();
					break;
				case "makeenemy":
					Enemy_x = Integer.parseInt(alramstr[1]);
					Enemy_y = Integer.parseInt(alramstr[2]);
					Enemy_kind = Integer.parseInt(alramstr[3]);
					engine.enemymake(Enemy_x, Enemy_y, Enemy_kind);
					break;
				case "partnermakeenemy":
					if (!playsolo) {
						Enemy_2_x = Integer.parseInt(alramstr[1]);
						Enemy_2_y = Integer.parseInt(alramstr[2]);
						Enemy_2_kind = Integer.parseInt(alramstr[3]);
						engine.partnerenemymake(Enemy_2_x, Enemy_2_y, Enemy_2_kind);
					}
					break;
				case "matched":
					ui.pnwait.dispose();
					ui.pnGame.btnexit.setText("SURRENDER");
					partnerusername = alramstr[1];
					playsolo = false;
					sendDualStartRequest();
					break;
				case "dualStart":
					clientenginestart(alramstr[1]);
					break;
				case "partnerkeypress":
					if (!playsolo) {
						Player2_x = Integer.parseInt(alramstr[1]);
						Player2_y = Integer.parseInt(alramstr[2]);
						engine.Partnerkeypress(Player2_x, Player2_y);
					}
					break;
				case "partnerkeyrelease":
					if (!playsolo) {
						Player2_x = Integer.parseInt(alramstr[1]);
						Player2_y = Integer.parseInt(alramstr[2]);
						engine.Partnerkeyrelease(Player2_x, Player2_y);
					}
					break;
				case "partnermissile":
					if (!playsolo) {
						Missile2_x = Integer.parseInt(alramstr[1]);
						Missile2_y = Integer.parseInt(alramstr[2]);
						engine.Partnermissilemake(Missile2_x, Missile2_y);
					}
					break;
				case "dualgameend":
					if (alramstr[1].equals("true")) {
						sendDualResultRequest(false);
						JOptionPane.showMessageDialog(ui, "당신이 졌습니다.");
					} else {
						sendDualResultRequest(true);
						JOptionPane.showMessageDialog(ui, "당신이 이겼습니다.");
					}
					break;
				case "engineend":
					ui.pnGame.btnexit.doClick(0);
					System.out.println("엔진끄겠다");
					clientengineend();
					break;
				case "partnerpoint":
					setPartnerPoint(Integer.parseInt(alramstr[1]));
					break;
				}

			} catch (IOException e) {
				dataSocket.close();
				break;
			}
		}

	}

	private void setPartnerPoint(int point) {
		if (engine.userPlane.get_flag()) {
			ui.pnGame.p2UserScore.setText(point + " Point");
		} else {
			ui.pnGame.p1UserScore.setText(point + " Point");
		}

	}

	public void clientengineend() {
		sendPointRequest();
		engine.flag = false;
		engine.interrupt();
		engine = null;
		ui.pnGame.gameboard.removeAll();
		ui.pnGame.gameboard.repaint();
		ui.pnLounge.btnstart.setEnabled(true);
	}

	public void clientenginestart(String partnername) {
		if (engine == null) {
			engine = new ClientEngine(ui, partnername);
			engine.start();
		}
	}

	public void clientenginestart() {
		if (engine == null) {
			engine = new ClientEngine(ui);
			engine.start();
		}
	}

	private void sendDualResultRequest(boolean b) {
		try {
			synchronized (oos) {

				oos.writeObject("DualResult#" + b);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendPointError//" + e.toString());

		}

	}

	private void sendDualStartRequest() {
		try {
			synchronized (oos) {

				oos.writeObject("Dualstart");

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendPointError//" + e.toString());

		}

	}

	public void sendPointRequest() {
		try {
			synchronized (oos) {
				int n = engine.point;
				oos.writeObject("point#" + n);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendPointError//" + e.toString());

		}
	}

	private void sendRankRequest() {
		try {
			synchronized (oos) {
				String req = "rank";
				oos.writeObject(req);
				List resp = (List) ois.readObject();
				resp.sort(new CustomComparator());
				Vector<String> vc = new Vector<>();
				vc.addAll(resp);
				/*
				 * for(int i = k.size()-1; i >= 0; i--) { String s = (String) resp.get(i) + i;
				 * vc.add(s); }
				 */
				ui.pnLounge.lilank.setListData(vc);

			}

		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendRankError//" + e.toString());
		}

	}

	class CustomComparator implements Comparator<Account> {

		@Override
		public int compare(Account o1, Account o2) {

			return -1 * (o1.getAcpoint() - o2.getAcpoint());
		}

	}

	public void sendJoinRequest(String id, String nick, String pass) {
		try {
			synchronized (oos) {
				String req = "join#" + id + "#" + nick + "#" + pass;
				oos.writeObject(req);
				Boolean resp = (Boolean) ois.readObject();
				if (resp) {
					JOptionPane.showMessageDialog(ui, "가입 성공이다."); // null말고 ui로주면 해당 프레임가운데로 뜬다.
					ui.setContentPane(ui.pnWelcome);
					ui.pnWelcome.txtId.setText(ui.pnJoin.txtid.getText());
					ui.pnWelcome.txtPassword.setText("");
				} else {
					JOptionPane.showMessageDialog(ui, "가입 실패다.");
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendJoinError" + e.toString());
		}

	}

	public void sendcheckRequest(String id) {
		try {
			synchronized (oos) {
				String req = "check#" + id;
				oos.writeObject(req);
				Boolean resp = (Boolean) ois.readObject();
				if (resp) {
					JOptionPane.showMessageDialog(ui, "사용가능한 아이디입니다,."); // null말고 ui로주면 해당 프레임가운데로 뜬다.
				} else {
					JOptionPane.showMessageDialog(ui, "이미 있는 존재한 아이디입니다.");
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendcheckError" + e.toString());
		}
	}

	public void sendAuthRequest(String id, String pass) {
		try {
			synchronized (oos) {
				String req = "auth#" + id + "#" + pass;
				oos.writeObject(req);
				Boolean resp = (Boolean) ois.readObject();

				if (resp) {
					// loginUser = "[" + resp.split("#")[1] + "]";
					JOptionPane.showMessageDialog(ui, "로그인 성공!");
					sendUserListRequest();
					sendRankRequest();
					ui.setContentPane(ui.pnLounge); // setContentpane으로 패널을 바꾸는 작업.
					ui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} else {
					JOptionPane.showMessageDialog(ui, "인증 실패다.\n아이디 또는 비밀번호 불일치다.");

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendAuthError" + e.toString());
		}

	}

	public void sendLogoutRequest() {
		try {
			synchronized (oos) {
				String req = "Logout";
				oos.writeObject(req);
				Boolean resp = (Boolean) ois.readObject();
				if (resp) {
					ui.setContentPane(ui.pnWelcome);
					ui.pnLounge.taLog.setText("");
				} else {
					JOptionPane.showMessageDialog(ui, "오류!");

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendLogoutError" + e.toString());
		}

	}

	public void sendPressKeyRequest(int key, int px, int py) {

		try {
			synchronized (oos) {
				String req = "keypress#" + key + "#" + px + "#" + py;
				oos.writeObject(req);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendPresskeyError" + e.toString());
		}

	}

	public void sendReleaseKeyRequest(int key, int px, int py) {
		// TODO Auto-generated method stub
		try {
			synchronized (oos) {
				String req = "keyrelease#" + key + "#" + px + "#" + py;
				oos.writeObject(req);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendReleaseKeyError" + e.toString());
		}

	}

	public void sendMisslieLaunchRequest(int px, int py) {
		try {
			synchronized (oos) {
				String req = "missile#" + px + "#" + py;
				oos.writeObject(req);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendMisslieError" + e.toString());
		}

	}

	public void sendStartGameRequest(int num) {
		// TODO Auto-generated method stub
		try {
			synchronized (oos) {
				String req = "StartGame#" + num;
				oos.writeObject(req);
				String resp = (String) ois.readObject();
				if (resp.split("#")[0].equals("oneplayer")) {
					ui.pnGame.btnexit.setText("EXIT");
					playsolo = true;
					playusername = resp.split("#")[1];
					clientenginestart();
				} else if (resp.split("#")[0].equals("twoplayer")) {
					ui.pnwait.setVisible(true);
					playusername = resp.split("#")[1];
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendStartgameError" + e.toString());
		}
	}

	public void sendChatRequest(String msg) {
		try {
			synchronized (oos) {
				String req = "chat#" + msg;
				oos.writeObject(req);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendChatError" + e.toString());
		}

	}

	private void sendUserListRequest() {
		// TODO Auto-generated method stub
		try {
			synchronized (oos) {
				String req = "all";
				oos.writeObject(req);
				List resp = (List) ois.readObject();
				Vector vc = new Vector<>();
				vc.addAll(resp);
				ui.pnLounge.tpuser.setListData(vc);
			}

		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(ui, "sendUserError" + e.toString());
		}
	}

	public void sendGameExitRequest(String string) {
		try {
			synchronized (oos) {
				String req = string;
				oos.writeObject(req);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendGameExitError" + e.toString());
		}

	}

	public void sendDualgameend() {
		try {
			synchronized (oos) {
				String req = "Dualgameend";
				oos.writeObject(req);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendGameExitError" + e.toString());
		}

	}

	public void sendcancelwaitRequest() {
		try {
			synchronized (oos) {
				String req = "cancelWait";
				oos.writeObject(req);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendGameExitError" + e.toString());
		}

	}

	public void sendPointtoPartner(int point) {
		try {
			synchronized (oos) {
				String req = "sendpoint#" + point;
				oos.writeObject(req);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ui, "sendGameExitError" + e.toString());
		}

	}

}
