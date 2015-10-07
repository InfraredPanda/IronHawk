package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;

import java.util.Random;

public class Enemy extends Entity
{
	public int speed;
	public int enemyHealth;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game);

		Random r = new Random();
		this.speed = r.nextInt(5) + 1;
		this.enemyHealth = enemyHealth;
	}

	@Override
	public void tick()
	{
		super.tick();
		
		y += speed;
		applySpeedMultiplier();
		
		if (Physics.collision(this, Game.player))
		{
			this.enemyHealth -= 100;
			Game.player.health -= 40;
		}
		
		if(this.enemyHealth <= 0)
		{
			c.removeEntity(this);
			game.setEnemiesKilled(game.getEnemiesKilled() + 1);
		}
	}
	
	public void applySpeedMultiplier()
	{
		if (y > (Game.HEIGHT * Game.SCALE))
		{
			Random r = new Random();
			speed = r.nextInt(5) + 1;
			x = r.nextInt(640);
			y = -10;
		}
	}
}
