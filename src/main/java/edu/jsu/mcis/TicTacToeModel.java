package edu.jsu.mcis;

public class TicTacToeModel {
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        /* INSERT YOUR CODE HERE */
		
		grid = new Mark[this.width][this.width];

        /* Initialize grid by filling every square with empty marks */

        /* INSERT YOUR CODE HERE */
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.width; j++){
				grid[i][j] = Mark.EMPTY;
			}
		}
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        
        /* INSERT YOUR CODE HERE */
		if(isValidSquare(row, col) && !isSquareMarked(row, col)){
			if(isXTurn()) {
				this.grid[row][col] = Mark.X;
				xTurn = false;
			}
			else{
				this.grid[row][col] = Mark.O;
				xTurn = true;
			}
			return true;
		}
		else{
			return false;
		}
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        
        /* INSERT YOUR CODE HERE */
		
		if(row < this.width && row >= 0 && col < this.width && col >= 0){
			return true;
		}
		else{
			return false;
		}
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        /* INSERT YOUR CODE HERE */
		
		if(grid[row][col] == Mark.X || grid[row][col] == Mark.O){
			return true;
		}
		else{
			return false;
		}
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        
        /* INSERT YOUR CODE HERE */
		
		return grid[row][col];
            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
        /* INSERT YOUR CODE HERE */
		
		Result gameResult = Result.NONE;
		
		if(isMarkWin(Mark.X)){
			gameResult = Result.X;
		}
		if(isMarkWin(Mark.O)){
			gameResult = Result.O;
		}
		if(isTie()){
			gameResult = Result.TIE;
		}
        return gameResult;

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        /* INSERT YOUR CODE HERE */
		boolean won = false;
		int markRowCounter = 0;
		int markColCounter = 0;
		int markTopLeftDiagCounter = 0;
		int markTopRightDiagCounter = 0;
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.width; j++){
				if(grid[i][j] == mark){
					++markRowCounter;
				}
				if(grid[j][i] == mark){
					++markColCounter;
				}
			}
			
			if(markRowCounter == this.width || markColCounter == this.width){
				won = true;
				break;
			}
			if(grid[i][i] == mark){
				++markTopLeftDiagCounter;
			}
			if(grid[i][(this.width - 1) - i] == mark){
				++markTopRightDiagCounter;
			}
			markColCounter = 0;
			markRowCounter = 0;
		}
		
		if(markTopLeftDiagCounter == this.width || markTopRightDiagCounter == this.width){
			won = true;
		}
        return won;

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        /* INSERT YOUR CODE HERE */
		
		boolean checkTie = false;
		int emptySquare = 0;
		
		if(!isMarkWin(Mark.X) && !isMarkWin(Mark.O)){
			for(int i = 0; i < this.width; i++){
				for(int j = 0; j < this.width; j++){
					if(!isSquareMarked(i, j)){
						++emptySquare;
					}
				}
			}
			if(emptySquare == 0){
			checkTie = true;
			}
		}

        return checkTie;
        
    }

    public boolean isGameover() {
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
}