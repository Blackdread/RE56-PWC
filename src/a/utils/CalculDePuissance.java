package a.utils;

import a.entities.Antenna;
import a.entities.Mobile;
import a.entities.Moveable;
import a.entities.Service;

public class CalculDePuissance {

	public static float calculPuissanceRecu(Moveable moduleRecepteur, Moveable moduleEmetteur) {

		float puissance;
		// Calcul de la distance entre les deux

		int x = (moduleRecepteur.getX() - moduleEmetteur.getX()) / 100;
		int y = (moduleRecepteur.getY() - moduleEmetteur.getY()) / 100;
		float distance = (float) Math.sqrt((x * x + y * y));
		float perte = (float) (32.44 + 20 * Math.log10(moduleRecepteur.getFrequence()) + 20 * Math
				.log10(distance));

		puissance = (float) (moduleEmetteur.getPuissanceEmission() + moduleEmetteur.getGain() - perte + moduleRecepteur.getGain());

		return puissance;

	}

	
//	Fonction de calcul des interferences en fonction du nombre de mobile connecté au NodeB, cette valeur devra être actualisé 
//	au niveau du nodeB
	
	public static float powerInterf(Antenna nodeB){
		
		float puissInterf = 0;
		
		for (Mobile mob : nodeB.mobiles ) {
			puissInterf = puissInterf + calculPuissanceRecu(nodeB, mob);
		}
		puissInterf = puissInterf/nodeB.getNbreMobile();
		
		
		return puissInterf;
	}
	
//Calcul de la puissance à émettre pour le mobile. il recoit en paramétre une antenne (avec ses spécifications), le mobile qui doit émettre
//et le type de service qu'il doit utiliser (Voix, Data1, Data2)	
	
	public static float powerEmitted (Antenna nodeB, Mobile mobile, Service type){
		float puissEmit = 0;
		
		
		puissEmit = (float) (nodeB.getPuissanceEmission()*0.1 - calculPuissanceRecu(mobile, nodeB) + type.getcOverI() + nodeB.getPuissInterf());	
		
		return puissEmit;
				
	}
	
}
