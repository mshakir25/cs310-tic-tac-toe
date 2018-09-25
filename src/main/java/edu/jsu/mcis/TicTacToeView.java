package edu.jsu.mcis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToeView extends JPanel implements ActionListener 
{
    private TicTacToeModel model;
	private JPanel squaresPanel;
	private JLabel resultLabel;
	private JButton[][] squares;    
    /* CONSTRUCTOR */
	public TicTacToeView(TicTacToeModel model) 
	{    
        this.model = model;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));	
		squaresPanel = new JPanel(new GridLayout(model.getWidth(), model.getWidth()));
		resultLabel = new JLabel();
		resultLabel.setName("ResultLabel");
		resultLabel.setText("Welcome to Tic-Tac-Toe!");
		resultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);		
		squares = new JButton[model.getWidth()][model.getWidth()];		
		for(int i = 0; i < model.getWidth(); i++)
		{
			for(int j = 0; j < model.getWidth(); j++)
			{
				squares[i][j] = new JButton();
				squares[i][j].setPreferredSize(new Dimension(64, 64));
				squares[i][j].addActionListener(this);
				squares[i][j].setName("Square" + i + j);
				squaresPanel.add(squares[i][j]);
			}
		}
		add(squaresPanel);
		add(resultLabel);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < model.getWidth(); i++){
			for(int j = 0; j < model.getWidth(); j++){
				if (squares[i][j] == e.getSource()){
					squares[i][j].setEnabled(false);
					if(model.isXTurn()){
						squares[i][j].setText("X");
						model.makeMark(i, j);
					}
					else {
						squares[i][j].setText("O");
						model.makeMark(i, j);
					}
				}
			}
		}
		
		if(model.isGameover())
		{
			for(int i = 0; i < model.getWidth(); i++){
				for(int j = 0; j < model.getWidth(); j++){
					squares[i][j].setEnabled(false);
				}
			}
			if(model.getResult() == TicTacToeModel.Result.X){
				resultLabel.setText("X");
			}
			if(model.getResult() == TicTacToeModel.Result.O){
				resultLabel.setText("O");
			}
			if(model.getResult() == TicTacToeModel.Result.TIE){
				resultLabel.setText("TIE");
			}		
		}
	}
}