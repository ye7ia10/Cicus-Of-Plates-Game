package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.cs19.world.IState;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ShapeObject implements GameObject {
	private String path;
	private int x;
	private int y;
	private int width = 20;
	private int height = 20;
	private boolean visible = true;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int type;
	private IState state;
	

	public ShapeObject(int posX, int posY, BufferedImage img) {
		this.x = posX;
		this.y = posY;
		this.visible = true;
		spriteImages[0] = img; 
		this.width = 193;
		this.height = 290;
	}
	
	
	@Override
	public int getX() {
		return state.getX();
	}

	@Override
	public void setX(int x) {
		state.setX(x);;
		
	}

	@Override
	public int getY() {
		return state.getY();
	}

	@Override
	public void setY(int y) {
		state.setY(y);
	}

	@Override
	public int getWidth() {
		return this.spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return this.spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	public void setState(IState state) {
		this.state = state;
	}
	public void setPath(String path) {
		this.path = (path);
	}
	public String getPath() {
		return path;
	}
}
