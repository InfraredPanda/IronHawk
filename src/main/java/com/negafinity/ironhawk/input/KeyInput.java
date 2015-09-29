package com.negafinity.ironhawk.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.Game.STATE;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.Bullet;

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
			if (key == KeyEvent.VK_RIGHT)
			{
				Game.player.setVelX(5);
			}
			else if (key == KeyEvent.VK_LEFT)
			{
				Game.player.setVelX(-5);
			}
			else if (key == KeyEvent.VK_DOWN)
			{
				Game.player.setVelY(5);
			}
			else if (key == KeyEvent.VK_UP)
			{
				Game.player.setVelY(-5);
			}
			else if (key == KeyEvent.VK_D)
			{
				Game.player.setVelX(5);
			}
			else if (key == KeyEvent.VK_A)
			{
				Game.player.setVelX(-5);
			}
			else if (key == KeyEvent.VK_S)
			{
				Game.player.setVelY(5);
			}
			else if (key == KeyEvent.VK_W)
			{
				Game.player.setVelY(-5);
			}
			else if (key == KeyEvent.VK_SPACE && !game.isShooting)
			{
				if (!Game.rapidFire)
				{
					game.isShooting = true;
				}

				c.addEntity(new Bullet(Game.player.getX(), Game.player.getY(), tex, c, game));
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
			game.isShooting = false;
		}

	}
}
