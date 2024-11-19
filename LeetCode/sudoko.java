package LeetCode;

public class sudoko {
    public static void sudokoSolver(char [][]grid){
        solve(grid,0,0);
    }
    public static boolean solve(char [][]board,int r,int c){
       
        if(c==9){
            r++;
            c=0;
        }
         if(r==9)return true;
        
        if(board[r][c]!='.')return solve(board,r,c+1);
        else{
                for(char num='1';num<='9';num++){
                    if(isValid(board,num,r,c)){
                        board[r][c]=num;
                        if(solve(board,r,c+1))return true;
                        board[r][c]='.';
                    }
                }  
        }
        return false;
    }
    public static boolean isValid(char [][]board,char ch,int row,int col){
    for (int i = 0; i < 9; i++) {
	    if (i != row && board[i][col] == ch)return false;
        if (i != col && board[row][i] == ch)return false;
	}
	// for 3*3 matrix
	int i = row - row % 3;
	int j = col - col % 3;
	for (int a = i; a < i + 3; a++) {
		for (int b = j; b < j + 3; b++) {
			if ((a != row && b != col) && board[a][b] == ch)return false;
		}
	}
    return true;
    }

    public  static boolean isValidSudoku(char[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] != '.') {
					char item = board[row][col];
					for (int i = 0; i < 9; i++) {
						if (i != row && board[i][col] == item)
							return false;
                            if (i != col && board[row][i] == item)
							return false;
					}
					// for 3*3 matrix
					int i = row - row % 3;
					int j = col - col % 3;
					for (int a = i; a < i + 3; a++) {
						for (int b = j; b < j + 3; b++) {
							if ((a != row && b != col) && board[a][b] == item) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

    
   public static void main(String[] args) {
    
   } 
}
