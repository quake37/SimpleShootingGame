package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class PersonalServer extends Thread {

	static DatagramSocket alramSocket; // �� ������������ ��Ŷ���۽� ����� UDP ����
	static List<SocketAddress> pools; // ����� ����ڵ��� �����ּҸ� ���� �÷���
	static Map<String, Account> allAccounts; // ���������� ������ ���� �÷���
	static List<Account> connectors; // ���������ؼ� ������ ����ڵ� ������ ����Ʈ�� �÷���
	static DataPool data; // ���� ���� ��ü
	static List<Account> dualList; //��Ī����Ʈ�� ��Ī��û�� ������ ��Ī���� �÷���
	List<Account> rank;

	static {
		allAccounts = new Hashtable<>();
		pools = new ArrayList<>();
		connectors = new Vector<>();
		dualList = new ArrayList<>();
		data = new DataPool();
		allAccounts = data.load();

		try {
			alramSocket = new DatagramSocket(56789);
		} catch (IOException e) {
			System.out.println("alramSocket create failed " + e.toString());
		}
	}

	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Account onUser;
	String chat;
	GameEngine Userengine;
	MakeenemyEngine enemyengine;
	boolean sendpartnerflag;
	boolean sendengineflag;

	public Account getonUser() {
		return onUser;
	}

	public PersonalServer(Socket soc) {
		socket = soc;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("[server-" + getName() + "] connected " + soc.getRemoteSocketAddress());
		} catch (IOException e) {
			System.out.println("[server-" + getName() + "] socket error " + soc.getRemoteSocketAddress());
			throw new RuntimeException();
		}

	}

	public void sendAlramToAll(String alram) {
		DatagramPacket p = new DatagramPacket(alram.getBytes(), alram.getBytes().length);
		for (SocketAddress sa : pools) {
			p.setSocketAddress(sa);
			try {
				synchronized (oos) {
					alramSocket.send(p);
				}
			} catch (IOException e) {
				System.out.println("[server-Thread] send alarm failed target : " + sa);
			}
		}
	}

	public void sendAlarmToPerson(String alram, Account t) {
		DatagramPacket p = new DatagramPacket(alram.getBytes(), alram.getBytes().length);

		p.setSocketAddress(t.getAdress());
		try {
			synchronized (oos) {
				alramSocket.send(p);
			}
		} catch (IOException e) {
			System.out.println("[server-Thread] send alarm failed target : " + socket.getRemoteSocketAddress());
		}
	}

	public void run() {
		while (!socket.isClosed()) {
			String req;
			try {
				req = (String) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				pools.remove(socket.getRemoteSocketAddress());
				connectors.remove(onUser);
				break;
			}
			// System.out.println("[server-" + getName() + "] received request : " + req);
			String[] reqs = req.split("#");

			switch (reqs[0]) {

			case "join":
				joinRequestHandle(reqs[1], reqs[2], reqs[3]);
				break;
			case "check":
				checkRequestHandle(reqs[1]);
				break;
			case "auth":
				authRequestHandle(reqs[1], reqs[2]);
				break;
			case "Logout":
				LogoutRequestHandle();
				break;
			case "keypress":
				keypressRequestHandler(Integer.parseInt(reqs[1]), Integer.parseInt(reqs[2]), Integer.parseInt(reqs[3]));
				break;
			case "keyrelease":
				keyreleaseRequestHandler(Integer.parseInt(reqs[1]), Integer.parseInt(reqs[2]),
						Integer.parseInt(reqs[3]));
				break;
			case "StartGame":
				StartGameRequestHandler(Integer.parseInt(reqs[1]));
				break;
			case "chat":
				if (reqs.length == 1)
					chatRequestHandler("");
				else
					chatRequestHandler(reqs[1]);
				break;
			case "all":
				allRequestHandler();
				break;
			case "missile":
				launchmissileRequest(Integer.parseInt(reqs[1]), Integer.parseInt(reqs[2]));
				break;
			case "rank":
				rankRequestHandler();
				break;
			case "GameExit":
				ExitGameRequestHandler();
				break;
			case "point":
				pointRequestHandler(Integer.parseInt(reqs[1]));
				break;
			case "Dualstart":
				initDualGameStart();
				break;
			case "Dualgameend":
				initDualGameExit();
				break;
			case "DualResult":
				setDualResult(reqs[1]);
				break;
			case "cancelWait":
				if(dualList.contains(onUser)) {
					dualList.remove(onUser);
				}
				break;
			case "sendpoint" :
				sendpointRequestHandler(Integer.parseInt(reqs[1]));
				break;
			}

		}

	}

	private void sendpointRequestHandler(int point) {
		if (onUser.getdual()) {
			synchronized (oos) {
				sendAlarmToPerson("partnerpoint#" + point, onUser.getpartnerAccount());

			}
		}
		
	}

	private void setDualResult(String reqs) {
		if (reqs.equals("true")) {
			onUser.setwin(onUser.getwin() + 1);
		} else {
			onUser.setlose(onUser.getlose() + 1);
		}
		onUser.setdual(false);
		onUser.setP1(true);
		onUser.setdualgameend(true);
		if(onUser.getEngine()) {
			onUser.setEngine(false);
			servergameengineend();
		}
		sendengineflag=false;
		sendAlarmToPerson("engineend", onUser);
		System.out.println("��������");

	}

	private void initDualGameExit() {
		if (onUser.getdual()) {
			System.out.println("�Ѹ��� �׾���!");
			synchronized (oos) {
				sendAlarmToPerson("dualgameend#" + "true", onUser);
				sendAlarmToPerson("dualgameend#" + "false", onUser.getpartnerAccount());

			}
		}

	}
	public void servergameengineend() {
		enemyengine.flag = false;
		enemyengine.interrupt();
		enemyengine = null;
		Userengine = null;
	}
	public void Userenginestart() {
		if (Userengine == null) {
			Userengine = new GameEngine(this);
		}
	}
	public void servergameenginestart() {
		if (enemyengine == null) {
			enemyengine = new MakeenemyEngine(this);
			enemyengine.start();
		}
	}
	private void ExitGameRequestHandler() {

		onUser.setdual(false);
		onUser.setP1(true);
		System.out.println("engine end@@");
		servergameengineend();

	}

	private void rankRequestHandler() {
		rank = new ArrayList<>();
		// TODO Auto-generated method stub
		Set<String> k = allAccounts.keySet();
		System.out.println(allAccounts.size());
		for (String i : k) {
			rank.add(allAccounts.get(i));
		}
		synchronized (oos) {
			sendRepsonse(rank);

		}
	}

	private void pointRequestHandler(int n) {
		int n2 = onUser.getAcpoint() >= n ? onUser.getAcpoint() : n;
		onUser.setPoint(n2);
		System.out.println(onUser.getAcpoint());
		sendAlramToAll("changeRank");
		sendAlramToAll("changeUsers");
		data.save(allAccounts);
	}

	private void allRequestHandler() {
		synchronized (oos) {
			sendRepsonse(connectors);
		}
	}

	private void chatRequestHandler(String txt) {
		chat = "[" + onUser.getAcname() + "]" + txt + "\n";
		synchronized (oos) {
			sendAlramToAll("newChat#" + chat);
		}
	}

	private void StartGameRequestHandler(int parseInt) {
		if (parseInt == 1) {
			synchronized (oos) {
				onUser.setP1(true);
				onUser.setdual(false);
				sendRepsonse("oneplayer#" + onUser.getAcname());
			}
			servergameenginestart();
			Userenginestart();

		} else if (parseInt == 2) {

			if (!dualList.contains(onUser)) {
				synchronized (oos) {
					sendRepsonse("twoplayer#" + onUser.getAcname());
				}
				sendpartnerflag = true;
				Userenginestart();
				if (dualList.size() >= 1) {
					System.out.println("duo clicked " + onUser.toString());
					Account p1 = dualList.get(0);
					dualList.remove(p1);
					sendAlarmToPerson("matched#" + onUser.getAcname(), p1);
					sendAlarmToPerson("matched#" + p1.getAcname(), onUser);
					p1.setdual(true);
					onUser.setdual(true);
					onUser.setEngine(true);
					p1.setpartnerAccount(onUser);
					onUser.setpartnerAccount(p1);
					onUser.setP1(false);
					servergameenginestart();

				} else {
					System.out.println("duo clicked " + onUser.toString());
					onUser.setP1(true);
					dualList.add(onUser);
				}
			}
		}
	}

	private void initDualGameStart() {
		synchronized (oos) {
			sendAlarmToPerson("dualStart#" + onUser.getP1(), onUser);

		}
	}

	private void launchmissileRequest(int px, int py) {
		if (onUser.getdual()) {
			synchronized (oos) {
				sendAlarmToPerson("missile#" + Userengine.missilepos(px, py), onUser);
				if (sendpartnerflag)
					sendAlarmToPerson("partnermissile#" + Userengine.missilepos(px, py), onUser.getpartnerAccount());
			}
		} else {
			synchronized (oos) {
				sendAlarmToPerson("missile#" + Userengine.missilepos(px, py), onUser);
			}
		}
	}

	private void keyreleaseRequestHandler(int key, int px, int py) {
		if (onUser.getdual()) {
			synchronized (oos) {

				sendAlarmToPerson("keyrelease#" + Userengine.keyReleased(key, px, py), onUser);
				if (sendpartnerflag)
					sendAlarmToPerson("partnerkeyrelease#" + Userengine.keyReleased(key, px, py),
							onUser.getpartnerAccount());
			}

		} else {
			synchronized (oos) {
				sendAlarmToPerson("keyrelease#" + Userengine.keyReleased(key, px, py), onUser);
			}
		}
	}

	private void keypressRequestHandler(int key, int px, int py) {
		if (onUser.getdual()) {
			synchronized (oos) {

				sendAlarmToPerson("keypress#" + Userengine.keyPressed(key, px, py), onUser);
				if (sendpartnerflag)
					sendAlarmToPerson("partnerkeypress#" + Userengine.keyPressed(key, px, py),
							onUser.getpartnerAccount());
			}

		} else {
			synchronized (oos) {
				sendAlarmToPerson("keypress#" + Userengine.keyPressed(key, px, py), onUser);
			}
		}
	}

	private void sendRepsonse(Object obj) {
		try {
			synchronized (oos) {
				oos.reset();
				oos.writeObject(obj);
			}
		} catch (IOException e) {
			System.out.println("[server-" + getName() + "] i/o error  : " + e.toString());
			pools.remove(socket.getRemoteSocketAddress());
			connectors.remove(onUser);
			stop();
		}
	}

	private void LogoutRequestHandle() {
		// TODO Auto-generated method stub
		if (pools.remove(socket.getRemoteSocketAddress()) && connectors.remove(onUser)) {
			synchronized (oos) {

				onUser.setdual(false);
				onUser.setP1(true);
				sendAlramToAll("LeaveConnector#" + "[" + onUser.getAcname() + "]" + "���� �α׾ƿ� �ϼ̽��ϴ�.");
				sendAlramToAll("changeUsers");
				sendRepsonse(true);
			}
		} else {
			synchronized (oos) {
				sendRepsonse(false);
			}
		}
	}

	private void authRequestHandle(String id, String pw) {

		boolean flag = true;
		for (Account i : connectors) {
			if (i.getId().equals(id)) {
				flag = false;
				break;
			}
		}
		if (flag == false) {
			sendRepsonse(false);
		} else {

			if (allAccounts.containsKey(id) && allAccounts.get(id).getPass().equals(pw)) { // public�� �ƴ� Ŭ��������
				pools.add(socket.getRemoteSocketAddress());
				onUser = allAccounts.get(id);
				onUser.setAdress(socket.getRemoteSocketAddress());
				connectors.add(onUser);
				synchronized (oos) {
					sendRepsonse(true);
					sendAlramToAll("newConnector#" + "[" + onUser.getAcname() + "]" + "����  �α��� �ϼ̽��ϴ�.");
					sendAlramToAll("changeUsers");
				}

			} else {
				synchronized (oos) {
					sendRepsonse(false);
				}
			}
		}
	}

	private void checkRequestHandle(String id) {
		if (allAccounts.containsKey(id)) {
			sendRepsonse(false);
		} else {
			sendRepsonse(true);
		}

	}

	private void joinRequestHandle(String id, String nick, String pass) {
		if (allAccounts.containsKey(id)) {
			sendRepsonse(false);
		} else {
			allAccounts.put(id, new Account(id, nick, pass));
			data.save(allAccounts);
			sendRepsonse(true);
			sendAlramToAll("changeRank");
		}
	}


	public void makeenemyRequest(int e1_x, int e1_y,int num) {
		if (onUser.getdual()) {
			synchronized (oos) {
				sendAlarmToPerson("makeenemy#" + e1_x + "#" + e1_y+"#"+num, onUser);
				if (sendpartnerflag)
					sendAlarmToPerson("partnermakeenemy#" + e1_x + "#" + e1_y+"#"+num, onUser.getpartnerAccount());
			}
		} else {
			synchronized (oos) {
				sendAlarmToPerson("makeenemy#" + e1_x + "#" + e1_y+"#"+num, onUser);
			}
		}

	}
}
