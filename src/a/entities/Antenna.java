/**
 * 
 */
package a.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.gui.GUIContext;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Antenna extends Moveable {

	Circle zone;
	
	
	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Antenna(GUIContext container, Image image, int x, int y,Camera camera, int radius) {
		super(container, image, x, y, camera);
		zone = new Circle(x, y, radius);
		
	}

	public void setRadius(float radius){
		zone.setRadius(radius);
	}
	
	public void render(Graphics g){
		super.render(container, g);
		g.setAntiAlias(true);
		g.draw(zone);
		g.setAntiAlias(false);
	}

	/* (non-Javadoc)
	 * @see a.entities.Moveable#positionChanged()
	 */
	@Override
	protected void positionChanged() {
		zone.setCenterX(super.getX());
		zone.setCenterY(super.getY());
	}
}
