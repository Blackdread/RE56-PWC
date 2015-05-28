package a.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.states.controllers.*;
import a.states.gui.Graph;
import a.states.gui.Point;
import a.states.gui.TraceGraph;
import a.states.gui.renderGraph;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 12 10 2012
 */
public class PowerControlView extends View {

	public static final int ID = 2;

	private Image background;

	TraceGraph testTraceGraph = new TraceGraph();
	Graph graph1 = new Graph();
	Graph graph2 = new Graph();
	
	private int xTemp = -20;

	private AntennaAndMobilesController antennaAndMobilesController;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		Rectangle rectRenderAntennaMobiles = new Rectangle(0, 0,
				2 * container.getWidth() / 3, 2 * container.getHeight() / 3);
		antennaAndMobilesController = new AntennaAndMobilesController(
				container, game, rectRenderAntennaMobiles);

		testTraceGraph.addGraphe(graph1);
		testTraceGraph.addGraphe(graph2);
		graph1.addPoint(new Point());
		graph2.addPoint(new Point());
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta)
			throws SlickException {
		super.update(container, sbGame, delta);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// g.drawImage(background, 0, 0);
		g.setBackground(Color.white);
		super.render(container, game, g);
		g.setLineWidth(2.0f);

		antennaAndMobilesController.render(container, game, g);

		g.setColor(Color.black);
		g.drawString("Vue principale", 50, 50);
		
		renderGraph.renderGraphe(testTraceGraph, g, 0, 0, 200, 300);
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (isMouseInRectCamera())
			antennaAndMobilesController.keyPressed(key, c);

		graph1.addPoint(new Point((float) xTemp, (float) Math
				.random() * 20));
		graph2.addPoint(new Point((float) xTemp, (float) Math
				.random() * 200 + 15.0f));
		if(xTemp%10 == 0){
			graph2.addPoint(new Point((float) xTemp, -30));
		}
		xTemp += 15;
		
		if(key == Input.KEY_MINUS){
			graph1.removePoint(0, graph1.size()/2);
			graph2.removePoint(0, graph2.size()/2);
		}
		
		testTraceGraph.majMaxMinGlobal();
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);

		if (isMouseInRectCamera())
			antennaAndMobilesController.mousePressed(button, x, y);
	}

	@Override
	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		if (isMouseInRectCamera())
			antennaAndMobilesController.mouseWheelMoved(change
					/ Math.abs(change));
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		if (isMouseInRectCamera())
			antennaAndMobilesController.mouseReleased(button, x, y);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
		if (isMouseInRectCamera())
			antennaAndMobilesController.mouseDragged(oldx, oldy, newx, newy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		if (isMouseInRectCamera())
			antennaAndMobilesController.mouseMoved(oldx, oldy, newx, newy);
	}

	private boolean isMouseInRectCamera() {
		return antennaAndMobilesController.cameraForAntennaAndMobiles.viewPortRect
				.contains(container.getInput().getMouseX(), container
						.getInput().getMouseY());
	}

	@Override
	public int getID() {
		return PowerControlView.ID;
	}

}
