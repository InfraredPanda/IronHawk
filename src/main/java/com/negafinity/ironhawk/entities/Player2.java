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

public class Player2 extends Entity
{
	private double velX = 0;
	private double velY = 0;

	public boolean hasBeenRotatedUpsideDown = false;
	public boolean rapidFire;
	public boolean isShooting = false;
	public int missleCount = 0;
	public int bombCount = 5;
	public int health = 200;
	public Animation defaultAnim;
	public Animation deathAnim;

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	public Player2(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Player 2";

		deathAnim = new Animation(15, tex.player[5], tex.player[6], tex.player[7]);
		defaultAnim = new Animation(5, tex.player[0], tex.player[1]);
		anim = new Animation(5, tex.player2[0], tex.player2[1]);
	}
	
	public void rotate()
	{
		BufferedImage bufferedImage = tex.player[0];
		AffineTransform transform = new AffineTransform();
	    transform.rotate(Math.PI, bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    bufferedImage = op.filter(bufferedImage, null);
	    
	    BufferedImage bufferedImage2 = tex.player[1];
		AffineTransform transform2 = new AffineTransform();
	    transform2.rotate(Math.PI, bufferedImage2.getWidth()/2, bufferedImage2.getHeight()/2);
	    AffineTransformOp op2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
	    bufferedImage2 = op2.filter(bufferedImage2, null);
	    
	    anim = new Animation(5, bufferedImage, bufferedImage2);
	}
	
	public void rotateBack()
	{   
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
					Game.player.health -= 40;
				}
			}
			
			if (entity instanceof JapaneseFighterPlane)
			{
				if (Physics.collision(this, entity))
				{
					c.removeEntity(entity);
					Game.player.health -= 100;
				}
			}
			
			if (entity instanceof Bomber)
			{
				if (Physics.collision(this, entity))
				{
					Bomber bomber = (Bomber) entity;
					bomber.enemyHealth -= 100;
					Game.player.health -= 100;
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

	public void endGameInOneSec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (anim.equals(deathAnim))
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
			this.endGameInOneSec();
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
