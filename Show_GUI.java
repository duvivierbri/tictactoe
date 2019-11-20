package tictacpackage;

import javax.swing.JFrame;

public class Show_GUI {
	public static void main(String[] args) {
//		System.out.println("lalalala");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				TicTacToe_GUI gui = new TicTacToe_GUI();
				gui.setVisible(true);
				gui.setResizable(false);
				gui.pack();
				gui.setSize(500,500);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

//				System.out.println("boohoo");
		

	}
}