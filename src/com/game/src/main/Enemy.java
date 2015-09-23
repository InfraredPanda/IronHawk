package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Enemy extends GameObject implements EntityB
{

	private double x;
	private double y;
	private Textures tex;
	private Game game;
	private Controller c;
	private int speed;
	private Animation anim;

	public Enemy(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.c = c;

		Random r = new Random();
		this.speed = r.nextInt(5) + 1;

		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}

	public void tick()
	{
		Random r = new Random();
		y += speed;

		if (y > (Game.HEIGHT * Game.SCALE))
		{
			speed = r.nextInt(3) + 1;
			x = r.nextInt(640);
			y = -10;

		}

		for (int i = 0; i < game.ea.size(); i++)
		{
			EntityA tempEnt = game.ea.get(i);

			if (Physics.Collision(this, tempEnt))
			{
				c.removeEntity(this);
				c.removeEntity(tempEnt);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
			}
		}

		anim.runAnimation();
	}

	public void render(Graphics g)
	{
		anim.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

}
