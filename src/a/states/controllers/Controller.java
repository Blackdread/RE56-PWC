package a.states.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Heriter de BasicGameState n'est pas vraiment juste mais c'est juste pour avoir InputListener (les fonctions)
 * @author Yoann CAPLAIN
 *
 */
public abstract class Controller /*extends BasicGameState*/ implements InputListener{
	
	/*
	 * Scale X de l'ensemble du renderer
	 */
	//float scaleX = 1.0f;
	/*
	 * Scale Y de l'ensemble du renderer
	 */
	//float scaleY = 1.0f;
	
	public Controller(GameContainer container, StateBasedGame game) throws SlickException{
		init(container, game);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	//@Override
	public abstract void init(GameContainer container, StateBasedGame game) throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
//	@Override
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
//	@Override
	public abstract void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
//	@Override
	public final int getID() {
		return 0;
	}
	
	
	@Override
	public void mouseWheelMoved(int change){
	
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.MouseListener#mouseClicked(int, int, int, int)
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(int button, int x, int y){
		
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.MouseListener#mousePressed(int, int, int)
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.MouseListener#mouseMoved(int, int, int, int)
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.MouseListener#mouseDragged(int, int, int, int)
	 */
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControlledInputReciever#setInput(org.newdawn.slick.Input)
	 */
	@Override
	public void setInput(Input input) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControlledInputReciever#isAcceptingInput()
	 */
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControlledInputReciever#inputEnded()
	 */
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControlledInputReciever#inputStarted()
	 */
	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(int key, char c) {
	
	}
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.KeyListener#keyReleased(int, char)
	 */
	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerLeftPressed(int)
	 */
	@Override
	public void controllerLeftPressed(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerLeftReleased(int)
	 */
	@Override
	public void controllerLeftReleased(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerRightPressed(int)
	 */
	@Override
	public void controllerRightPressed(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerRightReleased(int)
	 */
	@Override
	public void controllerRightReleased(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerUpPressed(int)
	 */
	@Override
	public void controllerUpPressed(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerUpReleased(int)
	 */
	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerDownPressed(int)
	 */
	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerDownReleased(int)
	 */
	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerButtonPressed(int, int)
	 */
	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.newdawn.slick.ControllerListener#controllerButtonReleased(int, int)
	 */
	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub
		
	}

}
