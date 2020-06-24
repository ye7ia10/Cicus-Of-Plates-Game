package eg.edu.alexu.csd.oop.cs19.world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import eg.edu.alexu.csd.oop.cs19.logic.Background;
import eg.edu.alexu.csd.oop.cs19.logic.ControlableObject;
import eg.edu.alexu.csd.oop.cs19.logic.HelpingMove;
import eg.edu.alexu.csd.oop.cs19.logic.Iterator;
import eg.edu.alexu.csd.oop.cs19.logic.JarReader;
import eg.edu.alexu.csd.oop.cs19.logic.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus implements World {

	private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private static int MAX_MOVING = 200;
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final List<GameObject> constant;
	private final List<GameObject> moving;
	private final List<GameObject> control;
	private Factory factory;
	private GameObject state;
	private HelpingMove help;
	private IStateLevel stateDifficulty ;
	private JLabel scoreLbl;
	private JLabel livesLbl;
	private JLabel lvlLabel;
	private JarReader rReader;
	private HashMap<String, BufferedImage> resources;
	
	public Circus(int screenWidth, int screenHeight, Factory factory, IStateLevel stateDifficulty, JLabel scoreLbl, JLabel livesLbl,JLabel lvlLabel) {
		
		this.scoreLbl = scoreLbl;
		this.livesLbl = livesLbl;
		this.stateDifficulty = stateDifficulty;
		this.factory = factory;
		this.width = screenWidth;
		this.height = screenHeight;
		this.lvlLabel = lvlLabel;
		this.constant = new LinkedList<GameObject>();
		this.moving = new LinkedList<GameObject>();
		this.control = new LinkedList<GameObject>();
		this.help = new HelpingMove(constant, moving, control);
		
		this.rReader = new JarReader();
		this.resources = new HashMap<>();
		
		String[] temp = getClass().getCanonicalName().split(getClass().getPackage().getName());
		File file = new File(temp[0]);
		System.out.println("Package name: " + file.getAbsolutePath());
		this.resources = rReader.getResFromJar(file.getAbsolutePath() + "\\LibImages.jar");
		
		/*String clown = this.resources.get("clownChar");
		String background = this.resources.get("background");
		String left = this.resources.get("left");
		String right = this.resources.get("right");*/
		
		BufferedImage clown = this.resources.get("clownChar");
		BufferedImage background = this.resources.get("background");
		BufferedImage left = this.resources.get("left");
		BufferedImage right = this.resources.get("right");
		
		this.constant.add(new Background(0, 0, background));
		this.control.add(
				factory.createObject("STICK", 20, this.height - 210, left));
		this.control.add(factory.createObject("STICK", 182, this.height - 210, right));
		this.control
				.add(factory.createObject("PLAYER", 62, this.height - 200, clown));

		this.stateDifficulty.moveChange(this.moving, this.factory);

	}

	@Override
	public List<GameObject> getConstantObjects() {
		// TODO Auto-generated method stub
		return this.constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		// TODO Auto-generated method stub
		return this.moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		// TODO Auto-generated method stub
		return this.control;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public boolean refresh() {

		moveToLimit();
		stateDifficulty.gameChange(moving, factory, help);
		scoreLbl.setText("score:   " + help.getScore());
		livesLbl.setText("lives:   " + help.getLives());
		lvlLabel.setText("level:   " + stateDifficulty.getLvlName());

		if (help.getScore() >= stateDifficulty.getThreshold()) {
			stateDifficulty = stateDifficulty.getNextState();
		}

		if (livesLbl.getText().contains("0")) {
			return false;
		}
		
		return true;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return stateDifficulty.getSpeed();
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return stateDifficulty.getControlSpeed();
	}

	public void moveToLimit() {

		if (this.control.get(1).getX() + this.control.get(1).getWidth() > this.width) {

			this.control.get(1).setX(this.width - this.control.get(1).getWidth() - 28);
			this.control.get(2).setX(this.control.get(2).getX() - stateDifficulty.getControlSpeed());
			this.control.get(0).setX(this.control.get(0).getX() - stateDifficulty.getControlSpeed());
			objectsLimit();

		} else if (this.control.get(2).getX() < 42) {

			this.control.get(2).setX(42);
			this.control.get(0).setX(0);
			this.control.get(1).setX(162);
			objectsLimit();

		}

	}

	public void objectsLimit() {

		Iterator controlItr = new Iterator(control);
		controlItr.setCursor(2);
		while (controlItr.hasNext()) {
			GameObject g = controlItr.next();
			if (Math.abs(g.getX() - control.get(0).getX()) > Math.abs(g.getX() - control.get(1).getX())) {
				g.setX(control.get(1).getX() + (control.get(1).getWidth() / 3) - (g.getWidth() / 2));
			} else {
				g.setX(control.get(0).getX() + (control.get(0).getWidth() / 8) - (g.getWidth() / 2));
			}
		}

	}

}
