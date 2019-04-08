package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import client.ClientUI;

public class GameExitbtHandler implements ActionListener {
	ClientUI ui;

	public GameExitbtHandler(ClientUI clientUI) {
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ui.net.playsolo) {
			ui.net.sendGameExitRequest("GameExit");
			ui.net.clientengineend();
			JOptionPane.showMessageDialog(ui.pnGame, "죽었습니다 게임이 종료됩니다.");
			ui.setVisible(true);
			ui.pnGame.setVisible(false);
			ui.pnGame.dispose();
			ui.setContentPane(ui.pnLounge);
		} else {

			ui.net.sendDualgameend();
			ui.setVisible(true);
			ui.pnGame.setVisible(false);
			ui.pnGame.dispose();
			ui.setContentPane(ui.pnLounge);

		}

	}

}
