package Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;

public class Sprite {

	public BufferedImage image;
	public Rectangle rect;
	public int xSpeed;
	public int ySpeed;
	public int top;
	public int bottom;
	public int left;
	public int right;
	
	public Game game;
	
	public boolean CollideRect(Rectangle other){
		
		if (this.rect.intersects(other)){
			return true;
		}
		return false;
		
	}
	public void update(){
		this.rect.x+=xSpeed;
		this.rect.y+=ySpeed;
	}
	public Sprite(BufferedImage image,int x,int y,int width,int height,int xSpeed,int ySpeed){
	
		this.rect=new Rectangle(x,y,width,height);
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
		this.image=image;
		
	}
}
