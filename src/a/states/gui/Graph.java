package a.states.gui;

import java.util.ArrayList;

public class Graph {

	ArrayList<Point> arrayPoints = new ArrayList<Point>();

	/**
	 * Liste contenant les listeners pour les notifier l'ajout/enlever point
	 */
	protected ArrayList<IGraphListener> arrayListeners = new ArrayList<IGraphListener>();

	float maxGraphY;
	float minGraphY;

	float maxGraphX;
	float minGraphX;

	boolean effacePoint = true;
	
	public String nomGraphe;

	public Graph() {
		this.maxGraphY = 1;
		this.minGraphY = -1;
		this.maxGraphX = 1;
		this.minGraphX = -1;
	}

	public void addPoint(Point nouveauPoint) {
		arrayPoints.add(nouveauPoint);

		// A chaque ajout de point, on v�rifie s'il faut modifier les min/max
		if (checkAndSetMinAndMax(nouveauPoint)) {
			notifier();
		}
	}
	
	/**
	 * Remove points in the range and reset min/max
	 * @param startIndex
	 * @param endIndex
	 */
	public void removePoint(int startIndex, int endIndex){
		this.arrayPoints.subList(startIndex, endIndex).clear();
		this.resetAndFindMinAndMax();
	}
	
	/**
	 * 
	 * @param point
	 * @return True if removed a point
	 */
	public boolean removePoint(Point point) {
		if(this.arrayPoints.remove(point)){
			if(isPointMinOrMaxPoint(point)){
				this.resetAndFindMinAndMax();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Remove a point with x = point.x
	 * @return True if removed a point
	 * @param x
	 */
	public boolean removePoint(float x) {
		/*
		// Recherche plus longue mais simple :P
		for (int i = 0; i < this.arrayPoints.size(); i++) {
			if (this.arrayPoints.get(i).x == x) {
				this.arrayPoints.remove(i);
				break;
			}
		}
		// */
		int index = getIndexOfPointWithX(x);
		if(index != -1){
			if(isPointMinOrMaxPoint(this.arrayPoints.remove(index))){
				this.resetAndFindMinAndMax();
			}
			return true;
		}
		return false;
	}

	/**
	 * graph is supposed sorted on x
	 * @param x
	 * @return index of point with this x, -1 if not found
	 */
	public int getIndexOfPointWithX(float x) {
		return getIndexOfPointWithX(x, this.arrayPoints.size() / 2, 0, this.arrayPoints.size()-1);
	}

	/**
	 * graph is supposed sorted on x
	 * @param x
	 * @param index
	 *            first call should be the middle of the array
	 * @param min 
	 * @param max 
	 * @return index of point with this x, -1 if not found
	 */
	protected int getIndexOfPointWithX(float x, int index, int min, int max) {
		if (this.arrayPoints.size() == 0 || index >= this.arrayPoints.size() - 1){
			return -1;
		} else if (this.arrayPoints.get(index).x == x) {
			return index;
		} else if (index == 0) {
			return -1;
		} else if (this.arrayPoints.get(index).x > x) {
			return getIndexOfPointWithX(x, (index + min)/2, min, index);
		} else if (this.arrayPoints.get(index).x < x) {
			return getIndexOfPointWithX(x, (index + max)/2, index, max);
		}

		return -1;
	}

	/**
	 * Check if a point is at least the min or max on x or y
	 * @param point
	 * @return true if it is max or min on x or y
	 */
	protected boolean isPointMinOrMaxPoint(Point point){
		if(minGraphX == point.x || maxGraphX == point.x || minGraphY == point.y || maxGraphY == point.y){
			return true;
		}
		return false;
	}
	
	/**
	 * Reset min and max of X and Y then find it <br />
	 * And also notify listeners
	 */
	protected void resetAndFindMinAndMax() {
		this.maxGraphY = Float.NEGATIVE_INFINITY;
		this.minGraphY = Float.MAX_VALUE;
		this.maxGraphX = Float.NEGATIVE_INFINITY;
		//this.maxGraphX = -923939239.0f;
		this.minGraphX = Float.MAX_VALUE;
		//this.minGraphX = 929292929929.0f;

		for (Point point : arrayPoints) {
			checkAndSetMinAndMax(point);
		}

		notifier();
	}

	/**
	 * 
	 * @param point
	 * @return boolean True if min or max changed
	 */
	protected boolean checkAndSetMinAndMax(Point point) {
		boolean ret = false;
		if (point.y > this.maxGraphY) {
			this.maxGraphY = point.y;
			ret = true;
		}

		if (point.y < this.minGraphY) {
			this.minGraphY = point.y;
			ret = true;
		}

		if (point.x > this.maxGraphX) {
			this.maxGraphX = point.x;
			ret = true;
		}

		if (point.x < this.minGraphX) {
			this.minGraphX = point.x;
			ret = true;
		}

		return ret;
	}

	/**
	 * 
	 * @return Size of array of points
	 */
	public int size(){
		return this.arrayPoints.size();
	}
	
	private void notifier() {
		for (IGraphListener a : this.arrayListeners) {
			a.graphChanged(this);
		}
	}

	public void addListener(IGraphListener listener) {
		arrayListeners.add(listener);
	}

	public void removeListener(IGraphListener listener) {
		arrayListeners.remove(listener);
	}
}