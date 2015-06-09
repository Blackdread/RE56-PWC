package a.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import a.entities.*;
import a.states.controllers.*;
import a.states.controllers.AntennaAndMobilesController.services;
import a.states.gui.Graph;
import a.states.gui.Point;
import a.states.gui.TraceGraph;
import a.states.gui.renderGraph;
import a.utils.ResourceManager;
import a.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 12 10 2012
 */
public class PowerControlView extends View {

	public static final int ID = 2;
	
	public static enum TraceGraphNo{
		traceGraph1,
		traceGraph2,
		traceGraph3;
	}
	
	TraceGraph traceGraph1 = new TraceGraph();
	TraceGraph traceGraph2 = new TraceGraph();
	TraceGraph traceGraph3 = new TraceGraph();
	Graph graph1 = new Graph();
	Graph graph2 = new Graph();
	
	ArrayList<InfoMobileAndGraph> arrayInfoMobileAndGraph = new ArrayList<InfoMobileAndGraph>();
	
	public static final int TIMER_GET_DATA = 100;
	private Timer testTimerGetData = new Timer(TIMER_GET_DATA);
	
	/**
	 * Zone camera
	 */
	private Rectangle rectRenderAntennaMobiles;
	/**
	 * Zone a droite de la camera
	 * meme hauteur que camera
	 */
	private Rectangle rectRenderOptions;
	//private MouseOverArea overConnecter;
	private Rectangle rectOverConnecter;
	
	private MouseOverArea overModeVoix;
	private MouseOverArea overModeData;
	private MouseOverArea overModeData2;
	
	private Rectangle rectError;
	private TextField textError;
	private MouseOverArea overOkError;
	
	private MouseOverArea overAddToGraph1;
	private MouseOverArea overAddToGraph2;
	private MouseOverArea overAddToGraph3;
	
	private MouseOverArea overPauseData;
	private boolean pauseData = false;
	
	private MouseOverArea overPauseSimulation;
	private boolean pauseSimulation = false;
	
	private Rectangle zoneGraph1;
	private Rectangle zoneGraph2;
	private Rectangle zoneGraph3;
	
	/**
	 * Quand on clique sur un graphe, on le met en centre de l'ecran et prend l'ecran
	 */
	private TraceGraph bigGraphe = null;
	private Rectangle zoneBigGraph;
	
	private int xTemp = -20;

	private AntennaAndMobilesController antennaAndMobilesController;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		traceGraph1.name = "nom1";
		traceGraph2.name = "nom2";
		traceGraph3.name = "nom3";
		
		graph1.nomGraphe = "g1";
		graph2.nomGraphe = "g2";
		
		rectRenderAntennaMobiles = new Rectangle(0, 0,
				2 * container.getWidth() / 3, 2 * container.getHeight() / 3);
		rectRenderOptions = new Rectangle(rectRenderAntennaMobiles.getWidth() + 10, 0,
				 container.getWidth() - rectRenderAntennaMobiles.getWidth()-10, rectRenderAntennaMobiles.getHeight());
		
		antennaAndMobilesController = new AntennaAndMobilesController(
				container, game, rectRenderAntennaMobiles);
		AntennaAndMobilesController.linkToView = this;
		
		//overConnecter = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(100, 22), (int)rectRenderOptions.getX()+10, (int)rectRenderOptions.getY()+5);
		rectOverConnecter = new Rectangle((int)rectRenderOptions.getX()+10, (int)rectRenderOptions.getY()+5, container.getDefaultFont().getWidth("(a) Toggle connected")+2, 22);

		overModeVoix = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(130, 22), (int)rectOverConnecter.getX(), (int)rectOverConnecter.getY()+(int)rectOverConnecter.getHeight()+10);
		overModeVoix.setMouseOverColor(Color.green);
		overModeVoix.setNormalColor(Color.lightGray);
		overModeVoix.setNormalImage(null);
		overModeVoix.setMouseOverImage(null);
		overModeData = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(130, 22), (int)overModeVoix.getX(), (int)overModeVoix.getY()+(int)overModeVoix.getHeight()+10);
		overModeData.setMouseOverColor(Color.green);
		overModeData.setNormalColor(Color.lightGray);
		overModeData.setNormalImage(null);
		overModeData.setMouseOverImage(null);
		overModeData2 = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(130, 22), (int)overModeData.getX(), (int)overModeData.getY()+(int)overModeData.getHeight()+10);
		overModeData2.setMouseOverColor(Color.green);
		overModeData2.setNormalColor(Color.lightGray);
		overModeData2.setNormalImage(null);
		overModeData2.setMouseOverImage(null);
		
		rectError = new Rectangle(overModeData2.getX(), overModeData2.getY()+overModeData2.getHeight()+10, 200,26);
		textError = new TextField(container, container.getDefaultFont(), (int)rectError.getX()+2, (int)rectError.getY()+2, (int)rectError.getWidth()-30, 20);
		overOkError = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(24, textError.getHeight()), (int)textError.getX()+(int)textError.getWidth()+2, (int)textError.getY());
		overOkError.setMouseOverColor(Color.green);
		overOkError.setNormalColor(Color.lightGray);
		overOkError.setNormalImage(null);
		overOkError.setMouseOverImage(null);
		
		overPauseData = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(container.getDefaultFont().getWidth("(p) Pause data recording")+4, 22), (int)overModeVoix.getX(), (int)overOkError.getY()+overOkError.getHeight()+55);
		overPauseData.setMouseOverColor(Color.green);
		overPauseData.setNormalColor(Color.green);
		overPauseData.setNormalImage(null);
		overPauseData.setMouseOverImage(null);
		
		overPauseSimulation = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(container.getDefaultFont().getWidth("(o) Pause simulation")+4, 22), (int)overPauseData.getX(), (int)overPauseData.getY()+overPauseData.getHeight()+10);
		overPauseSimulation.setMouseOverColor(Color.green);
		overPauseSimulation.setNormalColor(Color.green);
		overPauseSimulation.setNormalImage(null);
		overPauseSimulation.setMouseOverImage(null);
		
		
		overAddToGraph1 = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(container.getDefaultFont().getWidth("Put/remove in graph1")+4, 22), (int)rectRenderOptions.getX()+(int)rectRenderOptions.getWidth()/2, (int)rectRenderOptions.getY()+10);
		overAddToGraph1.setMouseOverColor(Color.green);
		overAddToGraph1.setNormalColor(Color.lightGray);
		overAddToGraph1.setNormalImage(null);
		overAddToGraph1.setMouseOverImage(null);
		
		overAddToGraph2 = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(container.getDefaultFont().getWidth("Put/remove in graph2")+4, 22), (int)rectRenderOptions.getX()+(int)rectRenderOptions.getWidth()/2, (int)overAddToGraph1.getY()+overAddToGraph1.getHeight()+10);
		overAddToGraph2.setMouseOverColor(Color.green);
		overAddToGraph2.setNormalColor(Color.lightGray);
		overAddToGraph2.setNormalImage(null);
		overAddToGraph2.setMouseOverImage(null);
		
		overAddToGraph3 = new MouseOverArea(container, ResourceManager.getImage("transparent").getScaledCopy(container.getDefaultFont().getWidth("Put/remove in graph3")+4, 22), (int)rectRenderOptions.getX()+(int)rectRenderOptions.getWidth()/2, (int)overAddToGraph2.getY()+overAddToGraph2.getHeight()+10);
		overAddToGraph3.setMouseOverColor(Color.green);
		overAddToGraph3.setNormalColor(Color.lightGray);
		overAddToGraph3.setNormalImage(null);
		overAddToGraph3.setMouseOverImage(null);
		
		zoneGraph1 = new Rectangle(20, rectRenderAntennaMobiles.getHeight()+10, 300, container.getHeight() - rectRenderAntennaMobiles.getHeight()-20);
		
		zoneGraph2  = new Rectangle(zoneGraph1.getX()+zoneGraph1.getWidth()+10, zoneGraph1.getY(), zoneGraph1.getWidth(), zoneGraph1.getHeight());
		
		zoneGraph3  = new Rectangle(zoneGraph2.getX()+zoneGraph2.getWidth()+10, zoneGraph2.getY(), zoneGraph2.getWidth(), zoneGraph2.getHeight());
		
		zoneBigGraph = new Rectangle(20, 20, container.getWidth()-40, container.getHeight()-40);
		
		antennaAndMobilesController = new AntennaAndMobilesController(
				container, game, rectRenderAntennaMobiles);
		
		/*
		traceGraph1.addGraphe(graph1);
		traceGraph2.addGraphe(graph2);
		traceGraph3.addGraphe(graph1);
		traceGraph3.addGraphe(graph2);
		// */
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta)
			throws SlickException {
		super.update(container, sbGame, delta);
		if(!pauseSimulation)
			this.antennaAndMobilesController.update(container, sbGame, delta);
		
		if(!pauseData)
			testTimerGetData.update(delta);
		
		if(testTimerGetData.isTimeComplete()){
			testTimerGetData.resetTimeDiffDeltaAndEventTime();
			for(InfoMobileAndGraph tmp : this.arrayInfoMobileAndGraph){
				tmp.getAndAddToGraphValue(xTemp, InfoMobileAndGraph.TypeData.power);
			}
			xTemp += TIMER_GET_DATA;
		}
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
		
		g.draw(rectRenderOptions);
		
		g.setColor(Color.black);
		g.drawString("(u) To delete points",rectRenderOptions.getX()+2,rectRenderOptions.getY()+rectRenderOptions.getHeight()-18);
		/*
		 * Dessine options
		 */
		
		//if(!this.antennaAndMobilesController.arraySelected.isEmpty()){
			
			g.drawString("(a) Toggle connected", rectOverConnecter.getX()+2, rectOverConnecter.getY()+2);
			g.draw(rectOverConnecter);
			
			overModeVoix.render(container, g);
			g.setColor(Color.black);
			g.drawString("(s) Mode voix", overModeVoix.getX()+2, overModeVoix.getY()+2);
			
			overModeData.render(container, g);
			g.setColor(Color.black);
			g.drawString("(d) Mode data", overModeData.getX()+2, overModeData.getY()+2);
			
			overModeData2.render(container, g);
			g.setColor(Color.black);
			g.drawString("(f) Mode data2", overModeData2.getX()+2, overModeData2.getY()+2);
			
			g.draw(rectError);
			textError.render(container, g);
			overOkError.render(container, g);
			g.setColor(Color.black);
			g.drawString("OK", overOkError.getX()+2, overOkError.getY()+2);
			
			overAddToGraph1.render(container, g);
			g.setColor(Color.black);
			g.drawString("Put/remove in graph1", overAddToGraph1.getX()+2, overAddToGraph1.getY()+2);
			
			overAddToGraph2.render(container, g);
			g.setColor(Color.black);
			g.drawString("Put/remove in graph2", overAddToGraph2.getX()+2, overAddToGraph2.getY()+2);
			
			overAddToGraph3.render(container, g);
			g.setColor(Color.black);
			g.drawString("Put/remove in graph3", overAddToGraph3.getX()+2, overAddToGraph3.getY()+2);
			
			overPauseData.render(container, g);
			g.setColor(Color.black);
			g.drawString("(p) Pause data recording", overPauseData.getX()+2, overPauseData.getY()+2);
			
			overPauseSimulation.render(container, g);
			g.setColor(Color.black);
			g.drawString("(o) Pause simulation", overPauseSimulation.getX()+2, overPauseSimulation.getY()+2);
			
			
			
		//}
		
		/*
		 * On desine le grand graphe selectionner
		 */
		if(bigGraphe != null){
			g.setLineWidth(2.0f);
			renderGraph.renderGraphe(bigGraphe, g, (int)zoneBigGraph.getX(), (int)zoneBigGraph.getY(), (int)zoneBigGraph.getWidth(),(int)zoneBigGraph.getHeight());
			renderGraph.mouseOverGraphe(bigGraphe, g, (int)zoneBigGraph.getX(), (int)zoneBigGraph.getY(), (int)zoneBigGraph.getWidth(),(int)zoneBigGraph.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			renderGraph.mouseOverGrapheName(bigGraphe, g, (int)zoneBigGraph.getX(), (int)zoneBigGraph.getY(), (int)zoneBigGraph.getWidth(),(int)zoneBigGraph.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
		}else{
			/*
			 * On dessines les petits graphes
			 */
			g.setLineWidth(1.0f);
			renderGraph.renderGraphe(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(), (int)zoneGraph1.getHeight());
			if(zoneGraph1.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
				renderGraph.mouseOverGraphe(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(), (int)zoneGraph1.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
				renderGraph.mouseOverGrapheName(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(),(int)zoneGraph1.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			}
			renderGraph.renderGraphe(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(), (int)zoneGraph2.getHeight());
			if(zoneGraph2.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
				renderGraph.mouseOverGraphe(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(), (int)zoneGraph2.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
				renderGraph.mouseOverGrapheName(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(),(int)zoneGraph2.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			}
			renderGraph.renderGraphe(traceGraph3, g, (int)zoneGraph3.getX(), (int)zoneGraph3.getY(), (int)zoneGraph3.getWidth(), (int)zoneGraph3.getHeight());
			if(zoneGraph3.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
				renderGraph.mouseOverGraphe(traceGraph3, g, (int)zoneGraph3.getX(), (int)zoneGraph3.getY(), (int)zoneGraph3.getWidth(), (int)zoneGraph3.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
				renderGraph.mouseOverGrapheName(traceGraph3, g, (int)zoneGraph3.getX(), (int)zoneGraph3.getY(), (int)zoneGraph3.getWidth(),(int)zoneGraph3.getHeight(), container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY());
			}
		}
		
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (isMouseInRectCamera())
			antennaAndMobilesController.keyPressed(key, c);

		/*
		graph1.addPoint(new Point((float) xTemp, (float) Math
				.random() * 20));
		graph2.addPoint(new Point((float) xTemp, (float) Math
				.random() * 200 + 15.0f));
		if(xTemp%10 == 0){
			graph2.addPoint(new Point((float) xTemp, -30));
		}
		xTemp += 15 * Math.random();
		// */
		/*
		for(InfoMobileAndGraph tmp : this.arrayInfoMobileAndGraph){
			tmp.getAndAddToGraphValue(xTemp, InfoMobileAndGraph.TypeData.power);
		}
		xTemp += 15 * Math.random();
		// */
		switch(key){
		case Input.KEY_P:
			togglePauseData();
			break;
		case Input.KEY_O:
			togglePauseSimulation();
			break;
		}
		
		if(key == Input.KEY_U){
			/*
			graph1.removePoint(0, graph1.size()/2);
			graph2.removePoint(0, graph2.size()/2);
			// */
			/* Works but don't remove Graph of mobiles that were deleted
			for(InfoMobileAndGraph tmp : this.arrayInfoMobileAndGraph){
				tmp.graph.removePoint(0, tmp.graph.size()/2);
			}
			// */
			//*
			int x = super.container.getInput().getAbsoluteMouseX();
			int y = super.container.getInput().getAbsoluteMouseY();
			Graphics g = super.container.getGraphics();
			
			if(this.zoneGraph1.contains(x, y) && bigGraphe == null){
				Vector2f vec = renderGraph.getValueXYWithLadder(traceGraph1, g, (int)zoneGraph1.getX(), (int)zoneGraph1.getY(), (int)zoneGraph1.getWidth(), (int)zoneGraph1.getHeight(), x, y);
				//removePointsOfTracegraphe(0, traceGraph1.indexOfPointXSlow(vec.x));
				traceGraph1.removePointOfAllGraphSlow(vec.x);
				//System.out.println(""+vec.x);
			}else if(zoneGraph2.contains(x, y) && bigGraphe == null){
				Vector2f vec = renderGraph.getValueXYWithLadder(traceGraph2, g, (int)zoneGraph2.getX(), (int)zoneGraph2.getY(), (int)zoneGraph2.getWidth(), (int)zoneGraph2.getHeight(), x, y);
				//removePointsOfTracegraphe(0, traceGraph1.indexOfPointXSlow(vec.x));
				traceGraph1.removePointOfAllGraphSlow(vec.x);
			}else if(zoneGraph3.contains(x, y) && bigGraphe == null){
				Vector2f vec = renderGraph.getValueXYWithLadder(traceGraph3, g, (int)zoneGraph3.getX(), (int)zoneGraph3.getY(), (int)zoneGraph3.getWidth(), (int)zoneGraph3.getHeight(), x, y);
				//removePointsOfTracegraphe(0, traceGraph1.indexOfPointXSlow(vec.x));
				traceGraph1.removePointOfAllGraphSlow(vec.x);
			}else if(bigGraphe != null){
				Vector2f vec = renderGraph.getValueXYWithLadder(bigGraphe, g, (int)zoneBigGraph.getX(), (int)zoneBigGraph.getY(), (int)zoneBigGraph.getWidth(), (int)zoneBigGraph.getHeight(), x, y);
				//removePointsOfTracegraphe(0, traceGraph1.indexOfPointXSlow(vec.x));
				traceGraph1.removePointOfAllGraphSlow(vec.x);
			}else
			// */
			if(arrayInfoMobileAndGraph.size()>=1){
				removePointsOfTracegraphe(0, arrayInfoMobileAndGraph.get(0).graph.size()/2);
			}
		}
	}

	public void removePointsOfTracegraphe(int startIndex, int endIndex){
		this.traceGraph1.removePointOfAllGraph(startIndex, endIndex);
		this.traceGraph2.removePointOfAllGraph(startIndex, endIndex);
		this.traceGraph3.removePointOfAllGraph(startIndex, endIndex);
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
		/*
		 * Options
		 */
		if(bigGraphe == null){
			if(this.rectOverConnecter.contains(x, y)){
				this.antennaAndMobilesController.toggleConnectedSelected();
			}
			if(this.overModeVoix.isMouseOver()){
				this.antennaAndMobilesController.changeModeSelected(services.voix);
			}else if(this.overModeData.isMouseOver()){
				this.antennaAndMobilesController.changeModeSelected(services.data1);
			}else if(this.overModeData2.isMouseOver()){
				this.antennaAndMobilesController.changeModeSelected(services.data2);
			}else if(this.overOkError.isMouseOver()){
				float error = 0.0f;
				try{
					error = Float.valueOf(this.textError.getText());
				}catch(Exception e){
					e.printStackTrace();
				}
				this.antennaAndMobilesController.setErrorSelected(error);
			}else if(overAddToGraph1.isMouseOver()){
				this.toggleMobileInGraph(TraceGraphNo.traceGraph1);
			}else if(overAddToGraph2.isMouseOver()){
				this.toggleMobileInGraph(TraceGraphNo.traceGraph2);
			}else if(overAddToGraph3.isMouseOver()){
				this.toggleMobileInGraph(TraceGraphNo.traceGraph3);
			}else if(overPauseData.isMouseOver()){
				togglePauseData();
			}else if(overPauseSimulation.isMouseOver()){
				togglePauseSimulation();
			}
		}
		
		/*
		 * Camera
		 */
		if (isMouseInRectCamera() && bigGraphe == null)
			antennaAndMobilesController.mouseReleased(button, x, y);
		
		/*
		 * Graphes
		 */
		if(this.zoneGraph1.contains(x, y) && bigGraphe == null){
			bigGraphe = this.traceGraph1;
		}else if(zoneGraph2.contains(x, y) && bigGraphe == null){
			bigGraphe = this.traceGraph2;
		}else if(zoneGraph3.contains(x, y) && bigGraphe == null){
			bigGraphe = this.traceGraph3;
		}else{
			bigGraphe = null;
		}
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
		if(bigGraphe != null){
			
		}else{
			
		}
		
	}

	private boolean isMouseInRectCamera() {
		return antennaAndMobilesController.cameraForAntennaAndMobiles.viewPortRect
				.contains(container.getInput().getMouseX(), container
						.getInput().getMouseY());
	}
	
	/**
	 * Creation de InfoMobileAndGraph
	 * @param mobile
	 */
	public void mobileAdded(Mobile mobile){
		InfoMobileAndGraph info = new InfoMobileAndGraph();
		info.graph = new Graph();
		info.graph.nomGraphe = ""+mobile.id;
		info.mobile = mobile;
		
		this.arrayInfoMobileAndGraph.add(info);
	}
	
	/**
	 * On supprime le mobile et le graph de InfoMobileAndGraph
	 * et des traceGraph ??
	 * @param mobile
	 */
	public void mobileDeleted(Mobile mobile){
		// Faut il enlever les graph des tracegraph
		for(int i=0;i < this.arrayInfoMobileAndGraph.size(); i++){
			if(this.arrayInfoMobileAndGraph.get(i).mobile.equals(mobile)){
				this.arrayInfoMobileAndGraph.remove(i);
				break;// no concurrent modification error (shouldn't happen, it's for(int i...) )
			}
		}
	}
	
	/**
	 * 
	 * @param traceGraphID
	 */
	public void toggleMobileInGraph(TraceGraphNo traceGraphID){
		// TODO
		switch(traceGraphID){
		case traceGraph1:
			toggleMobileInGraph(this.traceGraph1);
			break;
		case traceGraph2:
			toggleMobileInGraph(this.traceGraph2);
			break;
		case traceGraph3:
			toggleMobileInGraph(this.traceGraph3);
			break;
		}
	}
	public void toggleMobileInGraph(TraceGraph traceGraph){
		for(Moveable mobile : this.antennaAndMobilesController.arraySelected){
			if(mobile instanceof Mobile){
				Graph tmpGraph = getInfo((Mobile) mobile);
				if(tmpGraph != null){
					if(traceGraph.contains(tmpGraph)){
						//*
						if(!traceGraph.removeGraphe(tmpGraph)){
							System.err.println("erreur remove graphe"+traceGraph.name);
						}
						// */
						/*
						if(!traceGraph.removeGraphe(tmpGraph)){
							if(!traceGraph.addGraphe(tmpGraph)){
								System.err.println("erreur add/remove graphe ("+traceGraph.name+")");
							}
						}
						// */
					}else{
						if(!traceGraph.addGraphe(tmpGraph)){
							System.err.println("erreur ajout graphe "+traceGraph.name);
						}
					}
					
				}else{
					System.err.println("null 2292");
				}
			}
		}
	}
	
	private void togglePauseData(){
		pauseData = !pauseData;
		if(pauseData){
			overPauseData.setNormalColor(Color.red);
			overPauseData.setMouseOverColor(Color.red);
		}else{
			overPauseData.setNormalColor(Color.green);
			overPauseData.setMouseOverColor(Color.green);
		}
	}
	
	private void togglePauseSimulation(){
		pauseSimulation = !pauseSimulation;
		if(pauseSimulation){
			overPauseSimulation.setNormalColor(Color.red);
			overPauseSimulation.setMouseOverColor(Color.red);
		}else{
			overPauseSimulation.setNormalColor(Color.green);
			overPauseSimulation.setMouseOverColor(Color.green);
		}
	}
	
	@Override
	public int getID() {
		return PowerControlView.ID;
	}
	
	/**
	 * Recupere l'objet Graph du mobile sinon null
	 * @param mobile
	 * @return null if not found
	 */
	private Graph getInfo(Mobile mobile){
		for(InfoMobileAndGraph tmp : this.arrayInfoMobileAndGraph){
			if(tmp.mobile.equals(mobile)){
				return tmp.graph;
			}
		}
		return null;
	}
	/**
	 * Classe pour faire la correspondance entre les data (graphe) et celui qui fournit les data
	 * @author Yoann CAPLAIN
	 *
	 */
	private static class InfoMobileAndGraph{
		public static enum TypeData{
			power;
		}
		public Mobile mobile;
		public Graph graph;
		
		public void getAndAddToGraphValue(float x, TypeData typeData){
			switch(typeData){
			case power:
				graph.addPoint(new Point(x, mobile.getPuissanceEmission()));
			break;
			}
		}
	}

}

