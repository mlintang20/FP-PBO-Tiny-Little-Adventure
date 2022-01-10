package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
         
        getImage();
        setDialogue();
    }
    public void getImage(){

        up1 = setup ("/NPC/oldman_up_1");
        up2 = setup ("/NPC/oldman_up_2");
        down1 = setup ("/NPC/oldman_down_1");
        down2 = setup ("/NPC/oldman_down_2");
        left1 = setup ("/NPC/oldman_left_1");
        left2 = setup ("/NPC/oldman_left_2");
        right1 = setup ("/NPC/oldman_right_1");
        right2 = setup ("/NPC/oldman_right_2");
    }
    
    public void setDialogue() {
    	dialogues[0]="Hello, Player!";
    	dialogues[1]="Where did you come from, actually?";
    	dialogues[2]="Im an old man who guard this forest \nfor a long ago";
    	dialogues[3]="For your information, \nI have very bad memories";
    	dialogues[4]="Who am I, again?";
    	dialogues[5]="Ah, I remember.. there is treasure \nhidden in this forest. You must find out 3 \nkeys to open 3 doors to get the treasure";
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1; //pickup a number from 1 to 100

            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
            if(i>50 && i<=75){
                direction = "left";
            }
            if(i>75 && i<=100){
                direction = "right";
            }
            actionLockCounter=0;
        }        
    }
    public void update(){
        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);
        if(collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
        spriteCounter++;
		if(spriteCounter > 30) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}
    }
    
    public void speak() {
    	super.speak();
    }
}