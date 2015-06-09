package a.states.gui;

import java.util.ArrayList;

public class TraceGraph implements IGraphListener{

	ArrayList<Graph> graphes = new ArrayList<Graph>();
	float minXGlobal = -1;
	float maxXGlobal = 1;
	float minYGlobal = -1;
	float maxYGlobal = 1;
	
	public String name = "";
	
	/**
	 * On n'accepte pas les doublons
	 * @param graphe
	 * @return True if added
	 */
	public boolean addGraphe(Graph graphe) {
		if(!this.graphes.contains(graphe)){
			this.graphes.add(graphe);
			graphe.addListener(this);
			majMaxMinGlobal();
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param graphe
	 * @return True if removed, false if it was not in
	 */
	public boolean removeGraphe(Graph graphe) {
		if(this.graphes.remove(graphe)){
			graphe.removeListener(this);
			majMaxMinGlobal();
			return true;
		}
		return false;
	}
	
	public boolean contains(Graph graphe){
		return this.graphes.contains(graphe);
	}

	public void majMaxMinGlobal() {
		//*
		minXGlobal = Float.MAX_VALUE;
		maxXGlobal = Float.NEGATIVE_INFINITY;
		minYGlobal = Float.MAX_VALUE;
		maxYGlobal = Float.NEGATIVE_INFINITY;
		// */
		
		for (Graph graphe : this.graphes) {
			if (graphe.minGraphX < this.minXGlobal)
				this.minXGlobal = graphe.minGraphX;

			if (graphe.minGraphY < this.minYGlobal)
				this.minYGlobal = graphe.minGraphY;
			
			if (graphe.maxGraphX > this.maxXGlobal)
			{
				this.maxXGlobal = graphe.maxGraphX;
			}

			if (graphe.maxGraphY > this.maxYGlobal)
				this.maxYGlobal = graphe.maxGraphY;
		}

	}

	public float echelleAxeY(float height) {
		
		// Distance de maxY � minY suivant la hauteur de la fen�tre
		
		return height / (Math.abs(this.maxYGlobal - this.minYGlobal));
	}

	public float echelleAxeX(float width) {
		
		// Distance de maxX � minX suivant la largeur de la fen�tre
		return width / (Math.abs(this.maxXGlobal - this.minXGlobal));
	}
	
	/**
	 * C'est bug, byzarre
	 * @param xValue
	 * @return
	 * @deprecated Il faut revoir pourquoi ca bug
	 */
	public int indexOfPointXSlow(float xValue){
		for(Graph graph : this.graphes){
			int index = graph.getIndexOfPointWithXSlow(xValue);
			//removePointOfAllGraph(0, index);
			if(index != -1)
				return index;
		}
		return 0;
	}
	
	public int indexOfPointX(float xValue){
		for(Graph graph : this.graphes){
			int index = graph.getIndexOfPointWithX(xValue);
			if(index != -1)
				return index;
		}
		return 0;
	}
	
	/**
	 * enleve les points pour x <= a xValue
	 * @param xValue
	 */
	public void removePointOfAllGraphSlow(float xValue){
		for(Graph graph : this.graphes){
			int index = graph.getIndexOfPointWithXSlow(xValue);
			if(index != -1)
				removePointOfAllGraph(0, index);
		}
	}
	
	public void removePointOfAllGraph(int startIndex, int endIndex){
		for(Graph graph : this.graphes){
			graph.removePoint(startIndex, endIndex);
		}
	}
	
	public void clear(){
		for(Graph graph : this.graphes){
			graph.clear();
		}
	}
	
	public void removePoints(float width){
		
		for (Graph graphe : this.graphes) {
			
			if(graphe.effacePoint == false)
				return;
			
			int widthInt = (int) width;
			
			int etalement = (graphe.arrayPoints.size()*100) / widthInt;
			
			if(etalement > 17)
			{
				// on enl�ve 4 points
				
				for(int n = 0; n < 4; n ++)
				graphe.arrayPoints.remove(n);
			}
			
		}
		
		return;
		
	}

	/* (non-Javadoc)
	 * @see a.states.gui.IGraphListener#graphChanged()
	 */
	@Override
	public void graphChanged(Graph changed) {
		// TODO on peut ameliorer pour comparer seulement avec celui qui a change
		majMaxMinGlobal();
	}

}
