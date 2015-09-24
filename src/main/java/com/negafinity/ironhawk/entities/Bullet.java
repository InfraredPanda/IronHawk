package com.negafinity.ironhawk.entities;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.GameObject;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA
{
	private Animation anim;
	private Controller c;
	
	public Bullet(double x, double y, Textures tex, Controller c)
	{
		super(x, y);
		this.c = c;
		this.x = x;
		this.y = y;

		anim = new Animation(5, tex.bullet[0], tex.bullet[1], tex.bullet[2]);
	}

	public void tick()
	{
		if(this.y < -1000 || this.y > 1000)
		{
			c.removeEntity(this);
		}
		
		y -= 10;
		anim.runAnimation();
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void render(Graphics g)
	{
		anim.drawAnimation(g, x, y, 0);
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
