/**
 * 
 */
package a.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Mobile extends Moveable {

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Mobile(GUIContext container, Image image, int x, int y, Camera camera) {
		super(container, image, x, y, camera);
	}

	public void render(Graphics g){
		super.render(container, g);
	}

	/* (non-Javadoc)
	 * @see a.entities.Moveable#positionChanged()
	 */
	@Override
	protected void positionChanged() {
	}

}
