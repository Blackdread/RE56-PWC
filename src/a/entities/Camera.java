/**
 * 
 */
package a.entities;

import org.newdawn.slick.geom.Rectangle;


/**
 * @author Yoann CAPLAIN
 *
 */
public class Camera {
	
	/**
	 * Rectangle de vue sur le graphic principal, pour le clip
	 */
	public Rectangle viewPortRect;
	
	/**
	 * Un offset de ce qui est dessiner sur x
	 */
	public int xOffSet = 0;
	/**
	 * Un offset de ce qui est dessiner sur y
	 */
	public int yOffSet = 0;
	
	public float scaleX = 1.0f;
	public float scaleY = 1.0f;
	
	public final static float MAX_SCALE = 10.0f;
	public final static float MIN_SCALE = 0.1f;
	
	/**
	 * 
	 */
	public Camera(int x, int y, int width, int height) {
		this(new Rectangle(x,y,width,height));
	}
	public Camera(Rectangle cam) {
		viewPortRect = cam;
	}

	
	public void translate(int x, int y){
		viewPortRect.setLocation(viewPortRect.getX()+x, viewPortRect.getY()+y);
	}
	
	public void increaseScale(float inc){ 
		setScaleX(scaleX + inc);
		setScaleY(scaleY + inc);
		System.out.println("Scale camera:"+scaleX +";"+scaleY);
	}
	
	public void setScaleX(float scaleX){
		this.scaleX = scaleX;
		if(this.scaleX > MAX_SCALE)
			this.scaleX = MAX_SCALE;
		else if(this.scaleX < MIN_SCALE)
			this.scaleX = MIN_SCALE;
	}
	public void setScaleY(float scaleY){
		this.scaleY = scaleY;
		if(this.scaleY > MAX_SCALE)
			this.scaleY = MAX_SCALE;
		else if(this.scaleY < MIN_SCALE)
			this.scaleY = MIN_SCALE;
	}
	
}
