/**
 * 
 */
package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

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

	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g){
		super.renderInfos(container, game, g);
		g.setColor(Color.red);
		g.drawString("Value = 111", getX()+getWidth()/2+10, getY()+getHeight()/2);
	}
	
	/* (non-Javadoc)
	 * @see a.entities.Moveable#positionChanged()
	 */
	@Override
	protected void positionChanged() {
	}

}
