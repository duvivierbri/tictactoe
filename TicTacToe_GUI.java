package tictacpackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import tictacpackage.BoardGameInterface;


public class TicTacToe_GUI extends JFrame{
	private JPanel jpMain;
	private TicTacToeBoard jpBoard;//a JPanel containing the board of buttons
	private Player player1;
	private Player player2;
	private Player currPlayer;
	private Player winner;
	private int p1_score;
	private int p2_score;
	
	public TicTacToe_GUI(){
		setLayout(new BorderLayout());
		p1_score = 0;
		p2_score = 0;
		winner = new Player();
		player1 = new Player();
		player2 = new Player();
		currPlayer = player1; //player1 ALWAYS goes first!
		jpBoard = new TicTacToeBoard();
		add(jpBoard, BorderLayout.CENTER);
	}
	
	
	private class TicTacToeBoard extends JPanel implements BoardGameInterface, ActionListener{

		private JButton [][] board;
		public TicTacToeBoard(){
			setLayout(new GridLayout(3,3));
			displayBoard();
		}
		
		@Override
		public void displayBoard() {
			board = new JButton[3][3];
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col] = new JButton("-");
					board[row][col].addActionListener(this);
					add(board[row][col]);//add to JPanel
				}
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource(); 
			
			//Taking a turn ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
			if (button.getText().equalsIgnoreCase("o") || button.getText().equalsIgnoreCase("x")) {
				System.out.println("Sorry, that spot is full");
			} else {
				for(int row=0; row<board.length; row++){
					for(int col=0; col<board[row].length; col++){;
						if (board[row][col] == button) {
							placeMarker(row,col);
							changeTurn();
						}
					}
				}
			}// end of turn ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
			
			
			//What to check every turn
			isFull();
			isWinner();
			displayWinner();
		}

		
		public void changeTurn() {
			if (currPlayer == player2) {
				currPlayer = player1;
				System.out.println("Current player is now player1");
			} else {
				currPlayer = player2;
				System.out.println("Current player is now player2");
			}
		}

		
		public void placeMarker(int row, int col) {
			
			if(currPlayer == player1) {
				board[row][col].setText("X");
			} else if (currPlayer == player2) {
				board[row][col].setText("O");
			}
			
		}

		
		public boolean isWinner() {
			int Onum = 0;
			int Xnum = 0;
			
			//This for loop checks the rows
			for(int row=0; row<board.length; row++){	
				Onum = 0;
				Xnum = 0;
				for(int col=0; col<board[row].length; col++){
					if (board[row][col].getText().equalsIgnoreCase("o")) {
						Onum++;
					} else if (board[row][col].getText().equalsIgnoreCase("x")) {
						Xnum++;
					}
				}
				
				//This displays winner
				if (Xnum == 3) {
					System.out.println("X wins!");
					winner = player1;
					return true;
				}
				
				if (Onum == 3) {
					System.out.println("O wins!");
					winner = player2;
					return true;
				}
			}
			//End of row check loop~ ~ ~ ~ ~ ~ ~ ~ ~ ~
			
			//This for loop checks the columns
			for(int col=0; col<board.length; col++){	
				Onum = 0;
				Xnum = 0;
				for(int row=0; row<board[col].length; row++){
					if (board[row][col].getText().equalsIgnoreCase("o")) {
						Onum++;
					} else if (board[row][col].getText().equalsIgnoreCase("x")) {
						Xnum++;
					}
				}
				
				//This displays winner
				if (Xnum == 3) {
					System.out.println("X wins!");
					winner = player1;
					return true;
				}
				
				if (Onum == 3) {
					System.out.println("O wins!");
					winner = player2;
					return true;
				}
			}
			//End of column check~ ~ ~ ~ ~ ~ ~ ~ 
			
			//MISSING DIAGONAL CHECK!!
			
			return false;
		}

		@Override
		public boolean isFull() {
			int num = 0;
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					if (board[row][col].getText().equalsIgnoreCase("o") || board[row][col].getText().equalsIgnoreCase("x")) {
						num++;
					}
				}
			}//end of 1st for loop
			
			if (num == 9) {
				if (isWinner() == false) {
					JOptionPane.showMessageDialog(null,"Board is Full!\nNo one wins!");
				}
				clearBoard();
				return true;
			}
			
			return false;
		} //end of isFull()

		
		public void startGame() {
			currPlayer = player1;
			
		}

		@Override
		public void displayWinner() {
			if (isWinner()) {
				if(winner == player1) {
					JOptionPane.showMessageDialog(null, "Player 1 wins!");
					p1_score++;
					clearBoard();
				} else {
					JOptionPane.showMessageDialog(null, "Player 2 wins!");
					p2_score++;
					clearBoard();
				}
			}
			winner = null;
		}
		
		
		@Override
		public boolean isEmpty() {
			int num = 0;
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					if (board[row][col].getText().equalsIgnoreCase("-")) {
						num++;
					}
				}
			}//end of 1st for loop
			
			if (num == 9) {
				return true;
			}
			return false;
		}
		
		@Override
		public void clearBoard() {
			// same as display board :) You're just going back to where you started
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){;
					board[row][col].setText("-");
				}
			}
			
			currPlayer = player1;
			System.out.println("Current score:\nPlayer1(X): " + p1_score + "   |   Player2(O): " + p2_score);
			
		}

		
	}

}
