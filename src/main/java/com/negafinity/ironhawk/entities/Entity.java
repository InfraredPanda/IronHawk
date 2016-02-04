package com.negafinity.ironhawk.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.libs.Animation;

public class Entity
{
	public double x;
	public double y;
	public double currentRotation = 0;
	public String name;
	public Animation anim;
	public Controller c;
	public IronHawk game;
	public Textures tex;

	public Entity(double x, double y, Textures tex, Controller c, IronHawk game)
	{
		this.x = x;
		this.y = y;
		this.c = c;
		this.tex = tex;
		this.game = game;
	}

	public void rotate(double rotation)
	{
		this.currentRotation = rotation;

		BufferedImage rotatedImage1 = this.anim.img1;
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotation), rotatedImage1.getWidth() / 2, rotatedImage1.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		rotatedImage1 = op.filter(rotatedImage1, null);

		BufferedImage rotatedImage2 = this.anim.img2;
		AffineTransform transform2 = new AffineTransform();
		transform2.rotate(Math.toRadians(rotation), rotatedImage2.getWidth() / 2, rotatedImage2.getHeight() / 2);
		AffineTransformOp op2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
		rotatedImage2 = op2.filter(rotatedImage2, null);

		anim = new Animation(5, rotatedImage1, rotatedImage2);
	}

	public void tick()
	{
		if (this.y < -1000 || this.y > 1000)
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
