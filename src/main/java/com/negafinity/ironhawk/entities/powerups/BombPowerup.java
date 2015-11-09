package com.negafinity.ironhawk.entities.powerups;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Player;
import com.negafinity.ironhawk.libs.Animation;

public class BombPowerup extends Powerup
{
	public BombPowerup(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);

		this.name = "Bomb Powerup";
		this.anim = new Animation(5, tex.bombPowerup[0], tex.bombPowerup[1]);
	}

	@Override
	public void tick()
	{
		super.tick();

		for (Player player : Game.players)
		{
			if (Physics.collision(this, player))
			{
				player.bombCount++;
			}
		}
	}
}
