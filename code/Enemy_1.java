package code;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy_1 {
	private int x = 340, y = 570, dx = 3, dy =0, width = 40, height = 40,speed =0;
	private boolean alive = true;
////////////////////////////////////////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////
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
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Enemy_1(int x, int y){
		this.x = x;
		this.y = y;
		this.dy = 40;
		this.dx = 1;
	}
	
	// human adi = new human;
	//	adi.age
	public void update(Main mc){
		if(dx>0&&x+width<800-dx)
			x+=dx;
		else if(dx>0){
			int x_of =760;
			for(int x =0; x<mc.layer_1.length;x++){
				mc.layer_1[x].x=x_of;
				mc.layer_1[x].y+=dy;
				mc.layer_1[x].dx*=-1;
				x_of -= 60;
			}
		}
		if(dx<0&&x>0+dx)
			x+=dx;
		else if(dx<0){
			int x_of =0;
			for(int x =0; x<mc.layer_1.length;x++){
				mc.layer_1[x].x=x_of;
				mc.layer_1[x].y+=dy;
				mc.layer_1[x].dx*=-1;
				x_of += 60;
			}
		}
		if(y>=mc.getHeight()-200){
			mc.p.setAlive(false);
			mc.showStatus("You Died");
		}
	}

	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x,y,width,height);
		g.setColor(Color.RED);
		g.fillRect((x+width/2)-5, y+10, 10, 20);
	}
}
