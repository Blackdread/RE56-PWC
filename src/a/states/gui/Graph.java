package a.states.gui;

import java.util.ArrayList;

public class Graph {

	ArrayList<Point> arrayPoints = new ArrayList<Point>();

	/**
	 * Liste contenant les listeners pour les notifier
	 * l'ajout/enlever point
	 */
	protected ArrayList<IGraphListener> arrayListeners = new ArrayList<IGraphListener>();
	
	float maxGraphY;
	float minGraphY;

	float maxGraphX;
	float minGraphX;

	public Graph() {
		this.maxGraphY = 10;
		this.minGraphY = -10;
		this.maxGraphX = 10;
		this.minGraphX = -10;
	}

	public void addPoint(Point nouveauPoint) {
		arrayPoints.add(nouveauPoint);

		if (nouveauPoint.y > this.maxGraphY)
			this.maxGraphY = nouveauPoint.y;

		if (nouveauPoint.y < this.minGraphY)
			this.minGraphY = nouveauPoint.y;

		if (nouveauPoint.x > this.maxGraphX)
			this.maxGraphX = nouveauPoint.x;

		if (nouveauPoint.x < this.minGraphX)
			this.minGraphX = nouveauPoint.x;
		
		notifier();
	}
	
	private void notifier(){
		for(IGraphListener a : this.arrayListeners){
			a.graphChanged(this);
		}
	}
	
	public void addListener(IGraphListener listener){
		arrayListeners.add(listener);
	}
	public void removeListener(IGraphListener listener){
		arrayListeners.remove(listener);
	}
}