public class Trace{
	private String type;
	private String best;
	private String message;
	private int turn;
	
	public Trace(String move, String best_move, String message, int turn){
		switch(move){
			case "w":
				type = "winning";
				break;
			case "b":
				type = "blocking";
				break;
			case "s":
				type = "strategic";
				break;
			case "r":
				type = "random";
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
		this.message = message;
		this.turn = turn;
		System.out.println(type+" "+message+" "+turn);
	}
	
	public void why(){
		System.out.println("I decided the best move was a "+type+" move.");
		System.out.println(message);
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
	
	public String getMessage(){
		return this.message;
	}
	
	public int getTurn(){
		return this.turn;
	}
}