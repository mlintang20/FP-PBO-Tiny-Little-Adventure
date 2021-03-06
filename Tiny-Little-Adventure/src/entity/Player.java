package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import main.GamePanel;
import main.KeyHandler;
import main.UI;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.gp=gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 3;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			} else if(keyH.downPressed == true) {
				direction = "down";
			} else if(keyH.leftPressed == true) {
				direction = "left";
			} else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.collisionChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//CHECK NPC COLLISION
			int npcIndex=gp.collisionChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 15) {
				if(spriteNum == 1) spriteNum = 2;
				else if(spriteNum == 2) spriteNum = 1;
				spriteCounter = 0;
			}

		}
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			String objName = gp.obj[index].name;
			
			switch(objName) {
			case "Key":
				gp.playSoundEffect(1);
				hasKey++;
				gp.obj[index] = null;
				gp.ui.showMessage("You got a KEY!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSoundEffect(3);
					gp.obj[index] = null;
					hasKey--;
					gp.ui.showMessage("You've opened the door!");
				}
				else {
					gp.ui.showMessage("You need a KEY!");
				}
				break;
			case "Boots":
				gp.playSoundEffect(2);
				speed += 2;
				gp.obj[index] = null;
				gp.ui.showMessage("SPEED UP!");
				break;
			case "Chest":
				UI.gameFinished = true;
				gp.stopMusic();
				gp.playSoundEffect(4);
				break;
			}
		}
	}
	public void interactNPC(int i) {
		if(i!=999) {
			if(gp.keyH.enterPressed==true){
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		} 
		gp.keyH.enterPressed=false;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		switch (direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}
	
}