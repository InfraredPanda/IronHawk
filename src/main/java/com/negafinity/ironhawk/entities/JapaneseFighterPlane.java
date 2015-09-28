package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class JapaneseFighterPlane extends Enemy
{

	public JapaneseFighterPlane(double x, double y, Textures tex, Controller c, Game game, int enemyHealth)
	{
		super(x, y, tex, c, game, enemyHealth);

		this.name = "Japanese Fighter Plane";
		this.enemyHealth = 200;

		anim = new Animation(5, tex.japaneseFighterPlane[0], tex.japaneseFighterPlane[1]);
	}

	@Override
	public void tick()
	{
		
		super.tick();
		System.out.println(this.enemyHealth);
	}
}
