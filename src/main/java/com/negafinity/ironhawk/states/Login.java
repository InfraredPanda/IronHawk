package com.negafinity.ironhawk.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.negafinity.ironhawk.Game;
import com.negafinity.ironhawk.utils.User;

public class Login
{
	private Game game;
	private boolean bool = true;

	public Rectangle backButton = new Rectangle(5, 5, 100, 50);
	public Rectangle loginButton = new Rectangle(100, 60, 150, 50);
	public Rectangle registerButton = new Rectangle(450, 60, 150, 50);
	//This is a comment to test Jenkins.

	public Login(Game game)
	{
		this.game = game;
	}

	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Login/Register Menu", 225, 50);

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);
		g2d.draw(backButton);

		g.drawString("Log In", loginButton.x + 19, loginButton.y + 30);
		g2d.draw(loginButton);

		g.drawString("Register", registerButton.x + 13, registerButton.y + 30);
		g2d.draw(registerButton);

		Font fnt2 = new Font("arial", Font.PLAIN, 20);
		g.setFont(fnt2);
		g.setColor(Color.WHITE);

		g.drawString("Here you can login or do whatever.", 30, 135);

		if (bool)
		{
			bool = false;
			System.out.println("Creating elements");

			// Create JPanel
			final JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			panel.setSize(320, 240);

			// Create fields
			final JTextField userNameField = new JTextField();
			final JPasswordField passwordField = new JPasswordField();
			userNameField.setBounds(10, 10, 20, 10);
			
			passwordField.setBounds(10, 10, 20, 10);
			
			// Add Submit Button
			JButton submitButton = new JButton();
			submitButton.setText("Submit");
			submitButton.setBackground(Color.RED);
			submitButton.addActionListener(new ActionListener()
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent evt)
				{
					System.out.println("Registering User.");
					User user = new User(UUID.randomUUID().toString(), userNameField.getText(), passwordField.getText(), 0);
					Game.users.add(user);
					game.setVisible(true);
					panel.setVisible(false);
				}
			});

			// Make the game invisible
			game.setVisible(false);
			// Add to panel
			panel.add(submitButton);
			panel.add(userNameField);
			panel.add(passwordField);
			// Add panel to frame
			Game.frame.add(panel);
			// Show changes
			Game.frame.pack();
			System.out.println("Added to JFrame.");
		}
	}
}
