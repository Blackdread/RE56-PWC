package a.utils;

import java.util.ArrayList;

import a.entities.Antenna;
import a.entities.Mobile;
import a.entities.Moveable;
import a.entities.Service;

public class CalculDePuissance {

	public static float calculPuissanceRecu(Moveable moduleRecepteur, Moveable moduleEmetteur) {
		float puissance;
		// Calcul de la distance entre les deux

		float x = (float) ((moduleRecepteur.getX() - moduleEmetteur.getX()) * 3) / 200;
		float y = (float) ((moduleRecepteur.getY() - moduleEmetteur.getY()) * 3) / 200;
		float distance = (float) Math.sqrt((x * x + y * y));
		float perte = (float) (32.44 + 20 * Math.log10(moduleEmetteur
				.getFrequence()) + 20 * Math.log10(distance));

		puissance = (float) (moduleEmetteur.getPuissanceEmission()
				+ moduleEmetteur.getGain() - perte + moduleRecepteur.getGain());
		return puissance;  // En dBm
	}

	/**
	 * Fonction de calcul des interferences en fonction du nombre de mobile
	 * connect� au NodeB, cette valeur devra �tre actualis� au niveau du
	 * nodeB
	 * 
	 * @param nodeB
	 * @return
	 */
	public static double powerInterf(Antenna nodeB, ArrayList<Mobile> mobiles) {

		double puissInterf = 0;
		int i=0;
		
		for (Mobile mob : mobiles) {
			if (mob.isConnecte()) {
				i++;
				puissInterf = puissInterf + calculPuissanceRecu(nodeB, mob);
				
			}

		nodeB.setNbreMobile(i);
		}
		if (nodeB.getNbreMobile() != 0) {
			double puissInterEndB = puissInterf / nodeB.getNbreMobile();
			return (puissInterEndB);
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
	public static double powerEmitted(Antenna nodeB, Mobile mobile, Service type) {
		double puissEmission = 0;
//		puissEmission = (float) (nodeB.getPuissanceEmission()
//				- calculPuissanceRecu(mobile, nodeB) + type.getcOverI() + nodeB
//				.getPuissInterf());
		puissEmission = Math.pow(10, ((nodeB.getPuissanceEmission()/10))-3) - Math.pow(10, ((calculPuissanceRecu(mobile, nodeB)/10))-3)
					+ Math.pow(10, ((type.getcOverI()/10))-3) + Math.pow(10, ((nodeB.getPuissInterf())/10));
		puissEmission = 10 * Math.log10(puissEmission);
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
	public static double sirEstimated(ArrayList<Mobile> mobiles, Antenna nodeB,
			Mobile mobile) {
		double puissInterf = 0;
		// la valeur du gossian noise est à -60 dbm = 0.000001 mW
		double gaussianNoise = 0.00001;
		for (Mobile mob : mobiles) {
			if (mob.isConnecte() && (mob != mobile)) {
				puissInterf = puissInterf + Math.pow(10, ((calculPuissanceRecu(nodeB, mobile)/10)));
			}

		}
		double puissanceEnWatt = Math.pow(10, ((calculPuissanceRecu(nodeB, mobile)/10)));
		double sirEstimatedEndB = 10 * Math.log10((puissanceEnWatt * (mobile.getType().getsF())) / (puissInterf + gaussianNoise));		
		return (sirEstimatedEndB);
	}

	/**
	 * la fonction blerTarget constitue un nombre aléatoire en 10 et 50
	 * 
	 * @return
	 */
	public static double blerEstimate(Service type) {
		double i = type.getBlerTarget();
		return i;
	}

	public static double sirTarget(Antenna nodeB, Mobile mobile) {
		double i = 0;
		i = (double) (mobile.getSirTarget() + (((mobile.getBlerEstimated() - mobile.getType().getBlerTarget()) / mobile
				.getType().getBlerTarget()) * 0.1));

		return i;
	}

}
