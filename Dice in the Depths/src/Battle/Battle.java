package Battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import Dice.Die;
import Graphics.ImageLoader;
import Graphics.Sprite;
import Main.Button;
import Main.Mouse;
import Main.Settings;
import Main.Text;
import Main.Timer;

public class Battle {
	
	static Mouse mouse=new Mouse();
	public JFrame frame;
	static ArrayList<Sprite> DrawnSprites=new ArrayList<Sprite>();
	static ArrayList<Text> DrawnText=new ArrayList<Text>();
	public Die[] Dice;
	public boolean inbattle;
	static Timer timer=new Timer();
	static Settings settings=new Settings();
	static ImageLoader allImages=new ImageLoader();
	static Random random=new Random();
	public Button[] DiceButtons=new Button[9];
	public int atack=0;
	public int Poisonnum=0;
	public int Healingnum=0;
	public int Areanum=0;
	public int spellnum=0;
	public boolean atacking=false;
	public boolean transition=false;
	public int level;
	public boolean turn=true;
	public String outcome="";
	public int backgroundnum=random.nextInt(8);
	public int stages;
	static Button Atack=new Button(new Rectangle(775,508,150,40), allImages.Button, allImages.ButtonHighlight,"Atack! ",825,532);
	static Button RollAll=new Button(new Rectangle(775,468,150,40), allImages.Button,allImages.ButtonHighlight,"Roll All Dice! ",805,492);
	
	static Button Poison=new Button(new Rectangle(625,508,150,40), allImages.Button, allImages.Button,"Atack with: ",665,532);
	static Button Healing=new Button(new Rectangle(475,508,150,40), allImages.Button, allImages.Button,"Atack with: ",515,532);
	static Button Area=new Button(new Rectangle(325,508,150,40), allImages.Button, allImages.Button,"Atack with: ",345,532);
	static Button Spell=new Button(new Rectangle(175,508,150,40), allImages.Button, allImages.Button,"Atack with: ",195,532);
	static Button Damage=new Button(new Rectangle(25,508,150,40), allImages.Button, allImages.Button,"Atack with: ",65,532);
	static Button RollDice=new Button(new Rectangle(802,576,126,120), allImages.rollDice,allImages.rollDiceHighlighted,"",465,446);

	public Characters player=new Characters(new Sprite(allImages.Players.images[0], -50, 280, 200, 200, 4, 0), settings.playerhealth, "Normal",300,0,false,0);
	static Characters[] enemies=new Characters[3];
	
	public Battle(Die[] Dice,JFrame frame,int level,int stages){
		this.frame=frame;
		this.frame.addMouseListener(mouse);
		this.frame.addMouseMotionListener(mouse);
		this.Dice=Dice;
		this.inbattle=true;
		this.level=level;
		this.stages=stages-1;
		
		
		
	}
	public int findclose(Characters[] enemies){//find the nearest enemy
		for (int i=0;i<3;i++){
			if (enemies[i]!=null){
				return i;
			}
		}
		return -1;
	}
	
	public void resetEnemy(){
		for (int i=0;i<3;i++){
			int num=(random.nextInt(settings.enemynum)*4)+1;
			enemies[i]=new Characters(new Sprite(allImages.Players.images[num], 1500, 330, 150, 150, -(i*1)-5, 0), random.nextInt(25*level)+1, "Normal",i*200+450,1,false,num);
		}
	}
	public void SpawnBoss(){
		int num=(random.nextInt(settings.enemynum)*4)+1;
		enemies[2]=new Characters(new Sprite(allImages.Players.images[num], 1500, 200, 300, 300, -8, 0), random.nextInt(40*level)+50, "Normal",690,1,true,num);
	}
	
	public String startBattle(){
		for (int i=0;i<Dice.length;i++){
			Dice[i].Reroll();
		}
		for (int i=0;i<DiceButtons.length;i++){
			DiceButtons[i]=new Button(new Rectangle(settings.dieSpacingX*i+45,settings.dieOffsetY,80,80), allImages.rollDice,allImages.rollDiceHighlighted,"",465,446);
		}
		resetEnemy();
		while (inbattle){//change later not to true
			timer.update();
			if (timer.canupdate){

				PlayBattle();
			}
		}
		return outcome;
	}
	
	public static void renderText(Text text){
		DrawnText.add(text);
	}
	public static void render(Sprite sprite){
		sprite.update();
		DrawnSprites.add(sprite);
	}
	public void Draw (){
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs==null){
			frame.createBufferStrategy(3);
			return;
		}
		Graphics g =  bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, settings.windowSizeX, settings.windowSizeY);
		g.drawImage(allImages.DieHolder.images[0], 0, settings.windowSizeY-settings.battleBox,settings.windowSizeX,settings.windowSizeY-(settings.windowSizeY-settings.battleBox), null);
		g.drawImage(allImages.DungeonBackgrounds[backgroundnum], 0, 0,settings.windowSizeX,settings.windowSizeY-settings.battleBox, null);
		for (int i=0; i<DrawnSprites.size();){
			g.drawImage(DrawnSprites.get(i).image, DrawnSprites.get(i).rect.x, DrawnSprites.get(i).rect.y,DrawnSprites.get(i).rect.width,DrawnSprites.get(i).rect.height, null);
			DrawnSprites.remove(i);
		}
		for (int i=0; i<DrawnText.size();){
			g.drawString(DrawnText.get(i).text, DrawnText.get(i).x, DrawnText.get(i).y);
			DrawnText.remove(i);
		}
		g.dispose();
		bs.show();	
	}
	public void PlayBattle(){

		RollAll.DrawBattle(mouse);
		for (int i=0;i<Dice.length;i++){//dice stuff
			Dice[i].update();
			Dice[i].drawInBattle(i);
		}
		
		if (transition){
			player.update();
			player.location.update();
			player.render();
			
			if (player.hit){
				if (stages==1){
					SpawnBoss();
				}
				else{
					resetEnemy();
				}
				transition=false;
				
				player.hit=false;
				turn=true;
				backgroundnum=random.nextInt(8);
				for (int i=0;i<Dice.length;i++){
					Dice[i].Reroll();
				}
				player.over=false;
				stages-=1;
				player.inAtack=false;
				player.location.rect.x=-300;
				player.location.xSpeed=16;
			}
		}
		
		if (turn && !transition){
			
			if (RollAll.Update(mouse) && player.location.xSpeed==0){//roll all
				for (int i=0;i<Dice.length;i++){
					Dice[i].spinning=false;
				}
			}
			
			for (int i=0;i<Dice.length;i++){//dice stuff

				
				if (DiceButtons[i].Update(mouse)){
					Dice[i].spinning=false;
				}
				
				if (!Dice[i].spinning && !Dice[i].counted && Dice[i].done){
					Dice[i].counted =true;
					if (Dice[i].type=="poison"){Poisonnum+=Dice[i].number+1;}
					if (Dice[i].type=="healing"){Healingnum+=Dice[i].number+1;}
					if (Dice[i].type=="damage"){atack+=Dice[i].number+1;}
					if (Dice[i].type=="area"){Areanum+=Dice[i].number+1;}
					if (Dice[i].type=="spell"){spellnum+=Dice[i].number+1;}
				}
				
				if (Dice[i].done && Dice[i].number==0){
					turn=false;
					atack=0;
					
				}
			}

			if (Atack.Update(mouse) && player.location.xSpeed==0){//Atack!
				player.atack(enemies[findclose(enemies)]);
			}
			
			if (player.location.xSpeed==0 && player.over){
				turn=false;
				player.over=false;
			}
			
			if (player.hit){
				int num=findclose(enemies);
				player.hit=false;
				enemies[num].health-=atack;
				atack=0;
				enemies[num].update();
				if (enemies[num].dead){
					enemies[num]=null;
				}
				
			}
			
		}
		boolean cango=true;
		for (int i=0;i<3;i++){
			if (enemies[i]!=null){
				if (enemies[i].dieing){
					cango=false;
				}
			}
		}
		for (int i=0;i<3;i++){
			if (enemies[i]!=null){
				if (enemies[i].dead){
					enemies[i]=null;
				}
			}
		}
		if (!turn && !transition && cango){
			boolean won=true;
			boolean over=true;
			for (int i=0;i<3;i++){
				if (enemies[i]!=null ){
					if (!enemies[i].inAtack && enemies[i].location.xSpeed==0 && !enemies[i].over){
						enemies[i].atack(player);
					}
					if (enemies[i].hit){
						enemies[i].hit=false;
						enemies[i].over=true;
						if (enemies[i].isBoss){player.health-=(random.nextInt(10)+5)*level*2;}
						else {player.health-=(random.nextInt(10)+5)*level;}
					}
					if (enemies[i].inAtack || enemies[i].location.xSpeed!=0){
						over=false;
					}
					if (enemies[i]!=null){
						won=false;
					}
				}
			}
			if (won){
				if (stages==0){
					inbattle=false;
					outcome="win";
				}
				player.atack(new Characters(new Sprite(null, 1500, 280, 200, 200, 0, 0), 0, "Normal",300,0,false,0));
				transition=true;
			}
			if (over){
				turn=true;
				for (int i=0;i<3;i++){
					if (enemies[i]!=null){
						enemies[i].over=false;
					}
				}
				for (int i=0;i<Dice.length;i++){
					Dice[i].Reroll();
				}
			}
		}
		if (RollDice.Update(mouse)){
			boolean ok=true;
			for (int i=0;i<Dice.length;i++){
				if (!Dice[i].done){
					ok=false;
				}
			}
			if (ok){
				for (int i=0;i<Dice.length;i++){
					Dice[i].Reroll();
				}
			}
			mouse.down=false;
		}
		Damage.text.text="Damage:"+atack;Damage.DrawBattle(mouse);
		Poison.text.text="Poison:"+Poisonnum;Poison.DrawBattle(mouse);
		Healing.text.text="Healing:"+Healingnum;Healing.DrawBattle(mouse);
		Area.text.text="Area Damage:"+Areanum;Area.DrawBattle(mouse);
		Spell.text.text="Magic Damage:"+spellnum;Spell.DrawBattle(mouse);
		Atack.DrawBattle(mouse);RollDice.DrawBattle(mouse);
	
		for (int i=0;i<3;i++){
			if (enemies[i]!=null){
				enemies[i].update();
				enemies[i].location.update();
				enemies[i].render();
			}
		}
		player.update();
		player.location.update();
		player.render();
		
		if (player.health<=0){//you dead
			inbattle=false;
			outcome="loss";
		}
		
		Draw();
	}
}
