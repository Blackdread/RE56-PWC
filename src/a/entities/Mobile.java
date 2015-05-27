/**
 * 
 */
package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Mobile extends Moveable {

	// //////////////////ModifNico/////////////////////////////////////////////
	Service type;
	boolean connecte; // Boolean, pour savoir si le mobile est connect� ou pas
						// au NodeB
	double sirTarget; // SIR target
	float blerTarget; // BLER_Target

	// //////////////////////////////////////////////////////////////////

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Mobile(int x, int y, Camera camera) {
		super(x, y, camera);
		gain = 2;
		connecte = false;
		puissanceEmission = -100;
	}

	public void render(Graphics g) {
		g.drawImage(super.imageParDefaut, super.getX(), super.getY());
	}

	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g) {
		super.renderInfos(container, game, g);
		g.setColor(Color.red);
		g.drawString("Value = 111", getX() + getWidth() / 2 + 10, getY() + getHeight() / 2);
		type = new Service();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a.entities.Moveable#positionChanged()
	 */
	@Override
	protected void positionChanged() {
	}

	/**
	 * @return the connecte
	 */
	public boolean isConnecte() {
		return connecte;
	}

	/**
	 * @param connecte the connecte to set
	 */
	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	/**
	 * @return the sirTarget
	 */
	public double getSirTarget() {
		return sirTarget;
	}

	/**
	 * @param d the sirTarget to set
	 */
	public void setSirTarget(double d) {
		this.sirTarget = d;
	}

	/**
	 * @return the blerTarget
	 */
	public float getBlerTarget() {
		return blerTarget;
	}

	/**
	 * @param blerTarget the blerTarget to set
	 */
	public void setBlerTarget(float blerTarget) {
		this.blerTarget = blerTarget;
	}

	/**
	 * @return the type
	 */
	public synchronized Service getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public synchronized void setType(Service type) {
		this.type = type;
	}
}
