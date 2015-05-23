/**
 * 
 */
package a.states.controllers;


import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.factories.MobileFactory;
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
	
	public Camera cameraForAntennaAndMobiles;
	
	/**
	 * Contient les objets selectionnes
	 */
	public ArrayList<Moveable> arraySelected = new ArrayList<Moveable>();
	
	boolean leftClickPressedOnMoveable = false;
	
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
		
		g.scale(1/cameraForAntennaAndMobiles.scaleX, 1/cameraForAntennaAndMobiles.scaleY);
		g.translate(-cameraForAntennaAndMobiles.xOffSet, -cameraForAntennaAndMobiles.yOffSet);
		
		g.clearClip();
	}

	protected void renderInfosAntennaAndMobile(GameContainer container, StateBasedGame game, Graphics g){
		//*
		if(antenna.isPointOn(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
			antenna.renderInfos(container, game, g);
		}else{
			for(Mobile a : arrayMobiles){
				if(a != null && a.isPointOn(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
					a.renderInfos(container, game, g);
					return;
				}
			}
		}//*/
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
				System.out.println("mobile ajoute");
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
		
		for(Moveable tmp : this.arraySelected)
			tmp.setLocation(tmp.getX() + newx-oldx, tmp.getY() + newy-oldy);
		
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}

	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
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
		cameraForAntennaAndMobiles.increaseScale(((float)change)/40.0f);
		System.out.println("wheel");
	}
	
}
