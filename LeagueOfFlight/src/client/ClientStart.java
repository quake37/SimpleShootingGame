 package client;

import javax.swing.JOptionPane;

public class ClientStart {
	public static void main(String[] args) {
		
		String ip = JOptionPane.showInputDialog("ip�� �Է����ּ���.");
		ClientUI ui = new ClientUI(ip);
		ui.setVisible(true);
	}
}
