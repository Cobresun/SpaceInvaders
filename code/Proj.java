package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Proj implements ImageObserver {
	private int x, y, dy, width, height, in_frount;
	private float frame;
	private boolean avail;
	Image Bullet_Ref= Pictures.Bullet;
	
	public Proj(int x, int y, int dy){
		this.setX(x);
		this.setY(y);
		this.in_frount=20;
		this.setDy(dy);
		this.width = 8;
		this.height = 16;
		float frame = 0;
	}
	
	public void update(Main mc){
		
		int tester = (int)(frame + .2);
		if (tester < 3)
			frame += .2;
		else
			frame = 0;
		
		setY(getY() + getDy());
		if(getY()>700||getY()<-100){
			setDy(0);
			setAvail(true);
		}
		else{
			setAvail(false);
		}
		if(mc.p.isAlive()){
		    Check_Death(mc);
		}
	}
	
	private void Check_Death(Main mc) {
		if(dy<0){							// Shot by player
			for(int lay =0; lay<mc.aliens.length; lay++)
			for(int std=0, enm=0; enm<mc.aliens[0].length; std++,enm++){
				if(std<mc.barr.length && x+width>=mc.barr[std].getX()  &&  x<=mc.barr[std].getX()+mc.barr[std].getWidth())	// Barrier Collision detection
					if(y+height>=mc.barr[std].getY()  &&  y<= mc.barr[std].getY()+mc.barr[std].getHeight()){
						mc.barr[std].setLives(mc.barr[std].getLives() - 1);
						Sounds.BarrierBreak.play();
						y=-200;
					}
				if(x+width>=mc.aliens[lay][enm].getX()  &&  x<=mc.aliens[lay][enm].getX()+mc.aliens[lay][enm].getWidth())	//  Enemy Collision detection
					if(y<=mc.aliens[lay][enm].getY()+mc.aliens[lay][enm].getHeight()  &&  y>=mc.aliens[lay][enm].getY() && mc.aliens[lay][enm].isAlive()){
						mc.aliens[lay][enm].setDx(0);
						mc.aliens[lay][enm].setAlive(false);
						if(mc.aliens[lay][enm].getClass_of()==1)
							mc.setScore(mc.getScore() + 20);
						if(mc.aliens[lay][enm].getClass_of()==2)
							mc.setScore(mc.getScore() + 30);
						if(mc.aliens[lay][enm].getClass_of()==3)
							mc.setScore(mc.getScore() + 40);
						y=-200;
					}
			}
		}
		else{								// Shot by enemy
			for(int std=0;std<mc.barr.length;std++){
				if(x+width>=mc.barr[std].getX()  &&  x<=mc.barr[std].getX()+mc.barr[std].getWidth())	// Barrier Collision detection
					if(y+height>=mc.barr[std].getY()  &&  y<= mc.barr[std].getY()+mc.barr[std].getHeight()){
						mc.barr[std].setLives(mc.barr[std].getLives() - 1);
						Sounds.BarrierBreak.play();
						y=-200;
						break;
					}
				if(x+width>=mc.p.getX()  &&  x<=mc.p.getX()+mc.p.getWidth())					//  Player Collision detection
					if(y+height>=(mc.p.getLast()==2||mc.p.getLast()==4 ? mc.p.getY() : mc.p.getY()+20)  &&  y<=mc.p.getY()+mc.p.getHeight()){
						mc.p.setAlive(false);
						Sounds.Dying.play();
						Sounds.Music.stop();
						y=-200;
					}
			}
		}
	}

	public void paint(Graphics g){
		g.drawImage(Bullet_Ref, x, y, x+ width, y+ height, 8*(int)frame, 0, 8*(int)frame + 8, 16, Pictures.mc);
	}

	public boolean isAvail() {
		return avail;
	}

	public void setAvail(boolean avail) {
		this.avail = avail;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
