/**
 * 
 */
package a.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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

	// //////////////////////////////////////////////////////////////////
	
	public Image mobileDisconnected;
	
	/**
	 * id du mobile, supposed unique for all mobile (given by factory)
	 */
	public int id;
	
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
		type = new Service();
	}

	public void render(Graphics g) {
		if(connecte){
			g.drawImage(super.imageParDefaut, super.getX(), super.getY());
		}else{
			g.drawImage(mobileDisconnected, super.getX(), super.getY());
		}
		g.setColor(Color.black);
		g.drawString(""+this.id, super.getX()+super.getWidth()/2-g.getFont().getWidth(""+id)+2, super.getY()+super.getHeight()+1);
	}

	public void renderInfos(GameContainer container, StateBasedGame game, Graphics g) {
		super.renderInfos(container, game, g);
		g.setColor(Color.black);
		g.drawString("Connecte = "+connecte, getX() + getWidth() / 2 + 10, getY() + getHeight() / 2);
		g.drawString("SirTarget = "+sirTarget, getX() + getWidth() / 2 + 10, getY() + getHeight() / 2 + 18);
		g.drawString("BlerTarget = "+type.getBlerTarget(), getX() + getWidth() / 2 + 10, getY() + getHeight() / 2 + 36);
		g.drawString("PuissanceEmission = "+puissanceEmission, getX() + getWidth() / 2 + 10, getY() + getHeight() / 2 + 54);
		g.drawString("gain = "+gain, getX() + getWidth() / 2 + 10, getY() + getHeight() / 2 + 72);
		g.drawString("dist = non fait", getX() + getWidth() / 2 + 10, getY() + getHeight() / 2 + 90);
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
	
	public void toggleConnecte(){
		this.connecte = !this.connecte;
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
		return (float) type.blerTarget;
	}

	/**
	 * @param blerTarget the blerTarget to set
	 */
	public void setBlerTarget(float blerTarget) {
		type.blerTarget = blerTarget;
	}

	/**
	 * @return the type
	 */
	public Service getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Service type) {
		this.type = type;
	}
	
	
}
