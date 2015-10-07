package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class Missile extends Weapon
{
	public Missile(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Missile";
		this.speed = 20;
		anim = new Animation(5, tex.missile[0], tex.missile[1]);
	}

	@Override
	public void tick()
	{
		super.tick();
	}

}
