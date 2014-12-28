package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	
	
	public BufferedImage MainMenu;
	public BufferedImage Mouse;
	public BufferedImage Test;
	public BufferedImage Shop;
	public BufferedImage Button;
	public BufferedImage ButtonHighlight;
	public BufferedImage Game;
	public SpriteSheet DieHolder;
	public SpriteSheet Dice;
	public BufferedImage[] DungeonBackgrounds=new BufferedImage[8];
	public BufferedImage rollDice;
	public BufferedImage rollDiceHighlighted;
	public SpriteSheet Players;
	public BufferedImage health;
	public BufferedImage bar;
	
	public ImageLoader(){
		
		//MainMenu
		try {
			this.MainMenu=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/MainMenu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.Mouse=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Mouse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.Test=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.Shop=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Shop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.Button=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Buttons.png"));
			this.Button=this.Button.getSubimage(260,400,248,23);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.ButtonHighlight=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Buttons.png"));
			this.ButtonHighlight=this.ButtonHighlight.getSubimage(260,373,248,23);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.Game=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Game.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i=0; i<=7; i++){
			String num=Integer.toString(i+1);
			try {
				this.DungeonBackgrounds[i]=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/dungeon"+num+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			this.rollDice=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Buttons.png"));
			this.rollDice=this.rollDice.getSubimage(261,0,32,32);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.rollDiceHighlighted=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Buttons.png"));
			this.rollDiceHighlighted=this.rollDiceHighlighted.getSubimage(261,32,32,32);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.health=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/Health.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.bar=ImageIO.read(SpriteSheet.class.getResourceAsStream("/Images/bar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.Players=new SpriteSheet("/Images/Monsters.png", 39, 39, 6,6);
		this.Dice=new SpriteSheet("/Images/Dice.png", 17, 17, 6, 6);
		this.DieHolder=new SpriteSheet("/Images/Buttons.png", 260, 50, 1,5);
			
	}
}
