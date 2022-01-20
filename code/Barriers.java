package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Barriers {
	private int x =0, y =0, width = 81, height = 19, lives = 5;
	Image Barrier_Ref= Pictures.Barrier;
	URL url;
	public Barriers(int x, int y){
		this.setX(x);
		this.setY(y);
		setWidth(80);
		setHeight(20);
	}
	public void update(Main mc){
		if(getLives()==0)
			this.setY(-300);
		else if(lives == 4)
			Barrier_Ref = Pictures.Barrier1;
		else if(lives == 3)
			Barrier_Ref = Pictures.Barrier2;
		else if(lives == 2)
			Barrier_Ref = Pictures.Barrier3;
		else if(lives == 1)
			Barrier_Ref = Pictures.Barrier4;
	}
	public void paint(Graphics g){
		g.drawImage(Barrier_Ref,getX(),getY(),Pictures.mc);
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
