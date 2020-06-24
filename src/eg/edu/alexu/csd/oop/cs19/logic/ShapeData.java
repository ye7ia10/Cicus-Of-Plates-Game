package eg.edu.alexu.csd.oop.cs19.logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShapeData {

	private BufferedImage[] spriteImages = new BufferedImage[1];
	private String path;
	public ShapeData(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
		try {
			readImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readImage() throws IOException {
		File f = new File(path);
		spriteImages[0] = ImageIO.read(f);
		System.out.println(spriteImages[0].getHeight());
	}
	public BufferedImage[] getImage(){
		return spriteImages;
	}
	
}
