package Main;

public class Settings {

	public String name;
	public int windowSizeX;
	public int windowSizeY;
	public boolean printFps;
	public int battleBox;
	public int dieOffsetX;
	public int dieSpacingX;
	public int dieOffsetY;
	public int dieSize;
	public int playerxintro;
	public int enemiexintro;
	public int enemynum;
	
	//Statistics
	
	public int playerhealth;
	
	public Settings(){
	
		this.printFps=false;
		this.windowSizeX=960;
		this.windowSizeY=720;
		this.name="Dice in the Depths";
		this.battleBox=170;
		this.dieOffsetX=40;
		this.dieSpacingX=83;
		this.dieOffsetY=593;
		this.dieSize=80;
		this.playerxintro=350;
		this.enemiexintro=800;
		this.enemynum=1;
		
		//Statistics
		
		this.playerhealth=200;
	}
}
