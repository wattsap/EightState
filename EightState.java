import java.util.Arrays;
import java.util.*;
/********************************************************************

   An EightState represents the tiles on the board in a Game Of Eight
   at any given time:  

            1  3  4
            8  6  5
            0  2  7

   The zero represents the empty square.  
   
   *** You must add two methods: equals and hashCode
   
  *****************************************************************/

public class EightState 
{

    // fields for the EightState
    public int[][] board;


    public EightState() 
    {
        board = new int[3][3];
    }

    public EightState (int puz[][])
    {
       board = new int[3][3];
       for (int i=0; i < 3; i++)
           for (int j=0; j < 3; j++)
              board[i][j] = puz[i][j];
    }

 
   public int getBoard (int x, int y)
   {
       return board[x][y];
   }

   public void setBoard (int x, int y, int val)
   {
         board[x][y] = val;
   }

   public EightState clone()
   {
        EightState newState = new EightState();
        newState.copy(this); 
        return newState;
   } 

   public void copy (EightState comp)
   {
        
    for (int i = 0;  i < 3; i++)
       for (int j = 0;  j < 3; j++)
          board[i][j] = comp.board[i][j];
   }

   public void prettyPicture ()
   {
     int moveOn;

     System.out.println(" -----------------------");
     for (int i = 0; i < 3; i++)
     {
          System.out.println ("|       |       |       |");
          System.out.print ("|   ");
          for (int j = 0; j < 3; j++)
             if (board[i][j] != 0)
                System.out.print (board[i][j] + "   |   ");
             else
                System.out.print("    |   ");
          System.out.println();
          System.out.println("|       |       |       |");
          System.out.println(" -----------------------");
     }
   }

   public void setValues (int [] arr)
   {
       int index = 0;
       for (int i = 0; i < 3; i++)
          for (int j = 0; j < 3; j++)
              board[i][j] = arr[index++];
   }
   public boolean equals(Object obj) {
           if (obj instanceof EightState) {
           EightState es = (EightState) obj;
           return  Arrays.deepEquals(es.board, board);
       }
       return false;
   }
   public int hashCode() {

       int hash = 
       ((board[0][0] + board[1][0] + board[2][0]) * 
        (board[0][1] + board[1][1] + board[2][1]) *
        (board[0][2] + board[1][2]+ board[2][2]))
       
        * 
       
       ((board[0][0] + board[0][1] + board[0][2]) *
        (board[1][0] + board[1][1] + board[1][2]) *
        (board[2][0] + board[2][1] + board[2][2]))
       
       +
       
      (board[0][0] + board[1][1] + board[2][2])
      
      /
      
      (board[0][0] + board[0][2] + board[2][0] + board[2][2])

      -

       ((board[0][0] + board[0][1] + board[0][2]) *
        (board[1][0] + board[1][1] + board[1][2]) *
        (board[2][0] + board[2][1] + board[2][2]))
      ;
       return hash;
   }
   
}

