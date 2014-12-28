package Main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Battle.Battle;
import Graphics.Sprite;

public class Button {

	static Game game;
	public Text text;
	public Boolean visible;
	public Rectangle rect;
	public BufferedImage DefaultImage;
	public BufferedImage OverImage;
	
	public void Draw(Mouse mouse){
		
		Sprite mouseRect =new Sprite(null,0,0,1,1,0,0);
		mouseRect.rect=new Rectangle(mouse.xpos,mouse.ypos,1,1);
		
		if (mouseRect.CollideRect(rect)){
			Game.render(new Sprite(OverImage,rect.x,rect.y,rect.width,rect.height,0,0));
			Game.renderText(text);
		}
		else{
			Game.render(new Sprite(DefaultImage,rect.x,rect.y,rect.width,rect.height,0,0));
			Game.renderText(text);
		}
	}
	public void DrawBattle(Mouse mouse){
		
		Sprite mouseRect =new Sprite(null,0,0,1,1,0,0);
		mouseRect.rect=new Rectangle(mouse.xpos,mouse.ypos,1,1);

		if (mouseRect.CollideRect(rect)){
			Battle.render(new Sprite(OverImage,rect.x,rect.y,rect.width,rect.height,0,0));
			Battle.renderText(text);
		}
		else{
			Battle.render(new Sprite(DefaultImage,rect.x,rect.y,rect.width,rect.height,0,0));
			Battle.renderText(text);
		}
	}
	public boolean Update(Mouse mouse){
		Sprite mouseRect =new Sprite(null,0,0,1,1,0,0);
		mouseRect.rect=new Rectangle(mouse.xpos,mouse.ypos,1,1);
		
		if (mouseRect.CollideRect(rect)&&mouse.down==true){
			return true;
		}
		return false;
	}
	
	public Button(Rectangle rect,BufferedImage ButtonImage,BufferedImage HovveringImage,String text,int x,int y){
	
			this.text=new Text(text,x,y);
			this.rect=rect;
			this.DefaultImage=ButtonImage;
			this.OverImage=HovveringImage;
		
	}
}
