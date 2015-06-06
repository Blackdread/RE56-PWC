package a.states.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

//import a.utils.Vector2f;

public class renderGraph {

	public static void TraceAxes(TraceGraph graphe, Graphics g, int width,
			int height) {
		Color color = g.getColor();
		graphe.majMaxMinGlobal();

		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.black);

		// Axe x dont le y depend de la position du 0 du graphe
		// Distance de maxY a 0 pour savoir ou va passer l'axe X
		// Si on a pas le 0 sur le graphe, on place l'axe X en bas de la fenetre
		// d�cal� de 10

		// Axe y dont le x depend de la position du 0 du graphe
		// Distance de minX a O pour savoir ou va passer l'axe Y

		boolean dejaAxe = false;

		if (graphe.minXGlobal < 0 && graphe.minYGlobal < 0) {

			dejaAxe = true;
			g.drawLine(0,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height),
					width,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width),
					0, Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width),
					height);
		}

		if (graphe.minXGlobal > 0 && graphe.minYGlobal < 0) {

			dejaAxe = true;
			g.drawLine(0,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height),
					width,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawLine(width / 2, 0, width / 2, height);
		}

		if (dejaAxe == false) {

			dejaAxe = true;
			g.drawLine(
					0,
					height	- (height /10),
					width,
						height	- (height/10));
			g.drawLine(width / 2, 0, width / 2, height);
		}

	}

	public static void TracePoints(TraceGraph graphe, Graphics g, int width,
			int height) {
		Color color = Color.red;

		int choseColor = 0;

		for (Graph tmpGraphe : graphe.graphes) {
			int n = 1;

			while (n < tmpGraphe.arrayPoints.size() - 1) {

				g.setColor(color);
				
				if(Math.abs(tmpGraphe.arrayPoints.get(n).y) == Math.abs(tmpGraphe.arrayPoints.get(n + 1).y))
				{
					float decalage = (float) 0.0001;
					
					g.drawLine(
							(Math.abs((tmpGraphe.arrayPoints.get(n)).x
									- graphe.minXGlobal))
									* graphe.echelleAxeX(width),
							((Math.abs((tmpGraphe.arrayPoints.get(n)).y
									- graphe.maxYGlobal)) + decalage)
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
				
				else {
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
	}

	public static void TraceMinMax(TraceGraph graphe, Graphics g, int width,
			int height) {

		g.setColor(Color.black);

		int drawIntMinXGlobal = (int) graphe.minXGlobal;
		int drawIntMaxXGlobal = (int) graphe.maxXGlobal;
		int drawIntMinYGlobal = (int) graphe.minYGlobal;
		int drawIntMaxYGlobal = (int) graphe.maxYGlobal;

		boolean dejaMinMax = false;

		if (graphe.minXGlobal < 0 && graphe.minYGlobal < 0) {
			dejaMinMax = true;

			if (Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height) < 0.8 * height)
				g.drawString(
						"" + drawIntMinYGlobal,
						Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width),
						height - 20);

			g.drawString("" + drawIntMinXGlobal, 0, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height));
			g.drawString("" + drawIntMaxXGlobal,
					width - g.getFont().getWidth("" + drawIntMaxXGlobal),
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawIntMaxYGlobal, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width), 0);
		}

		if (graphe.minXGlobal > 0 && graphe.minYGlobal < 0) {
			dejaMinMax = true;
			g.drawString("" + drawIntMinXGlobal, 0, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height));
			g.drawString("" + drawIntMaxXGlobal,
					width - g.getFont().getWidth("" + drawIntMaxXGlobal),
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawIntMaxYGlobal, width / 2, 0);
		}

		if (dejaMinMax == false) {
			dejaMinMax = true;
			g.drawString("" + drawIntMinXGlobal, 0,
					height	- (height /10));
			g.drawString("" + drawIntMaxXGlobal,
					width - g.getFont().getWidth("" + drawIntMaxXGlobal),
					height	- (height /10));
			// g.drawString("" + drawIntMinYGlobal, (width / 2), height);
			g.drawString("" + drawIntMaxYGlobal, width / 2, 0);
		}
	}

	public static void TraceMilieux(TraceGraph graphe, Graphics g, int width,
			int height) {

		boolean dejaMilieu = false;

		float positionXValeurMoyGauche = 0, valeurMoyGauche = 0;
		float positionXValeurMoyDroite = 0, valeurMoyDroite = 0;
		float positionYValeurMoyHaut = 0, valeurMoyHaut = 0;
		float positionYValeurMoyBas = 0, valeurMoyBas = 0;

		if (graphe.minXGlobal < 0 && graphe.minYGlobal < 0) {
			dejaMilieu = true;
			positionXValeurMoyGauche = Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) / 2;
			valeurMoyGauche = graphe.minXGlobal / 2;

			positionXValeurMoyDroite = Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width)
					+ (Math.abs(graphe.maxXGlobal) * graphe.echelleAxeX(width))
					/ 2;
			valeurMoyDroite = graphe.maxXGlobal / 2;

			positionYValeurMoyHaut = (Math.abs(graphe.maxYGlobal) * graphe
					.echelleAxeY(height)) / 2;
			valeurMoyHaut = graphe.maxYGlobal / 2;

			positionYValeurMoyBas = Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height)
					+ (Math.abs(graphe.minYGlobal) * graphe.echelleAxeY(height))
					/ 2;
			valeurMoyBas = graphe.minYGlobal / 2;

		}

		if (graphe.minXGlobal > 0 && graphe.minYGlobal < 0) {
			dejaMilieu = true;

			positionXValeurMoyGauche = width / 4;
			valeurMoyGauche = (graphe.maxXGlobal - graphe.minXGlobal) / 4
					+ graphe.minXGlobal;

			positionXValeurMoyDroite = (3 * width) / 4;
			valeurMoyDroite = (3 * (graphe.maxXGlobal - graphe.minXGlobal)) / 4
					+ graphe.minXGlobal;

			positionYValeurMoyHaut = (Math.abs(graphe.maxYGlobal) * graphe
					.echelleAxeY(height)) / 2;
			valeurMoyHaut = graphe.maxYGlobal / 2;

			positionYValeurMoyBas = Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height)
					+ (Math.abs(graphe.minYGlobal) * graphe.echelleAxeY(height))
					/ 2;
			valeurMoyBas = graphe.minYGlobal / 2;
		}

		if (dejaMilieu == false) {
			dejaMilieu = true;

			System.out.println("Cas > et > again");

			positionXValeurMoyGauche = width / 4;
			valeurMoyGauche = (graphe.maxXGlobal - graphe.minXGlobal) / 4
					+ graphe.minXGlobal;

			positionXValeurMoyDroite = (3 * width) / 4;
			valeurMoyDroite = (3 * (graphe.maxXGlobal - graphe.minXGlobal)) / 4
					+ graphe.minXGlobal;

			positionYValeurMoyHaut = (height	- (height/10)) / 2;
			valeurMoyHaut = (graphe.maxYGlobal
					+ graphe.maxYGlobal - (height	- (height /10))/ graphe.echelleAxeY(height)) / 2;

			positionYValeurMoyBas = (height
					- ((graphe.maxYGlobal - graphe.minYGlobal) * graphe
							.echelleAxeY(height)) - 20 * graphe
					.echelleAxeY(height))
					/ 2
					+ ((graphe.maxYGlobal - graphe.minYGlobal) * graphe
							.echelleAxeY(height))
					- 20
					* graphe.echelleAxeY(height);
			valeurMoyBas = graphe.minYGlobal / 2;
		}

		int drawValeurMoyGauche = (int) valeurMoyGauche;
		int drawValeurMoyDroite = (int) valeurMoyDroite;
		int drawValeurMoyHaut = (int) valeurMoyHaut;
		int drawValeurMoyBas = (int) valeurMoyBas;

		boolean dejaTraceMoy = false;

		if (graphe.minXGlobal < 0 && graphe.minYGlobal < 0) {
			dejaTraceMoy = true;
			if (positionXValeurMoyGauche > 0.1 * width) {
				g.drawString("" + drawValeurMoyGauche,
						positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height));
				if (positionXValeurMoyGauche > 0.1 * width)
					g.drawLine(
							positionXValeurMoyGauche,
							Math.abs(graphe.maxYGlobal)
									* graphe.echelleAxeY(height) - 5,
							positionXValeurMoyGauche,
							Math.abs(graphe.maxYGlobal)
									* graphe.echelleAxeY(height) + 5);
			}

			if (positionYValeurMoyBas < 0.8 * height) {
				g.drawString("" + drawValeurMoyBas, Math.abs(graphe.minXGlobal)
						* graphe.echelleAxeX(width), positionYValeurMoyBas);

				if (positionYValeurMoyBas < 0.9 * height)
					g.drawLine(
							Math.abs(graphe.minXGlobal)
									* graphe.echelleAxeX(width) - 5,
							positionYValeurMoyBas, Math.abs(graphe.minXGlobal)
									* graphe.echelleAxeX(width) + 5,
							positionYValeurMoyBas);
			}

			g.drawString("" + drawValeurMoyDroite, positionXValeurMoyDroite,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawValeurMoyHaut, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width), positionYValeurMoyHaut);

			g.drawLine(positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height) - 5, positionXValeurMoyDroite,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							+ 5);
			g.drawLine(Math.abs(graphe.minXGlobal) * graphe.echelleAxeX(width)
					- 5, positionYValeurMoyHaut, Math.abs(graphe.minXGlobal)
					* graphe.echelleAxeX(width) + 5, positionYValeurMoyHaut);
		}

		if (graphe.minXGlobal > 0 && graphe.minYGlobal < 0) {
			dejaTraceMoy = true;

			g.drawString("" + drawValeurMoyGauche, positionXValeurMoyGauche,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawLine(positionXValeurMoyGauche, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height) - 5, positionXValeurMoyGauche,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							+ 5);

			if ((positionYValeurMoyBas < 0.8 * height))
				g.drawString("" + drawValeurMoyBas, width / 2,
						positionYValeurMoyBas);

			// if ((positionYValeurMoyHaut < 0.2 * height))
			g.drawString("" + drawValeurMoyHaut, width / 2,
					positionYValeurMoyHaut);

			g.drawLine(width / 2 - 5, positionYValeurMoyHaut, width / 2 + 5,
					positionYValeurMoyHaut);

			if (drawValeurMoyDroite < 1000)
				g.drawString("" + drawValeurMoyDroite,
						positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
								* graphe.echelleAxeY(height));

			g.drawLine(positionXValeurMoyDroite, Math.abs(graphe.maxYGlobal)
					* graphe.echelleAxeY(height) - 5, positionXValeurMoyDroite,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							+ 5);

			// g.drawLine(width/2
			// - 5, positionYValeurMoyBas, width/2 + 5, positionYValeurMoyBas);
		}

		if (dejaTraceMoy == false) {
			dejaTraceMoy = true;

			g.drawString("" + drawValeurMoyHaut, width / 2,
					positionYValeurMoyHaut);
			
			g.drawLine(
					width / 2 - 5,
					positionYValeurMoyHaut,
					width / 2 + 5,
					positionYValeurMoyHaut);
			
			g.drawString(
					"" + drawValeurMoyGauche,
					positionXValeurMoyGauche,
					height	- (height /10));
			g.drawLine(
					positionXValeurMoyGauche,
					height	- (height /10) - 5,
					positionXValeurMoyGauche,
					height	- (height /10) + 5);

			if (drawValeurMoyDroite < 1000)
			g.drawString(
					"" + drawValeurMoyDroite,
					positionXValeurMoyDroite,
					height	- (height /10));

			g.drawLine(
					positionXValeurMoyDroite,
					height	- (height /10) - 5,
					positionXValeurMoyDroite,
					height	- (height /10) + 5);
		}
	}

	public static void TraceCentre(TraceGraph graphe, Graphics g, int width,
			int height) {
		boolean dejaCentre = false;

		if (graphe.minXGlobal < 0 && graphe.minYGlobal < 0) {

			dejaCentre = true;
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

		if (graphe.minXGlobal > 0 && graphe.minYGlobal < 0) {

			dejaCentre = true;

			float valPositionCentreX = (graphe.maxXGlobal - graphe.minXGlobal)
					/ 2 + graphe.minXGlobal;
			float valPositionCentreY = 0;

			int drawValPositionCentreX = (int) valPositionCentreX;
			int drawValPositionCentreY = (int) valPositionCentreY;

			g.drawString("" + drawValPositionCentreX, width / 2,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height));
			g.drawString("" + drawValPositionCentreY, width / 2 - 11,
					Math.abs(graphe.maxYGlobal) * graphe.echelleAxeY(height)
							- 17);

		}

		if (dejaCentre == false) {

			dejaCentre = true;
			float valPositionCentreX = ((graphe.maxXGlobal - graphe.minXGlobal) / 2)
					+ graphe.minXGlobal;
			float valPositionCentreY = graphe.maxYGlobal - (height	- (height /10))/ graphe.echelleAxeY(height);

			int drawValPositionCentreX = (int) valPositionCentreX;
			int drawValPositionCentreY = (int) valPositionCentreY;

			g.drawString(
					"" + drawValPositionCentreX,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width),
							height	- (height /10));
			g.drawString(
					"" + drawValPositionCentreY,
					((graphe.maxXGlobal - graphe.minXGlobal) / 2)
							* graphe.echelleAxeX(width) - 30,
							height	- (height /10) - 17);
		}
	}

	public static void renderGraphe(TraceGraph graphe, Graphics g, int xOffSet,
			int yOffSet, int width, int height) {

		Rectangle rectGraphView = new Rectangle(xOffSet, yOffSet, width, height);

		g.setColor(Color.red);
		g.drawString(graphe.name,
				xOffSet + width / 2 - g.getFont().getWidth(graphe.name) / 2,
				yOffSet - 16);

		g.setClip(rectGraphView);
		g.translate(xOffSet, yOffSet);

		TraceAxes(graphe, g, width, height);

		TracePoints(graphe, g, width, height);

		TraceMinMax(graphe, g, width, height);

		TraceMilieux(graphe, g, width, height);

		TraceCentre(graphe, g, width, height);

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
		Vector2f vec = renderGraph.getValueXYWithLadder(graphe, g, xOffSet,
				yOffSet, width, height, mouseX, mouseY);
		g.drawString("x=" + (int) vec.x, xOffSet, yOffSet);
		g.drawString("y=" + (int) vec.y, xOffSet, yOffSet + 18);
	}

	private static Vector2f getValueXYWithLadder(TraceGraph graphe, Graphics g,
			int xOffSet, int yOffSet, int width, int height, int mouseX,
			int mouseY) {

		float positionY = -((mouseY - yOffSet) / graphe.echelleAxeY(height) - graphe.maxYGlobal);

		float positionX = (mouseX - xOffSet) / graphe.echelleAxeX(width)
				+ graphe.minXGlobal;

		int drawPositionY = (int) positionY;
		int drawPositionX = (int) positionX;

		return new Vector2f(drawPositionX, drawPositionY);
	}

	public static void mouseOverGrapheName(TraceGraph graphe, Graphics g,
			int xOffSet, int yOffSet, int width, int height, int mouseX,
			int mouseY) {

		Vector2f valueOfXY = renderGraph.getValueXYWithLadder(graphe, g,
				xOffSet, yOffSet, width, height, mouseX, mouseY);

		Vector2f vectorMouse = new Vector2f(mouseX-xOffSet,mouseY-yOffSet);
		
		String nomGrapheAffich = "";
		/*
		// EASY ONE, don't check everything, just add name if value within graph
		// min/max (not perfect if graphe almost same)
		for (Graph graph : graphe.graphes) {
			if (graph.minGraphX < valueOfXY.x && graph.maxGraphX > valueOfXY.x
					&& graph.minGraphY < valueOfXY.y
					&& graph.maxGraphY > valueOfXY.y) {
				nomGrapheAffich += graph.nomGraphe + " ";
			}
		}
		g.setColor(Color.white);
		g.drawString("" + nomGrapheAffich, mouseX, mouseY);
		// */

		Line tmp = new Line(0, 0);
		String nameMinOfGraphs = "";
		int minDistanceOfGraphs = Integer.MAX_VALUE;
		for (Graph graph : graphe.graphes) {
			int minOfThisGraph = Integer.MAX_VALUE;
			/**
			 *  if allows for less computation but you need to get the cursor inside en min/max area of the graph
			 */
			//if (graph.minGraphX < valueOfXY.x && graph.maxGraphX > valueOfXY.x && graph.minGraphY < valueOfXY.y && graph.maxGraphY > valueOfXY.y) {

				for (int i = 0; i < graph.size() - 1; i++) {
					tmp.set((Math.abs((graph.arrayPoints.get(i)).x
							- graphe.minXGlobal))
							* graphe.echelleAxeX(width),
							(Math.abs((graph.arrayPoints.get(i)).y
									- graphe.maxYGlobal))
									* graphe.echelleAxeY(height),
							(Math.abs((graph.arrayPoints.get(i + 1)).x
									- graphe.minXGlobal))
									* graphe.echelleAxeX(width),
							(Math.abs((graph.arrayPoints.get(i + 1)).y
									- graphe.maxYGlobal))
									* graphe.echelleAxeY(height));

					//System.out.println(""+tmp.getX1()+" "+tmp.getY1()+" "+tmp.getX2()+" "+tmp.getY2());
					//System.out.println("" + graph.nomGraphe + " x=" + valueOfXY.x + " y=" + valueOfXY.y + " dist="+ tmp.distance(valueOfXY));
					//System.out.println(""+vectorMouse.x+" "+vectorMouse.y+" dist="+ tmp.distance(vectorMouse)+"\n");
					
					if(minOfThisGraph > (int)tmp.distance(vectorMouse)){
						minOfThisGraph = (int)tmp.distance(vectorMouse);
						
					}
					
					//System.out.println("min="+minOfThisGraph);
					/*
					if (tmp.on(valueOfXY)) {
						System.out.println("true on");
					}// */
					/*
					 * if(tmp.includes(mouseX, mouseY)){
					 * System.out.println("true"); }//
					 */

				}

				//nomGrapheAffich += graph.nomGraphe + " ";
				// System.out.println(nomGrapheAffich);
		//	}
			if(minDistanceOfGraphs > minOfThisGraph){
				minDistanceOfGraphs = minOfThisGraph;
				nameMinOfGraphs = graph.nomGraphe;
			}
			//minDistanceOfGraphs = Math.min(minDistanceOfGraphs, minOfThisGraph);
		}

		g.setColor(Color.white);
		g.drawString("" + nameMinOfGraphs, mouseX+10, mouseY);

	}

}
