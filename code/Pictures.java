package code;

import java.awt.Image;
import java.net.URL;

public class Pictures {
	static Image TankGreenLeft, TankGreenLeftStill, TankGreenRight, TankGreenRightStill;
	static Main mc;
	URL url;
	public Pictures(Main mc) {
		try{
			url = mc.getDocumentBase();
		}
		catch(Exception e){
		}
		TankGreenLeft = mc.getImage(url, "images/TankGreenLeft.png");
		TankGreenLeftStill = mc.getImage(url, "images/TankGreenLeftStill.png");
		TankGreenRight = mc.getImage(url, "images/TankGreenRight.png");
		TankGreenRightStill = mc.getImage(url, "images/TankGreenRightStill.png");
		this.mc=mc;
	}
}
