package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.List;
import java.util.Random;

import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Hard implements IStateLevel {
	private int numBombs = 0;
	private int numSkulls = 0;
	private int numGifts = 0;
	private int numLives = 0;
	private final int MAX_MOVING = 300;

	@Override
	public void moveChange(List<GameObject> moving, Factory factory) {

		Random r = new Random();
		for (int i = 0; i < MAX_MOVING; i++) {
			moving.add(factory.createObject("Shape", r.nextInt(1500) + 100, -1 * r.nextInt(20000), null));
		}

	}

	@Override
	public void gameChange(List<GameObject> move, Factory factory, HelpingMove help) {

		numBombs++;
		numSkulls++;
		numGifts++;
		numLives++;
		Random r = new Random();
		
		Iterator movingItr = new Iterator(move); 
		while (movingItr.hasNext()) {
			GameObject g = movingItr.next();
			g.setY(g.getY() + 10);
			if (g.getY() > 1000) {
				g.setX(r.nextInt(1500) + 100);
				g.setY(-1 * r.nextInt(20000));
			}
		}

		help.setDifference(10);

		movingItr.setCursor(-1);
		while (movingItr.hasNext()) {
			if (help.isIntersect(movingItr.next(), movingItr.getCursor())) {
				movingItr.previous();
			}
		}

		if (numBombs > 2000) {
			numBombs = 0;
			move.add(factory.createObject("bomb", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}
		
		if (numSkulls > 600) {
			numSkulls = 0;
			move.add(factory.createObject("skull", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}

		if (numGifts > 2500) {
			numGifts = 0;
			move.add(factory.createObject("gift", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}
		
		if (numLives > 9000) {
			numLives = 0;
			move.add(factory.createObject("live", r.nextInt(1500) + 150, -1 * r.nextInt(20000), null));
		}

	}

	@Override
	public int getSpeed() {
		return 1;
	}

	@Override
	public int getControlSpeed() {
		return 80;
	}

	@Override
	public int getThreshold() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public IStateLevel getNextState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLvlName() {
		// TODO Auto-generated method stub
		return "Hard";
	}
}
