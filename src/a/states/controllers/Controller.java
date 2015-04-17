/**
 * 
 */
package a.states.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Heriter de BasicGameState n'est pas vraiment juste mais c'est juste pour avoir InputListener (les fonctions)
 * @author Yoann CAPLAIN
 *
 */
public abstract class Controller extends BasicGameState{
	
	/**
	 * Origin from (top left)
	 */
	int originX = 0;
	/**
	 * Origin from (top left)
	 */
	int originY = 0;
	
	/**
	 * Scale X de l'ensemble du renderer
	 */
	float scaleX = 1.0f;
	/**
	 * Scale Y de l'ensemble du renderer
	 */
	float scaleY = 1.0f;
	
	public Controller(GameContainer container, StateBasedGame game) throws SlickException{
		init(container, game);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public abstract void init(GameContainer container, StateBasedGame game) throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public abstract void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public final int getID() {
		return 0;
	}

}
