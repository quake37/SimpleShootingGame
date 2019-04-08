package client.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.ClientUI;

public class ChatActionHandler implements ActionListener {
	ClientUI ui;
	
	public ChatActionHandler(ClientUI clientUI) {
		ui = clientUI;		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = ui.pnLounge.tfChat.getText();			
		if(msg.contains("#")) {
			JOptionPane.showMessageDialog(ui, "메세지에 # 을 넣을수 없다");
		}else {
			ui.net.sendChatRequest(msg);
			ui.pnLounge.tfChat.setText("");
			ui.pnLounge.tfChat.setFocusable(true);
		}
		
	}

}
