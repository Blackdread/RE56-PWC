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

		float x = (float) ((moduleRecepteur.getX() - moduleEmetteur.getX()) * 0.1) / 200;
		float y = (float) ((moduleRecepteur.getY() - moduleEmetteur.getY()) * 0.1) / 200;
		float distance = (float) Math.sqrt((x * x + y * y));
		float perte = (float) (32.44 + 20 * Math.log10(moduleEmetteur
				.getFrequence()) + 20 * Math.log10(distance));

		puissance = (float) (moduleEmetteur.getPuissanceEmission()
				+ moduleEmetteur.getGain() - perte + moduleRecepteur.getGain());
		return puissance;

	}

	/**
	 * Fonction de calcul des interferences en fonction du nombre de mobile
	 * connect� au NodeB, cette valeur devra �tre actualis� au niveau du
	 * nodeB
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
		if (nodeB.getNbreMobile() != 0) {
			return (puissInterf / nodeB.getNbreMobile());
		} else
			return 0;

	}

	/**
	 * Calcul de la puissance � �mettre pour le mobile. il recoit en
	 * param�tre une antenne (avec ses sp�cifications), le mobile qui doit
	 * �mettre et le type de service qu'il doit utiliser (Voix, Data1, Data2)
	 * 
	 * @param nodeB
	 * @param mobile
	 * @param type
	 * @return
	 */
	public static float powerEmitted(Antenna nodeB, Mobile mobile, Service type) {
		float puissEmission;
		puissEmission = (float) (nodeB.getPuissanceEmission()
				- calculPuissanceRecu(mobile, nodeB) + type.getcOverI() - 30 + nodeB
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
	public static float sirEstimated(ArrayList<Mobile> mobiles, Antenna nodeB,
			Mobile mobile) {
		float puissInterf = 0;
		// la valeur du gossian noise est à -60 dbm
		int gaussianNoise = -90;
		for (Mobile mob : mobiles) {
			if (mob.isConnecte() && (mob != mobile)) {
				puissInterf = puissInterf + calculPuissanceRecu(mob, nodeB);
			}

		}
		return (float) ((calculPuissanceRecu(nodeB, mobile) * (mobile.getType()
				.getsF())) / (puissInterf - gaussianNoise));
	}

	/**
	 * la fonction blerTarget constitue un nombre aléatoire en 10 et 50
	 * 
	 * @return
	 */
	public static int blerEstimate(Service type) {
//		 int i = (int) (Math.random() * (type.getBlerTarget() + 2));
		int i = 4;
		return i;
	}

	public static double sirTarget(Antenna nodeB, Mobile mobile) {
		double i = 0;
		i = (double) (mobile.getSirTarget() + ((((double) blerEstimate(mobile
				.getType()) - mobile.getType().getBlerTarget()) / mobile
				.getType().getBlerTarget()) * 0.1));

		return i;
	}

}
