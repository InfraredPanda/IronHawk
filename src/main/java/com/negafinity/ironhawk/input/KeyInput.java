package com.negafinity.ironhawk.input;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.IronHawk;
import com.negafinity.ironhawk.Textures;
import com.negafinity.ironhawk.entities.weapons.Bomb;
import com.negafinity.ironhawk.entities.weapons.Bullet;
import com.negafinity.ironhawk.entities.weapons.Missile;
import com.negafinity.ironhawk.utils.ScreenManager;
import com.negafinity.ironhawk.utils.ScreenManager.STATE;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private IronHawk game;
	private Controller c;
	private Textures tex;

	public KeyInput(IronHawk game, Controller c, Textures tex)
	{
		this.game = game;
		this.c = c;
		this.tex = tex;
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE)
		{
			game.screenManager.currentScreen = STATE.MENU;
		}
		
		if (game.screenManager.currentScreen == ScreenManager.STATE.GAME)
		{
			if (key == KeyEvent.VK_RIGHT && IronHawk.players.get(0).health > 0)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelX(5);
				else
					IronHawk.players.get(0).setVelX(5);
			}
			else if (key == KeyEvent.VK_LEFT && IronHawk.players.get(0).health > 0)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelX(-5);
				else
					IronHawk.players.get(0).setVelX(-5);
			}
			else if (key == KeyEvent.VK_UP && IronHawk.players.get(0).health > 0)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelY(-5);
				else
					IronHawk.players.get(0).setVelY(-5);
			}
			else if (key == KeyEvent.VK_DOWN && IronHawk.players.get(0).health > 0)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelY(5);
				else
					IronHawk.players.get(0).setVelY(5);
			}
			else if (key == KeyEvent.VK_D && IronHawk.players.get(0).health > 0)
			{
				IronHawk.players.get(0).setVelX(5);
			}
			else if (key == KeyEvent.VK_A && IronHawk.players.get(0).health > 0)
			{
				IronHawk.players.get(0).setVelX(-5);
			}
			else if (key == KeyEvent.VK_S && IronHawk.players.get(0).health > 0)
			{
				IronHawk.players.get(0).setVelY(5);
			}
			else if (key == KeyEvent.VK_W && IronHawk.players.get(0).health > 0)
			{
				IronHawk.players.get(0).setVelY(-5);
			}
			else if (key == KeyEvent.VK_SPACE && !IronHawk.players.get(0).isShooting && IronHawk.players.get(0).health > 0)
			{
				if (!IronHawk.players.get(0).rapidFire)
				{
					IronHawk.players.get(0).isShooting = true;
				}
				c.addEntity(new Bullet(IronHawk.players.get(0).getX(), IronHawk.players.get(0).getY(), tex, c, game, IronHawk.players.get(0)));
			}
			else if (key == KeyEvent.VK_ENTER && !IronHawk.players.get(1).isShooting && IronHawk.players.get(1).health > 0)
			{
				if (IronHawk.players.size() == 2)
				{
					if (IronHawk.players.get(1).rapidFire)
					{
						IronHawk.players.get(1).isShooting = true;
					}
				}
				c.addEntity(new Bullet(IronHawk.players.get(1).getX(), IronHawk.players.get(1).getY(), tex, c, game, IronHawk.players.get(1)));
			}
			else if (key == KeyEvent.VK_B && IronHawk.players.get(0).bombCount > 0)
			{
				c.addEntity(new Bomb(IronHawk.players.get(0).getX(), IronHawk.players.get(0).getY(), tex, c, game, IronHawk.players.get(0)));
				IronHawk.players.get(0).bombCount--;
			}
			else if (key == KeyEvent.VK_M && IronHawk.players.get(0).missleCount > 0)
			{
				c.addEntity(new Missile(IronHawk.players.get(0).getX(), IronHawk.players.get(0).getY(), tex, c, game, IronHawk.players.get(0)));
				IronHawk.players.get(0).missleCount--;
			}
			else if (key == KeyEvent.VK_SHIFT && IronHawk.players.get(1).missleCount > 0)
			{
				c.addEntity(new Missile(IronHawk.players.get(1).getX(), IronHawk.players.get(1).getY(), tex, c, game, IronHawk.players.get(1)));
				IronHawk.players.get(1).missleCount--;
			}
			else if (key == KeyEvent.VK_CONTROL && IronHawk.players.get(1).bombCount > 0)
			{
				c.addEntity(new Bomb(IronHawk.players.get(1).getX(), IronHawk.players.get(1).getY(), tex, c, game, IronHawk.players.get(1)));
				IronHawk.players.get(1).bombCount--;
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if (IronHawk.players.size() != 0)
		{
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_RIGHT)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelX(0);
				else
					IronHawk.players.get(0).setVelX(0);
			}
			else if (key == KeyEvent.VK_LEFT)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelX(0);
				else
					IronHawk.players.get(0).setVelX(0);
			}
			else if (key == KeyEvent.VK_DOWN)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).setVelY(0);
				else
					IronHawk.players.get(0).setVelY(0);
			}
			else if (key == KeyEvent.VK_UP)
			{
				if (IronHawk.players.size() == 2)
				{
					IronHawk.players.get(1).setVelY(0);
				}
				else
					IronHawk.players.get(0).setVelY(0);
			}
			if (key == KeyEvent.VK_D)
			{
				IronHawk.players.get(0).setVelX(0);
			}
			else if (key == KeyEvent.VK_A)
			{
				IronHawk.players.get(0).setVelX(0);
			}
			else if (key == KeyEvent.VK_S)
			{
				IronHawk.players.get(0).setVelY(0);
			}
			else if (key == KeyEvent.VK_W)
			{
				IronHawk.players.get(0).setVelY(0);
			}
			else if (key == KeyEvent.VK_SPACE)
			{
				IronHawk.players.get(0).isShooting = false;
			}
			else if (key == KeyEvent.VK_ENTER)
			{
				if (IronHawk.players.size() == 2)
					IronHawk.players.get(1).isShooting = false;
			}
		}
	}
}
