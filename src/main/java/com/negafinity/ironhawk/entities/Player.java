package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.GameObject;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject implements EntityA
{
	private double velX = 0;
	private double velY = 0;
	private Game game;
	private Controller controller;
	private Animation anim;

	public Player(double x, double y, Textures tex, Game game, Controller controller)
	{
		super(x, y);

		this.x = x;
		this.y = y;
		this.game = game;
		this.controller = controller;

		anim = new Animation(5, tex.player[0], tex.player[1]);
	}

	public void tick()
	{
		x += velX;
		y += velY;

		if (x <= 0)
			x = 0;
		if (x >= 640 - 20)
			x = 640 - 20;
		if (y <= 0)
			y = 0;
		if (y > 480 - 32)
			y = 480 - 32;
		for (int i = 0; i < game.eb.size(); i++)
		{
			EntityB tempEnt = game.eb.get(i);

			if (Physics.Collision(this, tempEnt))
			{
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 40;
			}
			if (Game.HEALTH <= 0)
			{
				Game.State = Game.STATE.GAMEOVER;
			}
		}
		anim.runAnimation();
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void render(Graphics g)
	{
		anim.drawAnimation(g, x, y, 0);
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void setVelX(double velX)
	{
		this.velX = velX;
	}

	public void setVelY(double velY)
	{
		this.velY = velY;
	}

}