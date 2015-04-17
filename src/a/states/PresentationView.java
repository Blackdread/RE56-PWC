package a.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import a.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 12 10 2012
 */
public class PresentationView extends View {

	public static final int ID = 1;
	
	private static final int WAIT_TIME_BEFORE_NEXTR = 200;
	
	private Image background;
	private boolean ready;	// pret a passer au state suivant
	private Timer timer;
	
	public PresentationView(){
		super();
		timer = new Timer(WAIT_TIME_BEFORE_NEXTR);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		timer.update(delta);
		if (timer.isTimeComplete()) {
			// Voir si je ne fais pas d'autres trucs entre temps... (charger les images du prochain etat...)
			ready = true;
			timer.resetTime();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.drawImage(background, 0, 0);
		super.render(container, game, g);
		g.drawString("Power Control RE56", container.getWidth() / 2 - 90, container.getHeight() / 2 - 50);
		if (ready) {
			g.drawString("Press a key or click", container.getWidth() / 2 - 90, container.getHeight() / 2 + 10);
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		goToMenu();
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		goToMenu();
	}
	
	private void goToMenu() {
		if (ready) {
			game.getContainer().setMouseGrabbed(false);
			game.enterState(PowerControlView.ID, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	@Override
	public int getID() {
		return PresentationView.ID;
	}

}
