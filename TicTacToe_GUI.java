package TTT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import TTT.BoardGameInterface;


public class TicTacToe_GUI extends JFrame{
	private JPanel jpMain;
	private TicTacToeBoard jpBoard;//a JPanel containing the board of buttons
	private Player currPlayer;
	private Player player1;
	private Player player2;
	
	public TicTacToe_GUI(){
		setLayout(new BorderLayout());
		//player1 = new Player();
		//player2 = new Player();
		currPlayer = player1;
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
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			//button.setText("okay");
			placeMarker(1,1);
			
		}

		@Override
		public void displayBoard() {
			board = new JButton[3][3];
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
//					board[row][col] = new JButton("["+row+"]["+col+"]");
					board[row][col] = new JButton(" - ");
					board[row][col].addActionListener(this);
					add(board[row][col]);//add to JPanel
				}
			}
			
		}

		@Override
		public void clearBoard() {
			// same as display board :) Youre just going back to where you started
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
//					board[row][col] = new JButton("["+row+"]["+col+"]");
					board[row][col] = new JButton(" - ");
					
					add(board[row][col]);//add to JPanel
				}
			}
			
			
		}

		public void changeTurn() {
			if (currPlayer == player1) {
				currPlayer = player2;
			}else if (currPlayer == player2) {
				currPlayer = player1;
			}
			
		}

		
		public void placeMarker(int row, int col) {
			if(currPlayer == player1) {
				board[row][col].setText("X");
				changeTurn();
			} else if (currPlayer == player2) {
				board[row][col].setText("O");
				changeTurn();
			}
			
		}

		
		public boolean isWinner() {
			// TODO Auto-generated method stub
			return false;
		}

		
		public boolean isWinner(char currPlayer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFull() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col] = new JButton(" - ");
					add(board[row][col]);//add to JPanel
				}
			}
			return false;
		}

		
		public void startGame() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void populateBoard() {
			
		}
		@Override
		public void takeTurn() {
			
		}
		@Override
		public void displayWinner() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean isEmpty() {
			return false;
		}
		@Override
		public boolean isWinner(String currPlayer) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
