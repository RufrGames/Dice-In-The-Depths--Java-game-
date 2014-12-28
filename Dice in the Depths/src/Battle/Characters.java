package Battle;


import Graphics.ImageLoader;
import Graphics.Sprite;
import Main.Text;

public class Characters {
	
	public String type;
	public int health;
	public Sprite location;
	public int maxhealth;
	static ImageLoader allImages=new ImageLoader();
	public int stopAt;
	public boolean inAtack=false;
	public int toX;
	public boolean hit=false;
	public boolean dead=false;
	public boolean dieing=false;
	public boolean over=false;
	public boolean finished=true;
	public boolean isBoss;
	public int deathtimer=0;
	public int imageNum;
	public Characters(Sprite sprite,int health,String type,int stopAt,int level,boolean boss,int imagenum){
	
		this.health=health;
		this.maxhealth=health;
		this.location=sprite;
		this.type=type;
		this.stopAt=stopAt;
		this.isBoss=boss;
		this.imageNum=imagenum;
	}
	public void render(){
		if (!dieing){
			Battle.render(location);
		}
		else{
			Battle.render(new Sprite(allImages.Players.images[imageNum+(deathtimer/5)],location.rect.x,location.rect.y,location.rect.width,location.rect.height,0,0));
		}
		int tempx=(location.rect.x)-(maxhealth/2)+(location.rect.width/2);
		int tempy=location.rect.y;
		Battle.render(new Sprite(allImages.bar,tempx,tempy,maxhealth,2,0,0));
		Battle.render(new Sprite(allImages.health,tempx,tempy,health,2,0,0));
		String text=health+" / "+maxhealth;
		Battle.renderText(new Text(text,(tempx)+(maxhealth/2)-20,tempy+15 ));
		
	}
	public void update(){
		
		if (dieing){
			deathtimer++;
			if (deathtimer>=15){
				dead=true;
			}
		}
		if (!inAtack){
			if (location.xSpeed>0){
				if (location.rect.x+location.rect.width>=stopAt){
					location.xSpeed=0;
				}
			}
			if (location.xSpeed<0){
				if (location.rect.x+location.rect.width<=stopAt){
					location.xSpeed=0;
				}
			}
		}
		if (inAtack && !dieing){
			if (location.xSpeed>0){
				if (location.rect.x>toX){
					inAtack=false;
					location.xSpeed=-16;
					hit=true;
					over=true;
				}
			}
			if (location.xSpeed<0){
				if (location.rect.x<toX){
					inAtack=false;
					location.xSpeed=16;
					hit=true;
					over=true;
				}
			}
		}		
		if (health<=0){
			
			dieing=true;
		}
	}
	public void atack(Characters other){
		if (other.location.rect.x>location.rect.x){
			location.xSpeed=16;
		}
		if (other.location.rect.x<location.rect.x){
			location.xSpeed=-16;
		}
		inAtack=true;
		finished=false;
		toX=other.location.rect.x;
	}
}
