package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Antenna extends Moveable {

	Circle zone;
	Service voix;
	Service data1;
	Service data2;

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

		voix = new Service();
		data1= new Service();
		data2 = new Service();
		
		voix.setcOverI(-20);
		voix.setsF(256);
		voix.setBlerTarget(4);
		data1.setcOverI(-13.05);
		data1.setsF(32);
		data1.setBlerTarget(2);
		data2.setcOverI(-10.54);
		data2.setsF(16);
		data2.setBlerTarget(1.5);

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

	/**
	 * @return the voix
	 */
	public synchronized Service getVoix() {
		return voix;
	}

	/**
	 * @param voix the voix to set
	 */
	public synchronized void setVoix(Service voix) {
		this.voix = voix;
	}

	/**
	 * @return the data1
	 */
	public synchronized Service getData1() {
		return data1;
	}

	/**
	 * @param data1 the data1 to set
	 */
	public synchronized void setData1(Service data1) {
		this.data1 = data1;
	}

	/**
	 * @return the data2
	 */
	public synchronized Service getData2() {
		return data2;
	}

	/**
	 * @param data2 the data2 to set
	 */
	public synchronized void setData2(Service data2) {
		this.data2 = data2;
	}
}
