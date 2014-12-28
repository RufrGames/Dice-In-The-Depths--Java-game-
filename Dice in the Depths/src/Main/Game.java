package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import Battle.Battle;
import Dice.Die;
import Graphics.ImageLoader;
import Graphics.Sprite;

public class Game {
	
	public enum Location{MainMenu,Game,Shop,Battle};
	static Location CurrentLocation=Location.MainMenu;
	static Mouse mouse=new Mouse();
	static ArrayList<Sprite> DrawnSprites=new ArrayList<Sprite>();
	static ArrayList<Text> DrawnText=new ArrayList<Text>();
	static Settings settings=new Settings();
	static JFrame frame=new JFrame(settings.name);
	static Timer timer=new Timer();
	static ImageLoader allImages=new ImageLoader();
	static Die[] Dice=new Die[9];
	
	//test
	static Sprite Test =new Sprite(allImages.Test,0,0,1,1,0,0);
	//mouse
	static Sprite mouseRect =new Sprite(allImages.MainMenu,0,0,1,1,0,0);
	
	//Menus
	static Sprite MainMenu=new Sprite(allImages.MainMenu,0,0,settings.windowSizeX,settings.windowSizeY,0,0);
	static Sprite Shop=new Sprite(allImages.Shop,0,0,settings.windowSizeX,settings.windowSizeY,0,0);
	static Sprite Game=new Sprite(allImages.Game,0,0,settings.windowSizeX,settings.windowSizeY,0,0);
	
	//Buttons
	static Button toShopFromMain=new Button(new Rectangle(355,420,248,43), allImages.Button, allImages.ButtonHighlight,"Shop",465,446);
	static Button toGameFromMain=new Button(new Rectangle(355,370,248,43), allImages.Button, allImages.ButtonHighlight,"Play",465,396);
	static Button toMainFromShop=new Button(new Rectangle(355,620,248,43), allImages.Button, allImages.ButtonHighlight,"MainMenu",455,646);
	static Button toBattleFromGame=new Button(new Rectangle(355,620,248,43), allImages.Button, allImages.ButtonHighlight,"Start Battle",455,646);

	
	// sprite stuff
	
	// end
	
	public static void init(){		
		for (int i=0;i<9;i++){
			Dice[i]=new Die("damage",1,false);
		}
		
		frame.setMinimumSize(new Dimension(settings.windowSizeX,settings.windowSizeY));
		frame.setMaximumSize(new Dimension(settings.windowSizeX,settings.windowSizeY));
		frame.setPreferredSize(new Dimension(settings.windowSizeX,settings.windowSizeY));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		init();
		
		while (true){//change later not to true
			timer.update();
			if (timer.canupdate){
				game();
			}
			if (timer.onesec && settings.printFps){
				System.out.println("Fps:  "+timer.fps);
			}
		}	
		
	}
	
	public static void renderText(Text text){
		DrawnText.add(text);
	}
	public static void render(Sprite sprite){
		sprite.update();
		DrawnSprites.add(sprite);
	}
	public static void Draw (){
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs==null){
			frame.createBufferStrategy(3);
			return;
		}
		Graphics g =  bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, settings.windowSizeX, settings.windowSizeY);
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
	public static void game(){
		
		
		mouseRect.rect.x=mouse.xpos;
		mouseRect.rect.y=mouse.ypos;
		
		switch (CurrentLocation){
		case Shop:
						
			if (toMainFromShop.Update(mouse)){
				mouse.down=false;
				CurrentLocation=Location.MainMenu;
			}
			render(Shop);
			toMainFromShop.Draw(mouse);
			break;
		case MainMenu:
			if (toShopFromMain.Update(mouse)){
				mouse.down=false;
				CurrentLocation=Location.Shop;
			}
			if (toGameFromMain.Update(mouse)){
				mouse.down=false;
				CurrentLocation=Location.Game;
			}
			render(MainMenu);
			toShopFromMain.Draw(mouse);toGameFromMain.Draw(mouse);
			break;
		case Game:
			if (toBattleFromGame.Update(mouse)){
				mouse.down=false;
				CurrentLocation=Location.Battle;
			}
			render(Game);
			toBattleFromGame.Draw(mouse);
			break;
		case Battle:
			Battle battle=null;
			battle=new Battle(Dice,frame,1,3);
			battle.startBattle();
			CurrentLocation=Location.MainMenu;
			break;
		default:
			System.out.println("Error: Invalid Location");
			break;
		}
		Draw();
	}
}

