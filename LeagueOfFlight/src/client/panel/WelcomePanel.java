package client.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class WelcomePanel extends JPanel {
	public JTextField txtId;
	public JPasswordField txtPassword;
	public JButton btnLogin;
	public JButton btnJoin;
	public JButton btnNewButton;
	public JButton btnexit;

	public WelcomePanel() {
		setSize(1200, 820);
		setLayout(null);

		btnLogin = new JButton("LOGIN");
		btnLogin.setBackground(new Color(102, 153, 153));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnLogin.setBounds(942, 480, 180, 74);
		add(btnLogin);

		btnJoin = new JButton("JOIN");
		btnJoin.setBackground(new Color(102, 153, 153));
		btnJoin.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setBounds(783, 579, 339, 72);
		add(btnJoin);

		txtId = new JTextField();
		txtId.setBounds(805, 480, 125, 32);
		add(txtId);
		txtId.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setText("");
		txtPassword.setBounds(805, 522, 125, 32);
		add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setBackground(Color.BLACK);
		lblId.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblId.setBounds(730, 478, 63, 34);
		add(lblId);

		JLabel lblPass = new JLabel("PASS");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblPass.setBounds(730, 528, 73, 26);
		add(lblPass);

		JLabel lblNewLabel = new JLabel("Copyright 2018 TJSYMAX & GAMES Co.Ltd. ALL Rights Reserved.");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(12, 774, 392, 15);
		add(lblNewLabel);

		JLabel lblV = new JLabel("\uBC84\uC804.v.0.0.11.05");
		lblV.setForeground(Color.LIGHT_GRAY);
		lblV.setBounds(1094, 775, 94, 15);
		add(lblV);

		btnexit = new JButton("GAME EXIT");
		btnexit.setBackground(new Color(102, 153, 153));
		//btnexit.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/EXIT.png")));
		btnexit.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnexit.setForeground(Color.WHITE);
		btnexit.setBounds(783, 670, 339, 72);
		add(btnexit);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		Image img = new ImageIcon(ClassLoader.getSystemResource("images/IntroUniverse.jpg")).getImage();
		g.drawImage(img, 0,0,this);
	}

}
