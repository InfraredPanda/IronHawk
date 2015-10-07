package com.negafinity.ironhawk;

import com.negafinity.ironhawk.entities.Enemy;
import com.negafinity.ironhawk.entities.Entity;
import com.negafinity.ironhawk.entities.JapaneseFighterPlane;
import com.negafinity.ironhawk.entities.RedBaron;
import com.negafinity.ironhawk.entities.powerups.HealthPack;
import com.negafinity.ironhawk.entities.powerups.MissilePowerup;
import com.negafinity.ironhawk.entities.powerups.RapidFire;

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

	public void createRedBaron(int amountToSpawn)
	{
		for (int i = 0; i < amountToSpawn; i++)
		{
			Random r = new Random();
			addEntity(new RedBaron(r.nextInt(640), -10, tex, this, game, 100));
		}
	}

	public void createJapaneseFighterPlane(int amountToSpawn)
	{
		for (int i = 0; i < amountToSpawn; i++)
		{
			Random r = new Random();
			addEntity(new JapaneseFighterPlane(r.nextInt(640), -10, tex, this, game, 200));
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

	public void randomlySpawnRapidFire(double x, double y)
	{
		Random rand = new Random();
		int spawnChance = rand.nextInt(9);

		if (spawnChance == 0)
		{
			addEntity(new RapidFire(x, y, tex, this, game));
		}
	}
	public void randomlySpawnMissilePowerup(double x, double y)
	{
		Random rand = new Random();
		int spawnChance = rand.nextInt(9);

		if (spawnChance == 0)
		{
			addEntity(new MissilePowerup(x, y, tex, this, game));
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

		for (Entity entity : entities)
		{
			if (entity instanceof Enemy)
			{
				enemies++;
			}
		}

		Game.setEnemyCount(enemies);
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
