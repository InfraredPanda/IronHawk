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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.input.KeyInput;
import com.negafinity.ironhawk.input.MouseInput;
import com.negafinity.ironhawk.libs.Animation;
import com.negafinity.ironhawk.states.ChoiceMenu;
import com.negafinity.ironhawk.states.Controls;
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
//TODO: CHANGE MENU SCREEN LOGO TO ACTUAL ART. SKINS MENU, ETC.
//TODO: BUG LIST: PLAYER 2 REGISTERS AS 1 AFTER DEATH. 

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Iron Hawk";
	public static final String VERSION = "2.1";

	private boolean running = false;
	private boolean hasNotBeenCalled = true;

	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage player2Sprite = null;

	public Image negafinity = null;
	public Image ironhawkscreen = null;
	public Image controlscreen = null;
	public Image ironhawklogo = null;

	private static BufferedImage icon16 = null;
	private static BufferedImage icon64 = null;

	public static int enemyCount = 10;
	public static int roundNumber = 0;
	public static boolean gameStarting = false;
	public static int timeToRound = 0;
	public static ArrayList<Player> players = new ArrayList<>();
	public static IronHawk ironhawk;
	public static Controls controls;
	public static boolean multiplayerEnabled = false;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public Textures tex;

	private Controller c;
	private Menu menu;
	private Start start;
	private Help help;
	private GameOver gameover;
	private ChoiceMenu choiceMenu;

	public LinkedList<Entity> entities;

	public static enum STATE
	{
		MENU, GAME, HELP, GAMEOVER, START, IRONHAWK, CHOICEMENU, CONTROLS
	}

	public static STATE State = STATE.START;

	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spriteSheet = loader.loadImage("/spriteSheet.png");
			background = loader.loadImage("/background.png");
			player2Sprite = loader.loadImage("/player2Sprite.png");
			negafinity = loader.loadImage("/negafinity.png");
			ironhawkscreen = loader.loadImage("/ironhawkscreen.png");
			controlscreen = loader.loadImage("/controlscreen.png");
			ironhawklogo = loader.loadImage("/ironhawklogo.png");
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
		controls = new Controls();
		gameover = new GameOver();
		help = new Help();
		choiceMenu = new ChoiceMenu();
		Player player = new Player(200, 200, tex, c, this);
		players.add(player);
		Player player2 = new Player(250, 200, tex, c, this);
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

	private void render()
	{
		BufferStrategy bufferedStrat = this.getBufferStrategy();

		if (bufferedStrat == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics g = bufferedStrat.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, this);

		if (State == STATE.GAME)
		{
			c.render(g);

			for (Player player : players)
			{
				int moveFactor = 0;

				if (players.indexOf(player) > 0)
				{
					moveFactor = 120;
				}

				player.render(g);

				Font fnt0 = new Font("arial", Font.BOLD, 20);
				g.setFont(fnt0);

				if (player.health / 2 == 0)
				{
					g.setColor(Color.red);
					g.fillRect(5, 5, 200, 50);
				}

				Color healthBarColor = Color.green;

				if (player.health / 2 <= 100 && player.health / 2 >= 60)
				{
					healthBarColor = Color.green;
				}
				else if (player.health / 2 >= 40 && player.health / 2 < 60)
				{
					healthBarColor = Color.yellow;
				}
				else
				{
					healthBarColor = Color.red;
				}

				g.setColor(healthBarColor);
				g.fillRect(5, 5 + moveFactor, player.health, 50);

				g.setColor(Color.white);
				g.drawString("Health of Player " + (players.indexOf(player) + 1) + ":", 20, 20 + moveFactor);
				g.setColor(Color.gray);
				g.drawString(String.valueOf(player.health / 2), 20, 40 + moveFactor);

				g.setColor(Color.white);
				g.drawRect(5, 5 + moveFactor, 200, 50);

				g.setColor(Color.white);
				g.drawString("Bombs", 500 - moveFactor, 475);
				g.drawString(String.valueOf(player.bombCount), 575 - moveFactor, 475);

				if (player.missleCount > 0)
				{
					g.setColor(Color.white);
					g.drawString("Missles ", 250 - moveFactor, 475);
					g.drawString(String.valueOf(player.missleCount), 325 - moveFactor, 475);
				}
			}

			g.setColor(Color.white);
			g.drawString("Round", WIDTH + WIDTH - 80, 20);
			g.drawString(String.valueOf(roundNumber), WIDTH + WIDTH - 10, 20);

			if (gameStarting)
			{
				g.setColor(Color.white);
				g.drawString("Round Starts In: ", WIDTH - 35, 45);
				g.drawString(String.valueOf(timeToRound), WIDTH + 125, 45);
			}

			g.setColor(Color.red);
			g.drawString("Enemies", WIDTH - 15, 20);
			g.drawString(String.valueOf(enemyCount), WIDTH + 75, 20);
		}
		else if (State == STATE.MENU)
		{
			menu.render(g, this);
		}
		else if (State == STATE.GAMEOVER)
		{
			gameover.render(g);
		}
		else if (State == STATE.HELP)
		{
			help.render(g);
		}
		else if (State == STATE.CONTROLS)
		{
			controls.render(g, this);
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
		else if (State == STATE.CHOICEMENU)
		{
			choiceMenu.render(g);
			g.drawImage(icon64, 55, 200, this);
			g.drawImage(icon64, 355, 200, this);
			g.drawImage(player2Sprite, 455, 200, this);
		}
		// break
		g.dispose();
		bufferedStrat.show();
	}

	public static void main(String args[])
	{
		BufferedImageLoader loader = new BufferedImageLoader();

		try
		{
			icon16 = loader.loadImage("/16.png");
			icon64 = loader.loadImage("/64.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		ArrayList<Image> list = new ArrayList<Image>();
		list.add(icon16);
		list.add(icon64);
		JFrame frame = new JFrame(game.TITLE);
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

	public static int getRoundNumber()
	{
		return roundNumber;
	}

	public static void setRound(int roundNumber)
	{
		Game.roundNumber = roundNumber;
	}
}
