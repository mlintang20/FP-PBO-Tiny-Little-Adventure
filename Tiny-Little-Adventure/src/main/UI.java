package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Key;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80_B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public static boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	static double playTime;
	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80_B = new Font("Arial", Font.BOLD, 80);
		Key key = new Key(gp);
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);

		
		//Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//Play State
		if(gp.gameState == gp.playState) {
			if (gameFinished == true) {
				
				String text;
				int textLength;
				int x;
				int y;
				
				//text 1
				g2.setFont(arial_40);
				g2.setColor(Color.white);
				
				text = "You found the TREASURE!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				x = (gp.screenWidth / 2) - (textLength / 2);
				y = (gp.screenHeight / 2) - (gp.tileSize * 3);
				g2.drawString(text, x, y);
				
				// text 3
				g2.setFont(arial_40);
				g2.setColor(Color.yellow);
				
				text = "Your TIME is: " + decimalFormat.format(playTime) + "!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				x = (gp.screenWidth / 2) - (textLength / 2);
				y = (gp.screenHeight / 2) + (gp.tileSize * 4);
				g2.drawString(text, x, y);
				
				// text 2
				g2.setFont(arial_80_B);
				g2.setColor(new Color(255, 50, 20));
				
				text = "Congratulations!";
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				x = (gp.screenWidth / 2) - (textLength / 2);
				y = (gp.screenHeight / 2) + (gp.tileSize * 2);
				g2.drawString(text, x, y);
				
				gp.gameThread = null;
					
			} else {
				
				g2.setFont(arial_40);
				g2.setColor(Color.white);
				g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
				g2.drawString("X " + gp.player.hasKey, 75, 65);
				
				// TIME
				playTime += (double)1/60;
				g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize * 6, gp.tileSize * 11);
				
				// MESSAGE
				if(messageOn == true) {
					g2.setFont(g2.getFont().deriveFont(25F));
					g2.drawString(message, gp.tileSize * 10, gp.tileSize);
					
					messageCounter++;
					if(messageCounter > 120) {
						messageCounter = 0;
						messageOn = false;
					}
				}
			
			}
		}
		//Pause State
		if(gp.gameState==gp.pauseState) {
			drawPauseScreen();
		}
		//Dialogue State
		if(gp.gameState==gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	
	public void drawTitleScreen() {
		g2.setColor(new Color(50, 50, 255));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
		String text = "TINY LITTLE";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 2;
		
		//Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		text = "ADVENTURE";
		x = getXforCenteredText(text);
		y += gp.tileSize*2;
		
		//Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// Character Image
		x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
		y += gp.tileSize;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
			g2.drawImage(gp.player.right1, x - gp.tileSize*2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
			g2.drawImage(gp.player.right2, x - gp.tileSize*2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
		}
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,64F));
		String text = "GAME PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/3;
		
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
		text = "Press Esc to Main Menu";
		x = getXforCenteredText(text) - getXforCenteredText(text)/2 - 70;
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
		text = "Press Q to Quit";
		x = getXforCenteredText(text) + getXforCenteredText(text)/2 + 50;
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
	    text = "Press P to Continue";
	    x = getXforCenteredText(text);
	    y = gp.screenHeight/2 + 80;
	    
	    g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		// Window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4); 
		int height= gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28));
		x+=gp.tileSize;
		y+=gp.tileSize;
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+=40;
		} 
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c =new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c=new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gp.screenWidth/2 - length/2;
		return x;
	}
}
