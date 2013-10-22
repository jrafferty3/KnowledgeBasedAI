import java.util.ArrayList;

public class Rules{
    private int playerNumber;
    private int otherNumber;
    public Rules(int playerNumber, int otherNumber){
	this.playerNumber = playerNumber;
	this.otherNumber = otherNumber;
    }
    
    
    public boolean canWin(int[] board, ArrayList<String> trace){
	trace.add("Looking for winning move.");
	if(findMatch(board,playerNumber,0,1,2, trace)){
	    trace.add("Winning move found in first row.");
	    return true;
	}
	if(findMatch(board,playerNumber,3,4,5, trace)){
	    trace.add("Winning move found in second row.");
	    return true;
	}
	if(findMatch(board,playerNumber,6,7,8, trace)){
	    trace.add("Winning move found in third row.");
	    return true;
	}
	if(findMatch(board,playerNumber,0,3,6, trace)){
	    trace.add("Winning move found in first column.");
	    return true;
	}
	if(findMatch(board,playerNumber,1,4,7, trace)){
	    trace.add("Winning move found in second column.");
	    return true;
	}
	if(findMatch(board,playerNumber,2,5,8, trace)){
	    trace.add("Winning move found in third column.");
	    return true;
	}
	if(findMatch(board,playerNumber,0,4,8, trace)){
	    trace.add("Winning move found in first diagonal.");
	    return true;
	}
	if(findMatch(board,playerNumber,2,4,6, trace)){
	    trace.add("Winning move found in second diagonal.");
	    return true;
	}
	return false;
    }
	
    public boolean canBlock(int[] board, ArrayList<String> trace){
	trace.add("Looking for opponent winning move.");
	if(findMatch(board,otherNumber,0,1,2, trace)){
	    trace.add("Opponent winning move found in first row.");
	    return true;
	}
	if(findMatch(board,otherNumber,3,4,5, trace)){
	    trace.add("Opponent winning move found in second row.");
	    return true;
	}
	if(findMatch(board,otherNumber,6,7,8, trace)){
	    trace.add("Opponent winning move found in third row.");
	    return true;
	}
	if(findMatch(board,otherNumber,0,3,6, trace)){
	    trace.add("Opponent winning move found in first column.");
	    return true;
	}
	if(findMatch(board,otherNumber,1,4,7, trace)){
	    trace.add("Opponent winning move found in second column.");
	    return true;
	}
	if(findMatch(board,otherNumber,2,5,8, trace)){
	    trace.add("Opponent winning move found in third column.");
	    return true;
	}
	if(findMatch(board,otherNumber,0,4,8, trace)){
	    trace.add("Opponent winning move found in first diagonal.");
	    return true;
	}
	if(findMatch(board,otherNumber,2,4,6, trace)){
	    trace.add("Opponent winning move found in second diagonal.");
	    return true;
	}
	return false;
    }
	
    private boolean findMatch(int[]board, int player, int one, int two, int three, ArrayList<String> trace){
	String match = ""+board[one]+""+board[two]+""+board[three];
	trace.add("Checking "+match);
	String check_one = "0"+player+""+player;
	String check_two = ""+player+"0"+player;
	String check_three = ""+player+""+player+"0";
	return match.equals(check_one) || match.equals(check_two) || match.equals(check_three);
    }
}
