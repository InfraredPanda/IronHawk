package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.ScreenManager;
import com.negafinity.ironhawk.utils.User;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ScreenLogin extends Screen
{
	private boolean createJFrame = true;

	public static boolean user1Ready;
	public static boolean user2Ready;
	public static boolean user1LoggedIn;
	public static boolean user2LoggedIn;

	@Override
	public void render(Graphics g, Game game)
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

				final JLabel errorLabel = new JLabel("Error! Username/Password was incorrect!");
				errorLabel.setBounds(10, 150, 300, 25);

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
						User foundUser = null;

						for (User user : Game.users)
						{
							if (user.getUsername().equals(userNameField.getText()) && user.getPassword().equals(passwordField.getText()))
							{
								foundUser = user;
								break;
							}
						}

						if (foundUser != null)
						{
							Game.players.get(0).setUser(foundUser);
							game.screenManager.currentScreen = ScreenManager.STATE.MENU;
							user1LoggedIn = true;
							game.setVisible(true);
							panel.setVisible(false);
						}
						else
						{
							panel.add(errorLabel);
							panel.updateUI();
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
						game.screenManager.currentScreen = ScreenManager.STATE.MENU;
						user1LoggedIn = true;
						game.setVisible(true);
						panel.setVisible(false);
					}
				});
				panel.add(registerButton);

				JButton skipButton = new JButton("Skip");
				skipButton.setBounds(100, 80, 60, 25);

				skipButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						game.screenManager.currentScreen = ScreenManager.STATE.GAME;
						game.setVisible(true);
						panel.setVisible(false);
					}

				});
				panel.add(skipButton);

				// Make the game invisible
				game.setVisible(false);
				// Show changes
				Game.frame.setVisible(true);
			}
			// IF THERE ARE TWO PLAYERS.
			else
			{
				createJFrame = false;

				// Create JPanel
				final JPanel panel = new JPanel();
				// Add panel to frame
				Game.frame.add(panel);

				// Place Components
				panel.setLayout(null);

				JLabel player1Label = new JLabel("Player 1");
				player1Label.setBounds(100, 5, 80, 20);
				panel.add(player1Label);

				JLabel player2Label = new JLabel("Player 2");
				player2Label.setBounds(440, 5, 80, 20);
				panel.add(player2Label);

				JLabel userLabel = new JLabel("Username");
				userLabel.setBounds(10, 25, 80, 25);
				panel.add(userLabel);

				JLabel userLabel2 = new JLabel("Username");
				userLabel2.setBounds(350, 25, 80, 25);
				panel.add(userLabel2);

				JLabel passwordLabel = new JLabel("Password");
				passwordLabel.setBounds(10, 55, 80, 25);
				panel.add(passwordLabel);

				JLabel passwordLabel2 = new JLabel("Password");
				passwordLabel2.setBounds(350, 55, 80, 25);
				panel.add(passwordLabel2);

				final JLabel errorLabel = new JLabel("Error! Username/Password was incorrect!");
				errorLabel.setBounds(10, 150, 300, 25);

				final JLabel errorLabel2 = new JLabel("Error! Username/Password was incorrect!");
				errorLabel2.setBounds(350, 150, 300, 25);

				final JLabel readyLabel1 = new JLabel(" is Ready. Waiting on Player 2.");
				readyLabel1.setBounds(100, 310, 300, 25);

				final JLabel readyLabel2 = new JLabel(" is Ready. Waiting on Player 1.");
				readyLabel2.setBounds(440, 310, 300, 25);

				final JTextField userNameField = new JTextField();
				userNameField.setBounds(100, 25, 160, 25);
				panel.add(userNameField);

				final JTextField userNameField2 = new JTextField();
				userNameField2.setBounds(440, 25, 160, 25);
				panel.add(userNameField2);

				final JPasswordField passwordField = new JPasswordField();
				passwordField.setBounds(100, 55, 160, 25);
				panel.add(passwordField);

				final JPasswordField passwordField2 = new JPasswordField();
				passwordField2.setBounds(440, 55, 160, 25);
				panel.add(passwordField2);

				JButton loginButton = new JButton("Login");
				loginButton.setBounds(10, 95, 80, 25);

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
								user1LoggedIn = true;
								user1Ready = true;
							}
							else
							{
								panel.add(errorLabel);
								panel.updateUI();
							}
						}
					}

				});
				panel.add(loginButton);

				JButton loginButton2 = new JButton("Login");
				loginButton2.setBounds(350, 95, 80, 25);

				loginButton2.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0)
					{
						for (User user : Game.users)
						{
							if (user.getUsername().equals(userNameField2.getText()) && user.getPassword().equals(passwordField2.getText()))
							{
								Game.players.get(1).setUser(user);
								user2LoggedIn = true;
								user2Ready = true;
							}
							else
							{
								panel.add(errorLabel2);
								panel.updateUI();
							}
						}
					}

				});
				panel.add(loginButton2);

				JButton registerButton = new JButton("Register");
				registerButton.setBounds(180, 95, 90, 25);

				registerButton.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent evt)
					{
						User user = new User(UUID.randomUUID().toString(), userNameField.getText(), passwordField.getText(), Game.getRoundNumber());
						Game.users.add(user);
						Game.players.get(0).setUser(user);
						user1Ready = true;
						user1LoggedIn = true;
					}
				});
				panel.add(registerButton);

				JButton registerButton2 = new JButton("Register");
				registerButton2.setBounds(520, 95, 90, 25);

				registerButton2.addActionListener(new ActionListener()
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent evt)
					{
						User user = new User(UUID.randomUUID().toString(), userNameField2.getText(), passwordField2.getText(), Game.getRoundNumber());
						Game.users.add(user);
						Game.players.get(1).setUser(user);
						user2Ready = true;
						user2LoggedIn = true;
					}
				});
				panel.add(registerButton2);

				JButton skipButton = new JButton("Skip");
				skipButton.setBounds(100, 95, 60, 25);

				skipButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						user1Ready = true;
					}

				});
				panel.add(skipButton);

				JButton skipButton2 = new JButton("Skip");
				skipButton2.setBounds(440, 95, 60, 25);

				skipButton2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						user2Ready = true;
					}

				});
				panel.add(skipButton2);

				JButton readyButton = new JButton("Go!");
				readyButton.setBounds(290, 195, 60, 25);

				readyButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						if (user1Ready && user2Ready)
						{
							game.screenManager.currentScreen = ScreenManager.STATE.GAME;
							game.setVisible(true);
							panel.setVisible(false);
						}
					}

				});
				panel.add(readyButton);

				// Make the game invisible
				game.setVisible(false);
				// Show changes
				Game.frame.setVisible(true);
			}
		}

	}
}
