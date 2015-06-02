/**
 * 
 */
package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Yoann CAPLAIN
 *
 */
public abstract class Moveable /* extends MouseOverArea */{

	/**
	 * Corps de l'objet, permet selection, souris dessus, etc
	 */
	public Shape shape;

	/**
	 * Pour savoir connaitre le offset et le scale
	 */
	Camera camera;

	public Image imageParDefaut;

	// boolean mouseWasOn = false;

	// /////////////////Modif Nico//////////////////////////////

	float puissanceEmission; // Puissance d'emission de l'apareil.
	int gain; // Valeur fixe du gain d'antenne
	int frequence; // En Mhz

	// //////////////////////////////////////////////

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Moveable(int x, int y, Camera camera) {
		this.camera = camera;
		frequence = 900;
		
	}

	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(getX() + shape.getWidth() / 2, getY() + shape.getHeight() / 2, 200, 200);
	}

	protected abstract void positionChanged();

	public boolean isPointOn(int x, int y) {
		//return shape.contains(x, y);
		return shape.contains(-camera.xOffSet + x, -camera.yOffSet + y);
	}

	/**
	 * @return the puissanceEmission
	 */
	public float getPuissanceEmission() {
		return puissanceEmission;
	}

	/**
	 * @param puissanceEmission
	 *            the puissanceEmission to set
	 */
	public void setPuissanceEmission(float puissanceEmission) {
		this.puissanceEmission = puissanceEmission;
	}

	/**
	 * @return the gain
	 */
	public int getGain() {
		return gain;
	}

	/**
	 * @param gain
	 *            the gain to set
	 */
	public void setGain(int gain) {
		this.gain = gain;
	}

	/**
	 * @return the frequence
	 */
	public int getFrequence() {
		return frequence;
	}

	/**
	 * @param frequence
	 *            the frequence to set
	 */
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return (int) shape.getX();
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return (int) shape.getY();
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		shape.setX(x);
		positionChanged();
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		shape.setY(y);
		positionChanged();
	}

	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public float getWidth() {
		return shape.getWidth();
	}

	public float getHeight() {
		return shape.getHeight();
	}

}
