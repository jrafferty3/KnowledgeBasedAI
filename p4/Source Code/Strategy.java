import java.util.ArrayList;

public class Strategy{
    private Rules r;
    private int playerNumber;
    private int otherNumber;
    public Strategy(int playerNumber, int otherNumber){
	this.playerNumber = playerNumber;
	this.otherNumber = otherNumber;
	r = new Rules(this.playerNumber,this.otherNumber);
    }
    
    public String applyStrategy(int[] board, int wr, int br, int sr, ArrayList<String> trace){
	trace.add("Attempting to look for a winning move by checking win condition.");
	if(wr != 1 && r.canWin(board, trace)){
		trace.add("Picking a winning move.");
		return "w";
	}
	trace.add("Attempting to look for a blocking move by checking win condition for opponent.");
	if(br != 1 && r.canBlock(board, trace)){
		trace.add("Blocking opponents winning move.");
		return "b";
	}
	if(sr != 1){
		trace.add("Picking the best strategic move.");
		return "s";
	}
	trace.add("Picking a random move.");
	return "r";
	}
	
	public String applyStrategy(int[] board, ArrayList<String> trace){
	trace.add("Looking for a winning move by checking win condition.");
	if(r.canWin(board, trace)){
		trace.add("Picking a winning move.");
		return "w";
	}
	trace.add("Looking for a blocking move by checking win condition for opponent.");
	if(r.canBlock(board, trace)){
		trace.add("Blocking opponents winning move.");
		return "b";
	}
	trace.add("Picking the best strategic move.");
	return "s";
	}
}
