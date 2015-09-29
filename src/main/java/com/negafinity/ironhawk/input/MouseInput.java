package com.negafinity.ironhawk.input;

import com.negafinity.ironhawk.Controller;
import com.negafinity.ironhawk.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{
	private Controller c;
	private Game game;
	
	public MouseInput(Controller c, Game game)
	{
		this.c = c;
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		;
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		;
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int clickX = e.getX();
		int clickY = e.getY();

		// Play Button
		if(Game.State == Game.STATE.MENU )
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 150 && clickY <= 200)
				{
					// Pressed Play
					Game.State = Game.STATE.GAME;
				}
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 250 && clickY <= 300)
				{
					// Pressed Help
					Game.State = Game.STATE.HELP;
				}
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 340 && clickY <= 400)
				{
					// Pressed Quit
					System.exit(1);
				}
			}
		}

		if(Game.State == Game.STATE.IRONHAWK)
		{
			if(!(Game.ironhawk.hasNotBeenCalled))
			{
				Game.State = Game.STATE.MENU;
			}
		}

		if(Game.State == Game.STATE.START)
		{
			Game.State = Game.STATE.IRONHAWK;

			if(Game.ironhawk.hasNotBeenCalled)
			{
				Game.ironhawk.hasNotBeenCalled = false;
				Game.ironhawk.showMenuIn10Sec();
			}
		}

		if (Game.State == Game.STATE.HELP)
		{
			if (clickX >= 5 && clickX <= 105)
			{
				if (clickY >= 5 && clickY <= 55)
				{
					// Pressed Back
					Game.State = Game.STATE.MENU;
				}
			}
		}

		// Quit Button
		if (Game.State == Game.STATE.GAME || Game.State == Game.STATE.GAMEOVER)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 220)
			{
				if (clickY >= 350 && clickY <= 400)
				{
					// Pressed Quit
					System.exit(1);
				}
			}
		}
		if (Game.State == Game.STATE.GAMEOVER)
		{
			if (clickX >= Game.WIDTH / 2 + 120 && clickX <= Game.WIDTH / 2 + 250)
			{
				if (clickY >= 250 && clickY <= 300)
				{
					// Pressed Play Again
					game.entities.clear();
					Game.player.health = 200;
					Game.roundNumber = 0;
					Game.enemyCount = 10;
					Game.rapidFire = false;
					Game.State = Game.STATE.GAME;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{

	}
}
