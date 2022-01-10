package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//Title State
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				else if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		//Play State
		if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			//PAUSE
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			//ENTER
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
				enterPressed=true;
			}
			
			//GAME FINISH
			if(UI.gameFinished == true) {
				if(code == KeyEvent.VK_ESCAPE) {
					gp.ui.messageOn = false;
					gp.gameState = gp.titleState;
					gp.setupGame();
					gp.startGameThread();
					gp.update();
					gp.soundEffect.stop();
				} else if(code == KeyEvent.VK_Q) {
					System.exit(0);
				}
			}
		}
		
		//Pause State
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.stopMusic();
				gp.gameState = gp.titleState;
			}
			if(code == KeyEvent.VK_Q) {
				System.exit(0);
			}
		}
		//Dialogue State
		else if(gp.gameState==gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE || code==KeyEvent.VK_ESCAPE) {
				gp.gameState=gp.playState;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}
	
}
