package game.display;

import game.Game;
import game.map.Map;
import game.player.Player;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayMainMenu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] buttonLabels = { "NEW GAME", "LOAD GAME", "QUIT" };
	
	private Game game;
	
	public DisplayMainMenu(Game game) {
		this.game = game;
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(256, buttonLabels.length * 64));
		add(panel);
		
		panel.setLayout(new GridLayout(buttonLabels.length, 1));
		panel.setLocation((Game.WIDTH - panel.getWidth()) / 2, (Game.HEIGHT - panel.getHeight()) / 2);
		
		for (String s : buttonLabels) {
			JButton button = new JButton(s);
			button.addActionListener(this);
			panel.add(button);			
		}
		
		JPanel backPanel = new JPanel();
		backPanel.setSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		backPanel.setLocation(0, -5);
		ImageIcon backGround = new ImageIcon("res/misc/menu.png");
		Image img = backGround.getImage() ;  
		Image newimg = img.getScaledInstance(Game.WIDTH, Game.HEIGHT, java.awt.Image.SCALE_SMOOTH ) ;  
		backGround = new ImageIcon(newimg);
		JLabel back = new JLabel();
		back.setIcon(backGround);
		backPanel.add(back);
		add(backPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(buttonLabels[0])) {
			((CardLayout)(game.panel.getLayout())).show(game.panel, "New");
		}
		
		else if (e.getActionCommand().equals(buttonLabels[1])) {
			game.map = new Map("res/saves/save1/map.txt", game);
			game.player = new Player("res/saves/save1/player.txt", game);
			((CardLayout)(game.panel.getLayout())).show(game.panel, "Game");
			game.gameStarted = true;
		}

		else if (e.getActionCommand().equals(buttonLabels[2])) {
			game.running = false;
		}
	}
}

