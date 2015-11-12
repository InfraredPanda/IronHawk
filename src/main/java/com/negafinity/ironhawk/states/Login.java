package com.negafinity.ironhawk.states;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.utils.User;

public class Login
{
	private Game game;
	private boolean createJFrame = true;

	public Login(Game game)
	{
		this.game = game;
	}

	public void render(Graphics g)
	{
		if (createJFrame)
		{
			if (Game.players.size() == 1)
			{
				createJFrame = false;

				// Create JPanel
				final JPanel panel = new JPanel();
				// Add panel to frame
				Game.frame.add(panel);

				// Place Components
				panel.setLayout(null);

				JLabel userLabel = new JLabel("Username");
				userLabel.setBounds(10, 10, 80, 25);
				panel.add(userLabel);

				JLabel passwordLabel = new JLabel("Password");
				passwordLabel.setBounds(10, 40, 80, 25);
				panel.add(passwordLabel);

				final JTextField userNameField = new JTextField();
				userNameField.setBounds(100, 10, 160, 25);
				panel.add(userNameField);

				final JPasswordField passwordField = new JPasswordField();
				passwordField.setBounds(100, 40, 160, 25);
				panel.add(passwordField);

				JButton loginButton = new JButton("Login");
				loginButton.setBounds(10, 80, 80, 25);

				loginButton.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0)
					{
						for (User user : Game.users)
						{
							if (user.getUsername().equals(userNameField.getText()) && user.getPassword().equals(passwordField.getText()))
							{
								Game.players.get(0).setUser(user);
								Game.State = Game.STATE.MENU;
								game.setVisible(true);
								panel.setVisible(false);
							}
						}
					}

				});
				panel.add(loginButton);

				JButton registerButton = new JButton("Register");
				registerButton.setBounds(180, 80, 90, 25);

				registerButton.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent evt)
					{
						User user = new User(UUID.randomUUID().toString(), userNameField.getText(), passwordField.getText(), Game.getRoundNumber());
						Game.users.add(user);
						Game.players.get(0).setUser(user);
						Game.State = Game.STATE.MENU;
						game.setVisible(true);
						panel.setVisible(false);
					}
				});
				panel.add(registerButton);

				// Make the game invisible
				game.setVisible(false);
				// Show changes
				Game.frame.setVisible(true);
			}
			else
			{
				createJFrame = false;

				// Create JPanel
				final JPanel panel = new JPanel();
				// Add panel to frame
				Game.frame.add(panel);

				// Place Components
				panel.setLayout(null);

				JLabel userLabel = new JLabel("Username");
				userLabel.setBounds(10, 10, 80, 25);
				panel.add(userLabel);

				JLabel passwordLabel = new JLabel("Password");
				passwordLabel.setBounds(10, 40, 80, 25);
				panel.add(passwordLabel);

				final JTextField userNameField = new JTextField();
				userNameField.setBounds(100, 10, 160, 25);
				panel.add(userNameField);

				final JPasswordField passwordField = new JPasswordField();
				passwordField.setBounds(100, 40, 160, 25);
				panel.add(passwordField);

				JButton loginButton = new JButton("Login");
				loginButton.setBounds(10, 80, 80, 25);

				loginButton.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0)
					{
						for (User user : Game.users)
						{
							if (user.getUsername().equals(userNameField.getText()) && user.getPassword().equals(passwordField.getText()))
							{
								Game.players.get(0).setUser(user);
								Game.State = Game.STATE.MENU;
								game.setVisible(true);
								panel.setVisible(false);
							}
						}
					}

				});
				panel.add(loginButton);

				JButton registerButton = new JButton("Register");
				registerButton.setBounds(180, 80, 90, 25);

				registerButton.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent evt)
					{
						User user = new User(UUID.randomUUID().toString(), userNameField.getText(), passwordField.getText(), Game.getRoundNumber());
						Game.users.add(user);
						Game.players.get(0).setUser(user);
						Game.State = Game.STATE.MENU;
						game.setVisible(true);
						panel.setVisible(false);
					}
				});
				panel.add(registerButton);

				// Make the game invisible
				game.setVisible(false);
				// Show changes
				Game.frame.setVisible(true);
				
				//TODO: Replicate fields with different names for Player 2.
			}
		}
	}
}
