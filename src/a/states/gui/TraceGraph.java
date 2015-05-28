package a.states.gui;

import java.util.ArrayList;

public class TraceGraph implements IGraphListener{

	ArrayList<Graph> graphes = new ArrayList<Graph>();
	float minXGlobal = -10;
	float maxXGlobal = 10;
	float minYGlobal = -10;
	float maxYGlobal = 10;
	
	public void addGraphe(Graph graphe) {
		this.graphes.add(graphe);
		graphe.addListener(this);
		majMaxMinGlobal();
	}

	public void majMaxMinGlobal() {
		minXGlobal = Float.MAX_VALUE;
		//minXGlobal = 929292929929.0f;
		maxXGlobal = Float.NEGATIVE_INFINITY;
		//maxXGlobal = -923939239.0f;
		minYGlobal = Float.MAX_VALUE;
		maxYGlobal = Float.NEGATIVE_INFINITY;
		
		
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
