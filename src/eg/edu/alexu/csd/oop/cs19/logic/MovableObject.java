package eg.edu.alexu.csd.oop.cs19.logic;

import eg.edu.alexu.csd.oop.cs19.world.IState;

public class MovableObject implements IState {

	private int x;
	private int y;
	
	public MovableObject(int posX, int posY) {
		// TODO Auto-generated constructor stub		
		this.x = posX;
		this.y = posY;
	}

	
	@Override
	public int getX() {
		return this.x;
	}
	
	@Override
	public void setX(int x) {
		
	}
	
	@Override
	public int getY() {
		return this.y;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}

}
