package com.negafinity.ironhawk.entities;



import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Physics;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class RapidFire extends Entity
{
	public RapidFire(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);
		
		this.name = "Rapid Fire";
		anim = new Animation(5, tex.rapidFire[0], tex.rapidFire[1]);
	}

	@Override
	public void tick()
	{
		super.tick();
	
		if (Physics.collision(this, Game.player))
		{
			c.removeEntity(this);
			game.rapidFire = true;
		}
		if(game.rapidFire)
		{
		
		}
	}
}
