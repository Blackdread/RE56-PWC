/**
 * 
 */
package a.states.controllers;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.factories.MobileFactory;
import a.utils.ResourceManager;

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
		
		zoneAntennaPower = new Antenna(container, ResourceManager.getImage("antenne")/*new Image("images/antenne.png")*/, (int)rect.getCenterX(), (int) rect.getCenterY(),cameraForAntennaAndMobiles, (int)rect.getHeight()/2-10);
		
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
		g.setColor(Color.black);
		g.draw(rectangleClipAntennaAndMobile);
		g.setClip(rectangleClipAntennaAndMobile);
		
		g.translate(cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.yOffSet);
		g.scale(cameraForAntennaAndMobiles.scaleX, cameraForAntennaAndMobiles.scaleY);
		
		zoneAntennaPower.render(g);
		for(Mobile a : arrayMobiles)
			a.render(g);
		
		renderInfosAntennaAndMobile(container, game, g);
		
		g.scale(1/cameraForAntennaAndMobiles.scaleX, 1/cameraForAntennaAndMobiles.scaleY);
		g.translate(-cameraForAntennaAndMobiles.xOffSet, -cameraForAntennaAndMobiles.yOffSet);
		
		g.clearClip();
	}

	protected void renderInfosAntennaAndMobile(GameContainer container, StateBasedGame game, Graphics g){
		if(zoneAntennaPower.isMouseOver()){
			zoneAntennaPower.renderInfos(container, game, g);
		}else{
			for(Mobile a : arrayMobiles){
				if(a != null && a.isMouseOver()){
					a.renderInfos(container, game, g);
					return;
				}
			}
		}
	}
	//*
//	@Override
	public void mouseReleased(int button, int x, int y){
		//super.mouseReleased(button, x, y);
		System.out.println("mouse released");
		Mobile tmp;
		try {
			tmp = MobileFactory.createMobile(cameraForAntennaAndMobiles, MobileFactory.mobileType.MOBILE1);

			tmp.setLocation(x - cameraForAntennaAndMobiles.xOffSet, y - cameraForAntennaAndMobiles.yOffSet);
			arrayMobiles.add(tmp);
			System.out.println("mobile ajoute");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	// */
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_RIGHT:
			this.cameraForAntennaAndMobiles.xOffSet -= 10;
			break;
		case Input.KEY_LEFT:
			this.cameraForAntennaAndMobiles.xOffSet += 10;
			break;
		case Input.KEY_UP:
			this.cameraForAntennaAndMobiles.yOffSet += 10;
			break;
		case Input.KEY_DOWN:
			this.cameraForAntennaAndMobiles.yOffSet -= 10;
			break;
		}
	}
	
	
	@Override
	public void mouseWheelMoved(int change){
		super.mouseWheelMoved(change);
		cameraForAntennaAndMobiles.increaseScale(((float)change)/40.0f);
		System.out.println("wheel");
	}
	
}
