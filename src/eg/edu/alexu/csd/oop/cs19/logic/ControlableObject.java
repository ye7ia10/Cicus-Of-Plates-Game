package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.IState;

public class ControlableObject implements IState {

	private int x;
	private int y;
	
	public ControlableObject(int posX, int posY) {		
		this.x = posX;
		this.y = posY;
	}

	
	@Override
	public int getX() {
		return this.x;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return this.y;
	}
	@Override
	public void setY(int y) {
		
	}

}
