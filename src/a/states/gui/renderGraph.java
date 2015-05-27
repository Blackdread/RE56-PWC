package a.states.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class renderGraph {

	public static void TraceAxes(TraceGraph graphe, Graphics g, int longueur,
			int hauteur) {
		Color color = g.getColor();
		graphe.majMaxMinGlobal();

		// g.setColor(Color.white);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, longueur, hauteur);

		g.setColor(Color.black);
		g.drawLine(0, graphe.maxYGlobal * graphe.echelleAxeY(hauteur),
				longueur, graphe.maxYGlobal * graphe.echelleAxeY(hauteur));
		g.drawLine(graphe.maxXGlobal * graphe.echelleAxeX(longueur), 0,
				graphe.maxXGlobal * graphe.echelleAxeX(longueur), hauteur);

		g.setColor(color);
	}

	public static void renderGraphe(TraceGraph graphe, Graphics g, int x,
			int y, int width, int height) {

		// Rectangle rect = new Rectangle(x, y, width, height);

		// int originX = (int) rect.getX();
		// int originY = (int) rect.getY();

		TraceAxes(graphe, g, width, height);

		Color color = Color.red;
		
		int colour = 0;
		
		for (Graph tmpGraphe : graphe.graphes) {
			int n = 0;

			while (n < tmpGraphe.arrayPoints.size() - 1) {
				g.setColor(color);
				g.drawLine(
						(tmpGraphe.arrayPoints.get(n)).x
								* graphe.echelleAxeX(width),
						(tmpGraphe.arrayPoints.get(n)).y
								* graphe.echelleAxeY(height),
						(tmpGraphe.arrayPoints.get(n + 1)).x
								* graphe.echelleAxeX(width),
						(tmpGraphe.arrayPoints.get(n + 1)).y
								* graphe.echelleAxeY(height));
				n++;
			}

			colour++;
			
			if (colour == 1)
				color = Color.blue;
			if (colour == 2)
				color = Color.green;
			if (colour == 3)
				color = Color.yellow;
			if (colour == 4)
				color = Color.orange;
			if (colour == 5)
				color = Color.gray;
		}

	}

}
