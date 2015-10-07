package com.negafinity.ironhawk;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.input.KeyInput;
import com.negafinity.ironhawk.input.MouseInput;
import com.negafinity.ironhawk.states.GameOver;
import com.negafinity.ironhawk.states.Help;
import com.negafinity.ironhawk.states.IronHawk;
import com.negafinity.ironhawk.states.Menu;
import com.negafinity.ironhawk.states.Start;
import com.negafinity.ironhawk.utils.BufferedImageLoader;

/**
 * A 2D Game, fight the enemies!
 * @author InfraredPanda
 * @author HassanS6000
 */
//TODO: Texture BOMB, add special animation for health pack getting used, enemyHealth, boss(es), bomb count
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Iron Hawk";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	public Image negafinity = null;
	public Image ironhawkscreen = null;

	private static BufferedImage icon16 = null;
	private static BufferedImage icon32 = null;
	
	private int enemiesKilled = 0;
	
	public static int enemyCount = 10;
	public static int roundNumber = 1;
	public static Player player;

	public static IronHawk ironhawk;
	
	private Controller c;
	private Textures tex;
	private Menu menu;
	private Start start;
	private Help help;
	private GameOver gameover;

	public LinkedList<Entity> entities;

	public static enum STATE
	{
		MENU, GAME, HELP, GAMEOVER, START, IRONHAWK
	}

	public static STATE State = STATE.START;

	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spriteSheet = loader.loadImage("/spriteSheet.png");
			background = loader.loadImage("/background.png");
			negafinity = loader.loadImage("/negafinity.png");
			ironhawkscreen = loader.loadImage("/ironhawkscreen.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		tex = new Textures(this);
		c = new Controller(tex, this);
		menu = new Menu();
		start = new Start();
		ironhawk = new IronHawk();
		gameover = new GameOver();
		help = new Help();
		player = new Player(200, 200, tex, c, this);

		entities = c.getEntities();

		this.addKeyListener(new KeyInput(this, c, tex));
		this.addMouseListener(new MouseInput(c, this));

		c.createRedBaron(enemyCount);
	}

	private synchronized void start()
	{
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop()
	{
		if (!running)
			return;

		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(1);

	}

	// Game Loop
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick()
	{
		if (State == STATE.GAME)
		{
			player.tick();
			c.tick();
		}
		if (enemyCount == 0)
		{
			enemyCount = 10;
			roundNumber++;

			if (roundNumber >= 5)
			{
				c.createRedBaron((enemyCount + roundNumber) / 2);
				c.createJapaneseFighterPlane((enemyCount + roundNumber) / 2);
			}
			else if (roundNumber <= 5)
			{
				c.createRedBaron(enemyCount + roundNumber);
			}
		}
		if (player.health >= 200)
		{
			player.health = 200;
		}
	}

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		g.drawImage(background, 0, 0, this);
		if (State == STATE.GAME)
		{
			/*
			 * JLabel label1 = new JLabel("Start"); label1.setVerticalTextPosition(JLabel.BOTTOM); label1.setHorizontalTextPosition(JLabel.CENTER); label1.setText("Enemies spawn in 3"); label1.setText(""); label1.setText("Enemies spawn in 2"); label1.setText(""); label1.setText("Enemies spawn in 1"); label1.setText("");
			 */

			player.render(g);
			c.render(g);

			Font fnt0 = new Font("arial", Font.BOLD, 20);
			g.setFont(fnt0);
			g.setColor(Color.red);
			g.fillRect(5, 5, 200, 50);

			g.setColor(Color.green);
			g.fillRect(5, 5, player.health, 50);

			g.setColor(Color.BLACK);
			g.drawString("Health", 20, 20);
			g.drawString(String.valueOf(player.health / 2), 20, 40);

			g.setColor(Color.white);
			g.drawString("Round", WIDTH + WIDTH - 80, 20);
			g.drawString(String.valueOf(roundNumber), WIDTH + WIDTH - 10, 20);

			g.setColor(Color.red);
			g.drawString("Enemies", WIDTH - 15, 20);
			g.drawString(String.valueOf(enemyCount), WIDTH + 75, 20);

			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 50);
		}
		else if (State == STATE.MENU)
		{
			menu.render(g);
		}
		else if (State == STATE.GAMEOVER)
		{
			gameover.render(g);
		}
		else if (State == STATE.HELP)
		{
			help.render(g);
		}
		else if (State == STATE.IRONHAWK)
		{
			if (start.hasNotBeenCalled)
			{
				start.hasNotBeenCalled = false;
				start.showIronHawkIn10Sec();
			}
			ironhawk.render(g, this);
		}
		else if (State == STATE.START)
		{
			if (start.hasNotBeenCalled)
			{
				start.hasNotBeenCalled = false;
				start.showIronHawkIn10Sec();
			}
			start.render(g, this);
		}
		// break
		g.dispose();
		bs.show();
	}

	public static void main(String args[])
	{
		BufferedImageLoader loader = new BufferedImageLoader();

		try
		{
			icon16 = loader.loadImage("/16.png");
			icon32 = loader.loadImage("/32.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		ArrayList<Image> list = new ArrayList<Image>();
		list.add(icon16);
		list.add(icon32);
		frame.setIconImages(list);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}

	public static int getEnemyCount()
	{
		return enemyCount;
	}

	public static void setEnemyCount(int enemyCount)
	{
		Game.enemyCount = enemyCount;
	}

	public int getEnemiesKilled()
	{
		return enemiesKilled;
	}

	public void setEnemiesKilled(int enemiesKilled)
	{
		this.enemiesKilled = enemiesKilled;
	}

	public static int getRoundNumber()
	{
		return roundNumber;
	}

	public static void setRound(int roundNumber)
	{
		Game.roundNumber = roundNumber;
	}
}
