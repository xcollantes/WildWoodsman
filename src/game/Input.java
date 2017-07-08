package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input implements KeyListener {

	private static boolean[] keys = new boolean[256];
	
	public static boolean key_w, key_s, key_a, key_d, key_space, key_esc, key_up, key_down, key_left, key_right, key_left_brak, key_right_brak;
	
	public static void update() {
		key_w = keys[KeyEvent.VK_W];
		key_a = keys[KeyEvent.VK_A];
		key_s = keys[KeyEvent.VK_S];
		key_d = keys[KeyEvent.VK_D];
		key_space = keys[KeyEvent.VK_SPACE];
		key_esc = keys[KeyEvent.VK_ESCAPE];
		key_up = keys[KeyEvent.VK_UP];
		key_down = keys[KeyEvent.VK_DOWN];
		key_left = keys[KeyEvent.VK_LEFT];
		key_right = keys[KeyEvent.VK_RIGHT];
		key_left_brak = keys[KeyEvent.VK_OPEN_BRACKET];
		key_right_brak = keys[KeyEvent.VK_CLOSE_BRACKET];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
