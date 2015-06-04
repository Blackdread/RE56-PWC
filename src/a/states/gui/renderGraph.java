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
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.black);

		// Axe x dont le y depend de la position du 0 du graphe
		// Distance de maxY a 0 pour savoir ou va passer l'axe X

		g.drawLine(0, Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height),
				width, Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));

		/*
		 * System.out.println("_:"+height+":_");
		 * System.out.println("_:"+width+":_");
		 * 
		 * System.out.println("_:"+graphe.maxXGlobal+":_");
		 * System.out.println("_:"+graphe.minXGlobal+":_");
		 * System.out.println("_:"+graphe.maxYGlobal+":_");
		 * System.out.println("_:"+graphe.minYGlobal+":_");
		 * 
		 * 
		 * System.out.println("_:"+graphe.echelleAxeY(height)+":_");
		 * System.out.println("_:"+graphe.echelleAxeX(width)+":_");
		 */

		// Axe y dont le x depend de la position du 0 du graphe
		// Distance de minX a O pour savoir ou va passer l'axe Y

		// if(Math.abs(graphe.minXGlobal)*graphe.echelleAxeX(width) < width)
		if (graphe.minXGlobal < 0) {
			g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width),
					0, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width),
					height);
		}

		// Axe y dont le x depend de la position de la valeur moyenne des x
		// Distance de minX a la moiti� de la distance de minX � maxX
		else {
			// System.out.println("_:"+graphe.maxXGlobal+":_");
			// System.out.println("_:"+graphe.minXGlobal+":_");
			// System.out.println("_:"+graphe.echelleAxeX(width)+":_");
			g.drawLine(
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width),
					0,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width), height);
		}

		g.setColor(color);
	}

	public static void renderGraphe(TraceGraph graphe, Graphics g, int xOffSet,
			int yOffSet, int width, int height) {

		Rectangle rectGraphView = new Rectangle(xOffSet, yOffSet, width, height);

		g.drawString(""+graphe.name, xOffSet+width/2-g.getFont().getWidth(""+graphe.name)/2, yOffSet-18);
		
		g.setClip(rectGraphView);
		g.translate(xOffSet, yOffSet);

		TraceAxes(graphe, g, width, height);

		Color color = Color.red;

		int choseColor = 0;

		for (Graph tmpGraphe : graphe.graphes) {
			int n = 1;

			while (n < tmpGraphe.arrayPoints.size() - 1) {

				// Distance de minX a la valeur en X souhaitee pour connaitre le
				// X dans la fenetre
				// Distance de max Y a la valeur Y souhaitee pour connaitre le Y
				// dans la fenetre

				g.setColor(color);

				g.drawLine(
						(Math.abs((tmpGraphe.arrayPoints.get(n)).x
								- graphe.minXGlobal))
								* graphe.echelleAxeX(width),
						(Math.abs((tmpGraphe.arrayPoints.get(n)).y
								- graphe.maxYGlobal))
								* graphe.echelleAxeY(height),
						(Math.abs((tmpGraphe.arrayPoints.get(n + 1)).x
								- graphe.minXGlobal))
								* graphe.echelleAxeX(width),
						(Math.abs((tmpGraphe.arrayPoints.get(n + 1)).y
								- graphe.maxYGlobal))
								* graphe.echelleAxeY(height));
				g.setColor(color);
				n++;
			}

			choseColor++;

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

		int drawIntMinXGlobal = (int) graphe.minXGlobal;
		int drawIntMaxXGlobal = (int) graphe.maxXGlobal;
		int drawIntMinYGlobal = (int) graphe.minYGlobal;
		int drawIntMaxYGlobal = (int) graphe.maxYGlobal;

		// if (Math.abs(graphe.minXGlobal)*graphe.echelleAxeX(width) < width)
		if (graphe.minXGlobal < 0) {
			g.drawString("" + drawIntMinXGlobal, -32,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 10);
			g.drawString("" + drawIntMaxXGlobal, width,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 10);
			g.drawString("" + drawIntMinYGlobal, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) - 15, height);
			g.drawString("" + drawIntMaxYGlobal, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) - 15, -15);
		}

		else {
			g.drawString("" + drawIntMinXGlobal, -32,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 10);
			g.drawString("" + drawIntMaxXGlobal, width,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 10);
			g.drawString("" + drawIntMinYGlobal, (width / 2) - 15, height);
			g.drawString("" + drawIntMaxYGlobal, (width / 2) - 15, -15);
		}

		// Ecrire la valeur des milieux des axes

		float positionXValeurMoyGauche = 0;
		if (graphe.minXGlobal < 0) {
			positionXValeurMoyGauche = Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) / 2;
		} else {
			positionXValeurMoyGauche = ((graphe.maxXGlobal - graphe.minXGlobal) / 4)
					* graphe.echelleAxeX(width);
		}
		float valeurMoyGauche = 0;
		if (graphe.minXGlobal < 0) {
			valeurMoyGauche = graphe.minXGlobal / 2;
		} else {
			valeurMoyGauche = ((graphe.maxXGlobal - graphe.minXGlobal) / 4)
					+ graphe.minXGlobal;
		}

		float positionXValeurMoyDroite = 0;
		if (graphe.minXGlobal < 0) {
			positionXValeurMoyDroite = Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width)
					+ (Math.abs(graphe.maxXGlobal) * graphe.echelleAxeX(width))
					/ 2;
		} else {
			positionXValeurMoyDroite = (3 * ((graphe.maxXGlobal - graphe.minXGlobal)) / 4)
					* graphe.echelleAxeX(width);
		}

		float valeurMoyDroite = 0;
		if (graphe.minXGlobal < 0) {
			valeurMoyDroite = graphe.maxXGlobal / 2;
		} else {
			valeurMoyDroite = (3 * ((graphe.maxXGlobal - graphe.minXGlobal)) / 4)
					+ graphe.minXGlobal;
		}

		float positionYValeurMoyHaut = (Math.abs(graphe.maxYGlobal) * graphe
				.echelleAxeY(height)) / 2;
		float valeurMoyHaut = graphe.maxYGlobal / 2;
		;
		float positionYValeurMoyBas = Math.abs(graphe.maxYGlobal)
				* graphe.echelleAxeY(height)
				+ (Math.abs(graphe.minYGlobal) * graphe.echelleAxeY(height))
				/ 2;
		float valeurMoyBas = graphe.minYGlobal / 2;

		int drawValeurMoyGauche = (int) valeurMoyGauche;
		int drawValeurMoyDroite = (int) valeurMoyDroite;
		int drawValeurMoyHaut = (int) valeurMoyHaut;
		int drawValeurMoyBas = (int) valeurMoyBas;

		// if (Math.abs(graphe.minXGlobal)*graphe.echelleAxeX(width) < width)
		if (graphe.minXGlobal < 0) {
			g.drawString("" + drawValeurMoyGauche, positionXValeurMoyGauche,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawValeurMoyDroite, positionXValeurMoyDroite,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawValeurMoyHaut, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width), positionYValeurMoyHaut);
			g.drawString("" + drawValeurMoyBas, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width), positionYValeurMoyBas);

			g.drawLine(positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height) - 5, positionXValeurMoyGauche,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							+ 5);
			g.drawLine(positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height) - 5, positionXValeurMoyDroite,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							+ 5);
			g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width)
					- 5, positionYValeurMoyHaut, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) + 5, positionYValeurMoyHaut);
			g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width)
					- 5, positionYValeurMoyBas, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) + 5, positionYValeurMoyBas);
		}

		else {

			if (drawValeurMoyGauche < 1500) {
				g.drawString("" + drawValeurMoyGauche,
						positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height));

				g.drawString("" + drawValeurMoyDroite,
						positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height));

				g.drawLine(
						positionXValeurMoyGauche,
						Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height) - 5,
						positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height) + 5);
				g.drawLine(
						positionXValeurMoyDroite,
						Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height) - 5,
						positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height) + 5);

			}

			g.drawString(
					"" + drawValeurMoyHaut,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width), positionYValeurMoyHaut);
			g.drawString(
					"" + drawValeurMoyBas,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width), positionYValeurMoyBas);

			g.drawLine(
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) - 5,
					positionYValeurMoyHaut,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) + 5,
					positionYValeurMoyHaut);
			g.drawLine(
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) - 5,
					positionYValeurMoyBas,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) + 5,
					positionYValeurMoyBas);
		}

		// Ecrire la valeur du centre

		// if(Math.abs(graphe.minXGlobal)*graphe.echelleAxeX(width) < width)
		if (graphe.minXGlobal < 0) {
			int valPositionCentreX = 0;
			int valPositionCentreY = 0;

			g.drawString("" + valPositionCentreX, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width), Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height));
			g.drawString("" + valPositionCentreY, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) - 11,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 17);
		}

		else {
			float valPositionCentreX = ((graphe.maxXGlobal - graphe.minXGlobal) / 2)
					+ graphe.minXGlobal;
			float valPositionCentreY = 0;

			int drawValPositionCentreX = (int) valPositionCentreX;
			int drawValPositionCentreY = (int) valPositionCentreY;

			g.drawString(
					"" + drawValPositionCentreX,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width),
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString(
					"" + drawValPositionCentreY,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) - 11,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 17);
		}

		g.translate(-xOffSet, -yOffSet);
		g.clearClip();
	}

	/**
	 * Affiche la valeur x et y où se trouve la souris
	 * 
	 * @param graphe
	 * @param g
	 * @param xOffSet
	 * @param yOffSet
	 * @param width
	 * @param height
	 * @param mouseX
	 * @param mouseY
	 */
	public static void mouseOverGraphe(TraceGraph graphe, Graphics g,
			int xOffSet, int yOffSet, int width, int height, int mouseX,
			int mouseY) {
		
		
		g.drawString("x=2", xOffSet, yOffSet);
		g.drawString("y=2", xOffSet, yOffSet+18);
	}

}
