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

	TraceGraph traceGraph1 = new TraceGraph();
	TraceGraph traceGraph2 = new TraceGraph();
	Graph graph1 = new Graph();
	Graph graph2 = new Graph();

	private Rectangle zoneGraph1;
	private Rectangle zoneGraph2;
	
	/**
	 * Quand on clique sur un graphe, on le met en centre de l'ecran et prend l'ecran
	 */
	private TraceGraph bigGraphe = null;
	
	
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

		zoneGraph1 = new Rectangle(20, rectRenderAntennaMobiles.getHeight()+10, 300, container.getHeight() - rectRenderAntennaMobiles.getHeight()-20);
		
		zoneGraph2  = new Rectangle(zoneGraph1.getX()+zoneGraph1.getWidth()+10, zoneGraph1.getY(), zoneGraph1.getWidth(), zoneGraph1.getHeight());
		
		antennaAndMobilesController = new AntennaAndMobilesController(
				container, game, rectRenderAntennaMobiles);
		
		traceGraph1.addGraphe(graph1);
		traceGraph1.addGraphe(graph2);
		traceGraph2.addGraphe(graph2);
		graph1.addPoint(new Point());
		graph2.addPoint(new Point());
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta)
			throws SlickException {
		super.update(container, sbGame, delta);
		this.antennaAndMobilesController.update(container, sbGame, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// g.drawImage(background, 0, 0);
		g.setBackground(Color.white);
		super.render(container, game, g);
		g.setLineWidth(1.0f);
		
		antennaAndMobilesController.render(container, game, g);

		g.setColor(Color.black);
		
		/*
		 * On desine le grand graphe selectionner
		 */
		if(bigGraphe != null){
			g.setLineWidth(2.0f);
			renderGraph.renderGraphe(bigGraphe, g, 20, 20, container.getWidth()-40, container.getHeight()-40);
			renderGraph.mouseOverGraphe(bigGraphe, g, 20, 20, container.getWidth()-40, container.getHeight()-40, container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
		}else{
			/*
			 * On dessines les petits graphes
			 */
			g.setLineWidth(1.0f);
			renderGraph.renderGraphe(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(), (int)zoneGraph1.getHeight());
			if(zoneGraph1.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
				renderGraph.mouseOverGraphe(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(), (int)zoneGraph1.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			}
			renderGraph.renderGraphe(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(), (int)zoneGraph2.getHeight());
			if(zoneGraph2.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
				renderGraph.mouseOverGraphe(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(), (int)zoneGraph2.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			}
		}
		
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
		xTemp += 15 * Math.random();
		
		if(key == Input.KEY_MINUS){
			graph1.removePoint(0, graph1.size()/2);
			graph2.removePoint(0, graph2.size()/2);
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);

		if (isMouseInRectCamera() && bigGraphe == null)
			antennaAndMobilesController.mousePressed(button, x, y);
	}

	@Override
	public void mouseWheelMoved(int change) {
		super.mouseWheelMoved(change);
		if (isMouseInRectCamera() && bigGraphe == null)
			antennaAndMobilesController.mouseWheelMoved(change
					/ Math.abs(change));
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		if(this.zoneGraph1.contains(x, y) && bigGraphe == null){
			bigGraphe = this.traceGraph1;
		}else if(zoneGraph2.contains(x, y) && bigGraphe == null){
			bigGraphe = this.traceGraph2;
		}else{
			bigGraphe = null;
		}
		
		if (isMouseInRectCamera() && bigGraphe == null)
			antennaAndMobilesController.mouseReleased(button, x, y);
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx, oldy, newx, newy);
		if (isMouseInRectCamera() && bigGraphe == null)
			antennaAndMobilesController.mouseDragged(oldx, oldy, newx, newy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		if (isMouseInRectCamera() && bigGraphe == null)
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
