package server;

import java.io.Serializable;
import java.net.SocketAddress;

public class Account implements Serializable {
	String id;
	String name;
	String pw;
	int point;
	SocketAddress adress;

	Account partneraccount;
	boolean dual;
	boolean P1;
	boolean getEngine;
	boolean dualgameend;
	int win, lose;

	public Account(String id, String name, String pw) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		point = 0;
		dual = false;
		P1 = true;
		getEngine = false;
		dualgameend = false;
		win = 0;
		lose = 0;

	}

	public void setwin(int win) {
		this.win = win;
	}

	public void setlose(int lose) {
		this.lose = lose;
	}

	public int getwin() {
		return win;
	}

	public int getlose() {
		return lose;
	}

	public void setdualgameend(boolean dualgameend) {
		this.dualgameend = dualgameend;
	}

	public boolean getdualgameend() {
		return dualgameend;
	}

	public void setEngine(boolean getEngine) {
		this.getEngine = getEngine;

	}

	public boolean getEngine() {
		return getEngine;
	}

	public SocketAddress getAdress() {
		return adress;
	}

	public void setAdress(SocketAddress adress) {
		this.adress = adress;
	}

	public Account getpartnerAccount() {
		return partneraccount;
	}

	public void setpartnerAccount(Account partneraccount) {
		this.partneraccount = partneraccount;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean getdual() {
		return dual;
	}

	public void setdual(boolean dual) {
		this.dual = dual;
	}

	public boolean getP1() {
		return P1;
	}

	public void setP1(boolean P1) {
		this.P1 = P1;
	}

	public String getId() {
		return id;
	}

	public String getAcname() {
		return name;
	}

	public String getPass() {
		return pw;
	}

	public int getAcpoint() {
		return point;
	}

	public String toString() {
		return "¡²" + name + "¡³: " + point + "Á¡  / " + win + " win / " + lose + " lose";
	}
}
