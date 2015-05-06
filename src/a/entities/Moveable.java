/**
 * 
 */
package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

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
		// ca faisait une boucle lors de l'ajout d'un objet en cliquant
		this.consumeEvent();
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
		oldx -= camera.xOffSet;
		oldy -= camera.yOffSet;
		
		final int diffX = newx - oldx;
		final int diffY = newy - oldy;
		super.mouseDragged(oldx, oldy, newx, newy);
		if(mouseWasOn){
			if(camera.viewPortRect.contains(newx, newy)){
				//this.setLocation(newx-super.getWidth()/2, newy-super.getHeight()/2);
				this.setLocation(super.getX()+diffX, super.getY()+diffY);
			}
			positionChanged();
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		newx -= camera.xOffSet;
		newy -= camera.yOffSet;
		oldx -= camera.xOffSet;
		oldy -= camera.yOffSet;
		
		super.mouseMoved(oldx, oldy, newx, newy);
	}
	
	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g){
		g.setColor(Color.blue);
		g.fillRect(getX()+getWidth()/2, getY()+getHeight()/2, 300, 300);
	}
	
	protected abstract void positionChanged();
}
