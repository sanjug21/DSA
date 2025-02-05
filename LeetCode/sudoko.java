package LeetCode;

import java.util.Arrays;

public class sudoko {
    public static void sudokoSolver(int [][]grid){
        solve(grid,0,0);
    }
    public static boolean solve(int [][]board,int r,int c){
       
        if(c==9){
            r++;
            c=0;
        }
         if(r==9){
			for(int i[]:board)System.out.println(Arrays.toString(i));
			return true;}
        
        if(board[r][c]!=0)return solve(board,r,c+1);
        else{
                for(int num=1;num<=9;num++){
                    if(isValid(board,num,r,c)){
                        board[r][c]=num;
                        if(solve(board,r,c+1))return true;
                        board[r][c]=0;
                    }
                }  
        }
        return false;
    }
    public static boolean isValid(int [][]board,int ch,int row,int col){
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
    int grid[][]={{3,0,4,0,0,5,7,0,0},
				  {1,0,0,4,0,0,0,0,0},
				  {0,2,0,0,0,8,3,0,0},
				  {0,8,0,0,7,0,0,0,4},
				  {0,0,0,0,0,0,0,0,0},
				  {4,0,0,6,8,1,0,0,5},
				  {6,4,0,2,0,0,9,0,1},
				  {0,0,5,0,0,0,0,2,0},
				  {0,1,2,0,0,0,0,0,0}};
	sudokoSolver(grid);
   } 
}
