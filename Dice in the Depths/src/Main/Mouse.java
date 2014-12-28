package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	public int xpos=0;
	public int ypos=0;
	public boolean down=false;
	
	@Override
	public void mousePressed(MouseEvent e){
		xpos=e.getX();
		ypos=e.getY();
		down=true;
	}
	@Override
	public void mouseReleased(MouseEvent e){
		xpos=e.getX();
		ypos=e.getY();
		down=false;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		xpos=e.getX();
		ypos=e.getY();
	}
}
