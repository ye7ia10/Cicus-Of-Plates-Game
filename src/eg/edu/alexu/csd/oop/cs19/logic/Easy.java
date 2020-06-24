package eg.edu.alexu.csd.oop.cs19.logic;


import java.util.List;
import java.util.Random;

import eg.edu.alexu.csd.oop.cs19.world.Factory;
import eg.edu.alexu.csd.oop.cs19.world.IStateLevel;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Easy implements IStateLevel{
	private int numBombs = 0;
	private final int MAX_MOVING = 200;
	
	@Override
	public void moveChange(List<GameObject> moving, Factory factory) {

		Random r = new Random();
		for (int i = 0; i < MAX_MOVING; i++) {
			moving.add(factory.createObject("Shape", r.nextInt(1500) + 100, -1 * r.nextInt(20000), null));
		}

	}

	@Override
	public void gameChange(List<GameObject> move, Factory factory,HelpingMove help) {

		numBombs++;
		Random r = new Random();

		Iterator movingItr = new Iterator(move);
		while (movingItr.hasNext()) {
			GameObject g = movingItr.next();
			g.setY(g.getY() + 2);
			if(g.getY() > 1000) {
				g.setX(r.nextInt(1500) + 100);
				g.setY( -1 * r.nextInt(20000));
			}
		}

		help.setDifference(2);
		
		movingItr.setCursor(-1);
		while (movingItr.hasNext()) {
			if (help.isIntersect(movingItr.next(), movingItr.getCursor())) {
				movingItr.previous();
			}
		}
		
		if(numBombs > 700) {
			numBombs = 0;
			move.add(factory.createObject("bomb", r.nextInt(1500) + 100, -1 * r.nextInt(20000), null));
		}
		
	}

	@Override
	public int getSpeed() {
		return 1;
	}
	
	@Override
	public int getControlSpeed() {
		return 20;
	}

	@Override
	public int getThreshold() {
		// TODO Auto-generated method stub
		return 75;
	}

	@Override
	public IStateLevel getNextState() {
		// TODO Auto-generated method stub
		return new Medium();
	}

	@Override
	public String getLvlName() {
		// TODO Auto-generated method stub
		return "Easy";
	}

}
