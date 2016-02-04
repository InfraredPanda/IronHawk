package com.negafinity.ironhawk.entities;

import java.util.Random;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class RedBaron extends Enemy
{

	public RedBaron(double x, double y, Textures tex, Controller c, IronHawk game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);

		Random r = new Random();
		this.name = "Red Baron";
		this.enemyHealth = 50;
		this.speed = r.nextInt(5) + 1;
		anim = new Animation(5, tex.redBaron[0], tex.redBaron[1]);
	}

	@Override
	public void applySpeedMultiplier()
	{
		if (y > (IronHawk.HEIGHT * IronHawk.SCALE))
		{
			Random r = new Random();
			speed = r.nextInt(5) + 1;
			x = r.nextInt(640);
			y = -10;
		}
	}
}
