/**
 * 
 */
package a.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * @author Yoann CAPLAIN
 *
 */
public abstract class Moveable extends MouseOverArea{

	boolean mouseWasOn = false;
	
	Camera camera;
	
	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Moveable(GUIContext container, Image image, int x, int y, Camera camera) {
		super(container, image, x, y);
		this.camera = camera;
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		x -= camera.xOffSet;
		y -= camera.yOffSet;
		super.mousePressed(button, x, y);
		if(super.isMouseOver()){
			mouseWasOn = true;
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y){
		x -= camera.xOffSet;
		y -= camera.yOffSet;
		super.mouseReleased(button, x, y);
		mouseWasOn = false;
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		newx -= camera.xOffSet;
		newy -= camera.yOffSet;
		super.mouseDragged(oldx, oldy, newx, newy);
		if(mouseWasOn){
			if(camera.viewPortRect.contains(newx, newy))
				this.setLocation(newx-super.getWidth()/2, newy-super.getHeight()/2);
			positionChanged();
		}
	}
	
	protected abstract void positionChanged();
}
