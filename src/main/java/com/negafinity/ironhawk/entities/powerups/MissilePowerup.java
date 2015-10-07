package com.negafinity.ironhawk.entities.powerups;

import java.awt.Graphics;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class MissilePowerup extends Powerup
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
			Game.player.missleCount++;
		}
	}
}
