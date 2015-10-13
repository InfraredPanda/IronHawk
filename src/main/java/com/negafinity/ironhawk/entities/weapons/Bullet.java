package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.libs.Animation;

public class Bullet extends Weapon
{
	public Bullet(double x, double y, Textures tex, Controller c, Game game, Entity firer)
	{
		super(x, y, tex, c, game, firer);

		this.speed = 10;
		this.name = "Bullet";
		this.anim = new Animation(5, tex.bullet[0], tex.bullet[1]);
	}

}
