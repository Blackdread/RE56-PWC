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
import a.utils.CalculDePuissance;
import a.utils.Configuration;
import a.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class AntennaAndMobilesController extends Controller{

	public Antenna antenna;
	public ArrayList<Mobile> arrayMobiles = new ArrayList<Mobile>();
	int compteur;
	
	public Camera cameraForAntennaAndMobiles;
	
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
	public AntennaAndMobilesController(GameContainer container, StateBasedGame game, int x, int y, int width, int height) throws SlickException {
		this(container, game, new Rectangle(x, y, width, height));
	}
	public AntennaAndMobilesController(GameContainer container, StateBasedGame game, Rectangle rect) throws SlickException {
		super(container, game);
		cameraForAntennaAndMobiles = new Camera(rect);
		
		antenna = new Antenna(ResourceManager.getImage("antenne")/*new Image("images/antenne.png")*/, (int)rect.getCenterX(), (int) rect.getCenterY(),cameraForAntennaAndMobiles, (int)rect.getHeight()/2-10);
		
		Mobile tmp = MobileFactory.createMobile(cameraForAntennaAndMobiles, MobileFactory.mobileType.MOBILE1);
		tmp.setLocation((int)rect.getCenterX()-50, (int) rect.getCenterY()-50);
		arrayMobiles.add(tmp);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		antenna.setPuissInterf(CalculDePuissance.powerInterf(antenna,
				arrayMobiles));
		for (Mobile mob : arrayMobiles) {
			if (mob.isConnecte()) {
				if (compteur > 0) {

					mob.setPuissanceEmission(CalculDePuissance.powerEmitted(
							antenna, mob, mob.getType()));
					mob.setSirTarget(CalculDePuissance.sirTarget(antenna, mob,
							mob.getType()));

					compteur = 0;
				}
				if (mob.getPuissanceEmission()== -100) mob.setSirTarget(mob.getType().getcOverI());
				if (mob.getSirTarget() > CalculDePuissance.sirEstimated(
						arrayMobiles, antenna, mob, mob.getType()))
					mob.setPuissanceEmission((float) (mob
							.getPuissanceEmission() + 0.1));
				if (mob.getSirTarget() < CalculDePuissance.sirEstimated(
						arrayMobiles, antenna, mob, mob.getType()))
					mob.setPuissanceEmission((float) (mob
							.getPuissanceEmission() - 0.1));

			}
		}

		compteur++;
		
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
		g.setColor(Color.black);
		g.draw(cameraForAntennaAndMobiles.viewPortRect);
		g.setClip(cameraForAntennaAndMobiles.viewPortRect);
		
		g.translate(cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.yOffSet);
		g.scale(cameraForAntennaAndMobiles.scaleX, cameraForAntennaAndMobiles.scaleY);
		
		antenna.render(g);
		if(arraySelected.contains(antenna)){
			Color color = g.getColor();
			g.setColor(Color.green);
			g.drawOval(antenna.getX(), antenna.getY(), antenna.getHeight(), antenna.getWidth());
			g.setColor(color);
		}
		for(Mobile a : arrayMobiles){
			a.render(g);
			if(arraySelected.contains(a)){
				Color color = g.getColor();
				g.setColor(Color.green);
				g.drawOval(a.getX(), a.getY(), a.getHeight(), a.getWidth());
				g.setColor(color);
			}
		}
		
		renderInfosAntennaAndMobile(container, game, g);
		
		if(zoneSelection != null){
			g.setColor(Color.green);
			g.draw(zoneSelection);
		}
		
		g.scale(1/cameraForAntennaAndMobiles.scaleX, 1/cameraForAntennaAndMobiles.scaleY);
		g.translate(-cameraForAntennaAndMobiles.xOffSet, -cameraForAntennaAndMobiles.yOffSet);
		
		g.clearClip();
	}

	protected void renderInfosAntennaAndMobile(GameContainer container, StateBasedGame game, Graphics g){
		if(antenna.isPointOn(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
			antenna.renderInfos(container, game, g);
		}else{
			for(Mobile a : arrayMobiles){
				if(a != null && a.isPointOn(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
					a.renderInfos(container, game, g);
					return;
				}
			}
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(Input.MOUSE_LEFT_BUTTON == button){
			boolean tmp = false;
			if(this.antenna.isPointOn(x, y)){
				if(!arraySelected.contains(this.antenna)){
					if(!Keyboard.isKeyDown(Input.KEY_LSHIFT))
						arraySelected.clear();
					arraySelected.add(this.antenna);
				}
				tmp = true;
				this.leftClickPressedOnMoveable = true;
			}else{
				for(Mobile a : this.arrayMobiles){
					if(a.isPointOn(x, y)){
						if(!arraySelected.contains(a)){
							if(!Keyboard.isKeyDown(Input.KEY_LSHIFT))
								arraySelected.clear();
							arraySelected.add(a);
						}
						this.leftClickPressedOnMoveable = true;
						tmp = true;
						break;
					}
				}
			}
			if(!tmp){
				if(!Keyboard.isKeyDown(Input.KEY_LSHIFT))
					arraySelected.clear();
				this.leftClickPressedOnMoveable = false;
			}
		}
	}
	
	@Override
	public void mouseReleased(int button, int x, int y){
		super.mouseReleased(button, x, y);
		try {
			if(Keyboard.isKeyDown(Input.KEY_1)){
				Mobile tmp = MobileFactory.createMobile(cameraForAntennaAndMobiles, MobileFactory.mobileType.MOBILE1);
				
				tmp.setLocation(x - cameraForAntennaAndMobiles.xOffSet, y - cameraForAntennaAndMobiles.yOffSet);
				arrayMobiles.add(tmp);
				System.out.println("mobile ajoute ("+tmp.getX()+","+tmp.getY()+")");
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		if(this.selectByDragging && button == Input.MOUSE_LEFT_BUTTON){
			this.arraySelected.clear();
			if(zoneSelection != null)
			for(Mobile a : this.arrayMobiles){
				if(this.zoneSelection.contains(a.getX(), a.getY()) || a.shape.intersects(zoneSelection)){
					this.arraySelected.add(a);
				}
			}
			selectByDragging = false;
			zoneSelection = null;
		}
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
		
		if(Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)){
			for(Moveable tmp : this.arraySelected)
				tmp.setLocation(tmp.getX() + newx-oldx, tmp.getY() + newy-oldy);
			
			if(!this.selectByDragging && !this.leftClickPressedOnMoveable){
				selectByDragging = true;
				this.xStartSelectionDragged = newx - this.cameraForAntennaAndMobiles.xOffSet;
				this.yStartSelectionDragged = newy - this.cameraForAntennaAndMobiles.yOffSet;
			}else if(this.selectByDragging){
				int xRect = Math.min(this.xStartSelectionDragged, newx - this.cameraForAntennaAndMobiles.xOffSet);
				int yRect = Math.min(this.yStartSelectionDragged, newy - this.cameraForAntennaAndMobiles.yOffSet);
				int widthSelection = Math.max(this.xStartSelectionDragged, newx- this.cameraForAntennaAndMobiles.xOffSet) - Math.min(this.xStartSelectionDragged, newx - this.cameraForAntennaAndMobiles.xOffSet);
				int heightSelection = Math.max(this.yStartSelectionDragged, newy - this.cameraForAntennaAndMobiles.yOffSet) - Math.min(this.yStartSelectionDragged, newy - this.cameraForAntennaAndMobiles.yOffSet);
				
				zoneSelection = new Rectangle(xRect, yRect, widthSelection, heightSelection);
			}
		}
		
		
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}

	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		//System.out.println(key + " name="+Input.getKeyName(key));
		switch(key){
		case Input.KEY_DELETE:
			for(Moveable a : this.arraySelected)
				if(a instanceof Mobile){
					deleteMobile((Mobile) a);
				}
			break;
		case Input.KEY_BACK:
			for(Moveable a : this.arraySelected)
				if(a instanceof Mobile){
					deleteMobile((Mobile) a);
				}
			break;
		case Input.KEY_RIGHT:
			this.cameraForAntennaAndMobiles.xOffSet -= 10 * Configuration.getMultiplierScrollArrows();
			break;
		case Input.KEY_LEFT:
			this.cameraForAntennaAndMobiles.xOffSet += 10 * Configuration.getMultiplierScrollArrows();
			break;
		case Input.KEY_UP:
			this.cameraForAntennaAndMobiles.yOffSet += 10 * Configuration.getMultiplierScrollArrows();
			break;
		case Input.KEY_DOWN:
			this.cameraForAntennaAndMobiles.yOffSet -= 10 * Configuration.getMultiplierScrollArrows();
			break;
		}
	}
	
	
	@Override
	public void mouseWheelMoved(int change){
		super.mouseWheelMoved(change);
		//cameraForAntennaAndMobiles.increaseScale(((float)change)/40.0f);
		//System.out.println("wheel");
	}
	
	public void deleteMobile(Mobile mobile){
		this.arrayMobiles.remove(mobile);
		// TODO Remove from antenna
	}
	
}
