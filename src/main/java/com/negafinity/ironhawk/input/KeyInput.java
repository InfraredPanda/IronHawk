package com.negafinity.ironhawk.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Game.STATE;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.weapons.Bomb;
import com.negafinity.ironhawk.entities.weapons.Bullet;
import com.negafinity.ironhawk.entities.weapons.Missile;

public class KeyInput extends KeyAdapter
{
	private Game game;
	private Controller c;
	private Textures tex;

	public KeyInput(Game game, Controller c, Textures tex)
	{
		this.game = game;
		this.c = c;
		this.tex = tex;
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (Game.State == STATE.GAME)
		{
			if (key == KeyEvent.VK_RIGHT && Game.player.health > 0)
			{
				Game.player.setVelX(5);
			}
			else if (key == KeyEvent.VK_LEFT && Game.player.health > 0)
			{
				Game.player.setVelX(-5);
			}
			else if (key == KeyEvent.VK_DOWN && Game.player.health > 0)
			{
				if (!Game.player.hasBeenRotatedUpsideDown)
				{
					Game.player.hasBeenRotatedUpsideDown = true;
					Game.player.rotate();
				}
				
				Game.player.setVelY(5);
			}
			else if (key == KeyEvent.VK_UP && Game.player.health > 0)
			{
				if (Game.player.hasBeenRotatedUpsideDown)
				{
					Game.player.hasBeenRotatedUpsideDown = false;
					Game.player.rotateBack();
				}
				
				Game.player.setVelY(-5);
			}
			else if (key == KeyEvent.VK_D && Game.player.health > 0)
			{
				Game.player.setVelX(5);
			}
			else if (key == KeyEvent.VK_A && Game.player.health > 0)
			{
				Game.player.setVelX(-5);
			}
			else if (key == KeyEvent.VK_S && Game.player.health > 0)
			{
				if (!Game.player.hasBeenRotatedUpsideDown)
				{
					Game.player.hasBeenRotatedUpsideDown = true;
					Game.player.rotate();
				}
				
				Game.player.setVelY(5);
			}
			else if (key == KeyEvent.VK_W && Game.player.health > 0)
			{
				if (Game.player.hasBeenRotatedUpsideDown)
				{
					Game.player.hasBeenRotatedUpsideDown = false;
					Game.player.rotateBack();
				}
				
				Game.player.setVelY(-5);
			}
			else if (key == KeyEvent.VK_SPACE && !Game.player.isShooting && Game.player.health > 0)
			{
				if (!Game.player.rapidFire)
				{
					Game.player.isShooting = true;
				}
				c.addEntity(new Bullet(Game.player.getX(), Game.player.getY(), tex, c, game));
			}
			else if (key == KeyEvent.VK_B && Game.player.bombCount > 0)
			{
				c.addEntity(new Bomb(Game.player.getX(), Game.player.getY(), tex, c, game));
				Game.player.bombCount--;
			}
			else if (key == KeyEvent.VK_M && Game.player.missleCount > 0)
			{
				c.addEntity(new Missile(Game.player.getX(), Game.player.getY(), tex, c, game));
				Game.player.missleCount--;
			}
			else if (key == KeyEvent.VK_ESCAPE)
			{
				Game.State = STATE.MENU;
			}
		}

	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT)
		{
			Game.player.setVelX(0);
		}
		else if (key == KeyEvent.VK_LEFT)
		{
			Game.player.setVelX(0);
		}
		else if (key == KeyEvent.VK_DOWN)
		{
			Game.player.setVelY(0);
		}
		else if (key == KeyEvent.VK_UP)
		{
			Game.player.setVelY(0);
		}
		if (key == KeyEvent.VK_D)
		{
			Game.player.setVelX(0);
		}
		else if (key == KeyEvent.VK_A)
		{
			Game.player.setVelX(0);
		}
		else if (key == KeyEvent.VK_S)
		{
			Game.player.setVelY(0);
		}
		else if (key == KeyEvent.VK_W)
		{
			Game.player.setVelY(0);
		}
		else if (key == KeyEvent.VK_SPACE)
		{
			Game.player.isShooting = false;
		}

	}
}
