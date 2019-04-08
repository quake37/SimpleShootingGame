package client.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class LoungePanel extends JPanel {
	public JTextArea taLog;
	public JTextField tfChat;
	public JButton btnbefore;
	public JList tpuser;
	public JList lilank;
	public JButton btnstart;
	public JToggleButton btn1p;
	public JToggleButton btn2p;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public JLabel lblNewLabel_2;
	public LoungePanel() {
		setSize(1200,800);
		setLayout(null);
		
		tpuser = new JList();
		tpuser.setBackground(new Color(255, 255, 255));
		tpuser.setToolTipText("\uC811\uC18D\uC790 \uCD9C\uB825\uCC3D");
		tpuser.setBounds(694, 63, 233, 273);
		add(tpuser);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(939, 63, 233, 532);
		add(scrollPane);
		lilank = new JList();
		scrollPane.setViewportView(lilank);
		lilank.setToolTipText("\uB7AD\uD0B9\uCD9C\uB825\uCC3D");
		
		JLabel lblNewLabel = new JLabel("USER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel.setBounds(694, 38, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("RANKING");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(947, 38, 102, 15);
		add(lblNewLabel_1);
		
		btnstart = new JButton("START");
		btnstart.setBackground(new Color(102, 153, 153));
		btnstart.setForeground(Color.WHITE);
		btnstart.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnstart.setBounds(848, 691, 324, 75);
		add(btnstart);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 346, 915, 273);
		add(scrollPane_1);
		taLog = new JTextArea();
		scrollPane_1.setViewportView(taLog);
		taLog.setBackground(new Color(255, 255, 255));
		taLog.setText("");
		
		tfChat = new JTextField();
		tfChat.setText("");
		tfChat.setBounds(12, 644, 915, 37);
		add(tfChat);
		tfChat.setColumns(10);
		
		btnbefore = new JButton("<");
		btnbefore.setForeground(Color.WHITE);
		btnbefore.setBackground(new Color(102, 153, 153));
		//.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/before.png")));
		btnbefore.setFont(new Font("Arial", Font.PLAIN, 17));
		btnbefore.setBounds(12, 723, 57, 43);
		add(btnbefore);
		
		btn1p = new JToggleButton("1 PLAYER");
		btn1p.setForeground(new Color(102, 102, 153));
		btn1p.setBackground(new Color(204, 153, 204));
		//btn1p.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/1P_pick.png")));
		btn1p.setFont(new Font("Arial Black", Font.PLAIN, 13));
		buttonGroup.add(btn1p);
		btn1p.setBounds(939, 605, 110, 76);
		add(btn1p);
		
		btn2p = new JToggleButton("2 PLAYER");
		btn2p.setForeground(new Color(102, 102, 153));
		btn2p.setBackground(new Color(204, 153, 204));
		//btn2p.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/2P_pick.png")));
		btn2p.setFont(new Font("Arial Black", Font.PLAIN, 13));
		buttonGroup.add(btn2p);
		btn2p.setBounds(1070, 605, 103, 76);
		add(btn2p);
		
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		Image img = new ImageIcon(ClassLoader.getSystemResource("images/IntroUniverse.jpg")).getImage();
		g.drawImage(img, 0,0,this);
	}
}
