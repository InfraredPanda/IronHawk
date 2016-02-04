package com.negafinity.ironhawk;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.input.KeyInput;
import com.negafinity.ironhawk.input.MouseInput;
import com.negafinity.ironhawk.libs.Animation;
import com.negafinity.ironhawk.screens.Controls;
import com.negafinity.ironhawk.screens.IronHawk;
import com.negafinity.ironhawk.screens.Start;
import com.negafinity.ironhawk.utils.BufferedImageLoader;
import com.negafinity.ironhawk.utils.User;

/**
 * 2D Flight Game made by NEGAFINITY.
 * @author InfraredPanda
 * @author HassanS6000
 */
// TODO: READING OFF A FILE AND WRITING TO IT FOR HIGH SCORES SKINS MENU, ETC.
// TODO: BUG LIST: PLAYER 2 REGISTERS AS 1 AFTER DEATH.

public class Game extends Canvas implements Runnable
{
	protected Game()
	{
		;
	}

	private static final long serialVersionUID = 1L;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final String TITLE = "Iron Hawk";
	public static final String VERSION = "2.3";

	private boolean running = false;
	private boolean hasNotBeenCalled = true;
	private Thread thread;

	public static JFrame frame;
	public static int enemyCount = 10;
	public static int roundNumber = 0;
	public static boolean gameStarting = false;
	public static int timeToRound = 0;
	public static ArrayList<Player> players = new ArrayList<>();
	public static boolean multiplayerEnabled = false;

	private static Game game;

	public static ArrayList<User> users = new ArrayList<>();
	public static RenderManager renderManager;
	public static ImageManager imageManager;

	public Textures tex;
	public Controller c;
	public ScreenManager screenManager;
	public DataManager dataManager;

	public LinkedList<Entity> entities;

	public static Game getGame()
	{
		return game;
	}

	public void init()
	{
		dataManager = new DataManager();
		dataManager.readUsers();
		tex = new Textures(this);
		c = new Controller(tex, this);
		screenManager = new ScreenManager();

		//Create Players
		Player player = new Player(320, 240, tex, c, this);
		players.add(player);
		Player player2 = new Player(320, 280, tex, c, this);
		player2.anim = new Animation(5, tex.player2[0], tex.player2[1]);
		players.add(player2);

		entities = c.getEntities();

		this.addKeyListener(new KeyInput(this, c, tex));
		this.addMouseListener(new MouseInput(c, this, tex));
	}

	private synchronized void start()
	{
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void printRemainingTimeBeforeRound()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (timeToRound > 0)
				{
					timeToRound--;
				}
				else
				{
					return;
				}
			}
		};
		worker.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
	}

	public void startGame(Controller co)
	{
		final Controller c = co;

		Runnable task = new Runnable()
		{
			public void run()
			{
				gameStarting = false;

				for (Player player : Game.players)
				{
					if (player.getUser() != null && roundNumber > player.getUser().getHighscore())
					{
						player.getUser().setHighscore(roundNumber);
					}
				}

				if (roundNumber != 1)
					enemyCount = 10;
				else
					enemyCount = 9;

				if (roundNumber <= 5)
				{
					c.createRedBaron(enemyCount + roundNumber);
				}
				else if (roundNumber >= 5 && roundNumber < 10)
				{
					c.createRedBaron((enemyCount + roundNumber) / 2);
					c.createJapaneseFighterPlane((enemyCount + roundNumber) / 2);
				}
				else if (roundNumber >= 5)
				{
					c.createBomber();
				}

				hasNotBeenCalled = true;
			}
		};
		worker.schedule(task, 5, TimeUnit.SECONDS);
	}

	private synchronized void stop()
	{
		dataManager.saveUsers();

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
			renderManager.render();
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
		if (screenManager.currentScreen == ScreenManager.STATE.GAME)
		{
			for (Player player : players)
			{
				player.tick();
			}

			c.tick();
		}

		if (enemyCount == 0 && hasNotBeenCalled)
		{
			hasNotBeenCalled = false;
			roundNumber++;

			timeToRound = 5;
			this.startGame(c);
			gameStarting = true;

			if (roundNumber == 1)
				this.printRemainingTimeBeforeRound();
		}

		for (Player player : players)
		{
			if (player.health >= 200)
			{
				player.health = 200;
			}
		}
	}

	public static void main(String args[])
	{
		game = new Game();
		imageManager = new ImageManager();
		renderManager = new RenderManager(game);
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		ArrayList<Image> list = new ArrayList<Image>();
		list.add(ImageManager.icon16);
		list.add(ImageManager.icon64);
		frame = new JFrame(game.TITLE);
		frame.setIconImages(list);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public static int getEnemyCount()
	{
		return enemyCount;
	}

	public static void setEnemyCount(int enemyCount)
	{
		Game.enemyCount = enemyCount;
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
