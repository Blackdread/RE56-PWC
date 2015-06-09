package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Antenna extends Moveable {
	
	Circle zone;

	// //////////////////////Modif Nico/////////////////////////////////////

	int nbreMobile; // utile ou pas, il devra
	float puissInterf; // Puissance des interferences, devra �tre actualis�
						// r�guli�rement, pr�voir une boucle.
	float sensibility; // en dB, puissance minimum en dB que peut entendre le
						// NodeB

	// ////////////////////////////////////////////////////////////////////

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public Antenna(Image image, int x, int y, Camera camera, int radius) {
		super(x, y, camera);
		zone = new Circle(x, y, radius);
		super.imageParDefaut = image;
		super.shape = new Rectangle(x, y, image.getWidth(), image.getHeight());

		// /////////////////////////Modif
		// Nico///////////////////////////////////////////////
		// Ajout des valeurs par d�faut du NodeB, sp�cification technique
		// donn�
		// par le tableau

		puissanceEmission = (float) (330 * 0.1);
		nbreMobile = 0;
		gain = 3;
		puissInterf = 0;
		sensibility = -30;

		// /////////////////////////////////////////////////////////////////////////

	}

	public void setRadius(float radius) {
		zone.setRadius(radius);
	}

	public void render(Graphics g) {
		g.drawImage(super.imageParDefaut, super.getX(), super.getY());
		g.setColor(Color.black);
		g.setAntiAlias(true);
		g.draw(zone);
		g.setAntiAlias(false);
	}
	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g) {
		super.renderInfos(container, game, g);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a.entities.Moveable#positionChanged()
	 */
	@Override
	protected void positionChanged() {
		zone.setCenterX(super.getX());
		zone.setCenterY(super.getY());
	}


	/**
	 * @return the sensibility
	 */
	public float getSensibility() {
		return sensibility;
	}

	/**
	 * @param sensibility
	 *            the sensibility to set
	 */
	public void setSensibility(float sensibility) {
		this.sensibility = sensibility;
	}

	
	// ///////////////////////////////////////////////////////////

	public float getPuissInterf() {
		return puissInterf;
	}

	public void setPuissInterf(float puissInterf) {
		this.puissInterf = puissInterf;
	}

	public int getNbreMobile() {
		return nbreMobile;
	}

	public void setNbreMobile(int nbreMobile) {
		this.nbreMobile = nbreMobile;
	}

}
