import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {
	
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();         // A panel where the TicTacToe title will be added
	JPanel button_panel = new JPanel();        // A panel Where the buttons will be added
	JLabel textField = new JLabel();           // A label where the string name will be given to the button(X or O)
	JButton[] buttons = new JButton[9];        // 9 buttons because there is  3x3 boxes in a ticTacToe game
	boolean player1_turn;                      // Will be used to determine player1's(X) if true turn. false == player2 turn
	TicTacToe(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textField.setBackground(new Color(25, 25, 25));
		textField.setForeground(new Color(26, 255, 0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Tic-Tac-Toe");
		textField.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0 , 800, 100);
		
		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(new Color(150, 150, 150));
		
		for(int i = 0; i < 9; i++) {
			buttons [i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(true);
			buttons[i].addActionListener(this);
		
		}
		
		title_panel.add(textField);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		
		firstTurn();
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 * 		First the if statement checks each button with for loop to check the source of ActionEvent.
		 * Once source button[i] is found, another if statement checks if player1_turn is true, 
		 * if player1_turn is true, another if statement check if the button is empty("")
		 * if empty, the foreground set to a new color, and the button text is set as "X"
		 * assigns player1_turn to false to make it player2 turn.
		 * Uses the Check() method to check if any player has won after each turn
		 *      Else statement is player2 turn if player2_turn = false, same operations but for player2 "O"
		 */
		for(int i = 0; i < 9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1_turn = false;
						textField.setText("O turn");
						check();
					}
				}
				else {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(0, 0 , 255));
						buttons[i].setText("O");
						player1_turn = true;
						textField.setText("X turn");
						check();
					}
				}
			}
		}
	}
	/*
	 * The firstTurn() method: determine who turn would come first Player1(X) or Player2(O)
	 */
	public void firstTurn() {
		try {
			Thread.sleep(2000);                     // Used to slow down the transition of the next text by 2000 miliSecs
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random.nextInt(2) == 0) {               // uses the random object to call the nextInt(2) method to only call 
			player1_turn = true;                   // 2 random numbers, 0 & 1: 0 for "player1 X true" and 1 for "player2 O"
			textField.setText("X turn");
		}
		else {
			player1_turn = false;
			textField.setText("O turn");
		}
	}
	/*
	 * The check() Method: if there are any matching rows(columns) (3x that are the same)  with ("X") or ("O") in which 
	 * it will determine the winner and call the xWins() or oWins() method which determines the winner.
	 * The method has all the possibilities a player can win
	 */
	public void check() {
		if((buttons[0].getText()=="X") && (buttons[1].getText()=="X") && (buttons[2].getText()=="X")) {
			xWins(0, 1 ,2);
		}
		if((buttons[3].getText()=="X") && (buttons[4].getText()=="X") && (buttons[5].getText()=="X")) {
			xWins(3, 4, 5);
		}
		if((buttons[6].getText()=="X") && (buttons[7].getText()=="X") && (buttons[8].getText()=="X")) {
			xWins(6, 7 ,8);
		}
		if((buttons[0].getText()=="X") && (buttons[3].getText()=="X") && (buttons[6].getText()=="X")) {
			xWins(0, 3 ,6);
		}
		if((buttons[2].getText()=="X") && (buttons[5].getText()=="X") && (buttons[8].getText()=="X")) {
			xWins(2, 5 ,8);
		}
		if((buttons[2].getText()=="X") && (buttons[5].getText()=="X") && (buttons[8].getText()=="X")) {
			xWins(2, 5 ,8);
		}
		if((buttons[0].getText()=="X") && (buttons[4].getText()=="X") && (buttons[8].getText()=="X")) {
			xWins(0, 4 ,8);
		}
		if((buttons[2].getText()=="X") && (buttons[4].getText()=="X") && (buttons[6].getText()=="X")) {
			xWins(2, 4 ,6);
		}
		if((buttons[1].getText()=="X") && (buttons[4].getText()=="X") && (buttons[7].getText()=="X")) {
			xWins(1, 4 ,7);
		}
		//************************************
		// Below is O wins possibilities 
		//************************************
		if((buttons[0].getText()=="O") && (buttons[1].getText()=="O") && (buttons[2].getText()=="O")) {
			oWins(0, 1 ,2);
		}
		if((buttons[3].getText()=="O") && (buttons[4].getText()=="O") && (buttons[5].getText()=="O")) {
			oWins(3, 4, 5);
		}
		if((buttons[6].getText()=="O") && (buttons[7].getText()=="O") && (buttons[8].getText()=="O")) {
			oWins(6, 7 ,8);
		}
		if((buttons[0].getText()=="O") && (buttons[3].getText()=="O") && (buttons[6].getText()=="O")) {
			oWins(0, 3 ,6);
		}
		if((buttons[2].getText()=="O") && (buttons[5].getText()=="O") && (buttons[8].getText()=="O")) {
			oWins(2, 5 ,8);
		}
		if((buttons[2].getText()=="O") && (buttons[5].getText()=="O") && (buttons[8].getText()=="O")) {
			oWins(2, 5 ,8);
		}
		if((buttons[0].getText()=="O") && (buttons[4].getText()=="O") && (buttons[8].getText()=="O")) {
			oWins(0, 4 ,8);
		}
		if((buttons[2].getText()=="O") && (buttons[4].getText()=="O") && (buttons[6].getText()=="O")) {
			oWins(2, 4 ,6);
		}
		if((buttons[1].getText()=="O") && (buttons[4].getText()=="O") && (buttons[7].getText()=="O")) {
			oWins(1, 4 ,7);
		}
		
	}
	/*
	 * xWins() method: Determines "X" wins by having three matching in the same row. It eventually sets the background of those
	 * matching in green. It also sets each button setEnabled to false. Sets the textField to "X Wins!"
	 * @param a, b, c:  int that are the matching row
	 */
	
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("X Wins!");
	}
	/*
	 * 
	 * oWins() method: Determines "O" wins by having three matching in the same row. It eventually sets the background of those
	 * matching in green. It also sets each button setEnabled to false. Sets the textField to "O Wins!"
	 * @param a, b, c:  int that are the matching row
	 */
	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText("O wins!");
	}

}
