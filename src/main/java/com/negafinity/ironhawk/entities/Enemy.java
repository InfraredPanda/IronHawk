package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.GameObject;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;

import com.negafinity.ironhawk.libs.Animation;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB
{
	private double x;
	private double y;
	private Game game;
	private Controller c;
	private int speed;
	private Animation anim;
	private Textures tex;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.x = x;
		this.y = y;
		this.game = game;
		this.tex = tex;
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
				x = game.getLatestEnemyKilledX();
				y = game.getLatestEnemyKilledY();
				createHealthPack();
				game.setEnemiesKilled(game.getEnemiesKilled() + 1);
			}
		}

		anim.runAnimation();
	}
	
	public void createHealthPack()
	{
		if (game.getEnemiesKilled() != 0)
		{
			Random r = new Random();
			int spawnChance = r.nextInt(10);
			int spawnLocationX = game.getLatestEnemyKilledX();
			int spawnLocationY = game.getLatestEnemyKilledY();
			if (spawnChance <= 10)
			{
				System.out.println("hi");
				c.addEntity(new HealthPack(spawnLocationX, spawnLocationY, tex, c, game));
			}

		}
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
