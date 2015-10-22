package com.negafinity.ironhawk.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import com.negafinity.ironhawk.entities.weapons.Warhead;
import com.negafinity.ironhawk.libs.Animation;

public class Bomber extends Enemy
{
	private boolean hasShot;
	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
	private Player targetPlayer;
	public boolean hasBeenRotatedUpsideDown = false;
	public boolean hasBeenRotatedRight = false;
	public boolean hasBeenRotatedLeft = false;

	public Bomber(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);

		this.name = "B-29 Bomber";
		this.speed = 0;
		this.enemyHealth = 500;
		anim = new Animation(5, tex.bomber[0], tex.bomber[1]);

	}

	@Override
	public void tick()
	{
		super.tick();
		findTarget();

		if (this.targetPlayer != null)
		{
			c.moveTowardsPlayer(this, this.targetPlayer);

			if (this.x == this.targetPlayer.x && this.y < this.targetPlayer.y && !this.hasShot)
			{
				c.addEntity(new Warhead(this.x + 16, this.y + 16, tex, c, game, true, this));
				this.hasShot = true;
				this.enableShootingInOneSec();
			}
			else if (this.x == this.targetPlayer.x && this.y > this.targetPlayer.y && !this.hasShot)
			{
				c.addEntity(new Warhead(this.x + 16, this.y + 16, tex, c, game, false, this));
				this.hasShot = true;
				this.enableShootingInOneSec();
			}
		}

		for (Player player : Game.players)
		{
			if (Physics.collision(this, player))
			{
				player.health -= 100;
			}
		}

	}
	public void rotate()
	{
		BufferedImage bufferedImage = tex.bomber[0];
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.PI, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage = op.filter(bufferedImage, null);

		BufferedImage bufferedImage2 = tex.bomber[1];
		AffineTransform transform2 = new AffineTransform();
		transform2.rotate(Math.PI, bufferedImage2.getWidth() / 2, bufferedImage2.getHeight() / 2);
		AffineTransformOp op2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage2 = op2.filter(bufferedImage2, null);

		anim = new Animation(5, bufferedImage, bufferedImage2);
	}

	public void rotateRight()
	{
		BufferedImage bufferedImage = tex.bomber[0];
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.PI / 2, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage = op.filter(bufferedImage, null);

		BufferedImage bufferedImage2 = tex.player[1];
		AffineTransform transform2 = new AffineTransform();
		transform2.rotate(Math.PI / 2, bufferedImage2.getWidth() / 2, bufferedImage2.getHeight() / 2);
		AffineTransformOp op2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage2 = op2.filter(bufferedImage2, null);

		anim = new Animation(5, bufferedImage, bufferedImage2);
	}

	public void rotateLeft()
	{
		BufferedImage bufferedImage = tex.bomber[0];
		AffineTransform transform = new AffineTransform();
		transform.rotate(-Math.PI / 2, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage = op.filter(bufferedImage, null);

		BufferedImage bufferedImage2 = tex.bomber[1];
		AffineTransform transform2 = new AffineTransform();
		transform2.rotate(-Math.PI / 2, bufferedImage2.getWidth() / 2, bufferedImage2.getHeight() / 2);
		AffineTransformOp op2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
		bufferedImage2 = op2.filter(bufferedImage2, null);

		anim = new Animation(5, bufferedImage, bufferedImage2);
	}

	public void rotateBack()
	{
		anim = new Animation(5, tex.bomber[0], tex.bomber[1]);
	}

	public void findTarget()
	{
		double currentDistance = Double.MAX_VALUE;

		for (Player player : Game.players)
		{
			double distance = Math.sqrt((this.x - player.x) * (this.x - player.x) + (this.y - player.y) * (this.y - player.y));

			if (distance < currentDistance)
			{
				this.targetPlayer = player;
				currentDistance = distance;
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);

		g.setColor(Color.white);
		g.drawRect(5, 60, 250, 50);

		g.setColor(Color.MAGENTA);
		g.fillRect(5, 60, this.enemyHealth / 2, 50);

		g.setColor(Color.black);
		g.drawString(" Boss Health", Game.WIDTH / 2 - 160, 90);

		g.setColor(Color.gray);
		g.drawString(String.valueOf(this.enemyHealth), 20, 110);
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 64, 64);
	}

	public void enableShootingInOneSec()
	{
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (hasShot)
				{
					hasShot = false;
				}
			}
		};
		worker.schedule(task, 1, TimeUnit.SECONDS);
	}
}
