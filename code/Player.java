package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Player {
	private int x = 340, y = 540, dx = 0, width = 60, height = 40;
	private boolean alive = true;
	private int last = 2;
	Image Tank_Ref;
	URL url;
	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	/////////////////////////////////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////////////
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void update(Main mc){
		if(last==1)
			Tank_Ref = Pictures.TankGreenLeft;
		else if(last==2)
			Tank_Ref = Pictures.TankGreenLeftStill;
		else if(last==3)
			Tank_Ref = Pictures.TankGreenRight;
		else if(last==4)
			Tank_Ref = Pictures.TankGreenRightStill;
		if(dx>0&&x+width<=800-dx&&alive)
			x+=dx;
		else if(dx<0&&x+2>=0+dx&&alive)
			x+=dx;
	}

	public void paint(Graphics g){
		g.drawImage(Tank_Ref, x, y, Pictures.mc);
		// g.fillRect(x,y,width,height);
		//g.fill3DRect(x, y, width, height, true);
	}

	public void MoveLeft(){
		if(alive){
			dx = -5;
			last = 1;
		}
	}
	
	public void MoveRight(){
		if(alive){
			dx = 5;
			last = 3;
		}	
	}
}
