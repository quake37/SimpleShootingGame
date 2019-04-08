package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class AuthActionHandler implements ActionListener {
	ClientUI ui;
	public AuthActionHandler(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = ui.pnWelcome.txtId.getText();
		String pass = ui.pnWelcome.txtPassword.getText();
		if(id.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(ui, "���̵�� ��й�ȣ�� �Է��Ѵ�.");
			return;
		}
		ui.net.sendAuthRequest(id, pass);

	}

}
