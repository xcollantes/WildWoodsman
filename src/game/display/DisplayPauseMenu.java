package game.display;

import game.Game;

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

public class DisplayPauseMenu extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] buttonLabels = { "CONTINUE", "SAVE", "QUIT TO MENU" };

	private Game game;
	
	public DisplayPauseMenu(Game game) {
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
		ImageIcon backGround = new ImageIcon("res/misc/pausemenu.png");
		Image img = backGround.getImage() ;  
		Image newimg = img.getScaledInstance(Game.WIDTH, Game.HEIGHT, java.awt.Image.SCALE_SMOOTH ) ;  
		backGround = new ImageIcon( newimg );
		JLabel back = new JLabel();
		back.setIcon(backGround);
		backPanel.add(back);
		add(backPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(buttonLabels[0])) {
			((CardLayout)(game.panel.getLayout())).show(game.panel, "Game");
			game.paused = false;
		}
		
		else if (e.getActionCommand().equals(buttonLabels[1])) {
			game.map.save("res/saves/save1/map.txt");
			game.player.save("res/saves/save1/player.txt");
		}

		else if (e.getActionCommand().equals(buttonLabels[2])) {
			((CardLayout) (game.panel.getLayout())).show(game.panel, "Main");
			game.paused = false;
			game.gameStarted = false;
		}
	}
}
