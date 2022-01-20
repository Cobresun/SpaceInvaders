package code;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Enemy_1 {
	private int x = 340, y = 570, dx = 3, dy =0, width = 22, height = 40, class_of =1;
	private boolean alive = true;
	Image En1_Ref= Pictures.Enemy1;
	URL url;
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
	public int getClass_of() {
		return class_of;
	}
	public void setClass_of(int class_of) {
		this.class_of = class_of;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Enemy_1(int x, int y, int class_of){ // Constructor
		this.x = x;
		this.y = y;
		this.setClass_of(class_of);
		if(class_of == 1)
			En1_Ref= Pictures.Enemy1;
		else if(class_of == 2)
			En1_Ref= Pictures.Enemy2;
		else if(class_of == 3)
			En1_Ref= Pictures.Enemy3;
		this.dy = 40;
		this.dx = 1;
	}
	
	public int checkDead(Main mc){
		int cheese = 0;
		int layer = 0;
		for( ;layer<mc.aliens.length-1;layer++)
			for(int number=mc.aliens[layer].length-1; number>=0; number--){
				if(mc.aliens[layer][number].isAlive() && number > cheese){
					cheese = number;
					break;
			}
		}
		return mc.aliens[layer].length - cheese;
	}
	
	public int checkDead2(Main mc){
		int cheese = 0;
		int layer = 0;
		for( ;layer<mc.aliens.length-1;layer++)
			for(int number=0; number<=mc.aliens[layer].length-1; number++){
				if(mc.aliens[layer][number].isAlive() && number >= cheese){
					cheese = number;
					break;
			}
		}
		mc.showStatus(String.valueOf(cheese));
		return cheese;
	}
	
	public void update(Main mc){
		if(isAlive()){
			if(!mc.p.isAlive()&&y<700&&y>0-dx)
				y+=5;
			if(dx>0&&x+width<800)
				x+=dx;
			else if(dx>0){
				for(int y=0; y<mc.aliens.length;y++){
					int x_of =(800-width)-((mc.aliens[y].length-checkDead(mc))*(width+28));
					for(int x =0; x<mc.aliens[y].length;x++){
						mc.aliens[y][x].x=x_of;
						mc.aliens[y][x].y+=dy;
						mc.aliens[y][x].dx*=-1;
						x_of += 22 + 28;
					}
				}
			}
			if(dx<0&&x>0+dx)
				x+=dx;
			else if(dx<0){
				int milk = checkDead2(mc);
				for(int y=0; y<mc.aliens.length;y++){
					int x_of = 0;
					for(int x = milk; x<mc.aliens[0].length;x++){
						mc.aliens[y][x].x=x_of;
						mc.aliens[y][x].y+=dy;
						mc.aliens[y][x].dx*=-1;
						x_of += 22 + 28;
					}
				}
			}
			if (mc. p.isAlive()){
				if(y>=mc.getHeight()-200){
					mc.p.setAlive(false);
					Sounds.Dying.play();
					Sounds.Music.stop();
					mc.showStatus("You Died");
				}
			}
		}
	}

	public void Shoot(Main mc, int yCoor) {
		if(alive)
			for(int x=0; x<mc.bull.length; x++)
				if(mc.bull[x].isAvail()){
					mc.bull[x].setX((int)(getX()));
					mc.bull[x].setY(getY()+15);
					mc.bull[x].setDy(5);
					Sounds.AlienShot.play();
					break;
				}
		else if(!alive && yCoor != 0)
			Shoot(mc,yCoor-1);
	}
	public void paint(Graphics g){
		g.drawImage(En1_Ref,x,y,Pictures.mc);
	}
}