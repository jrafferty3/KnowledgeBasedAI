import java.io.*;

public class Human implements Player{
	private int playerNumber;
	public Human(int number){
		playerNumber = number;
	}
	public int getMove(int[] board) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Player "+playerNumber+" choose move: ");
		while(true){
			int move = -1;
			 
			try {
				move = Integer.parseInt(br.readLine());
			} catch (IOException ioe) {
				System.out.println("IO error trying to read your name!");
				System.exit(1);
			} catch (Exception e){
				System.out.print("That is not a number, try again. ");
				continue;
			}
			
			if(available(move,board)){
				return move;
			}else{
				System.out.print("That position is not available, try again. ");
				continue;
			}
		}
	}
	
	public boolean available(int index, int[] aval){
		if(index < 0 || index > 8){
			return false;
		}else if(aval[index] == 0){
			return true;
		}else{
			return false;
		}
	}
}