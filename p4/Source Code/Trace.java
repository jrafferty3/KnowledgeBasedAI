public class Trace{
    private String type;
    private String best;
    private String message;
    private int turn;
    private int place;
    private String[] moves = {"top left corner.","top middle.","top right corner.","middle left.","middle.","middle right.","bottom left corner.","botton middle.","bottom right corner."};
	
    public Trace(String move, String best_move, String message, int turn, int place){
	this.message = message;
	this.turn = turn;
	this.place = place;
	switch(move){
	case "w":
	    type = "winning";
	    break;
	case "b":
	    type = "blocking";
	    break;
	case "s":
	    type = "strategic";
	    this.message = "I did not find any winning or blocking moves.";
	    break;
	case "r":
	    type = "random";
	    this.message = "I failed to pick any move.";
	    break;
	}
	switch(best_move){
	case "w":
	    best = "winning";
	    break;
	case "b":
	    best = "blocking";
	    break;
	case "s":
	    best = "strategic";
	    break;
	case "r":
	    best = "random";
	    break;
	}
	//System.out.println(type+" "+message+" "+turn);
    }
	
    public void why(){
	System.out.println(message);
	System.out.println("I decided the best move was a "+type+" move.");
    }
	
    public boolean isWrong(){
	return !type.equals(best);
    }
	
    public void wrong(){
	if(isWrong()){
	    System.out.println("I decided the best move was a "+type+" move, but it was actually a "+best+" move.");
	    System.out.println("I classified the move incorrectly and therefore made a bad move.");
	}else{
	    System.out.println("I did nothing wrong.");
	}
    }

    public void which(){
	System.out.println("I placed my move in "+moves[place]);
    }
	
    public String getMessage(){
	return this.message;
    }
	
    public int getTurn(){
	return this.turn;
    }
}
