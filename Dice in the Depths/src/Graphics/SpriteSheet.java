package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public int width;
	public int height;
	public BufferedImage[] images;
	public SpriteSheet(String Path,int width,int height,int row,int col){
		
		BufferedImage[] images=new BufferedImage[width*height];
		
		BufferedImage image=null;
		try {
			image=ImageIO.read(SpriteSheet.class.getResourceAsStream(Path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (image==null){
			return;
		}
		
		this.width =width;
		this.height=height;
		this.images=images;
		
		for (int i=0;i<col;i++){
			for (int j=0;j<row;j++){
				
				this.images[i*row+j]=image.getSubimage((j*width)+j+1,(i*height)+i+1,width,height);
				
			}
		}
	}
}
