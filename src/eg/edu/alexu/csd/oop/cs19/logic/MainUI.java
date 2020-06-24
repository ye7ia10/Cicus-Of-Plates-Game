package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import eg.edu.alexu.csd.oop.cs19.world.Circus;
import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import java.io.*;
import java.util.List;

import javax.sound.sampled.*;

public class MainUI {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		JMenuBar menuBar = new JMenuBar();
		JButton newGameBtn = new JButton("new Game");
		newGameBtn.setEnabled(true);
		JButton pauseBtn = new JButton("Pause");
		pauseBtn.setEnabled(true);
		JButton resumeBtn = new JButton("resume");
		resumeBtn.setEnabled(false);
		JLabel scoreLbl = new JLabel("Score:   " + 0);
		JLabel livesLbl = new JLabel("Lives:   " + 3);
		JLabel lvlLabel = new JLabel("Level:   ");

		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		//////////////////////////////////
		menuBar.add(newGameBtn);
		menuBar.add(new JSeparator());
		menuBar.add(pauseBtn);
		menuBar.add(new JSeparator());
		menuBar.add(resumeBtn);
		menuBar.add(new JSeparator());
		menuBar.add(scoreLbl);
		menuBar.add(new JSeparator());
		menuBar.add(livesLbl);
		menuBar.add(new JSeparator());
		menuBar.add(lvlLabel);

		/////////////////////////////////
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());
		menuBar.add(new JSeparator());

		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("circus_music.wav"));
		// Get a sound clip resource.
		Clip clip = AudioSystem.getClip();
		// Open audio clip and load samples from the audio input stream.
		clip.open(audioIn);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		// GameController g = GameEngine.start("Circus Of Plates", new Circus(1890,
		// 1000, ObjectFactory.getInstance()), menuBar,Color.white);
		GameController g = GameEngine.start("Circus Of Plates",
				new Circus(1890, 1000, ObjectFactory.getInstance(), new Easy(), scoreLbl, livesLbl, lvlLabel), menuBar, Color.white);

		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resumeBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				g.pause();
			}
		});
		
		resumeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pauseBtn.setEnabled(true);
				resumeBtn.setEnabled(false);
				g.resume();
			}
		});
		
		newGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				g.changeWorld(new Circus(1890, 1000, ObjectFactory.getInstance(), new Easy(), scoreLbl, livesLbl, lvlLabel));
			}
		});
	}

}
