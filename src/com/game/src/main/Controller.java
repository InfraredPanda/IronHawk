package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller
{
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private Textures tex;
	private Game game;

	public Controller(Textures tex, Game game)
	{
		this.tex = tex;
		this.game = game;
	}

	public void createEnemy(int enemyCount)
	{
		Random r = new Random();
		
		for (int i = 0; i < enemyCount; i++)
		{
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
		}
	}

	public void tick()
	{
		// A CLASS
		for (int i = 0; i < ea.size(); i++)
		{
			EntityA entityA = ea.get(i);
			entityA.tick();
		}
		// B CLASS
		for (int i = 0; i < eb.size(); i++)
		{
			EntityB entityB = eb.get(i);
			entityB.tick();
		}

	}

	public void render(Graphics g)
	{
		// A CLASS
		for (int i = 0; i < ea.size(); i++)
		{
			EntityA entityA = ea.get(i);
			entityA.render(g);

		}
		// B CLASS
		for (int i = 0; i < eb.size(); i++)
		{
			EntityB entityB = eb.get(i);
			entityB.render(g);

		}

	}

	public void addEntity(EntityA block)
	{
		ea.add(block);
	}

	public void removeEntity(EntityA block)
	{
		ea.remove(block);
	}

	public void addEntity(EntityB block)
	{
		eb.add(block);
	}

	public void removeEntity(EntityB block)
	{
		eb.remove(block);
	}

	public LinkedList<EntityA> getEntityA()
	{
		return ea;
	}

	public LinkedList<EntityB> getEntityB()
	{
		return eb;
	}

}
