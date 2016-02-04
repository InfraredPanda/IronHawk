package com.negafinity.ironhawk.utils;

import com.negafinity.ironhawk.IronHawk;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageManager
{
	public BufferedImage image = new BufferedImage(IronHawk.WIDTH, IronHawk.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public BufferedImage spriteSheet;
	public BufferedImage background;
	public BufferedImage player2Sprite;
	public BufferedImage negafinity;
	public BufferedImage ironhawkscreen;
	public BufferedImage controlscreen;
	public BufferedImage ironhawklogo;

	public static BufferedImage icon16;
	public static BufferedImage icon64;

	public ImageManager()
	{
		this.loadImages();
	}

	public void loadImages()
	{
		BufferedImageLoader loader = new BufferedImageLoader();

		try
		{
			spriteSheet = loader.loadImage("spriteSheet.png");
			background = loader.loadImage("background.png");
			player2Sprite = loader.loadImage("player2Sprite.png");
			negafinity = loader.loadImage("negafinity.png");
			ironhawkscreen = loader.loadImage("ironhawkscreen.png");
			controlscreen = loader.loadImage("controlscreen.png");
			ironhawklogo = loader.loadImage("ironhawklogo.png");
			icon16 = loader.loadImage("16.png");
			icon64 = loader.loadImage("64.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
