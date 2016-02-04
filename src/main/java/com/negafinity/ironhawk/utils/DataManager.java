package com.negafinity.ironhawk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.negafinity.ironhawk.IronHawk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager
{
	private static Gson gson = new GsonBuilder().create();

	public void saveUsers()
	{
		String json = gson.toJson(IronHawk.users);

		try
		{
			FileWriter fileWriter = new FileWriter("IronHawkData.json");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(json);
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("Could not save users to JSON file!");
		}
	}

	public void readUsers()
	{
		String json = null;

		try
		{
			json = readFile("IronHawkData.json", StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			System.out.println("Could not read JSON file!");
		}

		if (json != null)
			IronHawk.users = new ArrayList<User>(Arrays.asList(gson.fromJson(json, User[].class)));
	}

	private static String readFile(String path, Charset encoding) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
