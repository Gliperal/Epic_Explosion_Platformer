package utilities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	private Panel gamePanel;
	private static PackageControlObject packageControlObject = new menu.MenuDriver();// = new platformer.PlatformerFrame();

	static final int SCREEN_UPDATE_RATE = 60;  // number of game updates per second
	static final long UPDATE_PERIOD = 1000000000L / SCREEN_UPDATE_RATE;
	public static final int SCREEN_WIDTH = 1280, SCREEN_HEIGHT = 720;

	public Frame() {
		gameInit();
		
		gamePanel = new Panel();
		gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setContentPane(gamePanel);
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
		gameStart();
	}
	
	public void gameInit() {
		packageControlObject.init();
	}
	
	public void gameStart() {
		Thread gameThread =  new Thread() {
			@Override
			public void run() {
				gameLoop();
			}
		};
		gameThread.start();
	}
	
	public void gameUpdate() {
		packageControlObject.onUpdate();
	}
	
	public void gameLoop() {
		long beginTime, timeTaken, timeLeft;

		while(true) {
			beginTime = System.nanoTime();

			gameUpdate();
			repaint();
			
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = (UPDATE_PERIOD - timeTaken) / 1000000;
			if (timeLeft < 10) timeLeft = 10;
			try {
				Thread.sleep(timeLeft);
			} catch (InterruptedException ex) { }
		}
	}
	
	public void gameDraw(Graphics g) {
		packageControlObject.render(g);
	}
	
	public static void switchControlObject(PackageControlObject newControl) {
		packageControlObject = newControl;
	}

	public class Panel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
		Panel() {
			setFocusable(true);
			requestFocus();
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		public void paintComponent(Graphics g) {
			@SuppressWarnings("unused")
			Graphics2D g2d = (Graphics2D)g;
			super.paintComponents(g);
			
			gameDraw(g);
		}

		public void keyPressed(KeyEvent arg0) {
			UserInput.instance().userPressed(arg0.getKeyCode());
		}
		
		public void keyReleased(KeyEvent e) {
			UserInput.instance().userReleased(e.getKeyCode());
		}

		public void mousePressed(MouseEvent arg0) {
			UserInput.instance().userMouseClicked();
		}
		
		public void mouseReleased(MouseEvent arg0) {
			UserInput.instance().userMouseReleased();
		}
		
		public void keyTyped(KeyEvent e) {}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {
			onMouseMoved(e);
		}
	}
	
	public void onMouseMoved(MouseEvent e) {
		UserInput.instance().moveMouse(e.getX(), e.getY());
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame();
			}
		});
	}
}
