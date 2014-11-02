package code;

import java.awt.Image;
import java.net.URL;

public class Pictures {
	static Image TankGreenLeft, TankGreenLeftStill, TankGreenRight, TankGreenRightStill, Enemy1, Enemy2, Enemy3, Barrier, Background_1;
	static Image Barrier1, Barrier2, Barrier3, Barrier4, Bullet, Menu;
	static Main mc;
	URL url;
	public Pictures(Main mc) {
		try{
			url = mc.getDocumentBase();
		}
		catch(Exception e){
		}
		Background_1 = mc.getImage(url, "images/Background_1.png");
		Barrier = mc.getImage(url,"images/Barrier1.png");
		Barrier1= mc.getImage(url,"images/Barrier2.png");
		Barrier2= mc.getImage(url,"images/Barrier3.png");
		Barrier3= mc.getImage(url,"images/Barrier4.png");
		Barrier4= mc.getImage(url,"images/Barrier5.png");
		Bullet = mc.getImage(url, "images/tankBullet.png");
		Enemy1= mc.getImage(url, "images/enemyBlue.png");
		Enemy2= mc.getImage(url, "images/enemyGreen.png");
		Enemy3= mc.getImage(url, "images/enemyRed.png");
		Menu = mc.getImage(url, "images/Menu.png");
		TankGreenLeft = mc.getImage(url, "images/TankGreenLeft.png");
		TankGreenLeftStill = mc.getImage(url, "images/TankGreenLeftStill.png");
		TankGreenRight = mc.getImage(url, "images/TankGreenRight.png");
		TankGreenRightStill = mc.getImage(url, "images/TankGreenRightStill.png");
		this.mc=mc;
	}
}
