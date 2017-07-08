package game.sound;

import game.Input;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * Songs Available: "8 bit Kanye.mp3" "8 bit Happy.mp3" "8 bit Royals.mp3"
 * "8 bit Timber.mp3" "Danger Zone.mp3" "Gonk.mp3" "Minecraft.mp3" "Timber.mp3"
 * "Hey Boy.mp3"
 */

public class SoundThreadPlaylist implements Runnable {

	private Thread thread;
	private boolean nextSong;
	private boolean changeSong;

	private int i = 0;
	private Player playMp3;

	public SoundThreadPlaylist() {
		thread = new Thread(this);
		nextSong = true;
		changeSong = false;
		thread.start();
	}

	public void update() {

		if ((Input.key_left_brak || Input.key_right_brak) && thread.isAlive()) {
			if (!changeSong) {
				if (Input.key_right_brak) i++;
				else i--;
				if (i < 0) i = 7;
				if (i > 7) i = 0;
				nextSong = false;
				changeSong = true;
				playMp3.close();
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nextSong = true;
				thread = new Thread(this);
				thread.start();
			}
		}

		else {
			changeSong = false;
		}
	}

	@Override
	public void run() {
		String[] playlist = { "8 bit Timber.mp3", "8 bit Happy.mp3",
				"8 bit Kanye.mp3", "Danger Zone.mp3", "Minecraft.mp3",
				"Timber.mp3", "Gonk.mp3", "Hey Boy.mp3" };

		while (nextSong) {
			try {
				FileInputStream fis = new FileInputStream("./res/sound/" + playlist[i]); // Name of tune placed into file.
				playMp3 = new Player(fis);
				playMp3.play();

			} catch (Exception e) {
				System.out.println("Could not play file.");
				System.out.println(e);
			}
		}
	}
}
