package main;

import entity.NPC_OldMan;
import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
		gp.obj[0] = new Key(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 41 * gp.tileSize;
		
		gp.obj[2] = new Key(gp);
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 9 * gp.tileSize;
		
		gp.obj[3] = new Door(gp);
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 12 * gp.tileSize;
		
		gp.obj[4] = new Door(gp);
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new Door(gp);
		gp.obj[5].worldX = 12 * gp.tileSize;
		gp.obj[5].worldY = 23 * gp.tileSize;
		
		gp.obj[6] = new Chest(gp);
		gp.obj[6].worldX = 10 * gp.tileSize;
		gp.obj[6].worldY = 8 * gp.tileSize;
		
		gp.obj[7] = new Boots(gp);
		gp.obj[7].worldX = 37 * gp.tileSize;
		gp.obj[7].worldY = 41 * gp.tileSize;
	}
	public void setNPC() {
		gp.npc[0]=new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
	}
	
}
