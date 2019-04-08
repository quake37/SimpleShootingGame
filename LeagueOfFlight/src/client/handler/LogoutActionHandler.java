package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class LogoutActionHandler implements ActionListener {
	ClientUI ui;
	public LogoutActionHandler(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int n = JOptionPane.showConfirmDialog(ui, "로그아웃 하시겠습니까?");
		//예=0,아니요=1,취소=2 esc = -1;
		if(n==0) {
			ui.net.sendLogoutRequest();
			
		}
	}

}
