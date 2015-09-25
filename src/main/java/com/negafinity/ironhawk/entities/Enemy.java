package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

import java.util.Random;

public class Enemy extends Entity
{
	private int speed;

	public Enemy(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		Random r = new Random();
		this.speed = r.nextInt(5) + 1;
	}

	@Override
	public void tick()
	{
		super.tick();
		
		Random r = new Random();
		y += speed;

		if (y > (Game.HEIGHT * Game.SCALE))
		{
			speed = r.nextInt(3) + 1;
			x = r.nextInt(640);
			y = -10;
		}

		if (Physics.collision(this, game.player))
		{
			c.removeEntity(this);
			c.randomlySpawnHealthPack(x, y);
			game.setEnemiesKilled(game.getEnemiesKilled() + 1);
		}
	}
}
