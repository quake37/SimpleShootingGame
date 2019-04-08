package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GameUI extends JFrame{
	ClientUI ui;
	public JLabel plane;
	public JList list;
	public JButton btnpause;
	public JButton btnexit;
	
	public Point pt;
	public JPanel gameboard;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_5;
	public JLabel lblNewLabel_6;
	public JLabel lblNewLabel_7;
	public JLabel lblNewLabel_8;
	public JLabel lblNewLabel_9;
	public JLabel lblNewLabel_13;
	public JTextField p2ListLog;
	public JLabel lblNewLabel_14;
	public JLabel label;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_12;
	public JLabel p1UserName;
	public JLabel p1UserScore;
	
	public JLabel p2UserName;
	public JLabel p2UserScore;
	
	
	public GameUI(ClientUI clientUI) {
		// TODO Auto-generated constructor stub
		ui = clientUI;
		UIManager.put("OptionPane.font", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 12));
		UIManager.put("OptionPane.messageFont", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 12));
		UIManager.put("OptionPane.buttonFont", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 13));
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - 1214 / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - 840 / 2);
		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setSize(1200, 820);
		JPanel p = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Image img = new ImageIcon(ClassLoader.getSystemResource("images/IntroUniverse.jpg") ).getImage();
				g.drawImage(img, 0,0,this);
			}
		};
		
		
		setContentPane(p);
		getContentPane().setLayout(null);
		gameboard = new JPanel() {
			protected void paintComponent(Graphics g) {
				Image img = new ImageIcon(ClassLoader.getSystemResource("images/space.gif") ).getImage();
				g.drawImage(img, 0, 0, this);
			}
		};
		gameboard.setForeground(new Color(0, 0, 0));
		gameboard.setBackground(Color.BLACK);
		gameboard.setBounds(12, 35, 950, 637);
		getContentPane().add(gameboard);
		gameboard.setLayout(null);
		
		btnexit = new JButton("EXIT");
		btnexit.setForeground(Color.DARK_GRAY);
		btnexit.setBackground(new Color(102, 153, 153));
		btnexit.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnexit.setIcon(null);
		btnexit.setBounds(833, 682, 326, 77);
		getContentPane().add(btnexit);
		
		JLabel label_1 = new JLabel("PLAYER 1");
		label_1.setForeground(new Color(153, 204, 204));
		label_1.setFont(new Font("Arial Black", Font.BOLD, 21));
		label_1.setBackground(new Color(0, 128, 128));
		label_1.setBounds(985, 36, 134, 45);
		p.add(label_1);
		
		JLabel label_2 = new JLabel("NAME");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		label_2.setBounds(974, 91, 56, 34);
		p.add(label_2);
		
		JLabel label_3 = new JLabel("SCORE");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		label_3.setBounds(974, 145, 78, 34);
		p.add(label_3);
		
		
		p1UserName = new JLabel("");
		p1UserName.setForeground(Color.WHITE);
		p1UserName.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		p1UserName.setBounds(1064, 91, 125, 34);
		p.add(p1UserName);
		
		p1UserScore = new JLabel("");
		p1UserScore.setForeground(Color.WHITE);
		p1UserScore.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		p1UserScore.setBounds(1064, 145, 125, 34);
		p.add(p1UserScore);
		
		
		
		JLabel label_8 = new JLabel("PLAYER 2");
		label_8.setForeground(new Color(233, 150, 122));
		label_8.setFont(new Font("Arial Black", Font.BOLD, 21));
		label_8.setBackground(new Color(0, 128, 128));
		label_8.setBounds(985, 353, 134, 45);
		p.add(label_8);
		
		JLabel label_9 = new JLabel("NAME");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Arial Black", Font.PLAIN, 15));
		label_9.setBounds(974, 408, 56, 34);
		p.add(label_9);
		
		p2UserName = new JLabel("");
		p2UserName.setForeground(Color.WHITE);
		p2UserName.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		p2UserName.setBounds(1063, 408, 125, 34);
		p.add(p2UserName);
		
		JLabel label_11 = new JLabel("SCORE");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Arial Black", Font.PLAIN, 15));
		label_11.setBounds(974, 462, 78, 34);
		p.add(label_11);
		
		p2UserScore = new JLabel("");
		p2UserScore.setForeground(Color.WHITE);
		p2UserScore.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		p2UserScore.setBounds(1064, 462, 125, 34);
		p.add(p2UserScore);
		
		
		
		
	}
	
}
