package com.negafinity.ironhawk;

import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.HealthPack;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller
{
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private Textures tex;
	private Game game;

	public Controller(Textures tex, Game game)
	{
		this.tex = tex;
		this.game = game;
	}

	public void createEnemy(int enemyCount)
	{
		for (int i = 0; i < enemyCount; i++)
		{
			Random r = new Random();
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
		}
	}

	public void randomlySpawnHealthPack(double x, double y)
	{
		Random r = new Random();
		int spawnChance = r.nextInt(9);

		if (spawnChance == 0)
		{
			addEntity(new HealthPack(x, y, tex, this, game));
		}
	}

	public void tick()
	{
		updateEnemyCount();

		for (int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);
			entity.tick();
		}

	}

	public void updateEnemyCount()
	{
		int enemies = 0;

		for(Entity entity : entities)
		{
			if(entity instanceof Enemy)
			{
				enemies++;
			}
		}

		game.setEnemyCount(enemies);
	}

	public void render(Graphics g)
	{
		for (int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);
			entity.render(g);
		}

	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}

	public void removeEntity(Entity entity)
	{
		entities.remove(entity);
	}

	public LinkedList<Entity> getEntities()
	{
		return entities;
	}
}
