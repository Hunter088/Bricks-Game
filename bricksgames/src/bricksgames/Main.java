package bricksgames;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gameplay game = new Gameplay();
		
		frame.setTitle("Bricks Game");
		frame.add(game);
		frame.setResizable(false);
		frame.setBounds(0, 0, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
