package com.negafinity.ironhawk.entities.powerups;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;

public class HealthPack extends Powerup
{
	public HealthPack(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Health Pack";
		this.anim = new Animation(5, tex.healthPack[0], tex.healthPack[1]);
		this.animEffect = new Animation(5, tex.player[0], tex.player[3]);
	}

	@Override
	public void tick()
	{
		super.tick();

		for (Player player : Game.players)
		{
			if (Physics.collision(this, player))
			{
				player.health += 100;
			}
		}
	}
}
