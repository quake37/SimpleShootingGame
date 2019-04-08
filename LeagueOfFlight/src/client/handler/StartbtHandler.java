package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import client.ClientUI;
import client.engine.ClientEngine;

public class StartbtHandler implements ActionListener {
	ClientUI ui;

	public StartbtHandler(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (ui.pnLounge.btn1p.isSelected()) {

				
				ui.net.sendStartGameRequest(1);
			
		
		} else if (ui.pnLounge.btn2p.isSelected()) {

			ui.net.sendStartGameRequest(2);
			ui.pnLounge.btnstart.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(ui, "인원을 선택해주세요.");
		}

	}

}
