package eg.edu.alexu.csd.oop.cs19.world;


import java.util.List;

import eg.edu.alexu.csd.oop.cs19.logic.HelpingMove;
import eg.edu.alexu.csd.oop.game.GameObject;

public interface IStateLevel {
	public void moveChange(List<GameObject> move, Factory factory);
	// this method call the observer to calculate score or die
	public void gameChange(List<GameObject> move, Factory factory,HelpingMove help);
	public int getSpeed();
	public int getControlSpeed();
	public int getThreshold();
	public IStateLevel getNextState();
	public String getLvlName();
}
