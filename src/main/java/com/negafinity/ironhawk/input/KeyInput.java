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
			if (key == KeyEvent.VK_RIGHT && Game.players.get(0).health > 0)
			{
				Game.players.get(0).setVelX(5);
			}
			else if (key == KeyEvent.VK_LEFT && Game.players.get(0).health > 0)
			{
				Game.players.get(0).setVelX(-5);
			}
			else if (key == KeyEvent.VK_DOWN && Game.players.get(0).health > 0)
			{
				if (!Game.players.get(0).hasBeenRotatedUpsideDown)
				{
					Game.players.get(0).hasBeenRotatedUpsideDown = true;
					Game.players.get(0).rotate();
				}

				Game.players.get(0).setVelY(5);
			}
			else if (key == KeyEvent.VK_UP && Game.players.get(0).health > 0)
			{
				if (Game.players.get(0).hasBeenRotatedUpsideDown)
				{
					Game.players.get(0).hasBeenRotatedUpsideDown = false;
					Game.players.get(0).rotateBack();
				}

				Game.players.get(0).setVelY(-5);
			}
			else if (key == KeyEvent.VK_D && Game.players.get(0).health > 0)
			{
				if (!Game.players.get(0).hasBeenRotatedRight)
				{
					Game.players.get(0).hasBeenRotatedLeft = false;
					Game.players.get(0).hasBeenRotatedUpsideDown = false;
					Game.players.get(0).hasBeenRotatedRight = true;
					Game.players.get(0).rotateRight();
				}
				Game.players.get(0).setVelX(5);
			}
			else if (key == KeyEvent.VK_A && Game.players.get(0).health > 0)
			{
				if (!Game.players.get(0).hasBeenRotatedLeft)
				{
					Game.players.get(0).hasBeenRotatedRight = false;
					Game.players.get(0).hasBeenRotatedUpsideDown = false;
					Game.players.get(0).hasBeenRotatedLeft = true;
					Game.players.get(0).rotateLeft();
				}
				Game.players.get(0).setVelX(-5);
			}
			else if (key == KeyEvent.VK_S && Game.players.get(0).health > 0)
			{
				if (!Game.players.get(0).hasBeenRotatedUpsideDown)
				{
					Game.players.get(0).hasBeenRotatedRight = false;
					Game.players.get(0).hasBeenRotatedLeft = false;
					Game.players.get(0).hasBeenRotatedUpsideDown = true;
					Game.players.get(0).rotate();
				}

				Game.players.get(0).setVelY(5);
			}
			else if (key == KeyEvent.VK_W && Game.players.get(0).health > 0)
			{
				if (Game.players.get(0).hasBeenRotatedUpsideDown || Game.players.get(0).hasBeenRotatedRight || Game.players.get(0).hasBeenRotatedLeft)
				{
					Game.players.get(0).hasBeenRotatedRight = false;
					Game.players.get(0).hasBeenRotatedLeft = false;
					Game.players.get(0).hasBeenRotatedUpsideDown = false;
					Game.players.get(0).rotateBack();
				}

				Game.players.get(0).setVelY(-5);
			}
			else if (key == KeyEvent.VK_SPACE && !Game.players.get(0).isShooting && Game.players.get(0).health > 0)
			{
				if (!Game.players.get(0).rapidFire)
				{
					Game.players.get(0).isShooting = true;
				}
				c.addEntity(new Bullet(Game.players.get(0).getX(), Game.players.get(0).getY(), tex, c, game, Game.players.get(0)));
			}
			else if (key == KeyEvent.VK_B && Game.players.get(0).bombCount > 0)
			{
				c.addEntity(new Bomb(Game.players.get(0).getX(), Game.players.get(0).getY(), tex, c, game, Game.players.get(0)));
				Game.players.get(0).bombCount--;
			}
			else if (key == KeyEvent.VK_M && Game.players.get(0).missleCount > 0)
			{
				c.addEntity(new Missile(Game.players.get(0).getX(), Game.players.get(0).getY(), tex, c, game, Game.players.get(0)));
				Game.players.get(0).missleCount--;
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
			Game.players.get(0).setVelX(0);
		}
		else if (key == KeyEvent.VK_LEFT)
		{
			Game.players.get(0).setVelX(0);
		}
		else if (key == KeyEvent.VK_DOWN)
		{
			Game.players.get(0).setVelY(0);
		}
		else if (key == KeyEvent.VK_UP)
		{
			Game.players.get(0).setVelY(0);
		}
		if (key == KeyEvent.VK_D)
		{
			Game.players.get(0).setVelX(0);
		}
		else if (key == KeyEvent.VK_A)
		{
			Game.players.get(0).setVelX(0);
		}
		else if (key == KeyEvent.VK_S)
		{
			Game.players.get(0).setVelY(0);
		}
		else if (key == KeyEvent.VK_W)
		{
			Game.players.get(0).setVelY(0);
		}
		else if (key == KeyEvent.VK_SPACE)
		{
			Game.players.get(0).isShooting = false;
		}

	}
}
