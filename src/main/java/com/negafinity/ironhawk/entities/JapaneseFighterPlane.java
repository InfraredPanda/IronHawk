package com.negafinity.ironhawk.entities;

import java.util.Random;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class JapaneseFighterPlane extends Enemy
{
	public JapaneseFighterPlane(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);

		Random r = new Random();
		this.name = "Japanese Fighter Plane";
		this.enemyHealth = 200;
		this.speed = r.nextInt(5) + 5;
		anim = new Animation(5, tex.japaneseFighterPlane[0], tex.japaneseFighterPlane[1]);
	}

	@Override
	public void applySpeedMultiplier()
	{
		if (y > (Game.HEIGHT * Game.SCALE))
		{
			Random r = new Random();
			speed = r.nextInt(5) + 3;
			x = r.nextInt(640);
			y = -10;
		}
	}
}
