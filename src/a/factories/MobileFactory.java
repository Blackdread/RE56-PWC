/**
 * 
 */
package a.factories;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import a.utils.ResourceManager;
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
		Image tmp = ResourceManager.getImage("mobile");
		Mobile retour = new Mobile(0, 0,cameraForAntennaAndMobiles);
		retour.imageParDefaut = tmp;
		retour.shape = new Rectangle(0,0,tmp.getWidth(), tmp.getHeight());
		
		retour.mobileDisconnected = ResourceManager.getImage("mobileDisconnected");
		
		switch (type){
		case MOBILE1:
			
		case MOBILE2:
			
			
		default:
			
			return retour;
		}
	}
	
}
