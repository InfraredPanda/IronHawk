package com.negafinity.ironhawk.entities;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class MissilePowerup extends Entity
{
	public MissilePowerup(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Missle Powerup";
		this.anim = new Animation(5, tex.missilePowerup[0], tex.missilePowerup[1]);
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);

		if (Physics.collision(this, Game.player))
		{
			this.c.removeEntity(this);
			Game.missleCount ++;
		}
		if(Game.missleCount > 0){
			
		}
	}
}
