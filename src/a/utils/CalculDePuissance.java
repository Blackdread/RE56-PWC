package a.utils;

import java.util.ArrayList;

import a.entities.Antenna;
import a.entities.Mobile;
import a.entities.Moveable;
import a.entities.Service;

public class CalculDePuissance {

	public static float calculPuissanceRecu(Moveable moduleRecepteur,
			Moveable moduleEmetteur) {

		float puissance;
		// Calcul de la distance entre les deux

		int x = (moduleRecepteur.getX() - moduleEmetteur.getX()) / 100;
		int y = (moduleRecepteur.getY() - moduleEmetteur.getY()) / 100;
		float distance = (float) Math.sqrt((x * x + y * y));
		float perte = (float) (32.44 + 20 * Math.log10(moduleRecepteur
				.getFrequence()) + 20 * Math.log10(distance));

		puissance = (float) (moduleEmetteur.getPuissanceEmission()
				+ moduleEmetteur.getGain() - perte + moduleRecepteur.getGain());

		return puissance;

	}

	/**
	 * Fonction de calcul des interferences en fonction du nombre de mobile
	 * connect� au NodeB, cette valeur devra �tre actualis� au niveau du nodeB
	 * 
	 * @param nodeB
	 * @return
	 */
	public static float powerInterf(Antenna nodeB, ArrayList<Mobile> mobiles) {

		float puissInterf = 0;

		for (Mobile mob : mobiles) {
			if (mob.isConnecte()) {
				puissInterf = puissInterf + calculPuissanceRecu(nodeB, mob);
			}
			
		}
		puissInterf = puissInterf / nodeB.getNbreMobile();

		return puissInterf;
	}

	/**
	 * Calcul de la puissance � �mettre pour le mobile. il recoit en param�tre
	 * une antenne (avec ses sp�cifications), le mobile qui doit �mettre et le
	 * type de service qu'il doit utiliser (Voix, Data1, Data2)
	 * 
	 * @param nodeB
	 * @param mobile
	 * @param type
	 * @return
	 */
	public static float powerEmitted(Antenna nodeB, Mobile mobile, Service type) {
		float puissEmission;
		puissEmission = (float) (nodeB.getPuissanceEmission() * 0.1
				- calculPuissanceRecu(mobile, nodeB) + type.getcOverI() + nodeB
				.getPuissInterf());
		return puissEmission;


	}

	/**
	 * calcul du SIR estimé du mobile par le node B
	 * 
	 * @param nodeB
	 * @param mobile
	 * @param type
	 * @return
	 */
	public static float sirEstimated(ArrayList<Mobile> mobiles, Antenna nodeB, Mobile mobile, Service type) {
		float puissInterf = 0;
		// la valeur du gossian noise est à -60 dbm
		int gaussianNoise = -60;
		for (Mobile mob : mobiles) {
			puissInterf = puissInterf + calculPuissanceRecu(nodeB, mob);
		}

		return (float) ((calculPuissanceRecu(mobile, nodeB) * type.getsF()) / (puissInterf - gaussianNoise));
	}

	/**
	 * la fonction blerTarget constitue un nombre aléatoire en 10 et 50
	 * 
	 * @return
	 */
	public static int blerEstimate(Service type) {
		int i = (int) (Math.random() * type.getBlerTarget() * 2);
		return i;
	}

	public static double sirTarget(Antenna nodeB, Mobile mobile, Service type) {
		double i=0;
		i=(double) (mobile.getSirTarget() + ((((double)blerEstimate(type)-type.getBlerTarget())/type.getBlerTarget())*0.1));
		
		return i;
	}

}
