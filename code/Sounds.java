package code;

import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;

public class Sounds {
	static AudioClip Music, TankShot, AlienShot, BarrierBreak, Dying;
	static Main mc;
	URL url;
	public Sounds(Main mc) {
		try{
			url = mc.getDocumentBase();
		}
		catch(Exception e){
		}
		AlienShot = mc.getAudioClip(url, "sounds/Laser.wav");
		BarrierBreak = mc.getAudioClip(url, "sounds/barrierBreak.wav");
		Dying = mc.getAudioClip(url, "sounds/dying.wav");
		Music = mc.getAudioClip(url, "sounds/hi.wav");
		TankShot = mc.getAudioClip(url,"sounds/tankShot.wav");
		this.mc=mc;
	}
}

