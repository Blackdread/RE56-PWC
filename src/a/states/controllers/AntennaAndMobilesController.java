/**
 * 
 */
package a.states.controllers;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.factories.MobileFactory;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class AntennaAndMobilesController extends Controller{

	private Rectangle rectangleClipAntennaAndMobile;
	public Antenna zoneAntennaPower;
	public ArrayList<Mobile> arrayMobiles = new ArrayList<Mobile>();
	
	public Camera cameraForAntennaAndMobiles;
	
	
	/**
	 * @throws SlickException 
	 * 
	 */
	public AntennaAndMobilesController(GameContainer container, StateBasedGame game, int x, int y, int width, int height) throws SlickException {
		this(container, game, new Rectangle(x, y, width, height));
	}
	public AntennaAndMobilesController(GameContainer container, StateBasedGame game, Rectangle rect) throws SlickException {
		super(container, game);
		originX = (int) rect.getX();
		originY = (int) rect.getY();
		rectangleClipAntennaAndMobile = rect;
		cameraForAntennaAndMobiles = new Camera(rectangleClipAntennaAndMobile);
		zoneAntennaPower = new Antenna(container, new Image("images/antenne.png"), (int)rect.getCenterX(), (int) rect.getCenterY(),cameraForAntennaAndMobiles, (int)rect.getHeight()/2-10);
		
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
		
		
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
		g.draw(rectangleClipAntennaAndMobile);
		g.setClip(rectangleClipAntennaAndMobile);
		
		g.translate(cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.yOffSet);
		g.scale(cameraForAntennaAndMobiles.scaleX, cameraForAntennaAndMobiles.scaleY);
		
		zoneAntennaPower.render(g);
		for(Mobile a : arrayMobiles)
			a.render(g);
		
		g.scale(1/cameraForAntennaAndMobiles.scaleX, 1/cameraForAntennaAndMobiles.scaleY);
		g.translate(-cameraForAntennaAndMobiles.xOffSet, -cameraForAntennaAndMobiles.yOffSet);
		
		g.clearClip();
	}

	
	@Override
	public void mouseWheelMoved(int change){
		super.mouseWheelMoved(change);
		cameraForAntennaAndMobiles.increaseScale(((float)change)/40.0f);
	}
}
