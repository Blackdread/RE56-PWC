package a.states.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class renderGraph {
	
	
	public static void TraceAxes (TraceGraph graphe, Graphics g, int longueur, int hauteur) {
		Color color = g.getColor();
		graphe.majMaxMinGlobal();
		
		//g.setColor(Color.white);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, longueur, hauteur);
		
		g.setColor(Color.black);
		g.drawLine(0, graphe.minYGlobal/graphe.echelleAxeY(hauteur), longueur, graphe.minYGlobal/graphe.echelleAxeY(hauteur));
		g.drawLine(graphe.minXGlobal/graphe.echelleAxeX(longueur), 0, graphe.minXGlobal/graphe.echelleAxeX(longueur) , hauteur);
		
		
		g.setColor(color);
	}
	
	public static void renderGraphe(TraceGraph graphe, Graphics g, int x, int y, int width, int height) {
		
	// Rectangle rect = new Rectangle(x, y, width, height);
	
	// int originX = (int) rect.getX();
	// int originY = (int) rect.getY();
	
	TraceAxes (graphe, g, width, height);
	
	Color color = Color.red;
	
	for (Graph tmpGraphe : graphe.graphes){
		int n = 0;
		
		while(n < tmpGraphe.arrayPoints.size() - 1){
			g.drawLine((tmpGraphe.arrayPoints.get(n)).x*graphe.echelleAxeX(1), (tmpGraphe.arrayPoints.get(n)).y*graphe.echelleAxeY(1), (tmpGraphe.arrayPoints.get(n+1)).x*graphe.echelleAxeX(1), (tmpGraphe.arrayPoints.get(n+1)).y*graphe.echelleAxeY(1));
			g.setColor(color);
			n++;
		}	
		
		if(n==1)
			color = Color.blue;
		if(n==2)
			color = Color.green;
		if(n==3)
			color = Color.yellow;
		if(n==4)
			color = Color.orange;
		if(n==5)
			color = Color.gray;
	}
		
}


//g.translate(cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.yOffSet);
			// g.scale(cameraForAntennaAndMobiles.scaleX, cameraForAntennaAndMobiles.scaleY);
			
			//for(Mobile a : arrayMobile)
			//		a.render(graphe);
			
			/*public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException{
				g.draw(rectangleClipAntennaAndMobile);
				g.setClip(rectangleClipAntennaAndMobile);
				
				g.translate(cameraForAntennaAndMobiles.xOffSet, cameraForAntennaAndMobiles.yOffSet);
				g.scale(cameraForAntennaAndMobiles.scaleX, cameraForAntennaAndMobiles.scaleY);
				
				zoneAntennaPower.render(g);
				for(Mobile a : arrayMobiles)
					a.render(g);
				
				g.scale(1/cameraForAntennaAndMobiles.scaleX, 1/cameraForAntennaAndMobiles.scaleY);
				g.translate(-cameraForAntennaAndMobiles.xOffSet, -cameraForAntennaAndMobiles.yOffSet);
				
				g.clearClip();
			}
			*/


}
