package client.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinPanel extends JPanel{
	public JTextField txtid;
	public JTextField txtname;
	public JPasswordField txtpw;
	public JButton checkid;
	public JButton signup;
	private JLabel lblNewLabel_1;
	public JButton btnbefore;
	public JoinPanel() {
		setSize(1200, 800);
		setLayout(null);
		
		btnbefore = new JButton("<");
		btnbefore.setForeground(Color.WHITE);
		btnbefore.setBackground(new Color(102, 153, 153));
		btnbefore.setFont(new Font("Arial", Font.PLAIN, 13));
		//btnbefore.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/before.png")));
		btnbefore.setBounds(23, 707, 64, 45);
		add(btnbefore);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(761, 490, 57, 30);
		add(lblId);
		
		txtid = new JTextField();
		txtid.setText("");
		txtid.setToolTipText("");
		txtid.setBounds(852, 489, 166, 31);
		add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(852, 548, 166, 30);
		add(txtname);
		txtname.setColumns(10);
		
		txtpw = new JPasswordField();
		txtpw.setText("");
		txtpw.setBounds(852, 606, 166, 31);
		add(txtpw);
		txtpw.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(761, 548, 57, 30);
		add(lblName);
		
		JLabel lblPass = new JLabel("PASS");
		lblPass.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(761, 606, 80, 31);
		add(lblPass);
		
		signup = new JButton("JOIN");
		signup.setBackground(new Color(102, 153, 153));
		signup.setFont(new Font("Arial Black", Font.PLAIN, 25));
		//signup.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/JOIN.png")));
		signup.setForeground(Color.WHITE);
		signup.setBounds(761, 675, 368, 77);
		add(signup);
		
		checkid = new JButton("\uC911\uBCF5\uD655\uC778");
		checkid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		checkid.setBackground(new Color(102, 153, 153));
		checkid.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		//checkid.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/check.png")));
		checkid.setForeground(Color.WHITE);
		checkid.setToolTipText("");
		checkid.setBounds(1030, 490, 99, 30);
		add(checkid);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		Image img = new ImageIcon(ClassLoader.getSystemResource("images/IntroUniverse.jpg")).getImage();
		g.drawImage(img, 0,0,this);
	}
	
}
