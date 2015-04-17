package a.states.gui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 16 10 2012
 */
public class ListeDeroulante extends MouseOverArea {
	
	private static int DECALAGE_Y = -4, DECALAGE_X = -1;	// decalage du a mes images
	
	private static ArrayList<Elements> elements;
	private Elements currentElementUsed;
	//private String name;
	private boolean scrolled;
	
	public ListeDeroulante(GUIContext container, Image image, Shape shape){
		super(container, image, shape);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y){
		super(container, image, x, y);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, ComponentListener listener) {
		super(container, image, x, y, listener);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, int width, int height) {
		super(container, image, x, y, width, height);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}

	public ListeDeroulante(GUIContext container, Image image, int x, int y, int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
		elements = new ArrayList<Elements>();
		scrolled=false;
	}
	
	@Override
	public boolean isMouseOver(){
		if(!scrolled){
			if(super.isMouseOver())
					scrolled=true;
		}else{
			if(elementsOver())
				scrolled=true;
			else
				scrolled=false;
		}
		//return super.isMouseOver();
		return scrolled;
	}
	
	private boolean elementsOver(){
		for(Elements v : elements)
			if(v!=null)
				if(v.isMouseOver())
					return true;
			
		return false;
	}
	
	private void update(){
		int i=0;
		for(Elements v : elements)
			if(v!=null){
				v.setLocation(super.getX() + DECALAGE_X, super.getY() + i*v.getHeight() + super.getHeight() + DECALAGE_Y);
				i++;
			}
	}
	
	@Override
	public void render(GUIContext container, Graphics g){
		super.render(container, g);
		if(currentElementUsed!=null){
			//currentElementUsed.render(container, g, super.getX(),super.getY());	// foireux car render aussi l'image
			currentElementUsed.renderString(container, g, super.getX(),super.getY());
		}
		//int i=1;
		if(scrolled)
			for(Elements v : elements)
				if(v!=null){
					v.render(container, g);
					//v.render(container, g, v.getX(), v.getY());
					//v.render(container, g, super.getX() + DELAGE_SUR_X_DES_ELEMENT, super.getY() + i*v.getHeightElement());
					//i++;
				}
	}
	

	public boolean isScrolled() {
		return scrolled;
	}

	public void setScrolled(boolean scrolled) {
		this.scrolled = scrolled;
	}

	public void applyImageOverAllElement(final Image image){
		for(Elements v : elements)
			if(v!=null)
				v.setMouseOverImage(image);
	}
	
	public void addElement(Elements element){
		elements.add(element);
		update();
	}
	
	public void chercherElementUsed(){
		for(Elements v : elements)
			if(v!=null)
				if(v.isElementUsed()){
					this.currentElementUsed = v;
					break;
				}
	}
}
