/**
 * 
 */
package a.states.controllers;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.factories.MobileFactory;
import a.states.PowerControlView;
import a.states.gui.entites.Spark;
import a.utils.CalculDePuissance;
import a.utils.Configuration;
import a.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class AntennaAndMobilesController extends Controller {

	public static PowerControlView linkToView;
	
	public static enum services{
		voix,
		data1,
		data2;
	}
	final Service voix;
	final Service data1;
	final Service data2;
	
	
	public Antenna antenna;
	public ArrayList<Mobile> arrayMobiles = new ArrayList<Mobile>();
	int compteur = 0;

	public Camera cameraForAntennaAndMobiles;

	public ArrayList<Spark> arraySparks = new ArrayList<Spark>();
	public boolean disableSparks = false;
	
	
	/**
	 * Contient les objets selectionnes
	 */
	public ArrayList<Moveable> arraySelected = new ArrayList<Moveable>();

	boolean leftClickPressedOnMoveable = false;
	boolean selectByDragging = false;
	int xStartSelectionDragged = 0;
	int yStartSelectionDragged = 0;
	Rectangle zoneSelection;

	/**
	 * @throws SlickException
	 * 
	 */
	public AntennaAndMobilesController(GameContainer container,
			StateBasedGame game, int x, int y, int width, int height)
			throws SlickException {
		this(container, game, new Rectangle(x, y, width, height));
	}

	public AntennaAndMobilesController(GameContainer container,
			StateBasedGame game, Rectangle rect) throws SlickException {
		super(container, game);
		cameraForAntennaAndMobiles = new Camera(rect);

		antenna = new Antenna(ResourceManager.getImage("antenne"),
				(int) rect.getCenterX(), (int) rect.getCenterY(),
				cameraForAntennaAndMobiles, (int) rect.getHeight() / 2 - 40);

		//Mobile tmp = MobileFactory.createMobile(cameraForAntennaAndMobiles,MobileFactory.mobileType.MOBILE1);
		//addMobile(tmp,(int) rect.getCenterX() - 50, (int) rect.getCenterY() - 50);
		
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta)
			throws SlickException {
		ArrayList<Spark> arraySparksToAdd = new ArrayList<Spark>();
		ArrayList<Spark> arraySparksToDelete = new ArrayList<Spark>();
		for (Spark spark : arraySparks) {
			spark.update(delta);
			if (spark.isDead()) {
				for (int i = 0; i < spark.getNumberOfSparkToCreate(); i++) {
					Spark tmp = new Spark(spark.getX(), spark.getY());
					// Spark tmp = new Spark(spark); // BUG
					tmp.setNumberOfSparkToCreate(spark
							.getNumberOfSparkToCreate() - 1);
					if (tmp.getNumberOfSparkToCreate() >= 0) {
						arraySparksToAdd.add(tmp);
					}
				}
				arraySparksToDelete.add(spark);
			}
		}
		if (!arraySparksToDelete.isEmpty()) {
			arraySparks.removeAll(arraySparksToDelete);
		}
		arraySparks.addAll(arraySparksToAdd);

		
		
		antenna.setPuissInterf((float) CalculDePuissance.powerInterf(antenna,
				arrayMobiles));
		
		for (Mobile mob : arrayMobiles) {
			if ((mob.isConnecte()) && ((mob.getPuissanceEmission()) > 24
					|| mob.getPuissanceEmission() < -60) && (mob.getPuissanceEmission() != -100)) {
				mob.setConnecte(false);
				mob.setPuissanceEmission(-100);
			}

			if (mob.isConnecte()) {
				if (mob.getPuissanceEmission() == -100) {
					mob.setPuissanceEmission((float) CalculDePuissance.powerEmitted(
							antenna, mob, mob.getType()));
					mob.setSirTarget(mob.getType().getcOverI());
				}

				if (compteur > 10) {
					mob.setSirTarget(CalculDePuissance.sirTarget(antenna, mob));
					
				}
				if ((mob.getSirTarget() > CalculDePuissance.sirEstimated(
						arrayMobiles, antenna, mob)))
					mob.setPuissanceEmission((float) (mob
							.getPuissanceEmission() + 0.1));
				if ((mob.getSirTarget() < CalculDePuissance.sirEstimated(
						arrayMobiles, antenna, mob)))
					mob.setPuissanceEmission((float) (mob
							.getPuissanceEmission() - 0.1));
			}
		}
		if (compteur > 10) {
			compteur = 0;
		}
		compteur++;
	//*/
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.black);
		g.draw(cameraForAntennaAndMobiles.viewPortRect);
		g.setClip(cameraForAntennaAndMobiles.viewPortRect);

		g.drawString("xOff="+cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.viewPortRect.getX(), cameraForAntennaAndMobiles.viewPortRect.getY());
		g.drawString("yOff="+cameraForAntennaAndMobiles.yOffSet, cameraForAntennaAndMobiles.viewPortRect.getX(), cameraForAntennaAndMobiles.viewPortRect.getY()+18);
		g.translate(cameraForAntennaAndMobiles.xOffSet,
				cameraForAntennaAndMobiles.yOffSet);
		
		g.scale(cameraForAntennaAndMobiles.scaleX,
				cameraForAntennaAndMobiles.scaleY);

		antenna.render(g);
		if (arraySelected.contains(antenna)) {
			Color color = g.getColor();
			g.setColor(Color.green);
			g.drawOval(antenna.getX(), antenna.getY(), antenna.getHeight(),
					antenna.getWidth());
			g.setColor(color);
		}
		for (Mobile mobile : arrayMobiles) {
			mobile.render(g);
			if (arraySelected.contains(mobile)) {
				Color color = g.getColor();
				g.setColor(Color.green);
				g.drawOval(mobile.getX(), mobile.getY(), mobile.getHeight(), mobile.getWidth());
				g.setColor(color);
			}
		}

		renderInfosAntennaAndMobile(container, game, g);

		for (Spark spark : arraySparks) {
			g.setColor(spark.getColor());
			g.fillRect(spark.getX(), spark.getY(), 2, 2);
		}

		if (zoneSelection != null) {
			g.setColor(Color.green);
			g.draw(zoneSelection);
		}

		g.scale(1 / cameraForAntennaAndMobiles.scaleX,
				1 / cameraForAntennaAndMobiles.scaleY);
		g.translate(-cameraForAntennaAndMobiles.xOffSet,
				-cameraForAntennaAndMobiles.yOffSet);

		g.clearClip();
	}

	protected void renderInfosAntennaAndMobile(GameContainer container,
			StateBasedGame game, Graphics g) {
		if (antenna.isPointOn((int)(container.getInput().getAbsoluteMouseX() / cameraForAntennaAndMobiles.scaleX),
				(int)(container.getInput().getAbsoluteMouseY() / cameraForAntennaAndMobiles.scaleY))) {
			antenna.renderInfos(container, game, g);
		} else {
			for (Mobile mobile : arrayMobiles) {
				if (mobile != null
						&& mobile.isPointOn(
								(int)(container.getInput().getAbsoluteMouseX() / cameraForAntennaAndMobiles.scaleX),
								(int)(container.getInput().getAbsoluteMouseY() / cameraForAntennaAndMobiles.scaleY))) {
					mobile.renderInfos(container, game, g);
					return;
				}
			}
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		x = (int) (x / cameraForAntennaAndMobiles.scaleX);
		y = (int) (y / cameraForAntennaAndMobiles.scaleY);
		super.mousePressed(button, x, y);

		if (Input.MOUSE_LEFT_BUTTON == button) {
			boolean tmp = false;
			if (this.antenna.isPointOn(x, y)) {
				if (!arraySelected.contains(this.antenna)) {
					if (!Keyboard.isKeyDown(Input.KEY_LSHIFT))
						arraySelected.clear();
					arraySelected.add(this.antenna);
				}
				tmp = true;
				this.leftClickPressedOnMoveable = true;
			} else {
				for (Mobile a : this.arrayMobiles) {
					if (a.isPointOn(x, y)) {
						if (!arraySelected.contains(a)) {
							if (!Keyboard.isKeyDown(Input.KEY_LSHIFT))
								arraySelected.clear();
							arraySelected.add(a);
						}
						this.leftClickPressedOnMoveable = true;
						tmp = true;
						break;
					}
				}
			}
			if (!tmp) {
				if (!Keyboard.isKeyDown(Input.KEY_LSHIFT))
					arraySelected.clear();
				this.leftClickPressedOnMoveable = false;
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		x = (int) (x / cameraForAntennaAndMobiles.scaleX);
		y = (int) (y / cameraForAntennaAndMobiles.scaleY);
		super.mouseReleased(button, x, y);
		try {
			if (Keyboard.isKeyDown(Input.KEY_1)) {
				Mobile tmp = MobileFactory.createMobile(
						cameraForAntennaAndMobiles,
						MobileFactory.mobileType.MOBILE1);

				addMobile(tmp,x - cameraForAntennaAndMobiles.xOffSet, y
						- cameraForAntennaAndMobiles.yOffSet);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			if (Keyboard.isKeyDown(Input.KEY_2)) {
				for(int i=0;i<20;i++){
					Mobile tmp = MobileFactory.createMobile(
							cameraForAntennaAndMobiles,
							MobileFactory.mobileType.MOBILE1);
	
					int yy  = y - cameraForAntennaAndMobiles.yOffSet;
					if(i > 10){
						yy += (int)tmp.getHeight()+1;
					}
					addMobile(tmp,x - cameraForAntennaAndMobiles.xOffSet+i*((int)tmp.getWidth()+3), yy);
				}
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (this.selectByDragging && button == Input.MOUSE_LEFT_BUTTON) {
			if(!Keyboard.isKeyDown(Input.KEY_LSHIFT))
				this.arraySelected.clear();
			if (zoneSelection != null)
				for (Mobile a : this.arrayMobiles) {
					if (this.zoneSelection.contains(a.getX(), a.getY())
							|| a.shape.intersects(zoneSelection)) {
						this.arraySelected.add(a);
					}
				}
			selectByDragging = false;
			zoneSelection = null;
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		oldx /= (cameraForAntennaAndMobiles.scaleX);
		oldy /= (cameraForAntennaAndMobiles.scaleY);
		newx /= (cameraForAntennaAndMobiles.scaleX);
		newy /= (cameraForAntennaAndMobiles.scaleY);
		super.mouseDragged(oldx, oldy, newx, newy);
		
		if (Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if(this.leftClickPressedOnMoveable){
				for (Moveable tmp : this.arraySelected)
					tmp.setLocation(tmp.getX() + newx - oldx, tmp.getY() + newy
							- oldy);
			}

			if (!this.selectByDragging && !this.leftClickPressedOnMoveable) {
				selectByDragging = true;
				this.xStartSelectionDragged = newx
						- this.cameraForAntennaAndMobiles.xOffSet;
				this.yStartSelectionDragged = newy
						- this.cameraForAntennaAndMobiles.yOffSet;
			} else if (this.selectByDragging) {
				int xRect = Math.min(this.xStartSelectionDragged, newx
						- this.cameraForAntennaAndMobiles.xOffSet);
				int yRect = Math.min(this.yStartSelectionDragged, newy
						- this.cameraForAntennaAndMobiles.yOffSet);
				int widthSelection = Math.max(this.xStartSelectionDragged, newx
						- this.cameraForAntennaAndMobiles.xOffSet)
						- Math.min(this.xStartSelectionDragged, newx
								- this.cameraForAntennaAndMobiles.xOffSet);
				int heightSelection = Math.max(this.yStartSelectionDragged,
						newy - this.cameraForAntennaAndMobiles.yOffSet)
						- Math.min(this.yStartSelectionDragged, newy
								- this.cameraForAntennaAndMobiles.yOffSet);

				zoneSelection = new Rectangle(xRect, yRect, widthSelection,
						heightSelection);
			}
		}

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);

	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		// System.out.println(key + " name="+Input.getKeyName(key));
		switch (key) {
		case Input.KEY_Y:
			this.disableSparks = !disableSparks;
			break;
		case Input.KEY_A:
			this.toggleConnectedSelected();
			break;
		case Input.KEY_S:
			this.changeModeSelected(services.voix);
			break;
		case Input.KEY_D:
			this.changeModeSelected(services.data1);
			break;
		case Input.KEY_F:
			this.changeModeSelected(services.data2);
			break;
		case Input.KEY_L:
			this.cameraForAntennaAndMobiles.scaleX = 1.0f;
			this.cameraForAntennaAndMobiles.scaleY = 1.0f;
			break;
		case Input.KEY_DELETE:
			for (Moveable a : this.arraySelected)
				if (a instanceof Mobile) {
					if(!disableSparks)
						createSparks(a.getX(), a.getY(), 5);
					deleteMobile((Mobile) a);
				}
			break;
		case Input.KEY_BACK:
			for (Moveable a : this.arraySelected)
				if (a instanceof Mobile) {
					if(!disableSparks)
						createSparks(a.getX(), a.getY(), 5);
					deleteMobile((Mobile) a);
				}
			break;
		case Input.KEY_RIGHT:
			this.cameraForAntennaAndMobiles.xOffSet -= 10 * Configuration
					.getMultiplierScrollArrows();
			break;
		case Input.KEY_LEFT:
			this.cameraForAntennaAndMobiles.xOffSet += 10 * Configuration
					.getMultiplierScrollArrows();
			break;
		case Input.KEY_UP:
			this.cameraForAntennaAndMobiles.yOffSet += 10 * Configuration
					.getMultiplierScrollArrows();
			break;
		case Input.KEY_DOWN:
			this.cameraForAntennaAndMobiles.yOffSet -= 10 * Configuration
					.getMultiplierScrollArrows();
			break;
		}
	}

	public void createSparks(int x, int y, int numberOfSparks) {
		for (int i = 0; i < numberOfSparks; i++) {
			Spark tmp = new Spark(x, y);
			this.arraySparks.add(tmp);
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		 cameraForAntennaAndMobiles.increaseScale(((float)change)/40.0f);
		// System.out.println("wheel");
	}

	/**
	 * Main function to add mobile (no manager)
	 * @param mobile
	 * @param x
	 * @param y
	 */
	public void addMobile(Mobile mobile, int x, int y){
		mobile.setLocation(x, y);
		arrayMobiles.add(mobile);
		
		AntennaAndMobilesController.linkToView.mobileAdded(mobile);
		
		System.out.println("mobile ajoute "+mobile.id+" (" + mobile.getX() + ","
				+ mobile.getY() + ")");
	}
	
	/**
	 * Main function to delete mobile (no manager)
	 * @param mobile
	 */
	public void deleteMobile(Mobile mobile) {
		this.arrayMobiles.remove(mobile);
		// TODO Remove from antenna
		AntennaAndMobilesController.linkToView.mobileDeleted(mobile);
	}
	
	/**
	 * Change selected mobiles to connected if the mobile is disconnected (and opposite)
	 */
	public void toggleConnectedSelected() {
		for(Moveable a : this.arraySelected){
			if(a instanceof Mobile){
				((Mobile) a).toggleConnecte();
			}
		}
	}
	/**
	 * Change mode of selected mobiles
	 * @param service
	 */
	public void changeModeSelected(services service) {
		for(Moveable a : this.arraySelected){
			if(a instanceof Mobile){
				switch(service){
				case data1:
					((Mobile) a).setType(new Service(data1));
					((Mobile) a).setBlerEstimated(data1.getBlerTarget());
					break;
				case data2:
					((Mobile) a).setType(new Service(data2));
					((Mobile) a).setBlerEstimated(data2.getBlerTarget());
					break;
				case voix:
					((Mobile) a).setType(new Service(voix));
					((Mobile) a).setBlerEstimated(voix.getBlerTarget());
					break;
				}
			}
		}
	}

	public void setErrorSelected(float error){
		for(Moveable a : this.arraySelected){
			if(a instanceof Mobile){
				((Mobile) a).setBlerEstimated(error);
			}
		}
	}
	
	/**
	 * @return the voix
	 */
	public Service getVoix() {
		return voix;
	}

	/**
	 * @return the data1
	 */
	public Service getData1() {
		return data1;
	}

	/**
	 * @return the data2
	 */
	public Service getData2() {
		return data2;
	}

}
