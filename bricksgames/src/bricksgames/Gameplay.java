package bricksgames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	boolean play = false;
	private int playerX = 310;
	
	private int totalbrick = 21;
	
	private int ballposX = 100;
	private int ballposY = 300;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private int score = 0;
	
	private Timer timer ;
	private int delay = 8;
	
	private Bricksbody  map;
	
	public Gameplay() {
		map = new Bricksbody(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	
	}
	
	public void paint(Graphics g) {
		
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 595, 580);
		
		//bricks body
		map.draw((Graphics2D) g);
		
		//ball
		g.setColor(Color.pink);
		g.fillOval(ballposX, ballposY, 30, 30);
		
		//paddle
		g.setColor(Color.BLUE);
		g.fillRect(playerX, 550, 120, 20);
		
		//border
		g.setColor(Color.RED);
		g.fillRect(0, 0, 600, 3);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(592, 0, 3, 600);
		
		//score
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString("" + score,500,30);
		
		if(totalbrick <= 0) {
			
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.CYAN);
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.drawString("You won Score:"+score, 190, 300);
			
			g.drawString("Press Enter to Restart", 190, 320);
		}

		if(ballposY > 570) {
			
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.drawString("Game Over , Your Score:"+score,190 ,300);
			
			g.drawString("Press Enter to Restart", 190, 320);
			
		}
		
		g.dispose();
		

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			
			if(new Rectangle(ballposX, ballposY, 20,20). intersects(new Rectangle(playerX, 550, 120, 20))) {
				ballYdir = -ballYdir;
			}
			
			A: for (int i=0; i<map.map.length; i++) {
				for (int j=0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j* map.brickWidth + 80;
						int brickY = i* map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20 ,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setbrickvalue(0, i, j);
							totalbrick--;
							score += 5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1>= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;	
							}
							else {
								ballYdir = -ballYdir;
							}
							
							break A;
							
						}
								
					}
				}
			}
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 568) {
				ballXdir = -ballXdir;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX > 450) {
				playerX = 475;
			}
			else {
				moveRight();
			}
		}
		
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	if(playerX < 10) {
				playerX = 0;
			}
			else {
				moveLeft();
			}
			
		}
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	if(!play) {
        		play = true;
        		ballposX = 100;
        		ballposY = 300;
        		ballXdir = -1;
        		ballYdir = -2;
        		playerX = 310;
        		totalbrick = 21;
        		score = 0;
        		map = new Bricksbody(3,7);
        		
        		repaint();
        	}
        	
        }
		
		
	}
	
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	   }

}
