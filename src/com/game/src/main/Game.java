package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

/**
 * @author InfraredPanda A 2D Game, fight the enemies!
 */

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Iron Hawk";
	
	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private boolean isShooting = false;

	private int enemyCount = 10;
	private int enemyKilled = 0;

	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private Help help;
	private GameOver gameover;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public static int HEALTH = 100 * 2;

	public static enum STATE {
		MENU, GAME, HELP, GAMEOVER
	};

	public static STATE State = STATE.MENU;
	public static STATE State1 = STATE.HELP;
	public static STATE State2 = STATE.GAMEOVER;

	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/spriteSheet.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		tex = new Textures(this);

		c = new Controller(tex, this);
		p = new Player(200, 200, tex, this, c);
		menu = new Menu();
		gameover = new GameOver();
		help = new Help();

		ea = c.getEntityA();
		eb = c.getEntityB();

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());

		c.createEnemy(enemyCount);
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);

	}

	// Game Loop
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

	private void tick() {
		if (State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		if (enemyKilled >= enemyCount) {
			enemyCount += 2;
			enemyKilled += 0;
			c.createEnemy(enemyCount);
		}

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// break

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		g.drawImage(background, 0, 0, this);

		if (State == STATE.GAME) {
			p.render(g);
			c.render(g);
			
			Font fnt0 = new Font("arial", Font.BOLD, 20);
			g.setFont(fnt0);	
			g.setColor(Color.red);
			g.fillRect(5, 5, 200, 50);

			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 50);
			
			g.setColor(Color.BLACK);
			g.drawString("Health", 20, 20);
			g.drawString(HEALTH/2 + "", 20, 40);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 50);
		} else if (State == STATE.MENU) {
			menu.render(g);
		} else if (State == STATE.GAMEOVER) {
			gameover.render(g);
		} else if (State == STATE.HELP) {
			help.render(g);
		}
		// break
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !isShooting) {
				isShooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			isShooting = false;
		}

	}

	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
}
