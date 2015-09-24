package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Entity
{
	public double x;
	public double y;
	public String name;
	public Animation anim;
	public Controller c;
	public Game game;
	public Textures tex;
	
	public Entity(double x, double y, Textures tex, Controller c, Game game)
	{
		this.x = x;
		this.y = y;
		this.c = c;
		this.tex = tex;
		this.game = game;
	}

	public void tick()
	{
		if(this.y < -1000 || this.y > 1000)
		{
			c.removeEntity(this);
		}

		anim.runAnimation();
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void render(Graphics g)
	{
		anim.drawAnimation(g, this.x, this.y, 0);
	}

	public double getY()
	{
		return y;
	}

	public double getX()
	{
		return x;
	}

}
