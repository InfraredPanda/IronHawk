package com.negafinity.ironhawk.entities.weapons;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class Warhead extends Weapon
{
	private boolean shotByEnemy;

	public Warhead(double x, double y, Textures tex, Controller c, Game game, boolean shotByEnemy)
	{
		super(x, y, tex, c, game);

		this.shotByEnemy = shotByEnemy;
		this.speed = 10;
		this.name = "Warhead";
		this.anim = new Animation(5, tex.warhead[0], tex.warhead[1]);
	}

	@Override
	public void tick()
	{
		if(this.shotByEnemy)
		{
			if(this.y < -1000 || this.y > 1000)
			{
				c.removeEntity(this);
			}

			anim.runAnimation();
			
			if(Physics.collision(this, Game.player))
			{
				Game.player.health -= 10;
			}
			
			y += speed;
		}
		else
		{
			super.tick();
		}
	}
}
