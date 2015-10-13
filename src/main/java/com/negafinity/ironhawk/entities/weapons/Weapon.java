package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.Player;

public class Weapon extends Entity
{
	public int speed;
	public boolean upsideDown;
	public boolean isFacingRight;
	public boolean isFacingLeft;

	public Weapon(double x, double y, Textures tex, Controller c, Game game, Entity firer)
	{
		super(x, y, tex, c, game);

		if (firer instanceof Player)
		{
			Player player = (Player) firer;
			
			if (player.hasBeenRotatedUpsideDown)
			{
				this.upsideDown = true;
			}

			if (!player.hasBeenRotatedUpsideDown)
			{
				this.upsideDown = false;
			}

			if (player.hasBeenRotatedLeft)
			{
				this.isFacingLeft = true;
			}

			if (player.hasBeenRotatedRight)
			{
				this.isFacingRight = true;
			}
		}
	}

	@Override
	public void tick()
	{
		super.tick();

		for (int i = 0; i < c.getEntities().size(); i++)
		{
			Entity entity = c.getEntities().get(i);

			if (entity instanceof Enemy)
			{
				if (Physics.collision(this, entity))
				{
					Enemy enemy = (Enemy) entity;
					enemy.enemyHealth = enemy.enemyHealth - 50;
					c.removeEntity(this);

					if (enemy.enemyHealth <= 0)
					{
						c.randomlySpawnHealthPack(x, y);
						c.randomlySpawnRapidFire(x, y);
						c.randomlySpawnMissilePowerup(x, y);
						c.randomlySpawnBombPowerup(x, y);

						game.setEnemiesKilled(game.getEnemiesKilled() + 1);
					}
				}
			}
		}

		if (this.upsideDown)
		{
			y += speed;
			return;
		}
		else if (this.isFacingRight)
		{
			x += speed;
			return;
		}
		else if (this.isFacingLeft)
		{
			x -= speed;
			return;
		}
		else if (!this.upsideDown)
		{
			y -= speed;
			return;
		}
	}

}
