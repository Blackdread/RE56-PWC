/**
 * 
 */
package a.states.gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * should be double, float, int
 * @author Yoann CAPLAIN
 *
 */
public class GraphYoann<T> {

	private class Point<T>{
		public T x;
		public T y;
		
		public Point(T xx, T yy){
			this.x = xx;
			this.y = yy;
		}
	}
	/**
	 * points avec def des x
	 * Can't use both at same time
	 */
	private ArrayList<Point<T>> listPoints = new ArrayList<Point<T>>();
	/**
	 * les x sont justes 0,1,2,3,4,...
	 * Can't use both at same time
	 */
	private ArrayList<T> listValues = new ArrayList<T>();
	
	public int maxValueOnY = 0;
	public int maxValueOnX = 0;
	
	public int minValueOnY = 0;
	public int minValueOnX = 0;
	
	public GraphYoann() {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
		
	}

	public void addValue(T y){
		listValues.add(y);
		maxValueOnY = Math.max((int) y, maxValueOnY);
		minValueOnY = Math.min((int) y, minValueOnY);
		maxValueOnX = this.listValues.size();
	}
	public void removeValue(T x){
		int pos = listValues.indexOf(x);
		if(pos != -1){
			removeValueAt(pos);
		}
	}
	public void removeValueAt(int pos){
		listValues.remove(pos);
		maxValueOnX--;
	}
	
	
	public void addPoint(T x, T y){
		listPoints.add(new Point<T>(x, y));
		maxValueOnY = Math.max((int) y, maxValueOnY);
		minValueOnY = Math.min((int) y, minValueOnY);
		
		maxValueOnX = Math.max((int) x, maxValueOnX);
		minValueOnX = Math.min((int) x, minValueOnX);
	}
	
	public void removePoints(T y){
		Point<T> remove = null;
		for(Point<T> a : listPoints){
			if(a != null && a.y == y){
				remove = a;
				break;
			}
		}
		if(remove != null){
			listPoints.remove(remove);
		}
	}
	
}
