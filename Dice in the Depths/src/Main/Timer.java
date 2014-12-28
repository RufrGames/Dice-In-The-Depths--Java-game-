package Main;

public class Timer {

	public boolean canupdate=false;
	boolean onesec=false;
	
	long lastTime=System.nanoTime();
	long lastSecound=System.nanoTime();
	int ticks=0;
	double updateTime=1000000000D/60D;
	int fps=60;
	
	public void update(){
		
		long currentTime=System.nanoTime();
		this.canupdate=false;this.onesec=false;
		
		if (currentTime>=(this.lastTime+this.updateTime)){
			this.lastTime=currentTime;
			this.canupdate=true;
			this.ticks+=1;
		}
		
		if (currentTime>=(this.lastSecound+(this.updateTime*60))){
			this.lastSecound=currentTime;
			this.fps=this.ticks;
			this.ticks=0;
			this.onesec=true;
	}
	}
	public Timer(){
		update();	
	}
}
