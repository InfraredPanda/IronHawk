package com.negafinity.ironhawk;

import java.awt.image.BufferedImage;

public class Textures
{
	public BufferedImage[] player = new BufferedImage[7];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] redBaron = new BufferedImage[3];
	public BufferedImage[] healthPack = new BufferedImage[3];
	public BufferedImage[] rapidFire = new BufferedImage[3];
	public BufferedImage[] japaneseFighterPlane = new BufferedImage[3];
	public BufferedImage[] bomb = new BufferedImage[4];
	public BufferedImage[] missile = new BufferedImage[3];
	public BufferedImage[] missilePowerup = new BufferedImage[3];
	
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
		player[3] = ss.grabImage(1, 4, 32, 32);
		player[4] = ss.grabImage(1, 5, 32, 32);
		player[5] = ss.grabImage(1, 6, 32, 32);
		player[6] = ss.grabImage(1, 7, 32, 32);

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
		
		bomb[0] = ss.grabImage(7, 1, 32, 32);
		bomb[1] = ss.grabImage(7, 2, 32, 32);
		bomb[2] = ss.grabImage(7, 3, 32, 32);
		
		missile[0] = ss.grabImage(8, 1, 32, 32);
		missile[1] = ss.grabImage(8, 2, 32 ,32);
		
		missilePowerup[0] = ss.grabImage(9, 1, 32, 32);
		missilePowerup[1] = ss.grabImage(9, 2, 32, 32);	
	}

}
