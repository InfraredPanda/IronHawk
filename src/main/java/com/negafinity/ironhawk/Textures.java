package com.negafinity.ironhawk;

import java.awt.image.BufferedImage;

public class Textures
{
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] redBaron = new BufferedImage[3];
	public BufferedImage[] healthPack = new BufferedImage[3];
	public BufferedImage[] rapidFire = new BufferedImage[3];
	public BufferedImage[] japaneseFighterPlane = new BufferedImage[3];
	
	private SpriteSheet ss;

	public Textures(Game game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private void getTextures()
	{
		player[0] = ss.grabImage(1, 1, 32, 32);
		player[1] = ss.grabImage(1, 2, 32, 32);
		player[2] = ss.grabImage(1, 3, 32, 32);

		bullet[0] = ss.grabImage(2, 1, 32, 32);
		bullet[1] = ss.grabImage(2, 2, 32, 32);

		redBaron[0] = ss.grabImage(3, 1, 32, 32);
		redBaron[1] = ss.grabImage(3, 2, 32, 32);
		
		japaneseFighterPlane[0] = ss.grabImage(4, 1, 32, 32);
		japaneseFighterPlane[1] = ss.grabImage(4, 2, 32, 32);
		
		healthPack[0] = ss.grabImage(5, 1, 32, 32);
		healthPack[1] = ss.grabImage(5, 2, 32, 32);
		
		rapidFire[0] = ss.grabImage(6, 1, 32, 32);
		rapidFire[1] = ss.grabImage(6, 2, 32, 32);
	}

}
