package a.states.gui;

import java.util.ArrayList;

public class TraceGraph implements IGraphListener{

	ArrayList<Graph> graphes = new ArrayList<Graph>();
	float minXGlobal = 0;
	float maxXGlobal = 0;
	float minYGlobal = 0;
	float maxYGlobal = 0;
	
	public void addGraphe(Graph graphe) {
		this.graphes.add(graphe);
		graphe.addListener(this);
		majMaxMinGlobal();
	}

	public void majMaxMinGlobal() {
		for (Graph graphe : this.graphes) {
			if (graphe.minGraphX < this.minXGlobal)
				this.minXGlobal = graphe.minGraphX;

			if (graphe.minGraphY < this.minYGlobal)
				this.minYGlobal = graphe.minGraphY;

			if (graphe.maxGraphX > this.maxXGlobal)
				this.maxXGlobal = graphe.maxGraphX;

			if (graphe.maxGraphY > this.maxYGlobal)
				this.maxYGlobal = graphe.maxGraphY;
		}

	}

	public float echelleAxeY(float hauteur) {
		return (this.maxYGlobal - this.minYGlobal) / hauteur;
	}

	public float echelleAxeX(float longueur) {
		return (this.maxXGlobal - this.minXGlobal) / longueur;
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
