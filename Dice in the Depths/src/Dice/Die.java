package Dice;

import java.util.Random;

import Battle.Battle;
import Graphics.ImageLoader;
import Graphics.Sprite;
import Main.Settings;

public class Die {
	
	Random random=new Random();
	public String type;
	public int level;
	static ImageLoader allImages=new ImageLoader();
	static Settings settings=new Settings();
	public int number;
	public int rollnum;
	public boolean counted;
	public boolean spinning;
	public boolean done;
	
	public Die(String type,int level,boolean isRandom){
		if (isRandom){
			this.level=random.nextInt(3)+1;
			this.type="normal";
		}
		else{
			this.type=type;
			this.level=level;
		}
		
	}
	public void update(){
		if (!spinning){
			rollnum--;
		}
		if (rollnum>=0){
			number=random.nextInt(6);
			if (number==0){
				number=random.nextInt(3);
				if (number!=0){
					number=random.nextInt(6);
				}
			}
		}
		else{
			done=true;
		}
		
		
	}
	public void Reroll(){
		counted=false;
		rollnum=random.nextInt(50);
		spinning=true;
		done=false;
	}
	public void drawInBattle(int num){
		
		Battle.render(new Sprite(allImages.Dice.images[number],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		if (type=="poison")//green
		{
			Battle.render(new Sprite(allImages.Dice.images[10],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		if (type=="healing")//yellow
		{
			Battle.render(new Sprite(allImages.Dice.images[7],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		if (type=="damage")//red
		{
			Battle.render(new Sprite(allImages.Dice.images[6],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		if (type=="area")//blue
		{
			Battle.render(new Sprite(allImages.Dice.images[9],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		if (type=="spell")//purple
		{
			Battle.render(new Sprite(allImages.Dice.images[11],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		if (type=="stat boost")//orange
		{
			Battle.render(new Sprite(allImages.Dice.images[8],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
		
		if (number==0 && done){
			Battle.render(new Sprite(allImages.Dice.images[12],(num*settings.dieSpacingX)+settings.dieOffsetX,settings.dieOffsetY,settings.dieSize,settings.dieSize,0,0));
		}
	}
}
