/**
 * 
 */
package a;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import a.factories.MobileFactory;
import a.states.*;
import a.utils.Configuration;

/**
 * @author Yoann CAPLAIN
 *
 */
public class Main extends StateBasedGame {

	/**
	 * @param name
	 */
	public Main() {
		super("RE56 PWC");
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new PresentationView());
		addState(new PowerControlView());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Configuration.init("./config.properties");
			
			AppGameContainer container = new AppGameContainer(new Main());
			container.setDisplayMode(800,600,false);
			
			MobileFactory.container = container;
			
			// Mandatory
			container.setMinimumLogicUpdateInterval(10);
			container.setMaximumLogicUpdateInterval(500);
			container.setUpdateOnlyWhenVisible(false);
			container.setAlwaysRender(true);
			
			//container.setIcons(new String[] {"testdata/icon.tga"});
			
			applyCurrentConfiguration(container);
			
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void applyCurrentConfiguration(AppGameContainer container) throws IOException, SlickException {
		Configuration.updateConfigFile();
		container.setDisplayMode(Configuration.getWidth(), Configuration.getHeight(), Configuration.isFullScreen());  
		container.setTargetFrameRate(Configuration.getTargetFPS());
		container.setSmoothDeltas(Configuration.isSmoothDeltas());
		container.setVSync(Configuration.isVSynch());
		container.setMusicVolume(Configuration.getMusicVolume());
		container.setMusicOn(Configuration.isMusicOn());
		container.setSoundVolume(Configuration.getSoundVolume());
		container.setShowFPS((Configuration.isDebug()) ? true : false);
		container.setVerbose((Configuration.isDebug()) ? true : false);
	}

}
