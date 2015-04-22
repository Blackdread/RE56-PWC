package a.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.states.controllers.*;


/**
 * 
 * @author Yoann CAPLAIN
 * @since 12 10 2012
 */
public class PowerControlView extends View {

	public static final int ID = 2;
	
	private Image background;

	
	private AntennaAndMobilesController antennaAndMobilesController;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		Rectangle rectRenderAntennaMobiles = new Rectangle(0, 0, 2*container.getWidth()/3, 2*container.getHeight()/3);
		antennaAndMobilesController = new AntennaAndMobilesController(container, game, rectRenderAntennaMobiles);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.drawImage(background, 0, 0);
		g.setBackground(Color.white);
		super.render(container, game, g);
		g.setLineWidth(2.0f);
		
		antennaAndMobilesController.render(container, game, g);
		
		g.setColor(Color.black);
		g.drawString("Vue principale", 50, 50);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		antennaAndMobilesController.keyPressed(key, c);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
	}
	
	@Override
	public void mouseWheelMoved(int change){
		super.mouseWheelMoved(change);
		antennaAndMobilesController.mouseWheelMoved(change);
	}
	
	@Override
	public void mouseReleased(int button, int x, int y){
		super.mouseReleased(button, x, y);
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public int getID() {
		return PowerControlView.ID;
	}

}
