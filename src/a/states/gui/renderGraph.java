package a.states.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class renderGraph {
	

	public static void TraceAxes(TraceGraph graphe, Graphics g, int width,
			int height) {
		Color color = g.getColor();
		graphe.majMaxMinGlobal();

		// g.setColor(Color.white);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.black);

		// Axe x dont le y d�pend de la position du 0 du graphe
		// Distance de maxY � 0 pour savoir ou va passer l'axe X
		
		g.drawLine(0, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height), 
				width, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height));
		
		/*System.out.println("_:"+height+":_");
		System.out.println("_:"+width+":_");
		
		System.out.println("_:"+graphe.maxXGlobal+":_");
		System.out.println("_:"+graphe.minXGlobal+":_");
		System.out.println("_:"+graphe.maxYGlobal+":_");
		System.out.println("_:"+graphe.minYGlobal+":_");
		
		
		System.out.println("_:"+graphe.echelleAxeY(height)+":_");
		System.out.println("_:"+graphe.echelleAxeX(width)+":_"); */

		// Axe y dont le x d�pend de la position du 0 du graphe
		// Distance de minX � O pour savoir ou va passer l'axe Y
		
		g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), 0,
				Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), height);

		g.setColor(color);
	}

	public static void renderGraphe(TraceGraph graphe, Graphics g, int xOffSet,
			int yOffSet, int width, int height) {

		
		Rectangle rectGraphView = new Rectangle(xOffSet, yOffSet, width, height);
		
		g.translate(200, 200);
		
		TraceAxes(graphe, g, width, height);

		Color color = Color.red;
		
		int choseColor = 0;

		 for (Graph tmpGraphe : graphe.graphes) {
			int n = 1;

			while (n < tmpGraphe.arrayPoints.size() - 1) {
				
				// Distance de minX � la valeur en X souhait�e pour conna�tre le X dans la fen�tre
				// Distance de max Y � la valeur Y souhait�e pour conna�tre le Y dans la fen�tre
				
				g.setColor(color);
				
				g.drawLine(
						(Math.abs((tmpGraphe.arrayPoints.get(n)).x - graphe.minXGlobal))
								* graphe.echelleAxeX(width),
						(Math.abs((tmpGraphe.arrayPoints.get(n)).y - graphe.maxYGlobal))
								* graphe.echelleAxeY(height),
						(Math.abs((tmpGraphe.arrayPoints.get(n + 1)).x - graphe.minXGlobal))
								* graphe.echelleAxeX(width),
						(Math.abs((tmpGraphe.arrayPoints.get(n + 1)).y -graphe.maxYGlobal))
								* graphe.echelleAxeY(height));
				g.setColor(color);
				n++;
			}
			
		
			choseColor ++;

			if (choseColor == 1)
				color = Color.blue;
			if (choseColor == 2)
				color = Color.green;
			if (choseColor == 3)
				color = Color.yellow;
			if (choseColor == 4)
				color = Color.orange;
			if (choseColor == 5)
				color = Color.gray;
			if (choseColor == 6)
				color = Color.white;
			if (choseColor == 7)
				color = Color.pink;
		}
		
		// Ecrire MIN MAX sur axe
		
		 g.setColor(Color.black);
		 
		g.drawString(""+ graphe.minXGlobal, -40, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height));
		g.drawString(""+ graphe.maxXGlobal, width, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height));
		g.drawString(""+graphe.minYGlobal, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), height);
		g.drawString(""+graphe.maxYGlobal, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), -15);
		
		// Ecrire valeur des milieux des axes
		
		float positionXValeurMoyGauche = Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width)/2;
		float valeurMoyGauche = graphe.minXGlobal/2;
		float positionXValeurMoyDroite = Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width) + (Math.abs(graphe.maxXGlobal) * graphe.echelleAxeX(width))/2;
		float valeurMoyDroite = graphe.maxXGlobal/2;
		float positionYValeurMoyHaut = (Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height))/2;
		float valeurMoyHaut = graphe.maxYGlobal/2;;
		float positionYValeurMoyBas = Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height) + (Math.abs(graphe.minYGlobal) * graphe.echelleAxeY(height))/2;
		float valeurMoyBas = graphe.minYGlobal/2;
		
		g.drawString(""+valeurMoyGauche, positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height));
		g.drawString(""+valeurMoyDroite, positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)*graphe.echelleAxeY(height));
		g.drawString(""+valeurMoyHaut, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), positionYValeurMoyHaut);
		g.drawString(""+valeurMoyBas, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width), positionYValeurMoyBas);
		
		g.translate(-200, -200);
	}
	/**
	 * Affiche la valeur x et y où se trouve la souris
	 * @param graphe
	 * @param g
	 * @param xOffSet
	 * @param yOffSet
	 * @param width
	 * @param height
	 * @param mouseX
	 * @param mouseY
	 */
	public static void mouseOverGraphe(TraceGraph graphe, Graphics g, int xOffSet,
			int yOffSet, int width, int height, int mouseX, int mouseY) {
		
	}

}
