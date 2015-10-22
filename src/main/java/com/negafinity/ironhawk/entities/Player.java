package com.negafinity.ironhawk.entities;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class Player extends Entity
{
	private double velX = 0;
	private double velY = 0;

	public boolean rapidFire;
	public boolean isShooting = false;
	public int missleCount = 0;
	public int bombCount = 5;
	public int health = 200;
	public Animation defaultAnim;
	public Animation deathAnim;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public Player(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Player";

		deathAnim = new Animation(15, tex.player[5], tex.player[6], tex.player[7]);
		defaultAnim = new Animation(5, tex.player[0], tex.player[1]);
		anim = new Animation(5, tex.player[0], tex.player[1]);
	}

	@Override
	public void tick()
	{
		super.tick();

		if (this.health < 0)
		{
			this.health = 0;
		}

		x += velX;
		y += velY;

		if (x <= 0)
		{
			x = 0;
		}

		if (x >= 640 - 20)
		{
			x = 640 - 20;
		}

		if (y <= 0)
		{
			y = 0;
		}

		if (y > 480 - 32)
		{
			y = 480 - 32;
		}

		for (int i = 0; i < c.getEntities().size(); i++)
		{
			Entity entity = c.getEntities().get(i);

			if (entity instanceof RedBaron)
			{
				if (Physics.collision(this, entity))
				{
					c.removeEntity(entity);
					this.health -= 40;
				}
			}

			if (entity instanceof JapaneseFighterPlane)
			{
				if (Physics.collision(this, entity))
				{
					c.removeEntity(entity);
					this.health -= 100;
				}
			}

			if (entity instanceof Bomber)
			{
				if (Physics.collision(this, entity))
				{
					Bomber bomber = (Bomber) entity;
					bomber.enemyHealth -= 100;
					this.health -= 100;
				}
			}
		}
	}

	public void setVelX(double velX)
	{
		this.velX = velX;
	}

	public void setVelY(double velY)
	{
		this.velY = velY;
	}

	public void endGameInOneSec(Player player)
	{
		final Player p = player;

		Runnable task = new Runnable()
		{
			public void run()
			{
				boolean end = true;
				int i = 0;

				while (end && i < Game.players.size())
				{
					Game.players.remove(p);
					c.removeEntity(p);

					if (Game.players.get(i) != null && Game.players.get(i).health >= 0)
					{
						end = false;
					}

					i++;
				}

				if (end)
				{
					Game.State = Game.STATE.GAMEOVER;
				}
			}
		};
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		if (this.health <= 0)
		{
			this.anim = this.deathAnim;
			this.endGameInOneSec(this);
		}
	}

	public int getBombCount()
	{
		return bombCount;
	}

	public void setBombCount(int missleCount)
	{
		this.missleCount = missleCount;
	}

	public int getMissleCount()
	{
		return missleCount;
	}

	public void setMissleCount(int missleCount)
	{
		this.missleCount = missleCount;
	}
}
