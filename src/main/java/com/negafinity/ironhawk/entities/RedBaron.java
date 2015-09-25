package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class RedBaron extends Enemy
{

	public RedBaron(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y, tex, c, game);
		
		this.name = "Red Baron";
		anim = new Animation(5, tex.redBaron[0], tex.redBaron[1], tex.redBaron[2]);
	}

}
