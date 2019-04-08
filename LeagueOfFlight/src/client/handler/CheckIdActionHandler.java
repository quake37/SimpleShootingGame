package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class CheckIdActionHandler implements ActionListener {
	ClientUI ui;
	public CheckIdActionHandler(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (ui.pnJoin.txtid.getText().equals("")) {
			JOptionPane.showMessageDialog(ui, "���̵� �Է����ּ���.");
			return;
		} else if(ui.pnJoin.txtid.getText().contains("#")) {
			JOptionPane.showMessageDialog(ui, "���̵� #�� ���ּ���.");
			return;
		}else {
			ui.net.sendcheckRequest(ui.pnJoin.txtid.getText());
		}
	}

	
}
