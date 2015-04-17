/**
 * 
 */
package a.factories;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import a.entities.Camera;
import a.entities.Mobile;

/**
 * @author Yoann CAPLAIN
 *
 */
public class MobileFactory {

	public static AppGameContainer container;
	
	public static enum mobileType{
		MOBILE1,
		MOBILE2;
		
	}
	
	private MobileFactory() {
	}

	public static Mobile createMobile(Camera cameraForAntennaAndMobiles, mobileType type) throws SlickException{
		Mobile retour = new Mobile(container, new Image("images/mobile.png"), 0, 0,cameraForAntennaAndMobiles);
		
		switch (type){
		case MOBILE1:
			
		case MOBILE2:
			
			
		default:
			
			return retour;
		}
	}
	
}
