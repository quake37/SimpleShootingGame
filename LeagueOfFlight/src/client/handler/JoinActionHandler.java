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
			JOptionPane.showMessageDialog(ui, "비밀번호는 2 ~ 8 글자다.");
			return;
		} else if (id.contains("#")) {
			JOptionPane.showMessageDialog(ui, "아이디에 #을 빼주세요.");
			return;
		} else if (nick.contains("#")) {
			JOptionPane.showMessageDialog(ui, "닉네임에 #을 빼주세요.");
			return;
		} else if (pass.contains("#")) {
			JOptionPane.showMessageDialog(ui, "비밀번호에 #을 빼주세요.");
			return;
		} else {
			ui.net.sendJoinRequest(id, nick, pass);
		}

	}

}
