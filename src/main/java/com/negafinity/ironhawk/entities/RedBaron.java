package com.negafinity.ironhawk.entities;

import java.util.Random;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class RedBaron extends Enemy
{

	public RedBaron(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);
		
		Random r = new Random();
		this.name = "Red Baron";
		this.enemyHealth = 100;
		this.speed = r.nextInt(5) + 1;
		anim = new Animation(5, tex.redBaron[0], tex.redBaron[1]);
	}

	@Override
	public void tick()
	{
		super.tick();
	}
	
	@Override
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
