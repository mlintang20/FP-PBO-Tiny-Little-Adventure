package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Key extends SuperObject {
	
	GamePanel gp;
	public Key(GamePanel gp) {
		this.gp = gp;
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
}
