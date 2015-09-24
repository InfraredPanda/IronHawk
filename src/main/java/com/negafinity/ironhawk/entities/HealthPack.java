package com.negafinity.ironhawk.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.GameObject;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class HealthPack extends GameObject implements EntityA
{
	private Textures tex;
	private Game game;
	private Animation anim;
	private Controller c;
	public HealthPack(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.c = c;
		this.x = x;
		this.y = y;
		this.tex = tex;
		this.game = game;
		
		anim = new Animation(5, tex.healthPack[0], tex.healthPack[1]);
	}

	public void tick()
	{
		anim.runAnimation();
	}

	public void render(Graphics g)
	{
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX()
	{
		return 0;
	}

	public double getY()
	{
		return 0;
	}
}