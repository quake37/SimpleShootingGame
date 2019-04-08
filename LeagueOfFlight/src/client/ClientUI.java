package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import client.handler.AuthActionHandler;
import client.handler.ChatActionHandler;
import client.handler.CheckIdActionHandler;
import client.handler.ExitbtHandler;
import client.handler.GameExitbtHandler;
import client.handler.GameKeyHandler;
import client.handler.LogoutActionHandler;
import client.handler.JoinActionHandler;
import client.handler.StartbtHandler;
import client.panel.JoinPanel;
import client.panel.LoungePanel;
import client.panel.MachingWaitpanel;
import client.panel.WelcomePanel;

public class ClientUI extends JFrame {

	public ClientNetWorker net;
	public WelcomePanel pnWelcome;
	public LoungePanel pnLounge;
	public JoinPanel pnJoin;
	public MachingWaitpanel pnwait;
	public GameUI pnGame;

	
	public ClientUI(String ip) {
		setUIcomponent();
		addListeners();
		net = new ClientNetWorker(ip, this);
		// run = new ClientEngine(this);
		
	}

	private void addListeners() {
		pnGame.addKeyListener(new GameKeyHandler(this));
		pnLounge.tfChat.addActionListener(new ChatActionHandler(this));
		pnJoin.signup.addActionListener(new JoinActionHandler(this));
		pnLounge.btnbefore.addActionListener(new LogoutActionHandler(this));
		pnJoin.checkid.addActionListener(new CheckIdActionHandler(this));
		pnWelcome.btnLogin.addActionListener(new AuthActionHandler(this));
		pnLounge.btnstart.addActionListener(new StartbtHandler(this));
		pnWelcome.btnexit.addActionListener(new ExitbtHandler(this));
		pnGame.btnexit.addActionListener(new GameExitbtHandler(this));
		pnJoin.btnbefore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(pnWelcome);

			}
		});
		pnWelcome.btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(pnJoin);
			}
		});
		pnwait.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				net.sendcancelwaitRequest();
				pnLounge.btnstart.setEnabled(true);
				pnwait.dispose();
				
			}
		});

	}

	private void setUIcomponent() {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.font", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 12));
		UIManager.put("OptionPane.messageFont", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 12));
		UIManager.put("OptionPane.buttonFont", new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 13));
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}

		pnWelcome = new WelcomePanel();
		pnLounge = new LoungePanel();
		pnJoin = new JoinPanel();
		// pnGame = new GamePanel();
		pnGame = new GameUI(this);
		pnwait = new MachingWaitpanel();
		pnwait.setVisible(false);
		setTitle("Shooting Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1200, 820);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - 1214 / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - 840 / 2);
		setLocation(f_xpos, f_ypos);
		setContentPane(pnWelcome);
	}

}
