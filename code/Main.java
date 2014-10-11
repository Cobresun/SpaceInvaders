package code;									// SKELETON
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Main extends Applet implements Runnable, KeyListener{
	
	private Image i;							// Ignore this too		
	private Graphics doubleG;					// And this				
	Player p;														
	Enemy_1 layer_1[] = new Enemy_1[9];				
	
	public void init(){
		setSize(800, 600);
		addKeyListener(this);
		Pictures ignore = new Pictures(this);
	}

	public void start(){
		p = new Player();
		int x_of_lay1 =0;
		for(int x =0; x<layer_1.length;x++){
			layer_1[x] = new Enemy_1(x_of_lay1,100);
			x_of_lay1 += 60;
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/////////////////Runs elements of the game////////////////
	public void run(){
		while(true){												//	THE ALMIGHTY WHILE LOOP
			if(p.isAlive()==false){
				for(int x=0;x<layer_1.length;x++)
					layer_1[x].setDx(0);
			}
			p.update(this);
			for(int x=0; x<layer_1.length;x++)
				layer_1[x].update(this);
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
			
			}
		}
	}
	/////////////////////////////////////////////////////////
	public void stop(){	}
	public void destroy(){}
	/////////////////////////"Ignore this weird stuff"///////////////////////////
	public void update(Graphics g) {
		if( i == null){
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i,0,0,this);
	}
	////////////////////////////////////////////////////////////////////////////
	
	//////////////////Draws it//////////////////////////////////
	@Override
	public void paint(Graphics g){
		p.paint(g);
		for(int x=0;x<layer_1.length;x++)
			layer_1[x].paint(g);
	}

	/////////////////Keys&&Controls/////////////////
	public void keyTyped(KeyEvent e) {						// ignore this.. for now	
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:								// if left arrow key is pressed
			p.MoveLeft();								// runs left function defined in player class
			this.showStatus("Moving Left");
			break;
		case KeyEvent.VK_RIGHT:								// if right arrow key is pressed
			p.MoveRight();								// runs right function defined in player class
			this.showStatus("Moving Right");
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			p.setDx(0);
			p.setLast(2);
			break;
		case KeyEvent.VK_RIGHT:
			p.setDx(0);
			p.setLast(4);
			break;
		}
	}
}
