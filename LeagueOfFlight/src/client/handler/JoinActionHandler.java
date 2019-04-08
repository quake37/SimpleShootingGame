package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class JoinActionHandler implements ActionListener {

	ClientUI ui;

	public JoinActionHandler(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id = ui.pnJoin.txtid.getText();
		String nick = ui.pnJoin.txtname.getText();
		String pass = ui.pnJoin.txtpw.getText();

		if (!pass.matches("\\w{2,8}")) {
			JOptionPane.showMessageDialog(ui, "��й�ȣ�� 2 ~ 8 ���ڴ�.");
			return;
		} else if (id.contains("#")) {
			JOptionPane.showMessageDialog(ui, "���̵� #�� ���ּ���.");
			return;
		} else if (nick.contains("#")) {
			JOptionPane.showMessageDialog(ui, "�г��ӿ� #�� ���ּ���.");
			return;
		} else if (pass.contains("#")) {
			JOptionPane.showMessageDialog(ui, "��й�ȣ�� #�� ���ּ���.");
			return;
		} else {
			ui.net.sendJoinRequest(id, nick, pass);
		}

	}

}
