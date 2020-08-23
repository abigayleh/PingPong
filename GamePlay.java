import java.awt.BasicStroke;
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

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	
	//If game is playing
	private boolean play = false;
	// True if AI, false if another player
	private boolean AI = false;
	//True if AI is on hard, false if AI is on easy
	private boolean hard = false;
	
	//Timing of ball
	private Timer timer;
	private int delay = 1;
	
	//Starting position of ball
	private int ballposX = 300;
	private int ballposY = 225;
	
	//Direction ball will move
	private int ballXdir = -4;
	private int ballYdir = -6;
	
	//Starting position of player
	private int player1Y = 200;
	
	//Starting position of AI
	private int player2Y = 200;
	
	//Speed of paddle
	private int playerSpeed = 30;
	private int easySpeed = 3;
	
	private int player1Score = 0;
	private int player2Score = 0;
	
	private int titleScreen = 260;
	private int chooseAI = 260;
	private boolean begin = false;
	private boolean begin2 = false;
	private boolean end = false;
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		
		//Background
		g.setColor(Color.black);
		g.fillRect(1,  1,  750, 550);

		//Dashed line
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g;
		float[] dash = {40, 20};

		BasicStroke bs1 = new BasicStroke(8, 
		        BasicStroke.CAP_BUTT, 
		        BasicStroke.JOIN_ROUND, 
		        1.0f, 
		        dash,
		        2f);
		 g2d.setStroke(bs1);
		 g2d.drawLine(375, 10, 375, 540);
		
		//Ball
		g.setColor(Color.white);
		g.fillOval(ballposX, ballposY, 20, 20);
	
		//Player1 Paddle
		g.setColor(Color.white);
		g.fillRect(20, player1Y, 8, 100);
		
		//Player2 Paddle
		g.setColor(Color.white);
		g.fillRect(715, player2Y, 8, 100);
		
		//Player 1 Score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Player 2 Score: ", 475, 35);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 75));
		g.drawString(Integer.toString(player2Score), 540, 110);
		
		//Player 2 Score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Player 1 Score: ", 100, 35);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 75));
		g.drawString(Integer.toString(player1Score), 165, 110);
		
		//Game start
		if(begin == false && play == false) {
			//Title screen
			g.setColor(Color.black);
			g.fillRect(150, 100, 450, 325);
			g.setColor(Color.white);
			g.fillRect(150, 100, 450, 10);
			g.fillRect(150, 425, 450, 10);
			g.fillRect(150, 100, 10, 325);
			g.fillRect(600, 100, 10, 335);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Welcome to Ping Pong!", 200, 175);
			g.drawString("1 Player", 325, 280);
			g.drawString("2 Players", 325, 330);
			
			g.setColor(Color.white);
			g.fillRect(275, titleScreen, 20, 20);	
				
		}
		
		else if(begin == true && AI == true) {
			//Title screen
			g.setColor(Color.black);
			g.fillRect(150, 100, 450, 325);
			g.setColor(Color.white);
			g.fillRect(150, 100, 450, 10);
			g.fillRect(150, 425, 450, 10);
			g.fillRect(150, 100, 10, 325);
			g.fillRect(600, 100, 10, 335);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Choose AI difficulty ", 225, 175);
			g.drawString("Easy", 325, 280);
			g.drawString("Hard", 325, 330);
			
			g.setColor(Color.white);
			g.fillRect(275, chooseAI, 20, 20);
		}
		else if(begin == true && AI == false) {
			//Title screen
			g.setColor(Color.black);
			g.fillRect(150, 100, 450, 325);
			g.setColor(Color.white);
			g.fillRect(150, 100, 450, 10);
			g.fillRect(150, 425, 450, 10);
			g.fillRect(150, 100, 10, 325);
			g.fillRect(600, 100, 10, 335);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Player 1 uses S and X keys", 190, 200);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Player 2 uses K and M keys", 175, 250);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Press keys to start game", 200, 350);
			
		}
		
		if(begin2) {
			//Title screen
			g.setColor(Color.black);
			g.fillRect(150, 100, 450, 325);
			g.setColor(Color.white);
			g.fillRect(150, 100, 450, 10);
			g.fillRect(150, 425, 450, 10);
			g.fillRect(150, 100, 10, 325);
			g.fillRect(600, 100, 10, 335);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Player 1 uses S and X keys", 180, 200);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Press keys to start game", 200, 275);
		}
		
		if(play) {
			begin = false;
			begin2 = false;
			
			if(ballposX < 0) {
				player2Score += 1;
				//Reset ball
				ballposY = 225;
				ballposX = 400;
				ballXdir = 4;
				ballYdir = -6;
				player1Y = 200;
				player2Y = 200;
				repaint();
			}
			
			if(ballposX > 730) {
				player1Score += 1;
				//Reset ball
				ballposY = 225;
				ballposX = 400;
				ballXdir = -4;
				ballYdir = -6;
				player1Y = 200;
				player2Y = 200;
				repaint();
			}
			g.dispose();
		}
		
		if(player1Score == 1 || player2Score == 1) {
			//reset the game 
			play = false;
			end = true;
		}
		if(player1Score == 1) {
			//Title screen
			g.setColor(Color.black);
			g.fillRect(150, 100, 450, 325);
			g.setColor(Color.white);
			g.fillRect(150, 100, 450, 10);
			g.fillRect(150, 425, 450, 10);
			g.fillRect(150, 100, 10, 325);
			g.fillRect(600, 100, 10, 335);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Player 1 has won!", 250, 200);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g.drawString("Press Space to play again", 200, 275);
		}
		//Player2Score is 10
		else if(player2Score == 1) {
			if(AI == true) {
				//Title screen
				g.setColor(Color.black);
				g.fillRect(150, 100, 450, 325);
				g.setColor(Color.white);
				g.fillRect(150, 100, 450, 10);
				g.fillRect(150, 425, 450, 10);
				g.fillRect(150, 100, 10, 325);
				g.fillRect(600, 100, 10, 335);
				
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("AI has won", 300, 200);
				
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("Better luck next time!", 240, 250);
				
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("Press Space to play again", 200, 325);
			}
			else {
				//Title screen
				g.setColor(Color.black);
				g.fillRect(150, 100, 450, 325);
				g.setColor(Color.white);
				g.fillRect(150, 100, 450, 10);
				g.fillRect(150, 425, 450, 10);
				g.fillRect(150, 100, 10, 325);
				g.fillRect(600, 100, 10, 335);
				
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("Player 2 has won!", 250, 200);
				
				g.setColor(Color.white);
				g.setFont(new Font("Times New Roman", Font.BOLD, 35));
				g.drawString("Press Space to play again", 200, 275);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			//If ball touches player2 paddle
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(715, player2Y, 8, 100))) {
				ballXdir = -ballXdir;}	
			
			//If ball touches player1 paddle
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(20, player1Y, 8, 100))) {
				ballXdir = -ballXdir;}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			// If ball touches borders
			if(ballposY < 10) {
				ballYdir = -ballYdir;
			}
			if(ballposY > 490) {
				ballYdir = -ballYdir;
			}
			
			if(AI) {
				if(player2Y >= 410 ) {
					player2Y = 410;
				}
				if(hard) {
					if(player2Y > ballposY && ballposX > 600) {
						player2Y = moveUp(player2Y);
					}
					else if(player2Y < ballposY && ballposX > 600) {
						player2Y = moveDown(player2Y);
					}
				}
				else {
					if(player2Y > ballposY && ballposX > 600) {
						player2Y = moveUpEasy(player2Y);
					}
					else if(player2Y < ballposY && ballposX > 600) {
						player2Y = moveDownEasy(player2Y);
					}
				}
				repaint();
			}

		// Shows changes made to paddle (calls paint method)
		repaint();
	}
}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		//Moves title screen options
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(begin == false) {
				titleScreen = 260;
				repaint();
			}
			else {
				chooseAI = 260;
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(begin == false) {
				titleScreen = 310;
				repaint();
			}
			else {
				chooseAI = 310;
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(begin == false) {
				if(titleScreen == 260) {
					AI = true;
					begin = true;
					repaint();
				}
				else if(titleScreen == 310) {
					AI = false;
					begin = true;
					repaint();
				}
			}
			else {
				if(chooseAI == 260) {
					hard = false;
					begin2 = true;
					repaint();
				}
				else if(chooseAI == 310) {
					hard = true;
					begin2 = true;
					repaint();
				}
			}
		}
		if(!end) {
			//Move player 2 up
			if(e.getKeyCode() == KeyEvent.VK_K) {
				if(!AI) {
					play = true;
					repaint();
				}
				//Ensure does not leave panel size
				if(player2Y <= 5 ) {
					player2Y = 5;
				}
				else {
					if(!AI) {
						player2Y = moveUp(player2Y);
						repaint();
					}
				}
			}
			//Move player 2 down 
			if(e.getKeyCode() == KeyEvent.VK_M) {
				if(!AI) {
					play = true;
					repaint();
				}
				
				//Ensure does not leave panel size
				if(player2Y >= 410 ) {
					player2Y = 410;
				}
				else {
					if(!AI) {
						player2Y = moveDown(player2Y);
						repaint();
					}
				}
			}
			//Move player 1 up
			if(e.getKeyCode() == KeyEvent.VK_S) {
				play = true;
				repaint();
				
				//Ensure does not leave panel size
				if(player1Y <= 5 ) {
					player1Y = 5;
				}
				else {
					player1Y = moveUp(player1Y);
					repaint();
				}
			}
			//Move player 1 down
			if(e.getKeyCode() == KeyEvent.VK_X) {
				play = true;
				repaint();
	
				//Ensure does not leave panel size
				if(player1Y >= 410 ) {
					player1Y = 410;
				}
				else {
					player1Y = moveDown(player1Y);
					repaint();
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(play == false) {

				ballposX = 300;
				ballposY = 225;
				ballXdir = -4;
				ballYdir = -6;
				
				player1Y = 200;
				player2Y = 200;
				
				player1Score = 0;
				player2Score = 0;
				
				begin = true;
				if(AI)
					begin2 = true;
				end = false;
				
				timer.stop();
				timer = new Timer(delay, this);
				timer.start();
				
				repaint();
			}
		}
}
	
	public int moveUpEasy(int player) {
		// Moves paddle upward
		player -= easySpeed;
		return player;
	}
	
	public int moveDownEasy(int player) {
		// Moves paddle downward
		player += easySpeed;
		return player;
	}
	
	public int moveUp(int player) {
		// Moves paddle upward
		player -= playerSpeed;
		return player;
	}
	
	public int moveDown(int player) {
		// Moves paddle downward
		player += playerSpeed;
		return player;
	}
}
	

