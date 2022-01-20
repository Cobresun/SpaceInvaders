package code;									// SKELETON
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class Main extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener{
	
	private Image i;							// Ignore this too		
	private Graphics doubleG;					// And this				
	Player p;														
	Enemy_1 aliens[][] = new Enemy_1[3][10];	// Spawning in Enemies
	Barriers barr[] = new Barriers[5];          // Spawning in Barriers
	Proj bull[] = new Proj[200];                // Spawning in Projectiles
	Image Background_Ref;
	Image Menu_Ref;
	private int enem_shot_count=0;
	private int score = 0;
	private boolean paused = false;             // Game States
	private boolean menu = true;
	private boolean game = false; 
	private boolean mouseHover = false;
	
	public void init(){
		setSize(800, 600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		new Pictures(this);
		new Sounds(this);
		Background_Ref = Pictures.Background_1; // Sets background
		Menu_Ref = Pictures.Menu;
	}

	public void start(){
		p = new Player();
		int x_of_enm =0;
		int y_of_enm=50;
		for(int y =0; y<aliens.length; y++){
			for(int x =0; x<aliens[0].length;x++){
				aliens[y][x] = new Enemy_1(x_of_enm, y_of_enm, aliens.length-y); // Setting location for every enemy
				x_of_enm += 50;
			}
			y_of_enm += 50;
		}
		for(int x =0; x<bull.length;x++)
			bull[x] = new Proj(300,-200,-5);
		int x_of_barr = 30;
		for(int x =0; x<barr.length;x++){
			barr[x] = new Barriers(x_of_barr,470);
			x_of_barr+=80+85;
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/////////////////Runs elements of the game////////////////
	public void run(){
		while(true){       //	THE ALMIGHTY WHILE LOOP
			if (menu){
				
			}
			else if(game){
				Sounds.Music.loop();
				repaint();
				while(true){
					if(!paused){
						enem_shot_count++;
						if(enem_shot_count % 100 ==0 && p.isAlive()){
							aliens[aliens.length-1][(int)(Math.random()*aliens[0].length)].Shoot(this,aliens.length);
						}
						p.update(this);
						if(!p.isAlive()){
							for(int y=0; y<aliens.length; y++)
								for(int x=0;x<aliens[0].length;x++)
									aliens[y][x].setDx(0);
						}
						for(int y=0; y<aliens.length; y++)
							for(int x=0; x<aliens[0].length;x++)
								aliens[y][x].update(this);
						for(int x=0; x<barr.length;x++)
							barr[x].update(this);
						for(int x=0; x<bull.length;x++)
							bull[x].update(this);
						p.setCooldwn(p.getCooldwn() - 1);}
					repaint();
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
		
					
					}
				}
			}	// end pause
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
		if(menu){
			g.drawImage(Menu_Ref, 0, 0, this);
			Font cfont = new Font("Monospaced", Font.BOLD,90);
			g.setFont(cfont);
			if(mouseHover){
				g.setColor(Color.LIGHT_GRAY);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.fillRoundRect(100, 450, 600, 100, 60, 60);
			g.setColor(Color.BLACK);
			g.drawString("Play", 300, 525);
		}
		else if(game){
			g.drawImage(Background_Ref,0,0,this);
			p.paint(g);												// Paints the player
			for(int y=0; y<aliens.length; y++)						// Paints the aliens
				for(int x=0; x<aliens[0].length;x++)
					if(aliens[y][x].isAlive())
						aliens[y][x].paint(g);
			for(int x=0; x<barr.length&&p.isAlive();x++)			// Paints the barriers
				barr[x].paint(g);
			for(int x=0; x<bull.length;x++)							// Paints the projectiles
				bull[x].paint(g);
			Font font = new Font("Monospaced", Font.BOLD,90);
			g.setFont(font);
			g.setColor(Color.RED);
			if(paused)
				g.drawString("PAUSED", 280, 170);
			if(!paused)
				g.drawString(String.valueOf(score), 310, 170);
			if(!p.isAlive()){
				Font bfont = new Font("Monospaced", Font.BOLD,30);
				g.setFont(bfont);
				g.setColor(Color.WHITE);
				g.fillRect(270, 130, 450, 50);
				g.setColor(Color.RED);
				g.drawString("YOU DEAD BRO, WHAT NOW?", 280, 170);
			}
		}
	}

	/////////////////Keys&&Controls/////////////////
	public void keyTyped(KeyEvent e) {						// ignore this.. for now	
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:								// if left arrow key is pressed
			p.MoveLeft();								// runs left function defined in player class
			break;
		case KeyEvent.VK_RIGHT:								// if right arrow key is pressed
			p.MoveRight();								// runs right function defined in player class
			break;
		case KeyEvent.VK_SPACE:                        // if space is pressed
			if (menu){
				
			}
			else if(game){
				p.Shoot(this);									
			}
			break;
		case KeyEvent.VK_P:
			if(paused){
				paused = false;
				Sounds.Music.loop();
			}
			else{
				paused = true;
				Sounds.Music.stop();
			}
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
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() >= 100 && e.getX() <= 700){
			if(e.getY() >= 450 && e.getY() <= 550)
				mouseHover = true;
		}
		if(e.getX() <= 100 || e.getX() >= 700)
			mouseHover = false;
		if(e.getY() <= 450 || e.getY() >= 550)
			mouseHover = false;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(mouseHover){
			game = true;
			menu = false;		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}