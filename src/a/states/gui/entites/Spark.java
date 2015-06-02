/**
 * 
 */
package a.states.gui.entites;

import org.newdawn.slick.Color;

import a.utils.Timer;
import a.utils.Vector2f;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class Spark {

	/**
	 * direction and speed
	 */
	Vector2f directionAndSpeed;
	
	/**
	 * if direction different than -1, then direction may vary with this margin - or +
	 */
	protected int directionMargin = 0;
	
	protected Timer timeBeforeDie = new Timer(150);
	
	protected int numberOfSparkToCreate = 5;
	
	protected Color color;
	
	protected float x;
	protected float y;
	
	/**
	 * 
	 */
	public Spark(float x, float y) {
		this.x = x;
		this.y = y;
		directionAndSpeed = new Vector2f((int)(Math.random()*360.0f));
		color = Color.red;
		
	}
	/**
	 * Create another spark but keep same direction
	 * @param spark
	 * @deprecated BUG !!!
	 */
	public Spark(Spark spark){
		this.x = spark.x;
		this.y = spark.y;
		this.directionAndSpeed = new Vector2f(spark.directionAndSpeed);
		this.numberOfSparkToCreate = spark.numberOfSparkToCreate;
		this.color = spark.color;
		this.timeBeforeDie = new Timer(500);
	}

	public void update(int delta){
		timeBeforeDie.update(delta);
		if(!isDead()){
			this.x = (int) (x + (directionAndSpeed.x * delta )%7);
			this.y = (int) (y + (directionAndSpeed.y * delta )%7);
		}
	}
	
	public boolean isDead(){
		return timeBeforeDie.isTimeComplete();
	}
	
	/**
	 * @return the directionMargin
	 */
	public int getDirectionMargin() {
		return directionMargin;
	}

	/**
	 * @param directionMargin the directionMargin to set
	 */
	public void setDirectionMargin(int directionMargin) {
		this.directionMargin = Math.abs(directionMargin);
	}

	/**
	 * @return the numberOfSparkToCreate
	 */
	public int getNumberOfSparkToCreate() {
		return numberOfSparkToCreate;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param numberOfSparkToCreate the numberOfSparkToCreate to set
	 */
	public void setNumberOfSparkToCreate(int numberOfSparkToCreate) {
		this.numberOfSparkToCreate = numberOfSparkToCreate;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	
	
}
