package a.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

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
	public ArrayList<Mobile> mobiles; // Un nodeB, poss�de une ArrayList de
										// Mobile connect�.
	float sensibility; // en dB, puissance minimum en dB que peut entendre le
						// NodeB

	// ////////////////////////////////////////////////////////////////////

	int voix;
	int data1;
	int data2;

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
		super.shape = new Rectangle(x,y,image.getWidth(), image.getHeight());
		
		// /////////////////////////Modif
		// Nico///////////////////////////////////////////////
		// Ajout des valeurs par d�faut du NodeB, sp�cification technique donn�
		// par le tableau

		puissanceEmission = (float) (330 * 0.1);
		nbreMobile = 0;
		gain = 3;
		puissInterf = 0;
		sensibility = -30;
		/*
		 * voix.setcOverI(-20); voix.setsF(256); data1.setcOverI(-13.05);
		 * data1.setsF(32); data2.setcOverI(-10.54); data2.setsF(16);
		 */

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
	 * @return the mobiles
	 */
	public ArrayList<Mobile> getMobiles() {
		return mobiles;
	}

	/**
	 * @param mobiles
	 *            the mobiles to set
	 */
	public void setMobiles(ArrayList<Mobile> mobiles) {
		this.mobiles = mobiles;
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

	/**
	 * @return the voix
	 */
	public int getVoix() {
		return voix;
	}

	/**
	 * @param voix
	 *            the voix to set
	 */
	public void setVoix(int voix) {
		this.voix = voix;
	}

	/**
	 * @return the data1
	 */
	public int getData1() {
		return data1;
	}

	/**
	 * @param data1
	 *            the data1 to set
	 */
	public void setData1(int data1) {
		this.data1 = data1;
	}

	/**
	 * @return the data2
	 */
	public int getData2() {
		return data2;
	}

	/**
	 * @param data2
	 *            the data2 to set
	 */
	public void setData2(int data2) {
		this.data2 = data2;
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
